import java.awt.*;
public class Lane implements DrawingObject{
    private double x, y, size, width, height;
    private String left, right;
    private Color asphaltColor, yellow, white;
    private Square asphalt, yellowLineL, yellowLineR, whiteLineL, whiteLineR;
    private Square[] leftDotted, rightDotted;

    public Lane(double x, double y, double size, String left, String right){
        this.x = x;
        this.y = y;
        this.size = size;
        width = size*1.2;
        height = 4000;
        this.left = left;
        this.right = right;
        asphaltColor = Color.decode("#272727");
        yellow = Color.decode("#ffcd62");
        white = Color.decode("#dbdbdb");
    }

    public void draw(Graphics2D g2d){
        asphalt = new Square(x, y, width, height, asphaltColor);
        asphalt.draw(g2d);
        yellowLineL = new Square(x+size*0.05, y, size*0.05, height, yellow);
        yellowLineR = new Square(x+size*1.1, y, size*0.05, height, yellow);
        whiteLineL = new Square(x-size*0.025, y, size*0.05, height, white);
        whiteLineR = new Square(x+size*47/40, y, size*0.05, height, white);

        leftDotted = new Square[40];
        rightDotted = new Square[40];
        for (int i = 0; i < 40; i++){
            leftDotted[i] = new Square(x+size*0.025, y+(size*i), size*0.05, size*0.5, white);
            rightDotted[i] = new Square(x+size*47/40, y+(size*i), size*0.05, size*0.5, white);
        }

        switch(left){
            case "Yellow": yellowLineL.draw(g2d); break;
            case "White": whiteLineL.draw(g2d); break;
            case "Dotted": for (Square s : leftDotted) {s.draw(g2d);} break;
        }
        switch(right){
            case "Yellow": yellowLineR.draw(g2d); break;
            case "White": whiteLineR.draw(g2d); break;
            case "Dotted": for (Square s : rightDotted){s.draw(g2d);} break;
        }
    }

    public double getX(){return x;}
    public double getY(){return y;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
}
