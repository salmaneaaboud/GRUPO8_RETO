package Tests.persistanceTests;

import Main.Persistance.Conexion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Conexion class.
 */
class ConexionTests {
    private Conexion conexion;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        conexion = new Conexion();
    }

    /**
     * Test the conectar method of the Conexion class.
     *
     * @throws SQLException if a database access error occurs or the url is null
     */
    @Test
    @DisplayName("Connection")
    @Tag("Controlador")
    void testConectar() throws SQLException {
        Connection connection = conexion.conectar();
        assertNotNull(connection);
        assertTrue(connection.isValid(5));
    }

    /**
     * Test the desconectar method of the Conexion class.
     */
    @Test
    void testDesconectar() {
        Connection conn = conexion.conectar();

        conexion.desconectar();

        assertThrows(SQLException.class, () -> conn.createStatement().executeQuery("SELECT 1 FROM DUAL"), "Connection should be closed and executing query should throw SQLException");
    }

    /**
     * Test the desconectar method of the Conexion class when handling SQLException.
     */
    @Test
    void desconectar_HandleSQLException() {
        assertThrows(RuntimeException.class, () -> {
            conexion.desconectar();
        });
    }

    /**
     * Test the getters and setters of the Conexion class.
     */
    @Test
    void testGettersAndSetters() {
        String testUrl = "jdbc:oracle:thin:@localhost:3306:test";
        String testPassword = "test";
        String testUsuario = "testUser";

        conexion.setUrl(testUrl);
        conexion.setPassword(testPassword);
        conexion.setUsuario(testUsuario);

        assertEquals(testUrl, conexion.getUrl());
        assertEquals(testPassword, conexion.getPassword());
        assertEquals(testUsuario, conexion.getUsuario());
    }
}
