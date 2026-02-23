import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class SceneCanvas extends JComponent {
    private double width, height;
    private Car selectedCar;
    private int difficulty;
    private Lane lane1;

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
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(background);
        selectedCar.draw(g2d);

        lane1 = new Lane((width/2)-60, 0, 100, false, false);
        lane1.draw(g2d);
        selectedCar.draw(g2d);
    }
}
