import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
public class GearSelect extends JComponent {
    private GearShifter gears;
    public GearSelect(){
        gears = new GearShifter(50, 0, 75, Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints AA = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2d.addRenderingHints(AA);

        setPreferredSize(new Dimension(400, 300));
        gears.draw(g2d);
    }
    public GearShifter getShifter(){return gears;}
}