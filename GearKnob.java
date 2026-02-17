import java.awt.*;
import java.awt.geom.*;
public class GearKnob implements DrawingObject{
    private Circle gearKnob, gearKnobOutline;
    private Square gearStick;
    private double x, y;
    private int size;
    private Color knobMain, knobOutline, stickColor;
    public GearKnob(double x, double y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        knobMain = Color.WHITE;
        knobOutline = Color.BLACK;
        stickColor = Color.decode("#585858");
    }
    public void draw(Graphics2D g2d){
        gearStick = new Square(x+size*4/9, y+size*5/6, size*1/9, size*1/3, stickColor);
        gearKnobOutline = new Circle(x, y, size, size, knobOutline);
        gearKnob = new Circle(x+size*1/9, y+size*1/9, size*7/9, size*7/9, knobMain);
        gearStick.draw(g2d);
        gearKnobOutline.draw(g2d);
        gearKnob.draw(g2d);
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}
