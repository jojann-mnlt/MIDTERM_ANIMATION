/**
This class describes a basic line. It accepts 2 pairs of x and y
coordinates to create a line.

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

public class Line implements DrawingObject {
    private double x, y, x2, y2;
    private int size;
    private Color color;

    public Line(double x, double y, double x2, double y2, int size, Color color) {
        /** Instantiates the end points of the line. */
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics2D g2d) {
        /** Renders the line. */
        Line2D.Double line = new Line2D.Double(x, y, x2, y2);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(size));
        g2d.draw(line);
    }

    /** Allow for access to private fields and to change position. */
    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}
