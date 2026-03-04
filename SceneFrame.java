// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
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
    // Start Menu fields
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
    private double gameSpeed;;
    private int gameLives, score;
    private JLabel counterflowMultiplier, speedDisplay, livesDisplay, scoreDisplay;
    private JPanel HUD;

    private Car selectedCar;
    private int selectedGear;
    // Save states
    private double lastRoadX, lastRoadY;
    private double[] lastNormalX, lastNormalY;
    private int lastRemoveNormal, lastRemoveCounter;

    // Pause/Game Over Fields
    private JPanel gamePausePanel, pauseButtons;
    private JLabel pauseText;
    private JButton resumeGame, restartGame, backToMenu;

    private Clip bgm;

    /* Constructor method for the SceneFrame. */
    public SceneFrame() {
        resumeGame = new JButton("Continue");
        backToMenu = new JButton("Back To Menu");
        restartGame = new JButton("Restart");

        frame_width = 800;
        frame_height = 600;
        phone =  new Phone();

        carSelect = new CarSelect();
        gearSelect = new GearSelect();

        startMenuMainPanel = new JPanel();
        leftHalfPanel = new JPanel();
        rightHalfPanel = new JPanel();
        driveButton = new JButton("Drive");

        carSelectPanel = new JPanel();
        gearSelectPanel = new JPanel();

        detailsPanel = phone.drawPhoneScreen();

        RGBPanel = new JPanel();
        RGBPanel.setBackground(Color.DARK_GRAY);
        redPanel = new JPanel();
        greenPanel = new JPanel();
        bluePanel = new JPanel();
        startSpeedPanel = new JPanel();
        livesPanel = new JPanel();
        normalLanesPanel = new JPanel();
        counterflowPanel = new JPanel();

        redSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        greenSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        blueSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);

        red = new JPanel();
        green = new JPanel();
        blue = new JPanel();

        UIManager.put("Label.foreground", Color.WHITE);
        RGBLabel = new JLabel("Select Car Color", JLabel.CENTER);
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

        lives = new JLabel("❤❤❤", JLabel.LEFT);
        normalLanes = new JLabel("| 2 | 1 | ", JLabel.LEFT);
        counterflowLanes = new JLabel("X", JLabel.LEFT);
        startSpeed  = new JLabel("★", JLabel.LEFT);
        startSpeedLabel = new JLabel("Start Speed ", JLabel.RIGHT);
        normalLanesLabel = new JLabel("Normal Lanes ", JLabel.RIGHT);
        livesLabel = new JLabel("Lives ", JLabel.RIGHT);
        counterflowLanesLabel = new JLabel("Counterflow ", JLabel.RIGHT);

        setUpGUI();
        setUpButtonListeners();
        setUpMouseListeners();
        setUpSliderListeners();
    }

    /* This method renders the main menu / the start menu where players can customize. The frame is split into two parts:
    The first half is the canvas, CarSelect, and the second half is the gearSelect and the details panel. */
    public void setUpGUI() {
        Container cp = getContentPane();

        detailsPanel.removeAll();

        // left half
        leftHalfPanel.setLayout(new GridLayout(1, 1));
        carSelectPanel.setLayout(new BorderLayout());
        carSelectPanel.add(carSelect);

        carSelectPanel.add(carSelect, BorderLayout.CENTER);
        carSelectPanel.add(driveButton, BorderLayout.SOUTH);
        leftHalfPanel.add(carSelectPanel);

        RGBPanel.setLayout(new GridLayout(4, 1));

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

        // right half
        rightHalfPanel.setBackground(Color.CYAN);
        rightHalfPanel.setLayout(new GridLayout(2, 1));
        gearSelectPanel.setLayout(new GridLayout(1,2 ));

        detailsPanel.setLayout(new GridLayout(7, 1));
        detailsPanel.add(phone.drawUpperPhone()); // top phone design
        detailsPanel.add(difficulty, JLabel.CENTER, 1);

        startSpeedPanel.setLayout(new GridLayout(1, 2));
        startSpeedPanel.add(startSpeedLabel);
        startSpeedPanel.add(startSpeed);
        startSpeedPanel.setOpaque(false);
        detailsPanel.add(startSpeedPanel);

        normalLanesPanel.setLayout(new GridLayout(1, 2));
        normalLanesPanel.add(normalLanesLabel);
        normalLanesPanel.add(normalLanes);
        normalLanesPanel.setOpaque(false);
        detailsPanel.add(normalLanesPanel);

        counterflowPanel.setLayout(new GridLayout(1, 2));
        counterflowPanel.add(counterflowLanesLabel);
        counterflowPanel.add(counterflowLanes);
        counterflowPanel.setOpaque(false);
        detailsPanel.add(counterflowPanel);

        livesPanel.setLayout(new GridLayout(1, 2));
        livesPanel.add(livesLabel);
        livesPanel.add(lives);
        livesPanel.setOpaque(false);
        detailsPanel.add(livesPanel);

        detailsPanel.add(phone.drawLowerPhone());
        detailsPanel.setOpaque(false);


        gearSelectPanel.add(gearSelect);
        gearSelectPanel.add(detailsPanel);
        rightHalfPanel.add(gearSelectPanel);
        rightHalfPanel.add(RGBPanel);

        startMenuMainPanel.setLayout(new GridLayout(1, 2));

        startMenuMainPanel.add(leftHalfPanel); startMenuMainPanel.add(rightHalfPanel); // adds the left and right half
        cp.add(startMenuMainPanel);
        setTitle("21C: Drive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        pack();
        setVisible(true);
    }

    /* this method renders the game proper after driveButton is clicked. */
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

    /* this method renders the simple pause menu after the escape button is clicked. */
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

    /* The next few methods set up the various Action/ChangeListeners we used in our project.
    This sets up the slider listeners used for player customization. */
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

    /* The next few methods set up the various Action/ChangeListeners we used in our project.
    This sets up the mouse listeners used for changing the car and the difficulty on the gearstick. */
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
                                startSpeed.setText("★");
                                normalLanes.setText("| 1 | 2 |");
                                counterflowLanes.setText("X");
                                break;
                            case 2:
                                startSpeed.setText("★ ★");
                                normalLanes.setText("| 1 | 2 | 3 |");
                                break;
                            case 3:
                                startSpeed.setText("★ ★ ★");
                                normalLanes.setText("| 1 | 2 | 3 | 4 |");
                                break;
                            case 4:
                                startSpeed.setText("★ ★ ★");
                                normalLanes.setText("| 1 | 2 |");
                                counterflowLanes.setText("| 1 | 2 |");
                                break;
                            case 5:
                                startSpeed.setText("★ ★ ★ ");
                                normalLanes.setText("| 1 | 2 | 3 |");
                                counterflowLanes.setText("| 1 | 2 | 3 |");
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

    /* The next few methods set up the various Action/ChangeListeners we used in our project.
    This sets up the button listeners used the pause menu, game over menu, and the main menu. */
    public void setUpButtonListeners() { 
        ActionListener buttonListeners = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == driveButton || e.getSource() == restartGame){
                    selectedCar = carSelect.getCar();
                    reloadSelectedCar("Game");
                    selectedGear = gearSelect.getShifter().getGear();
                    gameSpeed = gearSelect.getShifter().getSpeed();
                    gameLives = 3;
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

    /* Ensures that the player car is in the correct position */
    private void reloadSelectedCar(String purpose){ // Ensures that the player car loads properly
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

    /* The next few methods set up the various Action/ChangeListeners we used in our project.
    This sets up the key listeners, vital for player movement. */
    public void setUpKeyListeners() {
        KeyListener keyListener = new KeyListener() {
            // KeyBinds: A > Steer Left, D > Steer Right, ESC = Pause
            @Override public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_A){left = true;}
                else if (e.getKeyCode() == KeyEvent.VK_D){right = true;} 
                else if (e.getKeyCode() == KeyEvent.VK_W){accelerating = true;}
                else if (e.getKeyCode() == KeyEvent.VK_S){braking = true;}
                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    reloadSelectedCar("Menu");
                    lastRoadX = sceneCanvas.getRoad().getX();
                    lastRoadY = sceneCanvas.getRoad().getY();
                    lastNormalX = sceneCanvas.getTraffic().saveNormalCarsX();
                    lastNormalY = sceneCanvas.getTraffic().saveNormalCarsY();
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
                else if (e.getKeyCode() == KeyEvent.VK_W){accelerating = false;}
                else if (e.getKeyCode() == KeyEvent.VK_S){braking = false;}
            }
            @Override public void keyTyped(KeyEvent e){}
        };
        sceneCanvas.setFocusable(true);
        sceneCanvas.addKeyListener(keyListener);
        sceneCanvas.requestFocus();
    }

    /* Sets up the game timer that updates every 16 milliseconds (60 FPS) */
    public void setUpTimers(){
        gameTimer = new Timer(16, e -> drive(gameSpeed));
        gameTimer.start();
    }

    /* this method is checked every 16 milliseconds by the game timer. Updates the movements of the road and the enemy cars spawning */
    public void drive(double speed){
        // Fields to be used in this method
        double delta = speed*0.18;
        Road road = sceneCanvas.getRoad();
        TrafficSystem traffic = sceneCanvas.getTraffic();
        double amount = speed*0.15;

        Car[] normalCars = traffic.getNormalCars();
        Car[] counterFlowCars = traffic.getCounterFlowCars();

        // Updating HUD
        speedDisplay.setText(String.valueOf((int) gameSpeed)+" kph");
        livesDisplay.setText(String.valueOf(gameLives)+" lives");
        scoreDisplay.setText(String.valueOf(score));

        // Checking if car is in counterflow lane
        if (selectedCar.getX()+selectedCar.getWidth() <= road.getCounterFlowX()) {
            inCounterFlow = true;
            counterflowMultiplier.setText("Active");
        }
        else {
            inCounterFlow = false;
            counterflowMultiplier.setText("Inactive");
        }

        // Adding to score
        if (!inCounterFlow) score += delta;
        else if (inCounterFlow) score += delta*3;

        // Ensure that road does not fall off the frame
        road.moveY(delta);
        if (road.getY() >= 0){road.moveY(-3400);}
        // Illusion of forward moving
        for (Car c : normalCars){
            c.moveY(delta*0.75);
            if (c.getY() > 800) {
                traffic.refreshNormalRNG();
                c.moveY(-1500);
            } else if (c.isColliding(selectedCar) && c.isVisible()){
                gameLives -= 1;
                c.moveY(-1500);
            }
        }
        for (Car c : counterFlowCars){
            c.moveY(delta*1.25);
            if (c.getY() > 800) {
                traffic.refreshCounterRNG();
                c.moveY(-1500);
            } else if (c.isColliding(selectedCar) && c.isVisible()){
                gameLives -= 1;
                c.moveY(-1500);
            }
        }

        // Acceleration, Braking, and Natural Decceleration
        if (accelerating) {gameSpeed += 0.15;}
        else if (braking) {gameSpeed -= 0.5;}
        else if (!accelerating && !braking) {gameSpeed -= 0.01;}
        if (gameSpeed <= 50) {gameSpeed = 50;}

        // Left and Right steering
        if (left && selectedCar.getX() != road.getMaxXL() && selectedCar.getX() > road.getMaxXL()) {
            road.moveX(amount);
            traffic.moveX(amount);
            selectedCar.rotate(-15);
        }
        else if (right && selectedCar.getX()+75 != road.getMaxXR() && selectedCar.getX()+75 < road.getMaxXR()) {
            road.moveX(-amount);
            traffic.moveX(-amount);
            selectedCar.rotate(15);
        }
        else if ((!left && !right) || (selectedCar.getX() < road.getMaxXL() || selectedCar.getX()+75 > road.getMaxXR())) {
            selectedCar.rotate(0);
        }

        // Constantly check if lives become 0
        if (gameLives <= 0){
            selectedCar.moveTo(125, 100);
            selectedCar.changeSize(150);
            gameTimer.stop();
            getContentPane().removeAll();
            revalidate();
            repaint();
            setUpPauseGUI();
        }
        sceneCanvas.repaint();
    }


    /* This loads the fail music when the player dies after 3 lives */
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