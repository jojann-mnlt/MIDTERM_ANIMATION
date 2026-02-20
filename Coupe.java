import java.awt.*;
import java.awt.geom.*;

public class Coupe extends Car {
    private double x, y, width, height, size;
    private Color color, windowColor;
    private Square base, body, windowBase, roof;
    private Circle frontBumper, frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel, windshield, rearViewWindow, roofFront, roofBack, trunk;
    private Line frontLeftPillar, frontRightPillar, backLeftPillar,  backRightPillar, divider;

    public Coupe(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.width = size;
        this.height = size*2.4;
        this.color = color;
        windowColor = Color.decode("#202020");
    }

    @Override
    public void draw(Graphics2D g2d) {
        base = new Square(x, y, width, height, Color.GRAY);
        frontBumper = new Circle(x+size*0.025, y+size*.30, width*.95, height*5/24, color);
        frontLeftWheel = new Circle(x, y+size*0.49, width*0.1, height*1/4, color);
        frontRightWheel = new Circle(x+size*0.90, y+size*0.49, width*.1, height*1/4, color);
        rearLeftWheel = new Circle(x, y+size*1.648, width*0.1, height*1/4, color);
        rearRightWheel = new Circle(x+size*0.90, y+size*1.648, width*0.1, height*1/4, color);
        trunk = new Circle(x+size*.025, y+size*2, width*.95, height*1/6, color);
        body = new Square(x+size*.025, y+size*.55, width*.95, height*11/16, color);

        // windows
        windowBase = new Square(x+size*.10, y+size*1.10, width*4/5, height*1/3, windowColor);
        windshield = new Circle(x+size*.10, y+size*.95, width*4/5, height*1/8, windowColor);
        rearViewWindow = new Circle(x+size*.10, y+size*1.675, width*4/5, height*3/16, windowColor);
        frontLeftPillar = new Line(x+size*.10, y+size*.95, x+size*.221, y+size*1.35, 6, color);
        frontRightPillar = new Line(x+size*.90, y+size*.95, x+size*.779, y+size*1.35, 6, color);
        backLeftPillar = new Line(x+size*.10, y+size*2, x+size*.221, y+size*1.75, 6, color);
        backRightPillar = new Line(x+size*.90, y+size*2.025, x+size*.779, y+size*1.75, 6, color);
        divider = new Line(x+size*.10, y+size*1.675, x+size*.90, y+size*1.675, 6, color);
        roof = new Square(x+size*.20, y+size*1.35, width*.60, height*1/6, color);
        roofFront = new Circle(x+size*.20, y+size*1.30, width*.60, height*1/24, color);
        roofBack = new Circle(x+size*.20, y+size*1.675,width*.60, height*1/16, color);

        base.draw(g2d);
        frontBumper.draw(g2d);
        frontLeftWheel.draw(g2d);
        frontRightWheel.draw(g2d);
        rearLeftWheel.draw(g2d);
        rearRightWheel.draw(g2d);
        trunk.draw(g2d);
        body.draw(g2d);
        windowBase.draw(g2d);
        windshield.draw(g2d);
        rearViewWindow.draw(g2d);
        roof.draw(g2d);
        roofFront.draw(g2d);
        roofBack.draw(g2d);
        frontLeftPillar.draw(g2d);
        frontRightPillar.draw(g2d);
        backLeftPillar.draw(g2d);
        backRightPillar.draw(g2d);
        divider.draw(g2d);

    }

    public double getX(){return x;}
    public double getY(){return y;}
    public Color getColor(){return color;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}

    public void changeColor(Color newColor){color = newColor;}
}
