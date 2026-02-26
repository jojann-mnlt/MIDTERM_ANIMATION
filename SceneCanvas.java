import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SceneCanvas extends JComponent {
    private double width, height;
    private Car selectedCar;
    private int difficulty;
    private Road road;
    private TrafficSystem trafficSystem;
    private ArrayList<DrawingObject> gameSystems;

    public SceneCanvas(double width, double height, Car selectedCar, int selectedGear){
        gameSystems = new ArrayList<DrawingObject>();
        this.width = width;
        this.height = height;
        this.selectedCar = selectedCar;
        difficulty = selectedGear;
        road = new Road(340, -3400, difficulty);
        trafficSystem = new TrafficSystem(362.5, -1000, selectedGear);
        gameSystems.add(this.road);
        gameSystems.add(this.trafficSystem);
        gameSystems.add(this.selectedCar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldTransform = g2d.getTransform();
        g2d.setTransform(oldTransform);

        RenderingHints AA = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(AA);

        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.decode("#222222"));
        g2d.fill(background);
        for (DrawingObject o : gameSystems){o.draw(g2d);}
    }

    public Road getRoad(){return road;}
    public TrafficSystem getTraffic(){return trafficSystem;}
}
