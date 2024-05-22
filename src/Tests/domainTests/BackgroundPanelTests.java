package Tests.domainTests;

import Main.Domain.BackgroundPanel;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class BackgroundPanelTests {

    @Test
    public void testBackgroundImageLoading() {
        String filePath = "path/to/test/image.jpg";
        BackgroundPanel panel = new BackgroundPanel(filePath);

        assertNotNull(panel.getBackgroundImage(), "The background image should not be null.");
    }

    @Test
    public void testPaintComponent() {
        String filePath = "path/to/test/image.jpg";
        BackgroundPanel panel = new BackgroundPanel(filePath);

        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);

        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        panel.paintComponent(g2d);
        g2d.dispose();

        assertNotNull(image, "The panel should be painted onto the BufferedImage.");
    }
}
