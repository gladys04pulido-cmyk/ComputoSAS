/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class TarjetaGrafica extends Producto {

    private int memoriaVRAM;
    private String chipset;
    private String tipoMemoria;

    public TarjetaGrafica(int idProducto, String nombre, String numeroSerie,
            String descripcion, double precioVenta, int cantidadActual,
            int cantidadMinimaPermitida, boolean esBajoPedido,
            String rutaImagen, String paisProcedencia, String fabricante,
            double pesoGramos, double largo, double ancho,
            double profundidad, int idProveedor,
            int memoriaVRAM, String chipset, String tipoMemoria) {

        super(idProducto, nombre, numeroSerie, descripcion, precioVenta,
                cantidadActual, cantidadMinimaPermitida, esBajoPedido,
                rutaImagen, paisProcedencia, fabricante, pesoGramos,
                largo, ancho, profundidad, 1095, idProveedor, "TarjetaGrafica");

        this.memoriaVRAM = memoriaVRAM;
        this.chipset = chipset;
        this.tipoMemoria = tipoMemoria;
    }

    @Override
    public int calcularGarantia() {
        return 1095; // 3 años de garantía
    }

    @Override
    public String getInfo() {
        return "Tarjeta Gráfica: " + getNombre() +
               " | VRAM: " + memoriaVRAM + "GB" +
               " | Chipset: " + chipset +
               " | Memoria: " + tipoMemoria;
    }

    // Getters y Setters
    public int getMemoriaVRAM() { return memoriaVRAM; }
    public void setMemoriaVRAM(int memoriaVRAM) { this.memoriaVRAM = memoriaVRAM; }

    public String getChipset() { return chipset; }
    public void setChipset(String chipset) { this.chipset = chipset; }

    public String getTipoMemoria() { return tipoMemoria; }
    public void setTipoMemoria(String tipoMemoria) { this.tipoMemoria = tipoMemoria; }
}
