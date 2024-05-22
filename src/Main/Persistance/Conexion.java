package Main.Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Conexion class manages the connection to the database.
 */
public class Conexion {
    private Connection connection;
    private String usuario = "videojuegosdb";
    private String password = "zubiri";
    private String url = "jdbc:oracle:thin:@10.14.4.139:1521:orclcdb";

    /**
     * Default constructor for Conexion.
     */
    public Conexion() {}

    /**
     * Establishes a connection to the database.
     *
     * @return the Connection object representing the database connection
     */
    public Connection conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, usuario, password);
            System.out.println("Connected successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred while connecting to the database!");
        }
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public void desconectar() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the current database connection.
     *
     * @return the Connection object representing the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Retrieves the database URL.
     *
     * @return the database URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the database URL.
     *
     * @param url the new database URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Retrieves the database password.
     *
     * @return the database password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the database password.
     *
     * @param password the new database password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the database username.
     *
     * @return the database username
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the database username.
     *
     * @param usuario the new database username
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
