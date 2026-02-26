import java.awt.*;
import java.util.Random;

public class TrafficSystem implements DrawingObject{
    private double x, y;
    private Car[] normal, counterFlow;
    private int numNormal, numCounter;
    private Color highwayCarColor;
    private int difficulty, removeNormal, removeCounter;
    private Random rng;
    public TrafficSystem(double x, double y, int difficulty){
        this.x = x;
        this.y = y;
        this.difficulty = difficulty;
        switch (difficulty){
            case 1: numNormal = 2; numCounter = 0; break;
            case 2: numNormal = 3; numCounter = 0; break;
            case 3: numNormal = 4; numCounter = 0; break;
            case 4: numNormal = 2; numCounter = 2; break;
            case 5: numNormal = 3; numCounter = 3; break;
        }
        normal = new Car[numNormal];
        counterFlow = new Car[numCounter];
        highwayCarColor = Color.WHITE;
        for (int i = 0; i < numNormal; i++) {normal[i] = new Sedan(x-(120*i), y, 75, 0, highwayCarColor);}
        for (int i = 0; i < numCounter; i++) {
            if (difficulty == 4) {counterFlow[i] = new Sedan(x-(120*(i+2)), y, 75, 180, highwayCarColor);} 
            else if (difficulty == 5) {counterFlow[i] = new Sedan(x-(120*(i+3)), y, 75, 180, highwayCarColor);}
        }
        rng = new Random();
        removeNormal = rng.nextInt(numNormal);
        if(difficulty >= 4) removeCounter = rng.nextInt(numCounter);
        else removeCounter = 1;
    }
    @Override
    public void draw(Graphics2D g2d){
        for (int i = 0; i < numNormal; i++) {
            if (i != removeNormal) normal[i].draw(g2d);
            if (i == removeNormal) normal[i].changeRenderState(false);
        }
        if (difficulty >= 4){
            for (int i = 0; i < numCounter; i++) {
                if (i != removeCounter) counterFlow[i].draw(g2d);
                if (i == removeCounter) counterFlow[i].changeRenderState(false);
            }
        }
    }

    // Changes the index of the car that will NOT be rendered
    public void refreshNormalRNG(){removeNormal = rng.nextInt(numNormal);}
    public void refreshCounterRNG(){
        if(difficulty >= 4) removeCounter = rng.nextInt(numCounter);
        else removeCounter = 1;
    }

    // Returns the cars in the arrays for access
    public Car[] getNormalCars(){return normal;}
    public Car[] getCounterFlowCars(){return counterFlow;}

    public double getX(){return x;}
    public double getY(){return y;}

    public void moveX(double amount){x += amount;}
    public void moveY(double amount){y += amount;}
}