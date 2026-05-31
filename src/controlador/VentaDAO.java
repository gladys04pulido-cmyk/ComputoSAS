package controlador;

import conexion.Conexion;
import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    // Agregar venta
    public int agregar(Venta v, int idMetodoPago) {
        String sql = "INSERT INTO Venta (folio, fechaVenta, horaVenta, estatusVenta, subtotalSinIVA, totalConIVA, diasEstimadosImportacion, idCliente, idMetodoPago) VALUES (?,?,?,?,?,?,?,?,?) RETURNING idVenta";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getFolio());
            ps.setString(2, v.getFechaVenta());
            ps.setString(3, v.getHoraVenta());
            ps.setString(4, v.getEstatusVenta());
            ps.setDouble(5, v.getSubtotalSinIVA());
            ps.setDouble(6, v.getTotalConIVA());
            ps.setInt(7, v.getDiasEstimadosImportacion());
            ps.setInt(8, v.getIdCliente());
            ps.setInt(9, idMetodoPago);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("idVenta");
        } catch (SQLException e) {
            System.out.println("Error al agregar venta: " + e.getMessage());
        }
        return -1;
    }

    // Agregar lineas de venta
    public boolean agregarLineas(int idVenta, java.util.List<modelo.LineaDeVenta> lineas) {
        String sql = "INSERT INTO LineaVenta (cantidad, precioUnitario, subtotal, idVenta, idProducto) VALUES (?,?,?,?,?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (modelo.LineaDeVenta linea : lineas) {
                ps.setInt(1, linea.getCantidad());
                ps.setDouble(2, linea.getPrecioUnitario());
                ps.setDouble(3, linea.getSubtotal());
                ps.setInt(4, idVenta);
                ps.setInt(5, linea.getProducto().getIdProducto());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar lineas: " + e.getMessage());
            return false;
        }
    }

    // Obtener todas las ventas
    public List<Venta> obtenerTodas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta(
                    rs.getString("folio"),
                    rs.getInt("idCliente"),
                    null
                );
                v.setEstatusVenta(rs.getString("estatusVenta"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas: " + e.getMessage());
        }
        return lista;
    }

    // Obtener ventas pendientes
    public List<Venta> obtenerPendientes() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE estatusVenta='PENDIENTE'";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta(
                    rs.getString("folio"),
                    rs.getInt("idCliente"),
                    null
                );
                v.setEstatusVenta(rs.getString("estatusVenta"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pendientes: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar estatus de venta
    public boolean actualizarEstatus(String folio, String estatus) {
        String sql = "UPDATE Venta SET estatusVenta=? WHERE folio=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estatus);
            ps.setString(2, folio);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estatus: " + e.getMessage());
            return false;
        }
    }

    // Buscar venta por folio
    public Venta buscarPorFolio(String folio) {
        String sql = "SELECT * FROM Venta WHERE folio=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, folio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Venta v = new Venta(
                    rs.getString("folio"),
                    rs.getInt("idCliente"),
                    null
                );
                v.setEstatusVenta(rs.getString("estatusVenta"));
                return v;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar venta: " + e.getMessage());
        }
        return null;
    }
}