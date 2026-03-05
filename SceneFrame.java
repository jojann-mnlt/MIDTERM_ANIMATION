/** Handles all operations for the start menu, game, and pause menu.
 * @author Jacob Buenaventura
 * @author Johann Manulat
 * @version 1.1.0
 */
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;

public class SceneFrame extends JFrame {
    private int frame_width, frame_height;
    // Main Menu fields
    private JPanel startMenuMainPanel, leftHalfPanel, rightHalfPanel,
            RGBPanel, carSelectPanel, gearSelectPanel, detailsPanel,
            redPanel, greenPanel, bluePanel,
            red, green, blue,
            startSpeedPanel, livesPanel, normalLanesPanel, counterflowPanel;
    private JSlider redSlider, greenSlider, blueSlider;
    private JLabel RGBLabel, livesLabel, normalLanesLabel, counterflowLanesLabel, startSpeedLabel,
            difficulty, lives, normalLanes, counterflowLanes, startSpeed;
    private Phone phone;
    private JButton driveButton;
    private CarSelect carSelect;
    private GearSelect gearSelect;

    // Game fields
    private SceneCanvas sceneCanvas;
    private boolean accelerating, braking, left, right, inCounterFlow;
    private Timer gameTimer;
    private double gameSpeed, maxSpeed;
    private int gameLives, score;
    private JLabel counterflowMultiplier, speedDisplay, livesDisplay, scoreDisplay;
    private JPanel HUD;
    private Car selectedCar;
    private int selectedGear;

    // Save states
    private double lastRoadX, lastRoadY;
    private double[] lastNormalX, lastNormalY, lastCounterflowX, lastCounterflowY;
    private int lastRemoveNormal, lastRemoveCounter;

    // Pause/Game Over Fields
    private JPanel gamePausePanel, pauseButtons;
    private JLabel pauseText;
    private JButton resumeGame, restartGame, backToMenu;

    // SFX
    private Clip bgm;

    /** Constructor method for the SceneFrame. */
    public SceneFrame() {
        frame_width = 800;
        frame_height = 600;

        // Pause Menu Buttons
        resumeGame = new JButton("Continue");
        backToMenu = new JButton("Back To Menu");
        restartGame = new JButton("Restart");

        carSelect = new CarSelect();
        gearSelect = new GearSelect();

        // Main Menu Components
        startMenuMainPanel = new JPanel();
        // Left Half
        leftHalfPanel = new JPanel();
        driveButton = new JButton("Drive");
        carSelectPanel = new JPanel();
        // Right Half
        rightHalfPanel = new JPanel();
        gearSelectPanel = new JPanel();
        // Details
        phone =  new Phone();
        detailsPanel = phone.drawPhoneScreen();
        startSpeedPanel = new JPanel();
        normalLanesPanel = new JPanel();
        counterflowPanel = new JPanel();
        livesPanel = new JPanel();
        // RGB
        RGBPanel = new JPanel();
        RGBPanel.setBackground(Color.DARK_GRAY);
        redPanel = new JPanel();
        greenPanel = new JPanel();
        bluePanel = new JPanel();
        redSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        greenSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        blueSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        red = new JPanel();
        green = new JPanel();
        blue = new JPanel();
        UIManager.put("Label.foreground", Color.WHITE);
        RGBLabel = new JLabel("Color Picker", JLabel.CENTER);
        ArrayList<JSlider> sliders = new ArrayList<>();
        sliders.add(greenSlider);
        sliders.add(blueSlider);
        sliders.add(redSlider);
        for (JSlider slider : sliders) {
            slider.setBackground(Color.DARK_GRAY);
            slider.setPaintTrack(true);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.setMajorTickSpacing(255);
            slider.setMinorTickSpacing(5);
        }

        UIManager.put("Label.foreground", Color.BLACK);
        difficulty = new JLabel("DIFFICULTY", JLabel.CENTER);
        difficulty.setFont(new Font("Helvetica", Font.BOLD, 18));

        UIManager.put("Label.font", new Font("Helvetica", Font.BOLD, 13));
        startSpeedLabel = new JLabel("Start Speed ", JLabel.RIGHT);
        startSpeed  = new JLabel("50", JLabel.LEFT);
        normalLanesLabel = new JLabel("Normal Lanes ", JLabel.RIGHT);
        normalLanes = new JLabel("| 2 | 1 | ", JLabel.LEFT);
        counterflowLanesLabel = new JLabel("Counterflow ", JLabel.RIGHT);
        counterflowLanes = new JLabel("X", JLabel.LEFT);
        livesLabel = new JLabel("Lives ", JLabel.RIGHT);
        lives = new JLabel("❤❤", JLabel.LEFT);

        setUpGUI();
        setUpButtonListeners();
        setUpMouseListeners();
        setUpSliderListeners();
    }

    /** Renders the main menu where players can customize the car model, car color, and difficulty. */
    public void setUpGUI() {
        Container cp = getContentPane();

        detailsPanel.removeAll();

        // Left Half
        leftHalfPanel.setLayout(new GridLayout(1, 1));
        carSelectPanel.setLayout(new BorderLayout());
        carSelectPanel.add(carSelect);
        carSelectPanel.add(carSelect, BorderLayout.CENTER);
        carSelectPanel.add(driveButton, BorderLayout.SOUTH);
        leftHalfPanel.add(carSelectPanel);
        RGBPanel.setLayout(new GridLayout(4, 1));

        // Right Half
        rightHalfPanel.setLayout(new GridLayout(2, 1));
        gearSelectPanel.setLayout(new GridLayout(1,2 ));
        // Details Panel (Top of Phone)
        detailsPanel.setLayout(new GridLayout(7, 1));
        detailsPanel.add(phone.drawUpperPhone()); 
        detailsPanel.add(difficulty, JLabel.CENTER, 1);
        // Details Panel (Start Speed)
        startSpeedPanel.setLayout(new GridLayout(1, 2));
        startSpeedPanel.add(startSpeedLabel);
        startSpeedPanel.add(startSpeed);
        startSpeedPanel.setOpaque(false);
        detailsPanel.add(startSpeedPanel);
        // Details Panel (# of Normal Lanes)
        normalLanesPanel.setLayout(new GridLayout(1, 2));
        normalLanesPanel.add(normalLanesLabel);
        normalLanesPanel.add(normalLanes);
        normalLanesPanel.setOpaque(false);
        detailsPanel.add(normalLanesPanel);
        // Details Panel (# of Counterflow Lanes)
        counterflowPanel.setLayout(new GridLayout(1, 2));
        counterflowPanel.add(counterflowLanesLabel);
        counterflowPanel.add(counterflowLanes);
        counterflowPanel.setOpaque(false);
        detailsPanel.add(counterflowPanel);
        // Details Panel (# of Lives)
        livesPanel.setLayout(new GridLayout(1, 2));
        livesPanel.add(livesLabel);
        livesPanel.add(lives);
        livesPanel.setOpaque(false);
        detailsPanel.add(livesPanel);
        // Details Panel (Bottom of Phone)
        detailsPanel.add(phone.drawLowerPhone());
        detailsPanel.setOpaque(false);
        // RGB Panel, 1st Cell
        RGBLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        RGBPanel.add(RGBLabel);
        // RGB Panel, 2nd Cell (RED)
        redPanel.setLayout(new BorderLayout());
        redPanel.add(redSlider, BorderLayout.CENTER);
        redPanel.add(red, BorderLayout.WEST);
        RGBPanel.add(redPanel);
        // RGB Panel, 3rd Cell (GREEN)
        greenPanel.setLayout(new BorderLayout());
        greenPanel.add(greenSlider, BorderLayout.CENTER);
        greenPanel.add(green, BorderLayout.WEST);
        RGBPanel.add(greenPanel);
        // RGB Panel, 4th Cell (BLUE)
        bluePanel.setLayout(new BorderLayout());
        bluePanel.add(blueSlider, BorderLayout.CENTER);
        bluePanel.add(blue, BorderLayout.WEST);
        RGBPanel.add(bluePanel);
        red.setBackground(new Color(100, 0, 0));
        green.setBackground(new Color(0, 100, 0));
        blue.setBackground(new Color(0, 0, 100));
        // Compiles the Right Half of the Start Menu
        gearSelectPanel.add(gearSelect);
        gearSelectPanel.add(detailsPanel);
        rightHalfPanel.add(gearSelectPanel);
        rightHalfPanel.add(RGBPanel);

        // Compiles the whole Main Menu
        startMenuMainPanel.setLayout(new GridLayout(1, 2));
        startMenuMainPanel.add(leftHalfPanel); 
        startMenuMainPanel.add(rightHalfPanel);
        cp.add(startMenuMainPanel);
        setTitle("21C: Drive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        pack();
        setVisible(true);
    }

    /** Renders the game for gamplay. */
    private void setUpGameGUI() {
        Container cp = getContentPane();
        cp.repaint();
        counterflowMultiplier = new JLabel();
        speedDisplay = new JLabel();
        livesDisplay = new JLabel();
        scoreDisplay = new JLabel();
        counterflowMultiplier.setForeground(Color.BLACK);
        speedDisplay.setForeground(Color.BLACK);
        livesDisplay.setForeground(Color.BLACK);
        scoreDisplay.setForeground(Color.BLACK);

        HUD = new JPanel(new GridLayout(1, 4));
        HUD.add(counterflowMultiplier);
        HUD.add(speedDisplay);
        HUD.add(livesDisplay);
        HUD.add(scoreDisplay);
        cp.add(HUD, BorderLayout.NORTH);

        cp.add(sceneCanvas = new SceneCanvas(frame_width, frame_height, selectedCar, selectedGear, lastRemoveNormal, lastRemoveCounter));
        setTitle("21C: Drive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        setVisible(true);
    }

    /* Renders the pause menu after the escape button is clicked or the game over screen when lives hit 0. */
    private void setUpPauseGUI(){
        Container cp = getContentPane();
        cp.repaint();
        gamePausePanel = new JPanel();

        UIManager.put("Label.foreground", new Color(255, 255, 255));
        pauseText = new JLabel("", JLabel.CENTER);
        gamePausePanel.setBackground(Color.decode("#222222"));

        pauseButtons = new JPanel(new GridLayout(1, 3));
        boolean gameOver = (gameLives <= 0);
        if (!gameOver){
            pauseText.setText("Game Paused");
            pauseButtons.add(resumeGame);
            pauseButtons.add(restartGame);
            pauseButtons.add(backToMenu);
        }
        else if (gameOver){
            pauseText.setText("Game Over! You Scored: "+score);
            pauseButtons.add(restartGame);
            pauseButtons.add(new JLabel());
            pauseButtons.add(backToMenu);
            if (bgm != null && bgm.isRunning()) {
                bgm.stop();
            }
            bgm = loadMusic("failMusic.wav", true);
        }

        gamePausePanel.setLayout(new BorderLayout());
        gamePausePanel.add(pauseText, BorderLayout.CENTER);
        gamePausePanel.add(pauseButtons, BorderLayout.SOUTH);
        cp.add(gamePausePanel);

        setTitle("21C: Drive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        setVisible(true);
    }

    /* Sets up the slider listeners used for player customization. */
    public void setUpSliderListeners() {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ArrayList<Car> cars = carSelect.getListOfCars();
                for (Car c : cars){
                    c.changeColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue())); // Updates color across all car models
                }
                carSelect.repaint();
                red.setBackground(new Color(redSlider.getValue(), 0, 0));
                green.setBackground(new Color(0, greenSlider.getValue(), 0));
                blue.setBackground(new Color(0, 0, blueSlider.getValue()));
            }
        };
        redSlider.addChangeListener(changeListener);
        greenSlider.addChangeListener(changeListener);
        blueSlider.addChangeListener(changeListener);
    }

    /* Sets up the mouse listeners used for changing the car and the difficulty on the gearstick. */
    public void setUpMouseListeners() {
        MouseListener mouseListener = new MouseListener() {
            @Override public void mouseClicked(MouseEvent e){
                JComponent eventSource = (JComponent) e.getSource();
                if (eventSource == gearSelect) {
                    GearShifter g = gearSelect.getShifter();
                    GearKnob gk = gearSelect.getKnob();
                    int currentGear = g.getGear();
                    int size = g.getSize();
                    double xdelta = size*0.6;
                    double ydelta = size*1.25;
                    if (currentGear == 1){
                        gk.moveY(ydelta);
                        g.changeGear(2);
                    } else if (currentGear == 2){
                        gk.moveX(xdelta);
                        gk.moveY(-ydelta);
                        g.changeGear(3);
                    } else if (currentGear == 3){
                        gk.moveY(ydelta);
                        g.changeGear(4);
                    } else if (currentGear == 4){
                        gk.moveX(xdelta);
                        gk.moveY(-ydelta);
                        g.changeGear(5);
                    } else if (currentGear == 5){
                        gk.moveX(-size*1.2);
                        g.changeGear(1);
                    }
                    switch (gearSelect.getShifter().getGear()) {
                        case 1:
                            startSpeed.setText("50 kph");
                            normalLanes.setText("| 1 | 2 |");
                            counterflowLanes.setText("X");
                            lives.setText("❤❤");
                            break;
                        case 2:
                            startSpeed.setText("60 kph");
                            normalLanes.setText("| 1 | 2 | 3 |");
                            break;
                        case 3:
                            startSpeed.setText("70 kph");
                            normalLanes.setText("| 1 | 2 | 3 | 4 |");
                            lives.setText("❤❤❤");
                            break;
                        case 4:
                            normalLanes.setText("| 1 | 2 |");
                            counterflowLanes.setText("| 1 | 2 |");
                            break;
                        case 5:
                            normalLanes.setText("| 1 | 2 | 3 |");
                            counterflowLanes.setText("| 1 | 2 | 3 |");
                            lives.setText("❤❤❤❤");
                            break;
                    }

                    gearSelect.repaint();
                } else if (eventSource == carSelect) {
                    carSelect.changeVehicle();
                    carSelect.repaint();
                }
            }
            //Unused methods
            @Override public void mouseEntered(MouseEvent e){}
            @Override public void mouseExited(MouseEvent e){}
            @Override public void mousePressed(MouseEvent e){}
            @Override public void mouseReleased(MouseEvent e){}
        };
        gearSelect.addMouseListener(mouseListener);
        carSelect.addMouseListener(mouseListener);
    }

    /* Sets up the button listeners used in the main menu, pause screen, game over screen. */
    public void setUpButtonListeners() { 
        ActionListener buttonListeners = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == driveButton || e.getSource() == restartGame){
                    selectedCar = carSelect.getCar();
                    reloadSelectedCar("Game");
                    selectedGear = gearSelect.getShifter().getGear();
                    gameSpeed = gearSelect.getShifter().getSpeed();
                    maxSpeed = 120;

                    gameLives = gearSelect.getShifter().getLives();
                    score = 0;
                    lastRemoveNormal = 0;
                    lastRemoveCounter = 0;
                    getContentPane().removeAll();
                    setUpGameGUI();
                    setUpKeyListeners();
                    setUpTimers();
                    revalidate();
                    repaint();
                }
                else if (e.getSource() == resumeGame){
                    reloadSelectedCar("Game");
                    getContentPane().removeAll();
                    setUpGameGUI();

                    sceneCanvas.getRoad().setX(lastRoadX);
                    sceneCanvas.getRoad().setY(lastRoadY);
                    sceneCanvas.getTraffic().loadNormalCarPositions(lastNormalX, lastNormalY);
                    sceneCanvas.getTraffic().loadCounterflowCarPositions(lastCounterflowX, lastCounterflowY);

                    gameTimer.start();
                    setUpKeyListeners();
                    revalidate();
                    repaint();
                } else if (e.getSource() == backToMenu){
                    reloadSelectedCar("Menu");
                    gameTimer.stop();
                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    setUpGUI();
                }

                if (bgm != null && bgm.isRunning()) {
                    bgm.stop();
                }

            }
        };
        driveButton.addActionListener(buttonListeners);
        resumeGame.addActionListener(buttonListeners);
        restartGame.addActionListener(buttonListeners);
        backToMenu.addActionListener(buttonListeners);
    }

    /* Ensures that the player car loads in the correct position. */
    private void reloadSelectedCar(String purpose){
        if (purpose.equals("Game")){
            selectedCar.moveTo((frame_width/2)-37.5, (frame_height/2+50));
            selectedCar.changeSize(75);
            selectedCar.rotate(0);
            left = false;
            right = false;
            accelerating = false;
            braking = false;
        } else if (purpose.equals("Menu")){
            selectedCar.moveTo(125, 100);
            selectedCar.changeSize(150);
            selectedCar.rotate(0);
        }
    }

    /* Sets up the key listeners for player movement during gameplay. */
    public void setUpKeyListeners() {
        KeyListener keyListener = new KeyListener() {
            // KeyBinds: A > Steer Left, D > Steer Right, ESC = Pause
            @Override public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_A){left = true;}
                else if (e.getKeyCode() == KeyEvent.VK_D){right = true;} 
                // Unused, could be used later on
                else if (e.getKeyCode() == KeyEvent.VK_W){accelerating = true;}
                else if (e.getKeyCode() == KeyEvent.VK_S){braking = true;}

                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    reloadSelectedCar("Menu");

                    lastRoadX = sceneCanvas.getRoad().getX();
                    lastRoadY = sceneCanvas.getRoad().getY();
                    lastNormalX = sceneCanvas.getTraffic().saveNormalCarsX();
                    lastNormalY = sceneCanvas.getTraffic().saveNormalCarsY();
                    lastCounterflowX = sceneCanvas.getTraffic().saveCounterflowCarsX();
                    lastCounterflowY = sceneCanvas.getTraffic().saveCounterflowCarsY();
                    lastRemoveNormal = sceneCanvas.getTraffic().getCurrentRemoveNormal();
                    lastRemoveCounter = sceneCanvas.getTraffic().getCurrentRemoveCounter();

                    gameTimer.stop();
                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    setUpPauseGUI();
                }
                sceneCanvas.repaint();
            }
            @Override public void keyReleased(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_A) {left = false;}
                else if (e.getKeyCode() == KeyEvent.VK_D) {right = false;}
                // Unused, could be used later on
                else if (e.getKeyCode() == KeyEvent.VK_W){accelerating = false;}
                else if (e.getKeyCode() == KeyEvent.VK_S){braking = false;}
            }
            @Override public void keyTyped(KeyEvent e){}
        };
        sceneCanvas.setFocusable(true);
        sceneCanvas.addKeyListener(keyListener);
        sceneCanvas.requestFocus();
    }

    /* Sets up the game timer that updates every 16 milliseconds (Approx 60 times per second/60 FPS) */
    public void setUpTimers(){
        gameTimer = new Timer(16, e -> drive(gameSpeed));
        gameTimer.start();
    }

    /* This method is called every 16 milliseconds by the game timer. Updates the positions of all game components. */
    public void drive(double speed){
        // Calculates the value used in most gameplay systems
        double delta = speed*0.15;
        // Calls all the game components that require movement in game
        Road road = sceneCanvas.getRoad();
        TrafficSystem traffic = sceneCanvas.getTraffic();
        Car[] normalCars = traffic.getNormalCars();
        Car[] counterFlowCars = traffic.getCounterFlowCars();
        // Updates the HUD
        speedDisplay.setText(String.valueOf((int) gameSpeed)+" kph");
        livesDisplay.setText(String.valueOf(gameLives)+" lives");
        scoreDisplay.setText(String.valueOf(score));
        // Checks if car is in the counterflow lane
        if (selectedCar.getX()+selectedCar.getWidth() <= road.getCounterFlowX()) {
            inCounterFlow = true;
            counterflowMultiplier.setText("Active");
        }
        else {
            inCounterFlow = false;
            counterflowMultiplier.setText("Inactive");
        }
        // Adds to score, applies 3x multiplier if in counterflow lane
        if (!inCounterFlow) score += delta;
        else if (inCounterFlow) score += delta*3;
        // Ensures that road does not fall off the frame
        road.moveY(delta);
        if (road.getY() >= 0){road.moveY(-3400);}
        /* Sets the speed of the cars relative to the player speed and ensures they do not fall downward forever 
        Normal Car Speed = 75% of game speed
        Counterflow Car Speed 125% of game speed
        */
        for (Car c : normalCars){
            c.moveY(delta*0.8);
            if (c.getY() > 800) {
                traffic.refreshNormalRNG();
                c.moveY(-1500);
            } else if (c.isColliding(selectedCar) && c.isVisible()){
                gameLives -= 1;
                c.moveY(-1500);
            }
        }
        for (Car c : counterFlowCars){
            c.moveY(delta*1.2);
            if (c.getY() > 800) {
                traffic.refreshCounterRNG();
                c.moveY(-1500);
            } else if (c.isColliding(selectedCar) && c.isVisible()){
                gameLives -= 1;
                c.moveY(-1500);
            }
        }
        // Gradually accelerates the game.
        gameSpeed += 0.01;
        if (gameSpeed >= maxSpeed) {gameSpeed = maxSpeed;}
        // Implements left and right steering
        if (left && selectedCar.getX() != road.getMaxXL() && selectedCar.getX() > road.getMaxXL()) {
            road.moveX(delta);
            traffic.moveX(delta);
            selectedCar.rotate(-15);
        }
        else if (right && selectedCar.getX()+75 != road.getMaxXR() && selectedCar.getX()+75 < road.getMaxXR()) {
            road.moveX(-delta);
            traffic.moveX(-delta);
            selectedCar.rotate(15);
        }
        else if ((!left && !right) || (selectedCar.getX() < road.getMaxXL() || selectedCar.getX()+75 > road.getMaxXR())) {
            selectedCar.rotate(0);
        }

        // Checks if lives become 0
        if (gameLives <= 0){
            selectedCar.moveTo(125, 100);
            selectedCar.changeSize(150);
            gameTimer.stop();
            getContentPane().removeAll();
            revalidate();
            repaint();
            setUpPauseGUI();
        }
        // Repaints the canvas for smooth animation
        sceneCanvas.repaint();
    }


    /* Loads the SFX. */
    private Clip loadMusic(String filename, boolean loop) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(filename);
            if (audioSrc == null) {
                return null;
            }

            AudioInputStream audioStream =  AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            if(loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                return clip;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}