package controlador;

import conexion.Conexion;
import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Agregar cliente
    public boolean agregar(Cliente c) {
        String sql = "INSERT INTO Cliente (nombre, direccion, telefono, correoElectronico) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getCorreoElectronico());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correoElectronico")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar cliente
    public boolean actualizar(Cliente c) {
        String sql = "UPDATE Cliente SET nombre=?, direccion=?, telefono=?, correoElectronico=? WHERE idCliente=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getCorreoElectronico());
            ps.setInt(5, c.getIdCliente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // Eliminar cliente
    public boolean eliminar(int idCliente) {
        String sql = "DELETE FROM Cliente WHERE idCliente=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    // Buscar cliente por ID
    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT * FROM Cliente WHERE idCliente=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correoElectronico")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }
}