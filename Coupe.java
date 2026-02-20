import java.awt.*;

public class Coupe extends Car {
    private double x, y, width, height, size;
    private Color color;
    private

    public void draw(Graphics2D g2d) {

    }

    public double getX(){return x;}
    public double getY(){return y;}
    public Color getColor(){return color;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}

    public void changeColor(Color newColor){color = newColor;}
}
