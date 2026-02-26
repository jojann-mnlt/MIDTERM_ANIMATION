/**
This class renders a gear shifter. This allows for players to
cycle through gears which serve as the game's difficulty.

@author Jacob L. Buenventura (250930)
@author Johann Karol Benedict O. Manulat (253729)
@version February 26, 2026

We have not discussed the Java language code in our program
with anyone other than my instructor or the teaching assistants
assigned to this course.

WE have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.

If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of our program.
*/
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GearSelect extends JComponent {
    private GearShifter gears;
    private GearKnob knob;
    private ArrayList<DrawingObject> shifterParts;

    public GearSelect(){
        shifterParts = new ArrayList<DrawingObject>();
        gears = new GearShifter(30, 90, 70);
        knob = new GearKnob(26.5, 44.5, 70);
        shifterParts.add(gears);
        shifterParts.add(knob);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode("#292929"));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.WHITE);

        setPreferredSize(new Dimension(200, 300));
        RenderingHints AA = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(AA);

        for (DrawingObject p : shifterParts){
            p.draw(g2d);
        }
    }
    public GearShifter getShifter(){return gears;}
    public GearKnob getKnob(){return knob;}
}