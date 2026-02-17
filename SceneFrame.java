// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SceneFrame {
    private JFrame frame;
    private int frame_width, frame_height; 
    public SceneFrame(){
        frame = new JFrame();
        frame_width = 800;
        frame_height = 600;
    }
    public void setUpGUI(){
        Container cp = frame.getContentPane();
        frame.setTitle("Midterm Project - Buenaventura - Manulat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}