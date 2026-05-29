/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class DiscoDuro extends Producto {

    private int capacidadGB;
    private String tipo;
    private String interfaz;
    private int velocidadRPM;

    public DiscoDuro(int idProducto, String nombre, String numeroSerie,
            String descripcion, double precioVenta, int cantidadActual,
            int cantidadMinimaPermitida, boolean esBajoPedido,
            String rutaImagen, String paisProcedencia, String fabricante,
            double pesoGramos, double largo, double ancho,
            double profundidad, int idProveedor,
            int capacidadGB, String tipo, String interfaz, int velocidadRPM) {

        super(idProducto, nombre, numeroSerie, descripcion, precioVenta,
                cantidadActual, cantidadMinimaPermitida, esBajoPedido,
                rutaImagen, paisProcedencia, fabricante, pesoGramos,
                largo, ancho, profundidad, 365, idProveedor, "DiscoDuro");

        this.capacidadGB = capacidadGB;
        this.tipo = tipo;
        this.interfaz = interfaz;
        this.velocidadRPM = velocidadRPM;
    }

    @Override
    public int calcularGarantia() {
        return 365; // 1 año de garantía
    }

    @Override
    public String getInfo() {
        return "Disco Duro: " + getNombre() +
               " | Capacidad: " + capacidadGB + "GB" +
               " | Tipo: " + tipo +
               " | Interfaz: " + interfaz +
               " | RPM: " + velocidadRPM;
    }

    // Getters y Setters
    public int getCapacidadGB() { return capacidadGB; }
    public void setCapacidadGB(int capacidadGB) { this.capacidadGB = capacidadGB; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getInterfaz() { return interfaz; }
    public void setInterfaz(String interfaz) { this.interfaz = interfaz; }

    public int getVelocidadRPM() { return velocidadRPM; }
    public void setVelocidadRPM(int velocidadRPM) { this.velocidadRPM = velocidadRPM; }
}