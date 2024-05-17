package Tests.presentationTests;

import Presentation.AdminWindow;
import Persistance.Conexion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class adminWindowTests {
    private Connection connection;

    @BeforeEach
    void setUp() {
        Conexion conexion = new Conexion();
        connection = conexion.conectar();
    }

    @AfterEach
    void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createAdminPanel_validConnection_success() {
        AdminWindow adminWindow = new AdminWindow(connection);
        assertNotNull(adminWindow);
        assertNotNull(adminWindow.getConnection());
    }

    @Test
    void createAdminPanel_nullConnection_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new AdminWindow(null));
    }

}
