/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class Periferico extends Producto {

    private String tipoPeriferico;
    private String tipoConexion;

    public Periferico(int idProducto, String nombre, String numeroSerie,
            String descripcion, double precioVenta, int cantidadActual,
            int cantidadMinimaPermitida, boolean esBajoPedido,
            String rutaImagen, String paisProcedencia, String fabricante,
            double pesoGramos, double largo, double ancho,
            double profundidad, int idProveedor,
            String tipoPeriferico, String tipoConexion) {

        super(idProducto, nombre, numeroSerie, descripcion, precioVenta,
                cantidadActual, cantidadMinimaPermitida, esBajoPedido,
                rutaImagen, paisProcedencia, fabricante, pesoGramos,
                largo, ancho, profundidad, 180, idProveedor, "Periferico");

        this.tipoPeriferico = tipoPeriferico;
        this.tipoConexion = tipoConexion;
    }

    @Override
    public int calcularGarantia() {
        return 180; // 6 meses de garantía
    }

    @Override
    public String getInfo() {
        return "Periférico: " + getNombre() +
               " | Tipo: " + tipoPeriferico +
               " | Conexión: " + tipoConexion;
    }

    // Getters y Setters
    public String getTipoPeriferico() { return tipoPeriferico; }
    public void setTipoPeriferico(String tipoPeriferico) { this.tipoPeriferico = tipoPeriferico; }

    public String getTipoConexion() { return tipoConexion; }
    public void setTipoConexion(String tipoConexion) { this.tipoConexion = tipoConexion; }
}
