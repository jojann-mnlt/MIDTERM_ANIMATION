import java.awt.*;

public interface DrawingObject {
    void draw(Graphics2D g2d);

    double getX();
    double getY();

    void moveX(double amount);
    void moveY(double amount);
}
