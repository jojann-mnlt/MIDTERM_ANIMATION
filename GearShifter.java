import java.awt.*;
public class GearShifter implements DrawingObject{
    private Square shifterBase, shifterBaseOutline, gap, gap12, gap34, gap5;
    private int gearLevel;
    private double x, y;
    private int size;
    private Color baseColor, baseOutline, gapColor;
    public GearShifter(double x, double y, int size, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        gearLevel = 1;
        baseOutline = Color.decode("#5f5f5f");
        baseColor = Color.decode("#000000");
        gapColor = Color.decode("#292929");
    }
    public void draw(Graphics2D g2d){
        // Instantiating
        shifterBaseOutline = new Square(x, y, size*2, size*2, baseOutline);
        shifterBase = new Square(x+size*0.1, y+size*0.1, size*1.8, size*1.8, baseColor);
        gap = new Square(x+size*0.3, y+size*0.9, size*1.4, size*0.2, gapColor);
        gap12 = new Square(x+size*0.3, y+size*0.3, size*0.2, size*1.4, gapColor);
        gap34 = new Square(x+size*0.9, y+size*0.3, size*0.2, size*1.4, gapColor);
        gap5 = new Square(x+size*1.5, y+size*0.3, size*0.2, size*0.7, gapColor);
        // Rendering
        shifterBaseOutline.draw(g2d);
        shifterBase.draw(g2d);
        gap.draw(g2d);
        gap12.draw(g2d);
        gap34.draw(g2d);
        gap5.draw(g2d);
    }
    public void changeGear(int gear){gearLevel = gear;}
    public int getGear() {return gearLevel;}
    public int getSize() {return size;}
    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}