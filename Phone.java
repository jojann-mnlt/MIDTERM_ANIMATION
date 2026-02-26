/**
This class renders the design in the details panel in the start menu.
It contains inner classes that render each part of the phone.

@author Jacob L. Buenventura (250930)
@author Johann Karol Benedict O. Manulat (253729)
@version February 26, 2026

We have not discussed the Java language code in our program
with anyone other than my instructor or the teaching assistants
assigned to this course.

WE have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.

If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of our program.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
public class Phone extends JComponent {

    /* This is the inner class, PhoneScreen. This is used to render the light blue gradient JPanel for the detailsPanel of the SceneFrame. */
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

    /* This is the inner class, PhoneUpper. This is used to render the top, camera part for the detailsPanel of the SceneFrame. */
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

    /* This is the inner class, PhoneLower. This is used to render the lower, button part for the detailsPanel of the SceneFrame. */
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

    /* these are getter methods for all three inner classes */
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
