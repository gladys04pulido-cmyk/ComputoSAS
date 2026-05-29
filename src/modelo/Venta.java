/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private String folio;
    private String fechaVenta;
    private String horaVenta;
    private String estatusVenta;
    private double subtotalSinIVA;
    private double totalConIVA;
    private int diasEstimadosImportacion;
    private int idCliente;
    private MetodoPago metodoPago;
    private List<LineaDeVenta> lineas;

    public Venta(String folio, int idCliente, MetodoPago metodoPago) {
        this.folio = folio;
        this.idCliente = idCliente;
        this.metodoPago = metodoPago;
        this.lineas = new ArrayList<>();
        this.fechaVenta = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.horaVenta = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.estatusVenta = "PENDIENTE";
        this.subtotalSinIVA = 0;
        this.totalConIVA = 0;
    }

    public static String generarFolio() {
        return "FOL-" + System.currentTimeMillis();
    }

    public void agregarLinea(LineaDeVenta linea) {
        lineas.add(linea);
        calcularTotal();
    }

    public void calcularTotal() {
        subtotalSinIVA = 0;
        for (LineaDeVenta linea : lineas) {
            subtotalSinIVA += linea.getSubtotal();
        }
        totalConIVA = subtotalSinIVA * 1.19; // IVA 19%
    }

    public String generarFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== FACTURA ==========\n");
        sb.append("Empresa: Cómputo S.A.S\n");
        sb.append("Folio: ").append(folio).append("\n");
        sb.append("Fecha: ").append(fechaVenta).append("\n");
        sb.append("Hora: ").append(horaVenta).append("\n");
        sb.append("Cliente ID: ").append(idCliente).append("\n");
        sb.append("-----------------------------\n");
        for (LineaDeVenta linea : lineas) {
            sb.append(linea.getProducto().getNombre())
              .append(" x").append(linea.getCantidad())
              .append(" = $").append(linea.getSubtotal()).append("\n");
        }
        sb.append("-----------------------------\n");
        sb.append("Subtotal: $").append(subtotalSinIVA).append("\n");
        sb.append("IVA (19%): $").append(totalConIVA - subtotalSinIVA).append("\n");
        sb.append("Total: $").append(totalConIVA).append("\n");
        sb.append("Pago: ").append(metodoPago.getDetalle()).append("\n");
        sb.append("=============================\n");
        return sb.toString();
    }

    public String generarGarantia() {
        StringBuilder sb = new StringBuilder();
        sb.append("======= PÓLIZA DE GARANTÍA =======\n");
        sb.append("Empresa: Cómputo S.A.S\n");
        sb.append("Folio: ").append(folio).append("\n");
        sb.append("Fecha: ").append(fechaVenta).append("\n");
        sb.append("Cliente ID: ").append(idCliente).append("\n");
        sb.append("----------------------------------\n");
        for (LineaDeVenta linea : lineas) {
            sb.append("Producto: ").append(linea.getProducto().getNombre()).append("\n");
            sb.append("Serie: ").append(linea.getProducto().getNumeroSerie()).append("\n");
            sb.append("Garantía: ").append(linea.getProducto().calcularGarantia())
              .append(" días\n");
            sb.append("----------------------------------\n");
        }
        sb.append("==================================\n");
        return sb.toString();
    }

    public String generarManifiesto() {
        StringBuilder sb = new StringBuilder();
        sb.append("====== MANIFIESTO DE IMPORTACIÓN ======\n");
        sb.append("Empresa: Cómputo S.A.S\n");
        sb.append("Folio: ").append(folio).append("\n");
        sb.append("Fecha: ").append(fechaVenta).append("\n");
        sb.append("---------------------------------------\n");
        for (LineaDeVenta linea : lineas) {
            Producto p = linea.getProducto();
            sb.append("Producto: ").append(p.getNombre()).append("\n");
            sb.append("Fabricante: ").append(p.getFabricante()).append("\n");
            sb.append("País de origen: ").append(p.getPaisProcedencia()).append("\n");
            sb.append("Peso: ").append(p.getPesoGramos()).append("g\n");
            sb.append("Dimensiones: ").append(p.getLargo()).append("x")
              .append(p.getAncho()).append("x")
              .append(p.getProfundidad()).append(" cm\n");
            sb.append("---------------------------------------\n");
        }
        sb.append("=======================================\n");
        return sb.toString();
    }

    // Getters y Setters
    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }

    public String getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(String fechaVenta) { this.fechaVenta = fechaVenta; }

    public String getHoraVenta() { return horaVenta; }
    public void setHoraVenta(String horaVenta) { this.horaVenta = horaVenta; }

    public String getEstatusVenta() { return estatusVenta; }
    public void setEstatusVenta(String estatusVenta) { this.estatusVenta = estatusVenta; }

    public double getSubtotalSinIVA() { return subtotalSinIVA; }
    public double getTotalConIVA() { return totalConIVA; }

    public int getDiasEstimadosImportacion() { return diasEstimadosImportacion; }
    public void setDiasEstimadosImportacion(int dias) { 
        this.diasEstimadosImportacion = dias; 
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public List<LineaDeVenta> getLineas() { return lineas; }
}

