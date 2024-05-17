package Tests.presentationTests;

import Presentation.Main;
import org.junit.jupiter.api.Test;
import javax.swing.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {

    @Test
    void createCharacterPanelTest() {
        JPanel panel = Main.createCharacterPanel("Warrior", "Strong and resilient", "./photos/warrior.jfif");
        assertNotNull(panel);
        assertEquals(2, panel.getComponentCount());
    }

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

    @Test
    void mainConnectivityTest() {
        try {
            Main.main(null);
            assertNotNull(Main.conn);
        } catch (Exception e) {
            fail("El método main lanzó una excepción: " + e.getMessage());
        } finally {
            try {
                Main.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
