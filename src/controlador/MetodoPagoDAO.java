package controlador;

import conexion.Conexion;
import modelo.*;
import java.sql.*;

public class MetodoPagoDAO {

    // Agregar método de pago y retornar su ID
    public int agregar(MetodoPago mp) {
        String sql = "INSERT INTO MetodoPago (tipo, monto, numeroTarjeta, titular, numeroCuotas) VALUES (?,?,?,?,?) RETURNING idMetodoPago";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(2, mp.getMonto());

            if (mp instanceof Efectivo) {
                ps.setString(1, "EFECTIVO");
                ps.setNull(3, Types.VARCHAR);
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.INTEGER);
            } else if (mp instanceof TarjetaDebito) {
                TarjetaDebito td = (TarjetaDebito) mp;
                ps.setString(1, "DEBITO");
                ps.setString(3, td.getNumeroTarjeta());
                ps.setString(4, td.getTitular());
                ps.setNull(5, Types.INTEGER);
            } else if (mp instanceof TarjetaCredito) {
                TarjetaCredito tc = (TarjetaCredito) mp;
                ps.setString(1, "CREDITO");
                ps.setString(3, tc.getNumeroTarjeta());
                ps.setString(4, tc.getTitular());
                ps.setInt(5, tc.getNumeroCuotas());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("idMetodoPago");

        } catch (SQLException e) {
            System.out.println("Error al agregar método de pago: " + e.getMessage());
        }
        return -1;
    }
}
