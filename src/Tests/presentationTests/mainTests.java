package Tests.presentationTests;

import Main.Presentation.Main;
import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Main class.
 */
public class mainTests {

    /**
     * Tests the creation of a character panel.
     */
    @BeforeEach
    @Test
    void createCharacterPanelTest() {
        JPanel panel = Main.createCharaterBox("Warrior", "Strong and resilient", "./photos/warrior.jfif");
        assertNotNull(panel);
        assertEquals(2, panel.getComponentCount());
    }

    /**
     * Tests the addition of a mouse hover effect to a label.
     */
    @AfterEach
    @Test
    void addMouseHoverEffectTest() {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("./photos/warrior.jfif");
        Main.addMouseHoverEffect(label, icon);

        java.awt.event.MouseEvent enterEvent = new java.awt.event.MouseEvent(label, java.awt.event.MouseEvent.MOUSE_ENTERED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        label.dispatchEvent(enterEvent);
        assertEquals(icon.getIconWidth() + 50, label.getIcon().getIconWidth());

        java.awt.event.MouseEvent exitEvent = new java.awt.event.MouseEvent(label, java.awt.event.MouseEvent.MOUSE_EXITED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        label.dispatchEvent(exitEvent);
        assertEquals(icon, label.getIcon());
    }

    /**
     * Tests the main method.
     */
    @Test
    void mainTest() {
        try {
            Main.main(null);
        } catch (Exception e) {
            fail("The main method threw an exception: " + e.getMessage());
        }
    }

    /**
     * Tests the creation and showing of the GUI.
     */
    @Test
    void createAndShowGUITest() {
        try {
            Main.createAndShowGUI();
        } catch (Exception e) {
            fail("The createAndShowGUI method threw an exception: " + e.getMessage());
        }
    }
}
