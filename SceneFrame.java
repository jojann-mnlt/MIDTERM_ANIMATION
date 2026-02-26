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
    private double score;
    private double gameSpeed;
    private int gameLives;

    private CarSelect carSelect;
    private GearSelect gearSelect;
    private SceneCanvas sceneCanvas;

    private JPanel startMenuMainPanel, leftHalfPanel, rightHalfPanel,
            RGBPanel, carSelectPanel, gearSelectPanel, detailsPanel,
            redPanel, greenPanel, bluePanel,
            red, green, blue,
            startSpeedPanel, livesPanel, normalLanesPanel, counterflowPanel;

    private JLabel RGBLabel, difficultyLabel, livesLabel, normalLanesLabel, counterflowLanesLabel, startSpeedLabel,
            difficulty, lives, normalLanes, counterflowLanes, startSpeed;

    private JSlider redSlider, greenSlider, blueSlider;
    private JButton driveButton;

    private Car selectedCar;
    private int selectedGear;

    private Phone phone;

    public SceneFrame() {
        //Game Trackers
        score = 0; gameSpeed = 0; gameLives = 3;

        frame_width = 800;
        frame_height = 600;
        stars = "★";

        phone =  new Phone();

        carSelect = new CarSelect();
        gearSelect = new GearSelect();

        startMenuMainPanel = new JPanel();
        leftHalfPanel = new JPanel();
        rightHalfPanel = new JPanel();
        driveButton = new JButton("Drive");

        carSelectPanel = new JPanel();
        gearSelectPanel = new JPanel();

        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Panel.background", Color.decode("#222222"));
        detailsPanel = new JPanel();

        RGBPanel = new JPanel();
        RGBPanel.setBackground(Color.DARK_GRAY);
        redPanel = new JPanel();
        greenPanel = new JPanel();
        bluePanel = new JPanel();
        detailsPanel.setBackground(Color.decode("#222222"));
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

        difficulty = new JLabel("DIFFICULTY", JLabel.CENTER);
        difficulty.setFont(new Font("Helvetica", Font.BOLD, 18));

        UIManager.put("Label.font", new Font("Helvetica", Font.BOLD, 13));
        lives = new JLabel("❤❤❤", JLabel.LEFT);
        normalLanes = new JLabel("| 2 | 1 | ", JLabel.LEFT);
        counterflowLanes = new JLabel("X", JLabel.LEFT);
        startSpeed  = new JLabel("★", JLabel.LEFT);
        startSpeedLabel = new JLabel("Speed ", JLabel.RIGHT);
        normalLanesLabel = new JLabel("Normal Lanes ", JLabel.RIGHT);
        livesLabel = new JLabel("Lives ", JLabel.RIGHT);
        counterflowLanesLabel = new JLabel("Counterflow ", JLabel.RIGHT);

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

        detailsPanel.setLayout(new GridLayout(7, 1));
        detailsPanel.add(phone.drawUpperPhone()); // top phone design
        detailsPanel.add(difficulty, JLabel.CENTER, 1);

        startSpeedPanel.setLayout(new GridLayout(1, 2));
        startSpeedPanel.add(startSpeedLabel);
        startSpeedPanel.add(startSpeed);
        detailsPanel.add(startSpeedPanel);

        normalLanesPanel.setLayout(new GridLayout(1, 2));
        normalLanesPanel.add(normalLanesLabel);
        normalLanesPanel.add(normalLanes);
        detailsPanel.add(normalLanesPanel);

        counterflowPanel.setLayout(new GridLayout(1, 2));
        counterflowPanel.add(counterflowLanesLabel);
        counterflowPanel.add(counterflowLanes);
        detailsPanel.add(counterflowPanel);

        livesPanel.setLayout(new GridLayout(1, 2));
        livesPanel.add(livesLabel);
        livesPanel.add(lives);
        detailsPanel.add(livesPanel);

        detailsPanel.add(phone.drawLowerPhone());

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
                                startSpeed.setText("★ ★ ★ ★");
                                normalLanes.setText("| 1 | 2 |");
                                counterflowLanes.setText("| 1 | 2 |");
                                break;
                            case 5:
                                startSpeed.setText("★ ★ ★ ★ ★");
                                normalLanes.setText("| 1 | 2 | 3 |");
                                counterflowLanes.setText("| 1 | 2 | 3 |");
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
        KeyListener returnToStart = new KeyListener() {
            @Override public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    selectedCar.moveTo(125, 100);
                    selectedCar.changeSize(150);
                    gameTimer.stop();
                    distanceTimer.stop();
                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    setUpGUI();
                    System.out.println("Score: "+ score);
                }
            }
            @Override public void keyReleased(KeyEvent e){}
            @Override public void keyTyped(KeyEvent e){}
        };

        sceneCanvas.setFocusable(true);
        sceneCanvas.addKeyListener(keyListener);
        sceneCanvas.addKeyListener(returnToStart);
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
        score += 1;
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
                System.out.println("Crash!");
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
                System.out.println("Crash!");
                gameLives -= 1;
                c.moveY(-1500);
            }
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