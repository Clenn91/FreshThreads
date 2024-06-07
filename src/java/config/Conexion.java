package config;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/bdventas";
    String user="root";
    String pass="123456";
    public Connection Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace(); // Esto imprimirá el stack trace completo del error
        }
        return con;
    }
}