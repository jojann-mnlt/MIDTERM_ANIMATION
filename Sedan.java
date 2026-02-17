import java.awt.*;
import java.awt.geom.*;
public class Sedan implements DrawingObject{
    private double x, y, width, height, size;
    private Color color;
    private Square body, window, roof;
    public Sedan(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size*2.4;
        this.color = color;
        body = new Square(x, y, width, height, color);
        window = new Square(x+size*0.1, y+size*.6, width*0.8, height*7/12, Color.BLACK);
        roof = new Square(x+size*0.2, y+size, width*0.6, height*1/3, color);
    }
    public void draw(Graphics2D g2d){
        body.draw(g2d);
        window.draw(g2d);
        roof.draw(g2d);
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
    public void changeColor(Color newColor){color = newColor;}
}