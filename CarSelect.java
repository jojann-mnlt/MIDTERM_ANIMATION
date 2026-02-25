import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

public class CarSelect extends JComponent {

    private Car sedan, coupe;
    private int currentCar;
    private ArrayList<Car> cars;

    public CarSelect() {
        sedan = new Sedan(125, 100, 150, 0, Color.WHITE);
        coupe = new Coupe(125, 100, 150, 0, Color.WHITE);
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
        g2d.setColor(Color.decode("#5f5f5f"));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        cars.get(currentCar).draw(g2d);


        g2d.setStroke(new BasicStroke(7));
        FreeformQuadrilateral garage = new FreeformQuadrilateral(49.1, 70, 342.1, 70, 387, -34, 8.1, -34, Color.decode("#b7b7b7"));
        garage.fill(g2d);
        garage.changeColor(Color.DARK_GRAY);
        garage.draw(g2d);

        Square table = new Square(315.8, 253.5, 84.2, 336, Color.decode("#ffe5b6"));
        Square tableOutline = new Square(309.1, 246.4, 90.9, 349.8, Color.decode("#31220c"));
        tableOutline.draw(g2d);
        table.draw(g2d);

        Circle wrenchRoundEnd = new Circle(354.9, 327.1, 9.9, 9.9, Color.decode("#b44b46"));
        Square handle = new Square(356.4, 287.9, 7, 45.5, Color.decode("#b44b46"));
        Square handleDetail = new Square(357.4, 291.3, 5, 41.4, Color.decode("#ca594f"));
        Square wrenchHead = new Square(355.3, 285, 9.9, 12.3, Color.decode("#355368"));
        Circle wrenchHeadMain = new Circle(349.9, 270.4, 22.1, 22.1, Color.decode("#355368"));
        Square jaws = new Square(356.4, 268.6, 8.7, 16.3, Color.decode("#ffe5b6"));
        wrenchRoundEnd.draw(g2d);
        handle.draw(g2d);
        handleDetail.draw(g2d);
        wrenchHead.draw(g2d);
        wrenchHeadMain.draw(g2d);
        jaws.draw(g2d);




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