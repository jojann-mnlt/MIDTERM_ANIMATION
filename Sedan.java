import java.awt.*;
import java.awt.geom.*;
public class Sedan extends Car{
    private double x, y, width, height, size;
    private Color color;
    private Square body, window, roof;
    private Line front_left_pillar, front_right_pillar, middle_pillar, back_left_pillar, back_right_pillar;
    public Sedan(double k, double j, double s, Color c) {
        // k = x, j = y, s = size, c = color
        this.x = k; 
        this.y = j;
        this.size = s;
        this.width = size;
        this.height = size*2.4;
        this.color = c;
        body = new Square(x, y, width, height, color);
        window = new Square(x+size*0.1, y+size*.6, width*0.8, height*7/12, Color.BLACK);
        roof = new Square(x+size*0.2, y+size, width*0.6, height*1/3, color);
        front_left_pillar = new Line(x+size*0.1, y+size*0.6, x+size*0.2, y+size, 2, color);
        front_right_pillar = new Line(x+size*0.9, y+size*0.6, x+size*0.8, y+size, 2, color);
        middle_pillar = new Line(x+size*0.1, y+size*1.4, x+size*0.9, y+size*1.4, 2, color);
        back_left_pillar = new Line(x+size*0.1, y+size*2, x+size*0.2, y+size*1.8, 2, color);
        back_right_pillar = new Line(x+size*0.9, y+size*2, x+size*0.8, y+size*1.8, 2, color);
    }
    public void draw(Graphics2D g2d){
        body.draw(g2d);
        window.draw(g2d);
        roof.draw(g2d);
        front_left_pillar.draw(g2d);
        front_right_pillar.draw(g2d);
        middle_pillar.draw(g2d);
        back_left_pillar.draw(g2d);
        back_right_pillar.draw(g2d);
    }
}