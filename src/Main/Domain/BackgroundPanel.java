package Main.Domain;

import javax.swing.*;
import java.awt.*;
/**
 * A JPanel subclass that displays a background image with a transparency effect.
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructs a BackgroundPanel with the specified image file path.
     *
     * @param filePath the path to the image file used as the background.
     */
    public BackgroundPanel(String filePath) {
        try {
            backgroundImage = new ImageIcon(filePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Overrides the paintComponent method to draw the background image with a transparency effect.
     *
     * @param g the Graphics object to protect.
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

    public Image getBackgroundImage() {
        return backgroundImage;
    }
}
