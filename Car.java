import java.awt.*;
public abstract class Car implements DrawingObject {
    protected double x, y, size, width, height, angle;
    protected Color color, windowColor;
    protected String carModel;

    public Car(double x, double y, double size, double angle, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        width = size;
        height = size*2.4;
        this.angle = angle;
        this.color = color;
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
        this.x = x;
        this.y = y;
    }
    public void rotate(double amt){angle += amt;}
    public void changeSize(double size){this.size = size;}
    public void changeColor(Color color){this.color = color;}
}
