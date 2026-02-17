import java.awt.*;
import java.awt.geom.*;

public class SportsCar extends Car {
    private double x, y, width, height, size;
    private Color color;
    private Square body;
    private FreeFormQuadrilateral hood;

    public SportsCar(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.size = size;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
    }
}
