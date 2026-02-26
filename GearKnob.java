import java.awt.*;

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
        gearKnobOutline = new Circle(x, y, size*0.9, size*0.9, knobOutline);
        gearKnob = new Circle(x+size*0.1, y+size*0.1, size*0.7, size*0.7, knobMain);
        gearStick = new Square(x+size*0.4, y+size*0.45, size*0.1, size*0.6, stickColor);
        gearStick.draw(g2d);
        gearKnobOutline.draw(g2d);
        gearKnob.draw(g2d);
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}
