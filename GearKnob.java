import java.awt.*;
import java.awt.geom.*;
public class GearKnob implements DrawingObject{
    private Circle gearKnob, gearKnobOutline;
    private Square gearStick;
    private double x, y;
    private int size;
    private Color primary, secondary;
    public GearKnob(double x, double y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        primary = Color.WHITE;
        secondary = Color.BLACK;
    }
    public void draw(Graphics2D g2d){
        gearStick = new Square(x+size*4/9, y+size*61/90, size*1/9, size*1/3, primary);
        gearKnobOutline = new Circle(x, y, size, size, secondary);
        gearKnob = new Circle(x+size*1/9, y+size*1/9, size*7/9, size*7/9, primary);
        gearStick.draw(g2d);
        gearKnobOutline.draw(g2d);
        gearKnob.draw(g2d);
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}
