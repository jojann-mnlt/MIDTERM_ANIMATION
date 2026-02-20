import java.awt.*;
public class Sedan extends Car{
    private double x, y, width, height, size;
    private Color color, windowColor;
    private Square base, hood, body, trunk, windowBase, roof;
    private Circle frontBumper, rearBumper, windShield, rearWindow, roofFront, roofRear;
    private Triangle frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel;
    private Line aPillarL, aPillarR, cPillarL, cPillarR, bPillar;

    public Sedan(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.width = size;
        this.height = size*2.4;
        this.color = color;
        windowColor = Color.decode("#202020");
    }

    @Override
    public void draw(Graphics2D g2d){
        //Body
        base = new Square(x, y, width, height, Color.GRAY);
        frontBumper = new Circle(x+size*0.025, y, width*0.95, height*0.125, color);
        hood = new Square(x+size*0.025, y+size*0.15, width*0.95, height*11/48, color);
        frontLeftWheel = new Triangle(x, y+size*0.15, width*0.05, height*11/48, color);
        frontRightWheel = new Triangle(x+size*0.95, y+size*0.15, width*0.05, height*11/48, color);
        trunk = new Square(x+size*0.05, y+size*2.1, width*0.9, height*1/12, color);
        rearBumper = new Circle(x+size*0.05, y+size*2.2, width*0.9, height*1/12, color);
        rearLeftWheel = new Triangle(x, y+size*2.3, width*0.1, -(height*1/12), color);
        rearRightWheel = new Triangle(x+size*0.9, y+size*2.3, width*0.1, (-height*1/12), color);
        body = new Square(x, y+size*0.7, width, height*7/12, color);
        //Windows
        windowBase = new Square(x+size*0.1, y+size*0.8, width*0.8, height*0.47, windowColor);
        windShield = new Circle(x+size*0.1, y+size*0.69, width*0.8, height*1/12, windowColor);
        rearWindow = new Circle(x+size*0.1, y+size*1.778, width*0.8, height*1/8, windowColor);
        //Roof
        roofFront = new Circle(x+size*0.2, y+size*1.128, width*0.6, height*1/24, color);
        roof = new Square(x+size*0.2, y+size*1.178, width*0.6, height*1/4, color);
        roofRear = new Circle(x+size*0.2, y+size*1.723, width*0.6, height*1/24, color);
        aPillarL = new Line(x+size*0.05, y+size*0.7, x+size*0.225, y+size*1.178, 6, color);
        aPillarR = new Line(x+size*0.95, y+size*0.7, x+size*0.775, y+size*1.178, 6, color);
        bPillar = new Line(x+size*0.1, y+size*1.458, x+size*0.9, y+size*1.458, 6, color);
        cPillarL = new Line(x+size*0.05, y+size*2.028, x+size*0.225, y+size*1.744, 6, color);
        cPillarR = new Line(x+size*0.95, y+size*2.028, x+size*0.775, y+size*1.744, 6, color);
        //Render
        base.draw(g2d);
        frontBumper.draw(g2d);
        hood.draw(g2d);
        frontLeftWheel.draw(g2d);
        frontRightWheel.draw(g2d);
        body.draw(g2d);
        trunk.draw(g2d);
        rearBumper.draw(g2d);
        rearLeftWheel.draw(g2d);
        rearRightWheel.draw(g2d);
        windShield.draw(g2d);
        windowBase.draw(g2d);
        rearWindow.draw(g2d);
        roofFront.draw(g2d);
        roof.draw(g2d);
        roofRear.draw(g2d);
        aPillarL.draw(g2d);
        aPillarR.draw(g2d);
        bPillar.draw(g2d);
        cPillarL.draw(g2d);
        cPillarR.draw(g2d);
    }
}