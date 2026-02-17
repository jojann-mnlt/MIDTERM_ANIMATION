import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
public class GearSelect extends JComponent {
    private GearShifter gears;
    public GearSelect(){
        gears = new GearShifter(0, 0, 0, Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        gears.draw(g2d);
    }
}