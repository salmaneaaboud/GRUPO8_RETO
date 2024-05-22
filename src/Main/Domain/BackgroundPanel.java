package Main.Domain;

import javax.swing.*;
import java.awt.*;

/**
 * BackgroundPanel is a custom JPanel that allows a background image to be set.
 * The image is drawn with a specified transparency level.
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructs a BackgroundPanel with the specified image file path.
     *
     * @param filePath the path to the image file to be used as the background
     */
    public BackgroundPanel(String filePath) {
        try {
            backgroundImage = new ImageIcon(filePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Overrides the paintComponent method to draw the background image with a transparency.
     *
     * @param g the Graphics object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }

    /**
     * Gets the background image of this panel.
     *
     * @return the background image
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }
}
