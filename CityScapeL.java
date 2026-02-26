/**
This class renders the game's scenic backdrop on the left. This
class renders a grassy scene.

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

public class CityScapeL {
    private double x, y, numberOfAdditionalLanes;
    private Square sidewalk;
    private Square grass1, grass2, grass3, grass4, grass5;

    /* this is the constructor method for the CityScape */
    public CityScapeL(double x, double y, double numberOfAdditionalLanes) {
        this.x = x;
        this.y = y;
        this.numberOfAdditionalLanes = numberOfAdditionalLanes;
    }

    /* this renders all of the backdrop from the left side of the game map. This consists of five varying shades of green squares. */
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
