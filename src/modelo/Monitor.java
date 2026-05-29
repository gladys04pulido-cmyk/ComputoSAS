/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class Monitor extends Producto {

    private double pulgadas;
    private String resolucion;
    private String tipoPanel;
    private int tasaRefresco;

    public Monitor(int idProducto, String nombre, String numeroSerie,
            String descripcion, double precioVenta, int cantidadActual,
            int cantidadMinimaPermitida, boolean esBajoPedido,
            String rutaImagen, String paisProcedencia, String fabricante,
            double pesoGramos, double largo, double ancho,
            double profundidad, int idProveedor,
            double pulgadas, String resolucion, String tipoPanel, int tasaRefresco) {

        super(idProducto, nombre, numeroSerie, descripcion, precioVenta,
                cantidadActual, cantidadMinimaPermitida, esBajoPedido,
                rutaImagen, paisProcedencia, fabricante, pesoGramos,
                largo, ancho, profundidad, 730, idProveedor, "Monitor");

        this.pulgadas = pulgadas;
        this.resolucion = resolucion;
        this.tipoPanel = tipoPanel;
        this.tasaRefresco = tasaRefresco;
    }

    @Override
    public int calcularGarantia() {
        return 730; // 2 años de garantía
    }

    @Override
    public String getInfo() {
        return "Monitor: " + getNombre() +
               " | Pulgadas: " + pulgadas +
               " | Resolución: " + resolucion +
               " | Panel: " + tipoPanel +
               " | Refresco: " + tasaRefresco + "Hz";
    }

    // Getters y Setters
    public double getPulgadas() { return pulgadas; }
    public void setPulgadas(double pulgadas) { this.pulgadas = pulgadas; }

    public String getResolucion() { return resolucion; }
    public void setResolucion(String resolucion) { this.resolucion = resolucion; }

    public String getTipoPanel() { return tipoPanel; }
    public void setTipoPanel(String tipoPanel) { this.tipoPanel = tipoPanel; }

    public int getTasaRefresco() { return tasaRefresco; }
    public void setTasaRefresco(int tasaRefresco) { this.tasaRefresco = tasaRefresco; }
}


