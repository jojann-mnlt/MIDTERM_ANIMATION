/**
This interface describes a basic drawable object. It contains methods
which all drawing objects should implement, especially the draw() method
for rendering.

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

public interface DrawingObject {
    void draw(Graphics2D g2d);

    double getX();
    double getY();

    void moveX(double amount);
    void moveY(double amount);
}
