package Tests.presentationTests;

import Main.Presentation.Main;
import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class mainTests {

    @BeforeEach
    @Test
    void createCharacterPanelTest() {
        JPanel panel = Main.createCharaterBox("Warrior", "Strong and resilient", "./photos/warrior.jfif");
        assertNotNull(panel);
        assertEquals(2, panel.getComponentCount());
    }

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

    @Test
    void mainTest() {
        try {
            Main.main(null);
        } catch (Exception e) {
            fail("El método main lanzó una excepción: " + e.getMessage());
        }
    }

    @Test
    void createAndShowGUITest() {
        try {
            Main.createAndShowGUI();
        } catch (Exception e) {
            fail("El método createAndShowGUI lanzó una excepción: " + e.getMessage());
        }
    }
}
