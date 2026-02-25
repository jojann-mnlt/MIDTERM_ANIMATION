import java.awt.*;
public class TrafficSystem implements DrawingObject{
    private Car[] Traffic;
    private double x, y;
    private Color highwayCarColor;
    public TrafficSystem(double x, double y, int difficulty){
        this.x = x;
        this.y = y;
        highwayCarColor = Color.WHITE;
        Traffic = new Car[difficulty];
        for (int i = 0; i < difficulty; i++){
            Traffic[i] = new Sedan(x-(120*i), y, 75, 180, highwayCarColor);
        }
    }
    @Override
    public void draw(Graphics2D g2d){
        for (Car c : Traffic){
            c.draw(g2d);
        }
    }

    public double getX(){return x;}
    public double getY(){return y;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
}