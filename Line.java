import java.awt.*;
import java.awt.geom.*;

public class Line implements DrawingObject {
    private double x, y, x2, y2;
    private int size;
    private Color color;

    public Line(double x, double y, double x2, double y2, int size, Color color) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics2D g2d) {
        Line2D.Double line = new Line2D.Double(x, y, x2, y2);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(size));
        g2d.draw(line);
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}
