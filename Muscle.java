/**
This class describes a basic muscle car object. This is one of the
cars the player can use in the game. 

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
import java.awt.geom.*;

public class Muscle extends Car{
    private Square body, trunk, windowBase, rearWindow, roof;
    private Circle frontBumper, windShield, rearBumper;
    private Triangle rearLeftWheel, rearRightWheel, rearWindowL, rearWindowR;
    private Line aPillarL, aPillarR, cPillarL, cPillarR, bPillar;

    public Muscle(double x, double y, double size, double angle, Color color) {
        /** Instatiates the private fields and calls the superclass constructor. */
        super(x, y, size, angle, color);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override public void draw(Graphics2D g2d){
        /** Instantiates and renders all the parts of the car. */
        AffineTransform ogTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(angle), x+width*.50, y+height*.50);
        
        frontBumper = new Circle(x, y, width, height*1/8, color);
        body = new Square(x, y+size*0.125, width, height*23/32, color);
        trunk = new Square(x+size*0.075, y+size*1.7, width*0.85, height*1/4, color);
        rearLeftWheel = new Triangle(x, y+size*2.3, width*0.15, (-height*3/16), color);
        rearRightWheel = new Triangle(x+size*0.85, y+size*2.3, width*0.15, (-height*3/16), color);
        rearBumper = new Circle(x+size*0.075, y+size*2.2, width*0.85, height*1/12, color);

        windowBase = new Square(x+size*0.05, y+size*0.9, width*0.9, height*13/48, windowColor);
        windShield = new Circle(x+size*0.05, y+size*0.825, width*0.9, height*1/16, windowColor);
        rearWindow = new Square(x+size*0.15, y+size*1.45, width*0.7, height*5/24, windowColor);
        rearWindowL = new Triangle(x+size*0.05, y+size*1.95, width*0.2, -(height*1/6), windowColor);
        rearWindowR = new Triangle(x+size*0.75, y+size*1.95, width*0.2, -(height*1/6), windowColor);
        roof = new Square(x+size*0.15, y+size*1.05, width*0.7, height*11/48, color);
        aPillarL = new Line(x+size*0.05, y+size*0.825, x+size*0.175, y+size*1.1, pillarThicknessInt, color);
        aPillarR = new Line(x+size*0.95, y+size*0.825, x+size*0.822, y+size*1.1, pillarThicknessInt, color);
        bPillar = new Line(x+size*0.05, y+size*1.55, x+size*0.95, y+size*1.55, pillarThicknessInt, color);
        cPillarL = new Line(x+size*0.15, y+size*1.95, x+size*0.175, y+size*1.45, pillarThicknessInt, color);
        cPillarR = new Line(x+size*0.85, y+size*1.95, x+size*0.825, y+size*1.45, pillarThicknessInt, color);

        frontBumper.draw(g2d);
        body.draw(g2d);
        trunk.draw(g2d);
        rearLeftWheel.draw(g2d);
        rearRightWheel.draw(g2d);
        rearBumper.draw(g2d);
        windowBase.draw(g2d);
        windShield.draw(g2d);
        rearWindow.draw(g2d);
        rearWindowL.draw(g2d);
        rearWindowR.draw(g2d);
        roof.draw(g2d);
        aPillarL.draw(g2d);
        aPillarR.draw(g2d);
        bPillar.draw(g2d);
        cPillarL.draw(g2d);
        cPillarR.draw(g2d);
        g2d.setTransform(ogTransform);
        this.changeRenderState(true);
    }
    @Override public Square getHitbox(){
        hitbox = new Square(x+size*0.05, y+size*0.12, width*0.9, height*0.9, Color.RED);
        return hitbox;
    }
}