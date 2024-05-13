package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection connection;
    String usuario = "videojuegosdb";
    String password = "zubiri";
    String url;

    public Conexion(){
        this.url = "jdbc:oracle:thin:@localhost:1521:xe";
    }

    public Connection conectar(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url,usuario,password);
            System.out.println("Connected successfully!");
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
}
