import java.awt.*;
import java.awt.geom.*;

public class Triangle implements DrawingObject {
    private double x, y, width, height;
    private Color color;

    public Triangle(double x, double y, double width, double height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void draw(Graphics2D g2d) {
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(x, y+height);
        triangle.lineTo(x+width, y+height);
        triangle.lineTo((x+x+width)/2, y);
        triangle.closePath();
        g2d.setColor(color);
        g2d.fill(triangle);
    }
    public double getX() {return x;} // Made all the getter methods one line
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}