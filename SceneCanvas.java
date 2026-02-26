/**
This canvas holds all the drawing objects used in the game. It 
has methods to access the traffic system and the roads to allow
for posistion manuipulation.

@author Jacob L. Buenventura (250930)
@author Johann Karol Benedict O. Manulat (253729)
@version February 26, 2026

We have not discussed the Java language code in our program
with anyone other than my instructor or the teaching assistants
assigned to this course.

WE have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.

If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of our program.
*/
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
