import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class SceneCanvas extends JComponent {
    private double width, height;
    private Car carSelected;

    public SceneCanvas(double width, double height, Car carSelected){
        this.width = width;
        this.height = height;
        this.carSelected = carSelected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints antialiasing = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(antialiasing);

        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(background);
        carSelected.draw(g2d);
    }
}
