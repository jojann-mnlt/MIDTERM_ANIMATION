import java.awt.*;

public class CityScapeR {
    private double x, y, size, numberOfAdditionalLanes;
    private Square sidewalk;
    private Square sand1;
    private Square sand2;
    private Square sand3;
    private Square beach;

    public CityScapeR(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics2D g2d) {
        sidewalk = new Square(x+size*1.2, y, 86, 4000, Color.LIGHT_GRAY);
        sidewalk.draw(g2d);
        double sideWalkX = sidewalk.getX(), sideWalkY = sidewalk.getY();
        for (int i = 0; i < 4000; i += 100) {
            new Line(sideWalkX, sideWalkY, sideWalkX+86, sideWalkY, 1, Color.BLACK).draw(g2d);
            sideWalkY += 100;
        }
    }
}
