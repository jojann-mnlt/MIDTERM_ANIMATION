import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CarSelect extends JComponent {

    private Car sedan, coupe;
    private int currentCar;
    private ArrayList<Car> cars;

    public CarSelect() {
        sedan = new Sedan(150, 150, 100, 0, Color.WHITE);
        coupe = new Coupe(150, 150, 100, 0, Color.WHITE);
        cars = new ArrayList<>();
        cars.add(sedan);
        cars.add(coupe);
        currentCar = 0;
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints AA = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(AA);

        setPreferredSize(new Dimension(400, 600));
        g2d.setColor(Color.decode("#00bf63"));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        cars.get(currentCar).draw(g2d);
    }

    public Car getCar() { return cars.get(currentCar); }
    public void changeVehicle() {
        int carListSize = cars.size() - 1;
        if (currentCar < carListSize) {
            currentCar += 1;
        } else if (currentCar == carListSize) {
            currentCar = 0;
        }
    }

    public ArrayList<Car> getListOfCars() { return cars; }
}