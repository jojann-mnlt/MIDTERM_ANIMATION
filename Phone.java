import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Phone extends JComponent {
    private class PhoneScreen extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            setPreferredSize(new Dimension(195, 195));
            GradientPaint paint = new GradientPaint(100, 0, Color.decode("#52a1ff"), 100, 150, Color.WHITE);
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private class PhoneUpper extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            RenderingHints aa = new  RenderingHints(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(aa);

            setPreferredSize(new Dimension(200, 52));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 200, 52);
            Circle cameraTip1 = new Circle(52.3, 17, 5.2, 5.2, Color.LIGHT_GRAY);
            Circle cameraTip2 = new Circle(128.7, 17, 5.2, 5.2, Color.LIGHT_GRAY);
            Circle camera = new Circle(136.6, 17, 5.2, 5.2, Color.LIGHT_GRAY);
            Square line = new Square(55, 17, 76.4, 5.2, Color.LIGHT_GRAY);
            line.draw(g2d);
            camera.draw(g2d);
            cameraTip1.draw(g2d);
            cameraTip2.draw(g2d);
        }
    }

    private class PhoneLower extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            RenderingHints aa = new  RenderingHints(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(aa);

            setPreferredSize(new Dimension(200, 52));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 200, 52);
            Ellipse2D.Double phoneButton = new Ellipse2D.Double(85.6, 6.6, 30, 30);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(phoneButton);
            g2d.setColor(Color.BLACK);
            g2d.fill(phoneButton);
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

    public PhoneScreen drawPhoneScreen() {
        PhoneScreen phoneScreen = new PhoneScreen();
        return phoneScreen;
    }
}
