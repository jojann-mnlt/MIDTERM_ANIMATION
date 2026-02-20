import java.awt.*;
import java.awt.geom.*;

public class SportsCar extends Car {
    private double x, y, width, height, size;
    private Color color;
    private Square body, hood;

    public SportsCar(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size*2.9;
        this.size = size;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {

        body = new Square(x, y, width, height, color);
        hood = new Square(x, y, width, height, color);

        g2d.setColor(color);
        body.draw(g2d);
        hood.draw(g2d);
    }
}
