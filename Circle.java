import java.awt.*;
import java.awt.geom.*;

public class Circle implements DrawingObject {
    private double x, y, width, height, size;
    private Color color;

    public Circle(double x, double y, double width, double height, double size, Color color) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
        this.size = size;

        this.color = color;

    }


    public void draw(Graphics2D g2d) {
        Ellipse2D.Double square = new Ellipse2D.Double(x*size, y*size, width*size, height*size);
        g2d.setColor(color);
        g2d.fill(square);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void moveX(double amount) {
        x += amount;
    }

    public void moveY(double amount) {
        y += amount;
    }
}
