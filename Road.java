/**
This class renders the road used in game. It will render a cartain
number of roads depending on the difficulty. Each road had to be 
instantiated and drawn individually to achieve the look of a proper
highway.

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
public class Road implements DrawingObject{
    private double x, y, maxXL, maxXR, counterFlowX;
    private Lane main, lane2, lane3, lane4, lane5, lane6;
    private int difficulty;
    public Road(double x, double y, int difficulty){
        /** Instantiates the fields of this class. */
        this.x = x;
        this.y = y;
        this.difficulty = difficulty;
        maxXR = x+22.5;
        switch (difficulty){
            case 4: maxXL = x-(120*(3))+22.5; break;
            default: maxXL = x-(120*difficulty)+22.5; break;
        }
        counterFlowX = 0;
    }

    public void draw(Graphics2D g2d){
        /** Renders roads based on difficulty. */
        main = new Lane(x, y, 100, "Dotted", "White");
        new CityScapeR(x, y, 100).draw(g2d);
        main.draw(g2d);

        switch (difficulty){
            case 1:
                lane2 = new Lane(x-120, y, 100, "White", "Dotted");
                new CityScapeL(x, y, 1).draw(g2d);
                lane2.draw(g2d);
                break;
            case 2:
                lane2 = new Lane(x-120, y, 100, "Dotted", "Dotted");
                lane3 = new Lane(x-(120*2), y, 100, "White", "Dotted");
                new CityScapeL(x, y, 2).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                break;
            case 3:
                lane2 = new Lane(x-120, y, 100, "Dotted", "Dotted");
                lane3 = new Lane(x-(120*2), y, 100, "Dotted", "Dotted");
                lane4 = new Lane(x-(120*3), y, 100, "White", "Dotted");
                new CityScapeL(x, y, 3).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                lane4.draw(g2d);
                break;
            case 4:
                lane2 = new Lane(x-120, y, 100, "Yellow", "Dotted");
                lane3 = new Lane(x-(120*2), y, 100, "Dotted", "Yellow");
                lane4 = new Lane(x-(120*3), y, 100, "White", "Dotted");
                new CityScapeL(x, y, 3).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                lane4.draw(g2d);
                counterFlowX = x-120;
                break;
            case 5:
                lane2 = new Lane(x-120, y, 100, "Dotted", "Dotted");
                lane3 = new Lane(x-(120*2), y, 100, "Yellow", "Dotted");
                lane4 = new Lane(x-(120*3), y, 100, "Dotted", "Yellow");
                lane5 = new Lane(x-(120*4), y, 100, "Dotted", "Dotted");
                lane6 = new Lane(x-(120*5), y, 100, "White", "Dotted");
                new CityScapeL(x, y, 5).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                lane4.draw(g2d);
                lane5.draw(g2d);
                lane6.draw(g2d);
                counterFlowX = x-240;
                break;
        }

    }
    /** Allow for access to private fields and to change position. */
    public double getX(){return x;}
    public double getY(){return y;}
    public double getMaxXL(){return maxXL;}
    public double getMaxXR(){return maxXR;}
    public double getCounterFlowX(){return counterFlowX;}
    public void moveX(double amount){
        x += amount;
        maxXR = x+22.5;
        switch (difficulty){
            case 4:
                maxXL = x-(120*(3))+22.5; break;
            default:
                maxXL = x-(120*difficulty)+22.5; break;
        }
    }
    public void moveY(double amount){y += amount;}
}
