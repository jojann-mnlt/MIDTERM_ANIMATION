import java.awt.*;
public abstract class Car implements DrawingObject {
    protected double x, y, size, angle, width, height, pillarThickness;
    protected int pillarThicknessInt;
    protected Color color, windowColor;
    protected String carModel;
    protected boolean rendered;
    protected Square hitbox;

    public Car(double x, double y, double size, double angle, Color color){
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
    // Draw Method
    public abstract void draw(Graphics2D g2d);
    // Getter Methods
    public double getX(){return x;}
    public double getY(){return y;}
    public double getAngle(){return angle;}
    public double getWidth(){return width;} //for collision
    public double getHeight(){return height;} //for collision
    public Color getColor(){return color;}
    public abstract String getCarModel();
    public boolean isVisible(){return rendered;}

    // Hitbox detection
    public boolean isColliding(Car otherCar){
        return !((this.hitbox.getX() + this.hitbox.getWidth() <= otherCar.getHitbox().getX()) ||
            (this.hitbox.getX() >= otherCar.getHitbox().getX() + otherCar.getHitbox().getWidth()) ||
            (this.hitbox.getY() + this.hitbox.getHeight() <= otherCar.getHitbox().getY()) ||
            (this.hitbox.getY() >= otherCar.getHitbox().getY() + otherCar.getHitbox().getHeight())
        );
    }
    public Square getHitbox(){
        hitbox = new Square(x+size*0.05, y+size*0.35, width*0.9, height*0.75, Color.RED);
        return hitbox;
    }
    // Mutator Methods
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
