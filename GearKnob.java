/**
This class is a compound shape describing a GearKnob object.
It uses basic shapes to form a compound shape, using the size
to properly place objects relative to each other.

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
