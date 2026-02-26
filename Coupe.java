import java.awt.*;
import java.awt.geom.*;
public class Coupe extends Car {
    private Square body, windowBase, roof;
    private Circle frontBumper, frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel, windshield, rearWindow, roofFront, roofBack, rearBumper;
    private Line aPillarL, aPillarR, cPillarL,  cPillarR, bPillar;

    public Coupe(double x, double y, double size, double angle, Color color) {
        super(x, y, size, angle, color);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override public void draw(Graphics2D g2d) {
        AffineTransform ogTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(angle), x+width*.50, y+height*.50);

        frontBumper = new Circle(x+size*0.025, y+size*.15, width*.95, height*5/24, color);
        frontLeftWheel = new Circle(x, y+size*0.34, width*0.1, height*1/4, color);
        frontRightWheel = new Circle(x+size*0.90, y+size*0.34, width*.1, height*1/4, color);
        rearLeftWheel = new Circle(x, y+size*1.498, width*0.1, height*1/4, color);
        rearRightWheel = new Circle(x+size*0.90, y+size*1.498, width*0.1, height*1/4, color);
        rearBumper = new Circle(x+size*.025, y+size*1.85, width*.95, height*1/6, color);
        body = new Square(x+size*.025, y+size*.40, width*.95, height*11/16, color);
        windowBase = new Square(x+size*.10, y+size*.95, width*4/5, height*1/3, windowColor);
        windshield = new Circle(x+size*.10, y+size*.8, width*4/5, height*1/8, windowColor);
        rearWindow = new Circle(x+size*.10, y+size*1.525, width*4/5, height*3/16, windowColor);
        roofFront = new Circle(x+size*.20, y+size*1.15, width*.60, height*1/24, color);
        roof = new Square(x+size*.20, y+size*1.20, width*.60, height*1/6, color);
        roofBack = new Circle(x+size*.20, y+size*1.525,width*.60, height*1/16, color);
        aPillarL = new Line(x+size*.10, y+size*.8, x+size*.221, y+size*1.2, pillarThicknessInt, color);
        aPillarR = new Line(x+size*.90, y+size*.8, x+size*.779, y+size*1.2, pillarThicknessInt, color);
        bPillar = new Line(x+size*.10, y+size*1.525, x+size*.90, y+size*1.525, pillarThicknessInt, color);
        cPillarL = new Line(x+size*.10, y+size*1.85, x+size*.221, y+size*1.6, pillarThicknessInt, color);
        cPillarR = new Line(x+size*.90, y+size*1.85, x+size*.779, y+size*1.6, pillarThicknessInt, color);

        frontBumper.draw(g2d);
        frontLeftWheel.draw(g2d);
        frontRightWheel.draw(g2d);
        rearLeftWheel.draw(g2d);
        rearRightWheel.draw(g2d);
        rearBumper.draw(g2d);
        body.draw(g2d);
        windowBase.draw(g2d);
        windshield.draw(g2d);
        rearWindow.draw(g2d);
        roof.draw(g2d);
        roofFront.draw(g2d);
        roofBack.draw(g2d);
        aPillarL.draw(g2d);
        aPillarR.draw(g2d);
        bPillar.draw(g2d);
        cPillarL.draw(g2d);
        cPillarR.draw(g2d);
        g2d.setTransform(ogTransform);
        this.changeRenderState(true);
    }
}
