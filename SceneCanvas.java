import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class SceneCanvas extends JComponent {
    private double width, height;

    public SceneCanvas(double width, double height){
        this.width = width;
        this.height = height;
    }

    public SceneCanvas() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.GREEN);
        g2d.fill(background);
    }
}
