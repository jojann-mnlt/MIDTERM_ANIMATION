import java.awt.*;
public abstract class Car implements DrawingObject {
    private double x, y;
    private Color color;
    private String carModel;

    public abstract void draw(Graphics2D g2d);

    public double getX(){return x;}
    public double getY(){return y;}
    public Color getColor(){return color;}
    public String getCarModel(){return carModel;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}

    public void changeColor(Color newColor){color = newColor;}
}
