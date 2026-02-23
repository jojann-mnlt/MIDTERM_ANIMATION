import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class SceneCanvas extends JComponent {
    private double width, height;
    private Car selectedCar;
    private int difficulty;
    private Road road;

    public SceneCanvas(double width, double height, Car selectedCar, int selectedGear){
        this.width = width;
        this.height = height;
        this.selectedCar = selectedCar;
        difficulty = selectedGear;

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints AA = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(AA);

        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.decode("#399c00"));
        g2d.fill(background);
        selectedCar.draw(g2d);

        road = new Road(340, 0, difficulty);
        road.draw(g2d);
        selectedCar.draw(g2d);
    }
    public Road getRoad(){return road;}
}
