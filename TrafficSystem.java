import java.awt.*;
import java.util.*;
public class TrafficSystem implements DrawingObject{
    private Car main;
    private ArrayList<Car> Traffic;
    private double x, y;
    private Color highwayCarColor;
    private int difficulty;
    public TrafficSystem(double x, double y, int difficulty){
        this.x = x;
        this.y = y;
        this.difficulty = difficulty;
        Traffic = new ArrayList<Car>();
        highwayCarColor = Color.WHITE;
    }
    @Override
    public void draw(Graphics2D g2d){
        RenderingHints AA = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(AA);
        
        main = new Sedan(x, y, 75, 0, highwayCarColor);
        main.draw(g2d);
        switch (difficulty){
            case 1:
                Traffic.add(new Sedan(x-120, y, 75, 0, highwayCarColor)); break;
            case 2: 
                Traffic.add(new Sedan(x-120, y, 75, 0, highwayCarColor));
                Traffic.add(new Sedan(x-(120*2), y, 75, 0, highwayCarColor)); break;
            case 3:
                Traffic.add(new Sedan(x-120, y, 75, 0, highwayCarColor));
                Traffic.add(new Sedan(x-(120*2), y, 75, 0, highwayCarColor));
                Traffic.add(new Sedan(x-(120*3), y, 75, 0, highwayCarColor)); break;
            case 4:
                Traffic.add(new Sedan(x-120, y, 75, 0, highwayCarColor));
                Traffic.add(new Sedan(x-(120*2), y, 75, 0, highwayCarColor));
                Traffic.add(new Sedan(x-(120*3), y, 75, 180, highwayCarColor)); break;
            case 5:
                Traffic.add(new Sedan(x-120, y, 75, 0, highwayCarColor));
                Traffic.add(new Sedan(x-(120*2), y, 75, 0, highwayCarColor));
                Traffic.add(new Sedan(x-(120*3), y, 75, 180, highwayCarColor));
                Traffic.add(new Sedan(x-(120*4), y, 75, 180, highwayCarColor));
                Traffic.add(new Sedan(x-(120*5), y, 75, 180, highwayCarColor)); break;
        }
        for (Car c : Traffic){c.draw(g2d);}
    }

    public double getX(){return x;}
    public double getY(){return y;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
}