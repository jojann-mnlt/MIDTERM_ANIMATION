import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.Random;

public class SpawningSystem extends JComponent {
    private int x, y;
    private Car car;

    public SpawningSystem(int x, int y){
        this.x = x;
        this.y = y;
        car = new Sedan(0, 0, 75, 180, Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        spawnCar(g2d);
    }

    private void spawnCar(Graphics2D g2d) {
        car.draw(g2d);
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public Car getCar(){return car;}

    public void moveCar() {
        car.moveY(15);
        if (car.getY() > 700) {
            car.moveY(-900);
        }
        repaint();
    }

     public static void main(String[] args) {
        JFrame frame = new JFrame("Spawning System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
         SpawningSystem ss = new SpawningSystem(0, 0);
         frame.add(ss);
         Timer timer = new Timer(16, e -> ss.moveCar());
         timer.start();
     }
}
