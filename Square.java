import java.awt.*;
import java.awt.geom.*;

public class Square implements DrawingObject {
    private double x, y, width, height;
    private Color color;

    public Square(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    public void draw(Graphics2D g2d) {
        Rectangle2D.Double square = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(color);
        g2d.fill(square);
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}

    // Allow for square to be used as a hitbox
    public double getWidth() {return width;}
    public double getHeight() {return height;}
}
