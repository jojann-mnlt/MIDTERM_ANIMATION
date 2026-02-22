import java.awt.*;
import java.util.*;
public class TestCar extends Car{
    private ArrayList<DrawingObject> carParts;
    private String carModel;

    public TestCar(double x, double y, double size, double angle, Color color) {
        super(x, y, size, angle, color);
        carParts = new ArrayList<DrawingObject>();
        carModel = "TestCar";
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.rotate(Math.toRadians(angle), x+size*.50, y+size*.50);

        //Body
        carParts.add(new Square(x, y, width, height, Color.GRAY));
        carParts.add(new Circle(x+size*0.025, y, width*0.95, height*0.125, color));
        carParts.add(new Square(x+size*0.025, y+size*0.15, width*0.95, height*11/48, color));
        carParts.add(new Triangle(x, y+size*0.15, width*0.05, height*11/48, color));
        carParts.add(new Triangle(x+size*0.95, y+size*0.15, width*0.05, height*11/48, color));
        carParts.add(new Square(x+size*0.05, y+size*2.1, width*0.9, height*1/12, color));
        carParts.add(new Circle(x+size*0.05, y+size*2.2, width*0.9, height*1/12, color));
        carParts.add(new Triangle(x, y+size*2.3, width*0.1, -(height*1/12), color));
        carParts.add(new Triangle(x+size*0.9, y+size*2.3, width*0.1, (-height*1/12), color));
        carParts.add(new Square(x, y+size*0.7, width, height*7/12, color));
        //Windows
        carParts.add(new Square(x+size*0.1, y+size*0.8, width*0.8, height*0.47, windowColor));
        carParts.add(new Circle(x+size*0.1, y+size*0.69, width*0.8, height*1/12, windowColor));
        carParts.add(new Circle(x+size*0.1, y+size*1.778, width*0.8, height*1/8, windowColor));
        //Roof
        carParts.add(new Circle(x+size*0.2, y+size*1.128, width*0.6, height*1/24, color));
        carParts.add(new Square(x+size*0.2, y+size*1.178, width*0.6, height*1/4, color));
        carParts.add(new Circle(x+size*0.2, y+size*1.723, width*0.6, height*1/24, color));
        carParts.add(new Line(x+size*0.05, y+size*0.7, x+size*0.225, y+size*1.178, 6, color));
        carParts.add(new Line(x+size*0.95, y+size*0.7, x+size*0.775, y+size*1.178, 6, color));
        carParts.add(new Line(x+size*0.1, y+size*1.458, x+size*0.9, y+size*1.458, 6, color));
        carParts.add(new Line(x+size*0.05, y+size*2.028, x+size*0.225, y+size*1.744, 6, color));
        carParts.add(new Line(x+size*0.95, y+size*2.028, x+size*0.775, y+size*1.744, 6, color));
        //Render
        for (DrawingObject p : carParts){
            p.draw(g2d);
        }
    }
    @Override public String getCarModel(){return carModel;}
}