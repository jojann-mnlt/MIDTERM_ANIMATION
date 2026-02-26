import java.awt.*;

public class CityScapeR {
    private double x, y, size;
    private Square sidewalk;
    private Square sand1;
    private Square sand2;
    private Square shoreline;
    private Square sea1, sea2, sea3;

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

        sand1 = new Square(sidewalk.getX()+size*.863, y, 71.2, 4000, Color.decode("#d9d9c1"));
        sand1.draw(g2d);
        sand2 = new Square(sand1.getX()+size*.469, y, 63.2, 4000, Color.decode("#dae2cd"));
        sand2.draw(g2d);
        shoreline = new Square(sand2.getX()+size*.573, y, 22.7, 4000, Color.decode("#c4e3dd"));
        shoreline.draw(g2d);
        sea1 = new Square(shoreline.getX()+size*.16, y, 44.2, 4000, Color.decode("#a9dade"));
        sea1.draw(g2d);
        sea2 = new Square(sea1.getX()+size*.397, y, 50.2, 4000, Color.decode("#80ccdc"));
        sea2.draw(g2d);
        sea3 = new Square(sea2.getX()+size*.477, y, 145.7, 4000, Color.decode("#51c0de"));
        sea3.draw(g2d);
    }

}
