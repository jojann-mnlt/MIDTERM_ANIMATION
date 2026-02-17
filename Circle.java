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

    public double getX() {return x;} // Made all the getter methods one line.
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}
