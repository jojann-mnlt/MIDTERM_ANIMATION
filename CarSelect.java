import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class CarSelect extends JComponent {

    private Sedan sedan;

    public CarSelect() {
        sedan = new Sedan(0, 0, 75, Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setPreferredSize(new Dimension(400, 300));
        g2d.setColor(Color.decode("#00bf63"));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        sedan.draw(g2d);
    }

    public Sedan getCar() { return sedan; }
}
