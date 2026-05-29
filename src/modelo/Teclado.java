/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class Teclado extends Producto {

    private String tipoConexion;
    private boolean tieneIluminacion;
    private String layout;

    public Teclado(int idProducto, String nombre, String numeroSerie,
            String descripcion, double precioVenta, int cantidadActual,
            int cantidadMinimaPermitida, boolean esBajoPedido,
            String rutaImagen, String paisProcedencia, String fabricante,
            double pesoGramos, double largo, double ancho,
            double profundidad, int idProveedor,
            String tipoConexion, boolean tieneIluminacion, String layout) {

        super(idProducto, nombre, numeroSerie, descripcion, precioVenta,
                cantidadActual, cantidadMinimaPermitida, esBajoPedido,
                rutaImagen, paisProcedencia, fabricante, pesoGramos,
                largo, ancho, profundidad, 365, idProveedor, "Teclado");

        this.tipoConexion = tipoConexion;
        this.tieneIluminacion = tieneIluminacion;
        this.layout = layout;
    }

    @Override
    public int calcularGarantia() {
        return 365; // 1 año de garantía
    }

    @Override
    public String getInfo() {
        return "Teclado: " + getNombre() +
               " | Conexión: " + tipoConexion +
               " | Iluminación: " + (tieneIluminacion ? "Sí" : "No") +
               " | Layout: " + layout;
    }

    // Getters y Setters
    public String getTipoConexion() { return tipoConexion; }
    public void setTipoConexion(String tipoConexion) { this.tipoConexion = tipoConexion; }

    public boolean isTieneIluminacion() { return tieneIluminacion; }
    public void setTieneIluminacion(boolean tieneIluminacion) { this.tieneIluminacion = tieneIluminacion; }

    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
}
