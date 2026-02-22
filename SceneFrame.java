// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class SceneFrame extends JFrame {
    private int frame_width, frame_height;

    private CarSelect carSelect;
    private GearSelect gearSelect;
    private SceneCanvas sceneCanvas;

    private JPanel startMenuMainPanel, leftHalfPanel, rightHalfPanel, RGBPanel, carSelectPanel, selectButtonPanel;

    private JPanel redPanel, greenPanel, bluePanel;
    private JPanel red, green, blue;
    private JSlider redSlider, greenSlider, blueSlider;

    private JButton driveButton, selectLeftButton, selectRightButton, selectCarButton;

    public SceneFrame() {
        frame_width = 800;
        frame_height = 600;

        carSelect = new CarSelect();
        gearSelect = new GearSelect();

        startMenuMainPanel = new JPanel();
        leftHalfPanel = new JPanel();
        rightHalfPanel = new JPanel();
        driveButton = new JButton("Drive");

        carSelectPanel = new JPanel();
        selectButtonPanel = new JPanel();

        selectLeftButton = new JButton("<");
        selectRightButton = new JButton(">");
        selectCarButton = new JButton("Select Car");

        RGBPanel = new JPanel();
        redPanel = new JPanel();
        greenPanel = new JPanel();
        bluePanel = new JPanel();

        redSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        greenSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        blueSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);

        red = new JPanel();
        green = new JPanel();
        blue = new JPanel();


        ArrayList<JSlider> sliders = new ArrayList<>();

        sliders.add(greenSlider);
        sliders.add(blueSlider);
        sliders.add(redSlider);

        for (JSlider slider : sliders) {
            slider.setPaintTrack(true);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.setMajorTickSpacing(255);
            slider.setMinorTickSpacing(5);
        }
    }

    public void setUpGUI() {
        Container cp = getContentPane();

        // left half
        leftHalfPanel.setLayout(new GridLayout(2, 1));
        carSelectPanel.setLayout(new BorderLayout());
        carSelectPanel.add(carSelect);
        selectButtonPanel.setLayout(new BorderLayout());
        selectButtonPanel.add(selectLeftButton, BorderLayout.WEST);
        selectButtonPanel.add(selectRightButton, BorderLayout.EAST);
        selectButtonPanel.add(selectCarButton);
        carSelectPanel.add(carSelect, BorderLayout.CENTER);
        carSelectPanel.add(selectButtonPanel, BorderLayout.SOUTH);
        leftHalfPanel.add(carSelectPanel);

        RGBPanel.setLayout(new GridLayout(4, 1));

        // RGB Panel, 1st Cell
        JLabel RGBLabel = new JLabel("SELECT CAR COLOR", JLabel.CENTER);
        RGBLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
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

        leftHalfPanel.add(RGBPanel);

        // right half
        rightHalfPanel.setBackground(Color.CYAN);
        rightHalfPanel.setLayout(new GridLayout(2, 1));
        //rightHalfPanel.add(new JLabel("Title here", JLabel.CENTER));
        rightHalfPanel.add(gearSelect);
        rightHalfPanel.add(new JLabel("Details", JLabel.CENTER));

        startMenuMainPanel.setLayout(new GridLayout(1, 2));

        startMenuMainPanel.add(leftHalfPanel); startMenuMainPanel.add(rightHalfPanel); // adds the left and right half
        cp.add(startMenuMainPanel);
        cp.add(driveButton, BorderLayout.SOUTH);

        setTitle("Midterm Project - Buenaventura - Manulat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        pack();
        setVisible(true);
    }

    private void setUpGameGUI() {
        Container cp = getContentPane();
        cp.repaint();
        cp.add(sceneCanvas = new SceneCanvas(frame_width, frame_height));

        setTitle("Midterm Project - Buenaventura - Manulat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frame_width, frame_height));
        setVisible(true);
    }

    public void setUpSliderListeners() {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Car car = carSelect.getCar();
                JSlider slider = (JSlider) e.getSource();
                int value = slider.getValue();
                car.changeColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));// updates
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
                    gearSelect.repaint();
                }
            }
            //Unused methods
            @Override public void mouseEntered(MouseEvent e){}
            @Override public void mouseExited(MouseEvent e){}
            @Override public void mousePressed(MouseEvent e){}
            @Override public void mouseReleased(MouseEvent e){}
        };
        gearSelect.addMouseListener(mouseListener);
    }

    public void setUpButtonListeners() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == selectCarButton || e.getSource() == selectRightButton) {
                    System.out.println("Changing car...");
                    carSelect.changeVehicle(1);
                } else if (e.getSource() == selectLeftButton) {
                    System.out.println("Changing left...");
                    carSelect.changeVehicle(-1);
                }
                carSelect.repaint();
            }
        };

        ActionListener startButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting...");
                getContentPane().removeAll();
                setUpGameGUI();
                revalidate();
                repaint();
            }
        };

        selectCarButton.addActionListener(actionListener);
        selectLeftButton.addActionListener(actionListener);
        selectRightButton.addActionListener(actionListener);
        driveButton.addActionListener(startButtonListener);
    }
}