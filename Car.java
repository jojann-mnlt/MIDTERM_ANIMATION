/**
This class describes the car object which implements the DrawingObject
interface. This class has methods which all for cars to be manipulated.

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

public abstract class Car implements DrawingObject {
    protected double x, y, size, angle, width, height, pillarThickness;
    protected int pillarThicknessInt;
    protected Color color, windowColor;
    protected String carModel;
    protected boolean rendered;
    protected Square hitbox;

    public Car(double x, double y, double size, double angle, Color color){
        /** This constructor instantiates the car object. It sets the width and height
        to be a specific 1:2.4 ratio. */
        this.x = x;
        this.y = y;
        this.size = size;
        width = size;
        height = size*2.4;
        this.angle = angle;
        this.color = color;
        this.pillarThickness = size*0.06;
        pillarThicknessInt = (int) pillarThickness;
        windowColor = Color.decode("#202020");
        rendered = false;
        hitbox = new Square(x+size*0.05, y+size*0.3, width*0.9, height*0.75, Color.RED);
    }

    /** This abstract method ensures that all cars can be drawn/rendered. */
    public abstract void draw(Graphics2D g2d); 

    /** These set of methods allow access to this class' private fields */
    public double getX(){return x;} 
    public double getY(){return y;} 
    public double getAngle(){return angle;} 
    public double getWidth(){return width;} 
    public double getHeight(){return height;}
    public Color getColor(){return color;}

    /** This method returns the "rendered" field, which checks to see if the car is
    visible to the player. */
    public boolean isVisible(){return rendered;}

    /** This method creates and returns a car's hitbox. */
    public Square getHitbox(){
        hitbox = new Square(x+size*0.05, y+size*0.35, width*0.9, height*0.75, Color.RED);
        return hitbox;
    }
    /** This method checks if the hitbox of a car is colliding with the hitbox of another. */
    public boolean isColliding(Car otherCar){
        return !((this.hitbox.getX() + this.hitbox.getWidth() <= otherCar.getHitbox().getX()) ||
            (this.hitbox.getX() >= otherCar.getHitbox().getX() + otherCar.getHitbox().getWidth()) ||
            (this.hitbox.getY() + this.hitbox.getHeight() <= otherCar.getHitbox().getY()) ||
            (this.hitbox.getY() >= otherCar.getHitbox().getY() + otherCar.getHitbox().getHeight())
        );
    }
    
    /** These methods change various characteristics of a car: size, shape, rotation, color, position. */
    public void moveX(double amount){
        x += amount;
        hitbox.moveX(amount);
    }
    public void moveY(double amount){
        y += amount;
        hitbox.moveY(amount);
    }
    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void rotate(double amt){angle = amt;}
    public void changeSize(double size){
        this.size = size;
        this.width = size;
        this.height = size*2.4;
        this.pillarThickness = size*0.06;
        pillarThicknessInt = (int) pillarThickness;
    }
    public void changeColor(Color color){this.color = color;}
    public void changeRenderState(boolean b){rendered = b;}
}
