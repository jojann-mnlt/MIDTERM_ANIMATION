/**
This class renders the game's scenic backdrop on the right. This
class renders a beach scene.

@author Jacob L. Buenventura (250930)
@author Johann Karol Benedict O. Manulat (253729)
@version February 26, 2026

We have not discussed the Java language code in our program
with anyone other than my instructor or the teaching assistants
assigned to this course.

WE have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.

If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of our program.
*/
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
