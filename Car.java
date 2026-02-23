import java.awt.*;
public abstract class Car implements DrawingObject {
    protected static double x, y, size, width, height, angle;
    protected static int pillarThickness;
    protected static Color color, windowColor;
    protected String carModel;

    public Car(double x, double y, double size, double angle, Color color){
        Car.x = x;
        Car.y = y;
        Car.size = size;
        width = size;
        height = size*2.4;
        Car.angle = angle;
        Car.color = color;
        Car.pillarThickness = (int) size/20;
        windowColor = Color.decode("#202020");
    }
    // Draw Method
    public abstract void draw(Graphics2D g2d);
    // Getter Methods
    public double getX(){return x;}
    public double getY(){return y;}
    public double getAngle(){return angle;}
    public Color getColor(){return color;}
    public abstract String getCarModel();
    // Mutator Methods
    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
    public void moveTo(double x, double y){
        Car.x = x;
        Car.y = y;
    }
    public void rotate(double amt){angle += amt;}
    public void changeSize(double size){
        Car.size = size;
        Car.width = size;
        Car.height = size*2.4;
        Car.pillarThickness = (int) size/20;
    }
    public void changeColor(Color color){Car.color = color;}
}
