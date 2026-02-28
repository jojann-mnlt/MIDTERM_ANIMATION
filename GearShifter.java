/**
This class renders the base of the Gear Shifter. This also sets
the difficulty and speed of the game through the gear level and 
speed fields.

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

public class GearShifter implements DrawingObject{
    private Square shifterBase, shifterBaseOutline, gap, gap12, gap34, gap5;
    private int gearLevel;
    private double x, y, speed;
    private int size;
    private Color baseColor, baseOutline, gapColor;
    public GearShifter(double x, double y, int size){
        /** Instantiates the fields of this class. */
        this.x = x;
        this.y = y;
        this.size = size;
        speed = 80;
        gearLevel = 1;
        baseOutline = Color.decode("#5f5f5f");
        baseColor = Color.decode("#000000");
        gapColor = Color.decode("#292929");
    }
    public void draw(Graphics2D g2d){
        /** Instantiates the drawing objects. */
        shifterBaseOutline = new Square(x, y, size*2, size*2, baseOutline);
        shifterBase = new Square(x+size*0.1, y+size*0.1, size*1.8, size*1.8, baseColor);
        gap = new Square(x+size*0.3, y+size*0.9, size*1.4, size*0.2, gapColor);
        gap12 = new Square(x+size*0.3, y+size*0.3, size*0.2, size*1.4, gapColor);
        gap34 = new Square(x+size*0.9, y+size*0.3, size*0.2, size*1.4, gapColor);
        gap5 = new Square(x+size*1.5, y+size*0.3, size*0.2, size*0.7, gapColor);
        /** Renders the drawing objects. */
        shifterBaseOutline.draw(g2d);
        shifterBase.draw(g2d);
        gap.draw(g2d);
        gap12.draw(g2d);
        gap34.draw(g2d);
        gap5.draw(g2d);
    }
    public void changeGear(int gear){
        /** Changes the gear level */
        gearLevel = gear;
        switch (gear){
            case 1:
                speed = 80;
                break;
            case 2:
                speed = 90;
                break;
            case 3, 4, 5:
                speed = 100;
                break;
        }
    }
    /** Allow for access to private fields. */
    public double getSpeed() {return speed;}
    public int getGear() {return gearLevel;}
    public int getSize() {return size;}
    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}