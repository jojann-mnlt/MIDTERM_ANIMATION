import javax.swing.*;
import java.awt.*;

public class Phone extends JComponent {
    private class PhoneUpper extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            setPreferredSize(new Dimension(200, 52));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 200, 52);
            Circle cameraTip1 = new Circle(52.3, 17, 5.2, 5.2, Color.LIGHT_GRAY);
            Circle cameraTip2 = new Circle(128.7, 17, 5.2, 5.2, Color.LIGHT_GRAY);
            cameraTip1.draw(g2d);
            cameraTip2.draw(g2d);
        }
    }

    private class PhoneLower extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            setPreferredSize(new Dimension(200, 52));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 200, 52);
        }
    }

    public PhoneUpper drawUpperPhone() {
        PhoneUpper upperPhone = new PhoneUpper();
        return upperPhone;
    }

    public PhoneLower drawLowerPhone() {
        PhoneLower lowerPhone = new PhoneLower();
        return lowerPhone;
    }
}
