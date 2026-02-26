import java.awt.*;

public class CityScapeL {
    private double x, y, size, numberOfAdditionalLanes;
    private Square sidewalk;
    private Square grass1, grass2, grass3, grass4, grass5;

    public CityScapeL(double x, double y, double size, double numberOfAdditionalLanes) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.numberOfAdditionalLanes = numberOfAdditionalLanes;
    }

    public void draw(Graphics2D g2d) {
        sidewalk = new Square(x - 120 * (numberOfAdditionalLanes + .7), y, 86, 4000, Color.LIGHT_GRAY);
        sidewalk.draw(g2d);
        double sideWalkX = sidewalk.getX(), sideWalkY = sidewalk.getY();

        for (int i = 0; i < 4000; i += 100) {
            new Line(sideWalkX, sideWalkY, sideWalkX + 86, sideWalkY, 1, Color.BLACK).draw(g2d);
            sideWalkY += 100;
        }
        grass1 = new Square(sideWalkX - 100 * .659, y, 71.3, 4000, Color.decode("#136d15"));
        grass1.draw(g2d);
        grass2 = new Square(grass1.getX() - 100 * .632, y, 63.2, 4000, Color.decode("#117c13"));
        grass2.draw(g2d);
        grass3 = new Square(grass2.getX()-100*.646, y,65,  4000, Color.decode("#138510"));
        grass3.draw(g2d);
        grass4 = new Square(grass3.getX()-100*.744, y, 76.4, 4000, Color.decode("#41980a"));
        grass4.draw(g2d);
        grass5 = new Square(grass4.getX()-100, y, 76.4, 4000, Color.decode("#5ba22f"));
        grass5.draw(g2d);
    }

}
