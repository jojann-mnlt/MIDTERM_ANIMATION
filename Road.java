import java.awt.*;
public class Road implements DrawingObject{
    private double x, y, maxXL, maxXR;
    private Lane main, lane2, lane3, lane4, lane5, lane6;
    private Square sidewalk2;
    private int difficulty;
    public Road(double x, double y, int difficulty){
        this.x = x;
        this.y = y;
        this.difficulty = difficulty;
        maxXR = x+22.5;
        switch (difficulty){
            case 4:
                maxXL = x-(120*(3))+22.5; break;
            default:
                maxXL = x-(120*difficulty)+22.5; break;
        }
    }
    public void draw(Graphics2D g2d){
        main = new Lane(x, y, 100, false, false);
        new CityScapeR(x, y, 100).draw(g2d);
        main.draw(g2d);

        // Switch cases depending on difficulty
        switch (difficulty){
            case 1:
                lane2 = new Lane(x-120, y, 100, false, false);
                new CityScapeL(x, y, 120, 1).draw(g2d);
                lane2.draw(g2d);
                break;
            case 2:
                lane2 = new Lane(x-120, y, 100, false, false);
                lane3 = new Lane(x-(120*2), y, 100, false, false);
                new CityScapeL(x, y, 120, 2).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                break;
            case 3:
                lane2 = new Lane(x-120, y, 100, false, false);
                lane3 = new Lane(x-(120*2), y, 100, false, false);
                lane4 = new Lane(x-(120*3), y, 100, false, false);
                new CityScapeL(x, y, 120, 3).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                lane4.draw(g2d);
                break;
            case 4:
                lane2 = new Lane(x-120, y, 100, true, false);
                lane3 = new Lane(x-(120*2), y, 100, false, true);
                lane4 = new Lane(x-(120*3), y, 100, false, false);
                new CityScapeL(x, y, 120, 3).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                lane4.draw(g2d);
                break;
            case 5:
                lane2 = new Lane(x-120, y, 100, false, false);
                lane3 = new Lane(x-(120*2), y, 100, true, false);
                lane4 = new Lane(x-(120*3), y, 100, false, true);
                lane5 = new Lane(x-(120*4), y, 100, false, false);
                lane6 = new Lane(x-(120*5), y, 100, false, false);
                new CityScapeL(x, y, 120, 5).draw(g2d);
                lane2.draw(g2d);
                lane3.draw(g2d);
                lane4.draw(g2d);
                lane5.draw(g2d);
                lane6.draw(g2d);
                break;
        }

    }
    public double getX(){return x;}
    public double getY(){return y;}
    public double getMaxXL(){return maxXL;}
    public double getMaxXR(){return maxXR;}

    public void moveX(double amount){
        x += amount;
        maxXR = x+22.5;
        switch (difficulty){
            case 4:
                maxXL = x-(120*(3))+22.5; break;
            default:
                maxXL = x-(120*difficulty)+22.5; break;
        }
    }
    public void moveY(double amount){y += amount;}
}
