import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class CarSelect extends JComponent {

    private Car sedan, sportsCar;
    private int currentCar;
    private ArrayList<Car> cars;

    public CarSelect() {
        sedan = new Sedan(160, 40, 75, Color.BLUE);
        sportsCar = new SportsCar(160, 40, 75, Color.YELLOW);
        cars = new ArrayList<>();
        cars.add(sedan);
        cars.add(sportsCar);
        cars.add(new Sedan(160, 40, 75, Color.YELLOW));
        currentCar = 0;
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setPreferredSize(new Dimension(400, 300));
        g2d.setColor(Color.decode("#00bf63"));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        cars.get(currentCar).draw(g2d);
    }

    public Car getCar() { return cars.get(currentCar); }
    public void changeVehicle() { if (currentCar == cars.size()-1) { currentCar = 0; } else { currentCar++; } System.out.println(currentCar); }
}