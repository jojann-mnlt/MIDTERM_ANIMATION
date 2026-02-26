/**
This class renders a single lane. It accepts 2 string arguments which
dictate the line makrings on the road.

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

public class Lane implements DrawingObject{
    private double x, y, size, width, height;
    private String left, right;
    private Color asphaltColor, yellow, white;
    private Square asphalt, yellowLineL, yellowLineR, whiteLineL, whiteLineR;
    private Square[] leftDotted, rightDotted;

    public Lane(double x, double y, double size, String left, String right){
        /** Instantiates the fields of this class. */
        this.x = x;
        this.y = y;
        this.size = size;
        width = size*1.2;
        height = 4000;
        this.left = left;
        this.right = right;
        asphaltColor = Color.decode("#272727");
        yellow = Color.decode("#ffcd62");
        white = Color.decode("#dbdbdb");
    }

    public void draw(Graphics2D g2d){
        /** Instantiates and renders the drawing objects. */
        asphalt = new Square(x, y, width, height, asphaltColor);
        asphalt.draw(g2d);
        yellowLineL = new Square(x+size*0.05, y, size*0.05, height, yellow);
        yellowLineR = new Square(x+size*1.1, y, size*0.05, height, yellow);
        whiteLineL = new Square(x-size*0.025, y, size*0.05, height, white);
        whiteLineR = new Square(x+size*47/40, y, size*0.05, height, white);

        leftDotted = new Square[40];
        rightDotted = new Square[40];
        for (int i = 0; i < 40; i++){
            leftDotted[i] = new Square(x+size*0.025, y+(size*i), size*0.05, size*0.5, white);
            rightDotted[i] = new Square(x+size*47/40, y+(size*i), size*0.05, size*0.5, white);
        }

        switch(left){
            case "Yellow": yellowLineL.draw(g2d); break;
            case "White": whiteLineL.draw(g2d); break;
            case "Dotted": for (Square s : leftDotted) {s.draw(g2d);} break;
        }
        switch(right){
            case "Yellow": yellowLineR.draw(g2d); break;
            case "White": whiteLineR.draw(g2d); break;
            case "Dotted": for (Square s : rightDotted){s.draw(g2d);} break;
        }
    }

    /** Allow for access to posistion. */
    public double getX(){return x;}
    public double getY(){return y;}
    /** Allow for manipulation of drawing objects posistion. */
    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
}
