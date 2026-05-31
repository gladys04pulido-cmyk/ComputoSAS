package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://ep-misty-brook-acyk0j07-pooler.sa-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require";
    private static final String USUARIO = "neondb_owner";
    private static final String CONTRASENA = "npg_6ICaUwBt2Mmd";

    public static Connection getConexion() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a Neon!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conn;
    }
}
