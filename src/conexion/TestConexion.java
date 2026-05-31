package conexion;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = Conexion.getConexion();
        if (conn != null) {
            System.out.println("¡La base de datos está conectada correctamente!");
        } else {
            System.out.println("Error: no se pudo conectar.");
        }
    }
}