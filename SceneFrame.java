// This is the base class for the GUI. You can make any additions as you wish. Don't delete anything.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SceneFrame extends JFrame {
    private int frame_width, frame_height;

    public SceneFrame(){
        frame_width = 800;
        frame_height = 600;
    }

    public void setUpGUI(){
        Container cp = getContentPane();

        setTitle("Midterm Project - Buenaventura - Manulat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setVisible(true);
    }
}