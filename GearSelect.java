import javax.swing.*;
import java.awt.*;
public class GearSelect extends JComponent {
    private GearShifter gears;
    private GearKnob knob;
    private Square gearStickArea;
    private Square gearStickAreaOuter;

    public GearSelect(){
        gears = new GearShifter(125, 25, 50, Color.WHITE);
        knob = new GearKnob(125, 0, 50);
        gearStickArea = new Square(129, 30, 130, 130, Color.BLACK);
        gearStickAreaOuter = new Square(120, 20, 150, 150, Color.GRAY);

    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode("#222222"));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.WHITE);

        RenderingHints AA = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2d.addRenderingHints(AA);

        setPreferredSize(new Dimension(400, 300));
        gearStickAreaOuter.draw(g2d);
        gearStickArea.draw(g2d);
        gears.draw(g2d);
        knob.draw(g2d);
    }
    public GearShifter getShifter(){return gears;}
    public GearKnob getKnob(){return knob;}
}