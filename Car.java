import java.awt.*;
import java.awt.geom.*;
public abstract class Car {
    private double x, y, width, height, size;
    private Color color;
    private Square body;
    public void draw(Graphics2D g2d){body.draw(g2d);}
    public double getX(){return x;}
    public double getY(){return y;}
    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
    public void changeColor(Color newColor){color = newColor;}
}
