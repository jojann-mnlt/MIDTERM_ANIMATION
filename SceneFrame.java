// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SceneFrame extends JFrame {
    private int frame_width, frame_height;
    private SceneCanvas sceneCanvas;
    private JPanel startMenuMainPanel, LeftHalfPanel, RightHalfPanel, RGBPanel;
    private JButton driveButton;
    private JSlider RedSlider,  GreenSlider, BlueSlider;

    public SceneFrame() {
        frame_width = 800;
        frame_height = 600;

        sceneCanvas = new SceneCanvas();

        startMenuMainPanel = new JPanel();
        LeftHalfPanel = new JPanel();
        RightHalfPanel = new JPanel();
        driveButton = new JButton("Drive");

        RGBPanel = new JPanel();
        RedSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        GreenSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        BlueSlider = new JSlider(JSlider.HORIZONTAL, 255, 100);
        ArrayList<JSlider> sliders = new ArrayList<>();
        sliders.add(GreenSlider);
        sliders.add(BlueSlider);
        sliders.add(RedSlider);

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
        LeftHalfPanel.setLayout(new GridLayout(2, 1));
        LeftHalfPanel.add(sceneCanvas);

        RGBPanel.setLayout(new GridLayout(3, 1));
        RGBPanel.add(RedSlider);
        RGBPanel.add(GreenSlider);
        RGBPanel.add(BlueSlider);
        LeftHalfPanel.add(RGBPanel);

        // right half
        RightHalfPanel.setBackground(Color.CYAN);
        RightHalfPanel.setLayout(new GridLayout(3, 1));
        RightHalfPanel.add(new JLabel("Title here", JLabel.CENTER));
        RightHalfPanel.add(new JLabel("Insert GearStick Here", JLabel.CENTER));
        RightHalfPanel.add(new JLabel("Details", JLabel.CENTER));

        startMenuMainPanel.setLayout(new GridLayout(1, 2));

        startMenuMainPanel.add(LeftHalfPanel); startMenuMainPanel.add(RightHalfPanel); // adds the left and right half
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
                Car car = sceneCanvas.getCar();
                JSlider slider = (JSlider) e.getSource();
                int value = slider.getValue();
                car.changeColor(new Color(RedSlider.getValue(), GreenSlider.getValue(), BlueSlider.getValue())); // updates
                sceneCanvas.repaint();
            }
        };
        RedSlider.addChangeListener(changeListener);
        GreenSlider.addChangeListener(changeListener);
        BlueSlider.addChangeListener(changeListener);
    }

    public void setUpButtonListeners() {
    }
}