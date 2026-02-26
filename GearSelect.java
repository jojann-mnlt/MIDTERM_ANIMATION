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