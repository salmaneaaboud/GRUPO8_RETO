package test;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConexionTest {
    private Conexion conexion;

    @BeforeEach
    void setUp() {
        // Set up Conexion instance for each test
        conexion = new Conexion("videojuegosdb");
    }

    @Test
    void testConectar() throws SQLException {
        // Call conectar method and check if it returns a valid Connection object
        Connection connection = conexion.conectar();
        assertNotNull(connection);
        assertTrue(connection.isValid(5)); // Check if the connection is valid
    }
}
