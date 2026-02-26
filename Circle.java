/**
This class defines a basic circle object. It accepts position, size,
and color values to easily create a circle/ellipse.

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

public class Circle implements DrawingObject {
    private double x, y, width, height;
    private Color color;

    public Circle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    public void draw(Graphics2D g2d) {
        Ellipse2D.Double square = new Ellipse2D.Double(x, y, width, height);
        g2d.setColor(color);
        g2d.fill(square);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void moveX(double amount) { x += amount; }
    public void moveY(double amount) { y += amount; }
}
