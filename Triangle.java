/**
This class describes a basic triangle object. This class accepts
a width and a height, which are used to calculate the third point
of the triangle.

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
import java.awt.geom.*;

public class Triangle implements DrawingObject {
    private double x, y, width, height;
    private Color color;

    public Triangle(double x, double y, double width, double height, Color color){
        /** Instantiates the fields of the triangle. */
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void draw(Graphics2D g2d) {
        /** Renders the triangle. */
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(x, y+height);
        triangle.lineTo(x+width, y+height);
        triangle.lineTo((x+x+width)/2, y);
        triangle.closePath();
        g2d.setColor(color);
        g2d.fill(triangle);
    }
    /** Allow for access to private fields and to change position. */
    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}