import java.awt.*;
public class GearShifter implements DrawingObject{
    private Square gap, gap12, gap34, gap5;
    private int gearLevel;
    private double x, y;
    private int size;
    private Color accent;
    public GearShifter(double x, double y, int size, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        gearLevel = 1;
        accent = Color.decode("#292929");
    }
    public void draw(Graphics2D g2d){
        // Instantiating
        gap = new Square(x+size*7/18, y+size*23/18, size*2, size*2/9, accent);
        gap12 = new Square(x+size*7/18, y+size*0.5, size*2/9, size*16/9, accent);
        gap34 = new Square(x+size*23/18, y+size*0.5, size*2/9, size*16/9, accent);
        gap5 = new Square(x+size*13/6, y+size*0.5, size*2/9, size*7/9, accent);
        // Rendering
        gap.draw(g2d);
        gap12.draw(g2d);
        gap34.draw(g2d);
        gap5.draw(g2d);
    }
    public void changeGear(int gear){
        switch (gear){
            case 1:
                gearLevel = 1; break;
            case 2:
                gearLevel = 2; break;
            case 3:
                gearLevel = 3; break;
            case 4:
                gearLevel = 4; break;
            case 5:
                gearLevel = 5; break;
        }
    }
    public int getGear() {return gearLevel;}
    public int getSize() {return size;}
    public double getX() {return x;}
    public double getY() {return y;}
    public void moveX(double amount) {x += amount;}
    public void moveY(double amount) {y += amount;}
}