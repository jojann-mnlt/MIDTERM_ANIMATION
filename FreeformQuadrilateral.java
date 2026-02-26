import java.awt.*;
import java.awt.geom.Path2D;

public class FreeformQuadrilateral implements DrawingObject {
    double x1, y1, x2, y2, x3, y3, x4, y4;
    Color color;

    public FreeformQuadrilateral(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.color = color;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        Path2D.Double quad = new Path2D.Double();
        quad.moveTo(x1, y1);
        quad.lineTo(x2, y2);
        quad.lineTo(x3, y3);
        quad.lineTo(x4, y4);
        quad.closePath();
        g2d.draw(quad);
    }

    public void fill(Graphics2D g2d) {
        g2d.setColor(color);
        Path2D.Double quad = new Path2D.Double();
        quad.moveTo(x1, y1);
        quad.lineTo(x2, y2);
        quad.lineTo(x3, y3);
        quad.lineTo(x4, y4);
        quad.closePath();
        g2d.fill(quad);
    }

    public double getX() {return x1;}
    public double getY() {return y1;}
    public void moveX(double amount) {x1 += amount;}
    public void moveY(double amount) {y1 += amount;}
    public void changeColor(Color color) {this.color = color;}
}
