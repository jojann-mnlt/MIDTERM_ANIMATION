// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SceneFrame extends JFrame {
    private int frame_width, frame_height;
    private CarSelect carSelect;
    private JPanel startMenuMainPanel, leftHalfPanel, rightHalfPanel, RGBPanel;
    private ArrayList<JPanel> RGBCellPanels;
    private JPanel redPanel, greenPanel, bluePanel;
    private JButton driveButton;
    private JSlider redSlider, greenSlider, blueSlider;

    public SceneFrame() {
        frame_width = 800;
        frame_height = 600;

        carSelect = new CarSelect();

        startMenuMainPanel = new JPanel();
        leftHalfPanel = new JPanel();
        rightHalfPanel = new JPanel();
        driveButton = new JButton("Drive");

        RGBPanel = new JPanel();
        redPanel = new JPanel();
        greenPanel = new JPanel();
        bluePanel = new JPanel();

        redSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        greenSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        blueSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        ArrayList<JSlider> sliders = new ArrayList<>();

        RGBCellPanels = new ArrayList<>();
        RGBCellPanels.add(redPanel);
        RGBCellPanels.add(greenPanel);
        RGBCellPanels.add(bluePanel);

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
        leftHalfPanel.add(carSelect);

        RGBPanel.setLayout(new GridLayout(4, 1));

        // RGB Panel, 1st Cell
        JLabel RGBLabel = new JLabel("SELECT CAR COLOR", JLabel.CENTER);
        RGBLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        RGBPanel.add(RGBLabel);

        // RGB Panel, 2nd Cell
        RGBPanel.add(redSlider);
        RGBPanel.add(greenSlider);
        RGBPanel.add(blueSlider);
        leftHalfPanel.add(RGBPanel);

        // right half
        rightHalfPanel.setBackground(Color.CYAN);
        rightHalfPanel.setLayout(new GridLayout(3, 1));
        rightHalfPanel.add(new JLabel("Title here", JLabel.CENTER));
        rightHalfPanel.add(new JLabel("Insert GearStick Here", JLabel.CENTER));
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

    public void setUpSliderListeners() {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Car car = carSelect.getCar();
                JSlider slider = (JSlider) e.getSource();
                int value = slider.getValue();
                car.changeColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue())); // updates
                carSelect.repaint();
            }
        };
        redSlider.addChangeListener(changeListener);
        greenSlider.addChangeListener(changeListener);
        blueSlider.addChangeListener(changeListener);
    }

    public void setUpButtonListeners() {
    }
}