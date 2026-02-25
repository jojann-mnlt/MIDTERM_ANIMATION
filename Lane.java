import java.awt.*;
public class Lane implements DrawingObject{
    private double x, y, size, width, height;
    private boolean hasYellowL, hasYellowR;
    private Color asphaltColor, yellow, white;
    private Square asphalt, yellowLineL, yellowLineR, whiteLineL, whiteLineR;

    public Lane(double x, double y, double size, boolean left, boolean right){
        this.x = x;
        this.y = y;
        this.size = size;
        width = size*1.2;
        height = 4000;
        hasYellowL = left;
        hasYellowR = right;
        asphaltColor = Color.decode("#272727");
        yellow = Color.decode("#ffcd62");
        white = Color.decode("#dbdbdb");
    }

    public void draw(Graphics2D g2d){
        asphalt = new Square(x, y, width, height, asphaltColor);
        yellowLineL = new Square(x+size*0.05, y, size*0.05, height, yellow);
        yellowLineR = new Square(x+size*1.1, y, size*0.05, height, yellow);
        whiteLineL = new Square(x-size*0.025, y, size*0.05, height, white);
        whiteLineR = new Square(x+size*47/40, y, size*0.05, height, white);
        asphalt.draw(g2d);

        if (hasYellowL == true) yellowLineL.draw(g2d);
        else whiteLineL.draw(g2d);
        if (hasYellowR == true) yellowLineR.draw(g2d);
        else whiteLineR.draw(g2d);
    }

    public double getX(){return x;}
    public double getY(){return y;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
}
