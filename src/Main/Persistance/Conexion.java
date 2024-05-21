package Main.Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection connection;
    private String usuario = "videojuegosdb";
    private String password = "zubiri";
    private String url = "jdbc:oracle:thin:@10.14.4.139:1521:orclcdb";

    public Conexion(){}

    public Connection conectar(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url,usuario,password);
            System.out.println("Connected successfully!");
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error was found connecting to the database!");
        }
        return connection;
    }

    public void desconectar(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
