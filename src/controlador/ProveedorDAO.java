package controlador;

import conexion.Conexion;
import modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    // Agregar proveedor
    public boolean agregar(Proveedor p) {
        String sql = "INSERT INTO Proveedor (razonSocial, direccion, telefono, correoElectronico) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getDireccion());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getCorreoElectronico());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los proveedores
    public List<Proveedor> obtenerTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor p = new Proveedor(
                    rs.getInt("idProveedor"),
                    rs.getString("razonSocial"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correoElectronico")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener proveedores: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar proveedor
    public boolean actualizar(Proveedor p) {
        String sql = "UPDATE Proveedor SET razonSocial=?, direccion=?, telefono=?, correoElectronico=? WHERE idProveedor=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getDireccion());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getCorreoElectronico());
            ps.setInt(5, p.getIdProveedor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }

    // Eliminar proveedor
    public boolean eliminar(int idProveedor) {
        String sql = "DELETE FROM Proveedor WHERE idProveedor=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }

    // Buscar proveedor por ID
    public Proveedor buscarPorId(int idProveedor) {
        String sql = "SELECT * FROM Proveedor WHERE idProveedor=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Proveedor(
                    rs.getInt("idProveedor"),
                    rs.getString("razonSocial"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correoElectronico")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar proveedor: " + e.getMessage());
        }
        return null;
    }
}
