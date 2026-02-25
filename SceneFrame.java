// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;

public class SceneFrame extends JFrame {
    private int frame_width, frame_height;
    private boolean left, right;
    private Timer gameTimer, distanceTimer;
    private AffineTransform original;

    //Game trackers
    private int km;
    private double kph;

    private CarSelect carSelect;
    private GearSelect gearSelect;
    private SceneCanvas sceneCanvas;

    private JPanel startMenuMainPanel, leftHalfPanel, rightHalfPanel,
            RGBPanel, carSelectPanel, gearSelectPanel, detailsPanel,
            redPanel, greenPanel, bluePanel,
            red, green, blue;

    private JLabel RGBLabel, difficultyLabel, livesLabel, lanesLabel, startSpeedLabel,
            difficulty, lives, lanes, startSpeed;

    private JSlider redSlider, greenSlider, blueSlider;
    private JButton driveButton;

    private Car selectedCar;
    private int selectedGear;

    public SceneFrame() {
        km = 1; kph = 0;
        frame_width = 800;
        frame_height = 600;

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
        labels.add(difficulty = new JLabel("★", JLabel.CENTER));
        labels.add(lives = new JLabel("❤❤❤", JLabel.CENTER));
        labels.add(lanes = new JLabel("Lanes", JLabel.CENTER));
        labels.add(startSpeed = new JLabel("10 kph", JLabel.CENTER));
        labels.add(difficultyLabel = new JLabel("Difficulty", JLabel.CENTER));
        labels.add(startSpeedLabel = new JLabel("Start Speed", JLabel.CENTER));
        labels.add(lanesLabel = new JLabel("Lanes", JLabel.CENTER));
        labels.add(livesLabel = new JLabel("Lives", JLabel.CENTER));

        for (JLabel label : labels) {
            label.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        }

        setUpGUI();
        setUpButtonListeners();
        setUpMouseListeners();
        setUpSliderListeners();
    }

    public void setUpGUI() {
        Container cp = getContentPane();

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
        detailsPanel.add(difficultyLabel);
        detailsPanel.add(difficulty, JLabel.CENTER, 1);
        detailsPanel.add(startSpeedLabel);
        detailsPanel.add(startSpeed, JLabel.CENTER, 3);
        detailsPanel.add(livesLabel);
        detailsPanel.add(lives, JLabel.CENTER, 5);
        detailsPanel.add(lanesLabel);
        detailsPanel.add(lanes, JLabel.CENTER, 7);


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
        selectedCar.moveTo((frame_width/2)-37.5, (frame_height/2));
        selectedCar.changeSize(75);
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

                    String stars = "";
                    for (int i = 1; i <= gearSelect.getShifter().getGear(); i++) {
                        stars = stars + "★";

                        switch (i) {
                            case 1:
                                lives.setText("❤❤❤");
                                startSpeed.setText("10 kph");
                                break;
                            case 2:
                                lives.setText("❤❤❤");
                                startSpeed.setText("30 kph");
                                break;
                            case 3, 4:
                                lives.setText("❤❤");
                                startSpeed.setText("50 kph");
                                break;
                            case 5:
                                lives.setText("❤");
                                startSpeed.setText("70 kph");
                                break;
                        }
                        difficulty.setText(stars);
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
                kph = gearSelect.getShifter().getSpeed();
                System.out.println("Car Model: "+ selectedCar.getCarModel());
                System.out.println("Gear Level: "+ selectedGear);
                System.out.println("Starting Speed: "+ kph+" kph");
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
                    dispose();
                    getContentPane().removeAll();
                    setUpGUI();
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

        distanceTimer = new Timer(16, e-> drive(kph));
        distanceTimer.start();

    }

    public void drive(double speed){
        double ydelta = (((speed*40000)/60)/60)/60;
        Road road = sceneCanvas.getRoad();
        road.moveY(ydelta);
        if (road.getY() >= 0){
            road.moveY(-39400);
            System.out.println(km+ " km");
            km++;
        }
    }

    public void movement() {
        int amount = 5;
        Road road = sceneCanvas.getRoad();
        double maxRight = road.getMaxXR();
        double maxLeft = road.getMaxXL();

        if (left && selectedCar.getX() != maxLeft) {
            road.moveX(amount);
            selectedCar.rotate(-15);
        }
        else if (right && selectedCar.getX() != maxRight) {
            road.moveX(-amount);
            selectedCar.rotate(15);
        }
        else if ((!left && !right) || (selectedCar.getX() == maxLeft || selectedCar.getX() == maxRight)) {
            selectedCar.rotate(0);
        }

        sceneCanvas.repaint();
    }
}