// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;

public class SceneFrame extends JFrame {
    private int frame_width, frame_height;
    private boolean left, right;
    private Timer gameTimer, distanceTimer;
    private String stars;

    //Game trackers
    private int score;
    private double gameSpeed;
    private int gameLives;

    private CarSelect carSelect;
    private GearSelect gearSelect;
    private SceneCanvas sceneCanvas;

    private JPanel startMenuMainPanel, leftHalfPanel, rightHalfPanel,
            RGBPanel, carSelectPanel, gearSelectPanel, detailsPanel,
            redPanel, greenPanel, bluePanel,
            red, green, blue,
            gamePausePanel, pauseButtons;

    private JLabel RGBLabel, difficultyLabel, livesLabel, lanesLabel, startSpeedLabel,
            difficulty, lives, lanes, startSpeed, pauseText;

    private JSlider redSlider, greenSlider, blueSlider;
    private JButton driveButton, resumeGame, restartGame, backToMenu;

    private Car selectedCar;
    private int selectedGear;

    public SceneFrame() {
        //Game Trackers
        score = 0; gameSpeed = 0; gameLives = 3;

        frame_width = 800;
        frame_height = 600;
        stars = "★";

        UIManager.put("Label.font", new Font("Courier New", Font.BOLD, 12));
        UIManager.put("Label.foreground", Color.WHITE);
        carSelect = new CarSelect();
        gearSelect = new GearSelect();

        startMenuMainPanel = new JPanel();
        leftHalfPanel = new JPanel();
        rightHalfPanel = new JPanel();
        driveButton = new JButton("Drive");

        carSelectPanel = new JPanel();
        gearSelectPanel = new JPanel();
        detailsPanel = new JPanel();

        RGBPanel = new JPanel();
        RGBPanel.setBackground(Color.DARK_GRAY);
        redPanel = new JPanel();
        greenPanel = new JPanel();
        bluePanel = new JPanel();
        detailsPanel.setBackground(Color.decode("#222222"));

        redSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        greenSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        blueSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);

        red = new JPanel();
        green = new JPanel();
        blue = new JPanel();

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

        ArrayList<JLabel> labels = new ArrayList<>();
        labels.add(difficulty = new JLabel("Difficulty: " + stars, JLabel.CENTER));
        labels.add(lives = new JLabel("❤❤❤", JLabel.CENTER));
        labels.add(lanes = new JLabel("Lanes", JLabel.CENTER));
        labels.add(startSpeed = new JLabel("10 kph", JLabel.CENTER));
        labels.add(difficultyLabel = new JLabel("Difficulty", JLabel.CENTER));
        labels.add(startSpeedLabel = new JLabel("Start Speed", JLabel.CENTER));
        labels.add(lanesLabel = new JLabel("Lanes", JLabel.CENTER));
        labels.add(livesLabel = new JLabel("Lives", JLabel.CENTER));

        resumeGame = new JButton("Continue");
        backToMenu = new JButton("Back To Menu");
        restartGame = new JButton("Restart");

        setUpGUI();
        setUpButtonListeners();
        setUpMouseListeners();
        setUpSliderListeners();
    }

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

        detailsPanel.setLayout(new GridLayout(8, 1));
        detailsPanel.add(new JLabel());
        detailsPanel.add(difficulty, JLabel.CENTER, 1);
        detailsPanel.add(startSpeedLabel);
        detailsPanel.add(startSpeed, JLabel.CENTER, 3);
        detailsPanel.add(livesLabel);
        detailsPanel.add(lives, JLabel.CENTER, 5);
        detailsPanel.add(lanesLabel);
        detailsPanel.add(lanes, JLabel.CENTER, 7);
        UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 24));

        /*
        Speed: [No. of stars]
        Normal Flow Lanes:
        Counter Flow Lanes:
         */

        gearSelectPanel.add(gearSelect);
        gearSelectPanel.add(detailsPanel);
        rightHalfPanel.add(gearSelectPanel);
        rightHalfPanel.add(RGBPanel);

        startMenuMainPanel.setLayout(new GridLayout(1, 2));

        startMenuMainPanel.add(leftHalfPanel); startMenuMainPanel.add(rightHalfPanel); // adds the left and right half
        cp.add(startMenuMainPanel);
        setTitle("Midterm Project - Buenaventura - Manulat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        pack();
        setVisible(true);
    }

    private void setUpGameGUI() {
        Container cp = getContentPane();
        cp.repaint();
        cp.add(sceneCanvas = new SceneCanvas(frame_width, frame_height, selectedCar, selectedGear));
        setTitle("Midterm Project - Buenaventura - Manulat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        setVisible(true);
    }

    private void setUpPauseGUI(){
        Container cp = getContentPane();
        cp.repaint();
        gamePausePanel = new JPanel();

        pauseText = new JLabel();
        pauseButtons = new JPanel();
        pauseButtons.setLayout(new GridLayout(1, 2));
        boolean gameOver = (gameLives == 0);
        if (!gameOver){
            pauseText.setText("Game Paused");
            pauseButtons.add(resumeGame);
            pauseButtons.add(backToMenu);
        }
        else if (gameOver){
            pauseText.setText("Game Over");
            pauseButtons.add(restartGame);
            pauseButtons.add(backToMenu);
        }

        gamePausePanel.setLayout(new BorderLayout());
        gamePausePanel.add(pauseText, BorderLayout.CENTER);
        gamePausePanel.add(pauseButtons, BorderLayout.SOUTH);
        cp.add(gamePausePanel);

        setTitle("Midterm Project - Buenaventura - Manulat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        setVisible(true);
    }

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
                    System.out.println("Current Gear: "+gearSelect.getShifter().getGear());
                        switch (gearSelect.getShifter().getGear()) {
                            case 1:
                                difficulty.setText("Difficulty: ★");
                                break;
                            case 2:
                                difficulty.setText("Difficulty: ★★");
                                break;
                            case 3:
                                difficulty.setText("Difficulty: ★★★");
                                break;
                            case 4:
                                difficulty.setText("Difficulty: ★★★★");
                                break;
                            case 5:
                                difficulty.setText("Difficulty: ★★★★★");
                                break;
                    }

                    gearSelect.repaint();
                } else if (eventSource == carSelect) {
                    carSelect.changeVehicle();
                    System.out.println("Car Change: "+carSelect.getCar().getCarModel());
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

    public void setUpButtonListeners() {
        ActionListener startButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCar = carSelect.getCar();
                selectedGear = gearSelect.getShifter().getGear();
                selectedCar.moveTo((frame_width/2)-37.5, (frame_height/2+50));
                selectedCar.changeSize(75);
                gameSpeed = gearSelect.getShifter().getSpeed();
                gameLives = 3;
                score = 0;
                System.out.println("Car Model: "+ selectedCar.getCarModel());
                System.out.println("Gear Level: "+ selectedGear);
                getContentPane().removeAll();
                setUpGameGUI();
                setUpKeyListeners();
                setUpTimers();
                revalidate();
                repaint();
            }
        };
        driveButton.addActionListener(startButtonListener); 
        ActionListener pauseButtonListeners = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == resumeGame){
                    selectedCar = carSelect.getCar();
                    selectedGear = gearSelect.getShifter().getGear();
                    selectedCar.moveTo((frame_width/2)-37.5, (frame_height/2+50));
                    selectedCar.changeSize(75);
                    selectedCar.rotate(0);
                    gameSpeed = gearSelect.getShifter().getSpeed();
                    getContentPane().removeAll();
                    setUpGameGUI();
                    gameTimer.start();
                    distanceTimer.start();
                    setUpKeyListeners();
                    revalidate();
                    repaint();
                } else if (e.getSource() == restartGame){
                    selectedCar = carSelect.getCar();
                    selectedGear = gearSelect.getShifter().getGear();
                    selectedCar.moveTo((frame_width/2)-37.5, (frame_height/2+50));
                    selectedCar.changeSize(75);
                    selectedCar.rotate(0);
                    gameSpeed = gearSelect.getShifter().getSpeed();
                    gameLives = 3;
                    score = 0;
                    getContentPane().removeAll();
                    setUpGameGUI();
                    gameTimer.start();
                    distanceTimer.start();
                    setUpKeyListeners();
                    revalidate();
                    repaint();
                } else if (e.getSource() == backToMenu){
                    selectedCar.moveTo(125, 100);
                    selectedCar.changeSize(150);
                    selectedCar.rotate(0);
                    gameTimer.stop();
                    distanceTimer.stop();
                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    setUpGUI();
                    System.out.println(score);
                }
            }
        };
        resumeGame.addActionListener(pauseButtonListeners);
        restartGame.addActionListener(pauseButtonListeners);
        backToMenu.addActionListener(pauseButtonListeners);
    }

    public void setUpKeyListeners() {
        KeyListener keyListener = new KeyListener() {
            // Use keyPressed for the keybinds
            @Override public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_A){
                    left = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_D){
                    right = true;
                }
                sceneCanvas.repaint();
            }
            @Override public void keyReleased(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_A){
                    left = false;
                }
                else if (e.getKeyCode() == KeyEvent.VK_D){
                    right = false;
                }
            }
            @Override public void keyTyped(KeyEvent e){}
        };
        KeyListener pause = new KeyListener() {
            @Override public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    selectedCar.moveTo(125, 100);
                    selectedCar.changeSize(150);
                    gameTimer.stop();
                    distanceTimer.stop();
                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    setUpPauseGUI();
                }
            }
            @Override public void keyReleased(KeyEvent e){}
            @Override public void keyTyped(KeyEvent e){}
        };

        sceneCanvas.setFocusable(true);
        sceneCanvas.addKeyListener(keyListener);
        sceneCanvas.addKeyListener(pause);
        sceneCanvas.requestFocus();
    }

    public void setUpTimers(){
        gameTimer = new Timer(16, e -> movement());
        gameTimer.start();

        distanceTimer = new Timer(16, e-> drive(gameSpeed));
        distanceTimer.start();
    }

    public void drive(double speed){
        double delta = (((speed*40000)/60)/60)/60;
        Road road = sceneCanvas.getRoad();
        TrafficSystem traffic = sceneCanvas.getTraffic();
        road.moveY(delta);
        score += delta;
        if (road.getY() >= 0){
            road.moveY(-3400);
        }

        Car[] normalCars = traffic.getNormalCars();
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
        Car[] counterFlowCars = traffic.getCounterFlowCars();
        for (Car c : counterFlowCars){
            c.moveY(delta);
            if (c.getY() > 800) {
                traffic.refreshCounterRNG();
                c.moveY(-1500);
            } else if (c.isColliding(selectedCar) && c.isVisible()){
                gameLives -= 1;
                c.moveY(-1500);
            }
        }
        if (gameLives == 0){
            selectedCar.moveTo(125, 100);
            selectedCar.changeSize(150);
            gameTimer.stop();
            distanceTimer.stop();
            getContentPane().removeAll();
            revalidate();
            repaint();
            setUpPauseGUI();
            System.out.println(score);
        }
    }

    public void movement() {
        double amount = 0;
        if (gameSpeed*0.2 < 5) amount = 5;
        else if (gameSpeed*0.2 > 15) amount = 15;
        else amount = gameSpeed*0.2;

        Road road = sceneCanvas.getRoad();
        TrafficSystem traffic = sceneCanvas.getTraffic();
        Car[] normalCars = traffic.getNormalCars();
        Car[] counterFlowCars = traffic.getCounterFlowCars();

        if (left && selectedCar.getX() != road.getMaxXL()) {
            road.moveX(amount);
            for (Car c : normalCars){c.moveX(amount);}
            for (Car c : counterFlowCars){c.moveX(amount);}
            selectedCar.rotate(-15);
        }
        else if (right && selectedCar.getX() != road.getMaxXR()) {
            road.moveX(-amount);
            for (Car c : normalCars){c.moveX(-amount);}
            for (Car c : counterFlowCars){c.moveX(-amount);}
            selectedCar.rotate(15);
        }
        else if ((!left && !right) || (selectedCar.getX() == road.getMaxXL() || selectedCar.getX() == road.getMaxXR())) {
            selectedCar.rotate(0);
        }

        sceneCanvas.repaint();
    }
}