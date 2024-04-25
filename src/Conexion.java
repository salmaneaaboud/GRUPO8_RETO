import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection connection;
    String nombrebd;
    String usuario = "videojuegosdb";
    String password = "zubiri";
    String url;

    public Conexion(String nombrebd){
        this.nombrebd=nombrebd;
        this.url = "jdbc:oracle:thin:@172.16.7.11:1521:ORCLCDB";
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
}
