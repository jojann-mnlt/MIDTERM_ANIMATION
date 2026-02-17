import java.awt.*;
import java.awt.geom.*;
public class GearShifter {
    private Circle gearKnob, gearKnobOutline;
    private Square gap, gap12, gap34, gap5;
    private int gearLevel;
    private double x, y;
    private int size;
    private Color primary, secondary, accent;
    public GearShifter(double x, double y, int size, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        primary = color;
        secondary = Color.BLACK;
        accent = Color.decode("#292929");
    }
    public void draw(Graphics2D g2d){
        // Instantiating gear shifter parts
        gap = new Square(x+size*7/18, y+size*23/18, size*2, size*2/9, accent);
        gap12 = new Square(x+size*7/18, y+size*0.5, size*2/9, size*16/9, accent);
        gap34 = new Square(x+size*23/18, y+size*0.5, size*2/9, size*16/9, accent);
        gap5 = new Square(x+size*13/6, y+size*0.5, size*2/9, size, accent);
        gearKnobOutline = new Circle(x, y, size, size, secondary);
        gearKnob = new Circle(x+size*1/9, y+size*1/9, size*7/9, size*7/9, primary);
        // Rendering gear shifter parts
        gap.draw(g2d);
        gap12.draw(g2d);
        gap34.draw(g2d);
        gap5.draw(g2d);
        gearKnobOutline.draw(g2d);
        gearKnob.draw(g2d);
    }
}