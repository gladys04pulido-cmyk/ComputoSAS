package controlador;

import conexion.Conexion;
import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Agregar producto
    public boolean agregar(Producto p) {
        String sql = "INSERT INTO Producto (nombre, numeroSerie, descripcion, precioVenta, cantidadActual, cantidadMinimaPermitida, esBajoPedido, rutaImagen, paisProcedencia, fabricante, pesoGramos, largo, ancho, profundidad, duracionGarantiaDias, tipoProducto, tipoConexion, tieneIluminacion, layout, pulgadas, resolucion, tipoPanel, tasaRefresco, capacidadGB, tipoAlmacenamiento, interfaz, velocidadRPM, tipoDDR, frecuenciaMHz, memoriaVRAM, chipset, tipoMemoria, tipoPeriferico, idProveedor) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getNumeroSerie());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecioVenta());
            ps.setInt(5, p.getCantidadActual());
            ps.setInt(6, p.getCantidadMinimaPermitida());
            ps.setBoolean(7, p.isEsBajoPedido());
            ps.setString(8, p.getRutaImagen());
            ps.setString(9, p.getPaisProcedencia());
            ps.setString(10, p.getFabricante());
            ps.setDouble(11, p.getPesoGramos());
            ps.setDouble(12, p.getLargo());
            ps.setDouble(13, p.getAncho());
            ps.setDouble(14, p.getProfundidad());
            ps.setInt(15, p.getDuracionGarantiaDias());
            ps.setString(16, p.getTipoProducto());

            // Atributos especificos segun tipo
            if (p instanceof Teclado) {
                Teclado t = (Teclado) p;
                ps.setString(17, t.getTipoConexion());
                ps.setBoolean(18, t.isTieneIluminacion());
                ps.setString(19, t.getLayout());
            } else {
                ps.setNull(17, Types.VARCHAR);
                ps.setNull(18, Types.BOOLEAN);
                ps.setNull(19, Types.VARCHAR);
            }

            if (p instanceof Monitor) {
                Monitor m = (Monitor) p;
                ps.setDouble(20, m.getPulgadas());
                ps.setString(21, m.getResolucion());
                ps.setString(22, m.getTipoPanel());
                ps.setInt(23, m.getTasaRefresco());
            } else {
                ps.setNull(20, Types.DOUBLE);
                ps.setNull(21, Types.VARCHAR);
                ps.setNull(22, Types.VARCHAR);
                ps.setNull(23, Types.INTEGER);
            }

            if (p instanceof DiscoDuro) {
                DiscoDuro d = (DiscoDuro) p;
                ps.setInt(24, d.getCapacidadGB());
                ps.setString(25, d.getTipo());
                ps.setString(26, d.getInterfaz());
                ps.setInt(27, d.getVelocidadRPM());
            } else if (p instanceof MemoriaRAM) {
                MemoriaRAM r = (MemoriaRAM) p;
                ps.setInt(24, r.getCapacidadGB());
                ps.setNull(25, Types.VARCHAR);
                ps.setNull(26, Types.VARCHAR);
                ps.setNull(27, Types.INTEGER);
            } else {
                ps.setNull(24, Types.INTEGER);
                ps.setNull(25, Types.VARCHAR);
                ps.setNull(26, Types.VARCHAR);
                ps.setNull(27, Types.INTEGER);
            }

            if (p instanceof MemoriaRAM) {
                MemoriaRAM r = (MemoriaRAM) p;
                ps.setString(28, r.getTipoDDR());
                ps.setInt(29, r.getFrecuenciaMHz());
            } else {
                ps.setNull(28, Types.VARCHAR);
                ps.setNull(29, Types.INTEGER);
            }

            if (p instanceof TarjetaGrafica) {
                TarjetaGrafica tg = (TarjetaGrafica) p;
                ps.setInt(30, tg.getMemoriaVRAM());
                ps.setString(31, tg.getChipset());
                ps.setString(32, tg.getTipoMemoria());
            } else {
                ps.setNull(30, Types.INTEGER);
                ps.setNull(31, Types.VARCHAR);
                ps.setNull(32, Types.VARCHAR);
            }

            if (p instanceof Periferico) {
                Periferico per = (Periferico) p;
                ps.setString(33, per.getTipoPeriferico());
            } else {
                ps.setNull(33, Types.VARCHAR);
            }

            ps.setInt(34, p.getIdProveedor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = construirProducto(rs);
                if (p != null) lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return lista;
    }

    // Buscar producto por ID
    public Producto buscarPorId(int idProducto) {
        String sql = "SELECT * FROM Producto WHERE idProducto=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return construirProducto(rs);
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return null;
    }

    // Eliminar producto
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM Producto WHERE idProducto=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    // Construir objeto Producto desde ResultSet
    private Producto construirProducto(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipoProducto");
        int id = rs.getInt("idProducto");
        String nombre = rs.getString("nombre");
        String serie = rs.getString("numeroSerie");
        String desc = rs.getString("descripcion");
        double precio = rs.getDouble("precioVenta");
        int cantActual = rs.getInt("cantidadActual");
        int cantMin = rs.getInt("cantidadMinimaPermitida");
        boolean bajoPedido = rs.getBoolean("esBajoPedido");
        String imagen = rs.getString("rutaImagen");
        String pais = rs.getString("paisProcedencia");
        String fab = rs.getString("fabricante");
        double peso = rs.getDouble("pesoGramos");
        double largo = rs.getDouble("largo");
        double ancho = rs.getDouble("ancho");
        double prof = rs.getDouble("profundidad");
        int idProv = rs.getInt("idProveedor");

        switch (tipo) {
            case "Teclado":
                return new Teclado(id, nombre, serie, desc, precio, cantActual,
                        cantMin, bajoPedido, imagen, pais, fab, peso, largo,
                        ancho, prof, idProv,
                        rs.getString("tipoConexion"),
                        rs.getBoolean("tieneIluminacion"),
                        rs.getString("layout"));
            case "Monitor":
                return new Monitor(id, nombre, serie, desc, precio, cantActual,
                        cantMin, bajoPedido, imagen, pais, fab, peso, largo,
                        ancho, prof, idProv,
                        rs.getDouble("pulgadas"),
                        rs.getString("resolucion"),
                        rs.getString("tipoPanel"),
                        rs.getInt("tasaRefresco"));
            case "DiscoDuro":
                return new DiscoDuro(id, nombre, serie, desc, precio, cantActual,
                        cantMin, bajoPedido, imagen, pais, fab, peso, largo,
                        ancho, prof, idProv,
                        rs.getInt("capacidadGB"),
                        rs.getString("tipoAlmacenamiento"),
                        rs.getString("interfaz"),
                        rs.getInt("velocidadRPM"));
            case "MemoriaRAM":
                return new MemoriaRAM(id, nombre, serie, desc, precio, cantActual,
                        cantMin, bajoPedido, imagen, pais, fab, peso, largo,
                        ancho, prof, idProv,
                        rs.getInt("capacidadGB"),
                        rs.getString("tipoDDR"),
                        rs.getInt("frecuenciaMHz"));
            case "TarjetaGrafica":
                return new TarjetaGrafica(id, nombre, serie, desc, precio, cantActual,
                        cantMin, bajoPedido, imagen, pais, fab, peso, largo,
                        ancho, prof, idProv,
                        rs.getInt("memoriaVRAM"),
                        rs.getString("chipset"),
                        rs.getString("tipoMemoria"));
            case "Periferico":
                return new Periferico(id, nombre, serie, desc, precio, cantActual,
                        cantMin, bajoPedido, imagen, pais, fab, peso, largo,
                        ancho, prof, idProv,
                        rs.getString("tipoPeriferico"),
                        rs.getString("tipoConexion"));
            default:
                return null;
        }
    }
}