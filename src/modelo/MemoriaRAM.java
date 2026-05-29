/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class MemoriaRAM extends Producto {

    private int capacidadGB;
    private String tipoDDR;
    private int frecuenciaMHz;

    public MemoriaRAM(int idProducto, String nombre, String numeroSerie,
            String descripcion, double precioVenta, int cantidadActual,
            int cantidadMinimaPermitida, boolean esBajoPedido,
            String rutaImagen, String paisProcedencia, String fabricante,
            double pesoGramos, double largo, double ancho,
            double profundidad, int idProveedor,
            int capacidadGB, String tipoDDR, int frecuenciaMHz) {

        super(idProducto, nombre, numeroSerie, descripcion, precioVenta,
                cantidadActual, cantidadMinimaPermitida, esBajoPedido,
                rutaImagen, paisProcedencia, fabricante, pesoGramos,
                largo, ancho, profundidad, 365, idProveedor, "MemoriaRAM");

        this.capacidadGB = capacidadGB;
        this.tipoDDR = tipoDDR;
        this.frecuenciaMHz = frecuenciaMHz;
    }

    @Override
    public int calcularGarantia() {
        return 365; // 1 año de garantía
    }

    @Override
    public String getInfo() {
        return "Memoria RAM: " + getNombre() +
               " | Capacidad: " + capacidadGB + "GB" +
               " | Tipo: " + tipoDDR +
               " | Frecuencia: " + frecuenciaMHz + "MHz";
    }

    // Getters y Setters
    public int getCapacidadGB() { return capacidadGB; }
    public void setCapacidadGB(int capacidadGB) { this.capacidadGB = capacidadGB; }

    public String getTipoDDR() { return tipoDDR; }
    public void setTipoDDR(String tipoDDR) { this.tipoDDR = tipoDDR; }

    public int getFrecuenciaMHz() { return frecuenciaMHz; }
    public void setFrecuenciaMHz(int frecuenciaMHz) { this.frecuenciaMHz = frecuenciaMHz; }
}

