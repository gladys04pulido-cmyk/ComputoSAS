/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class TarjetaDebito extends MetodoPago {

    private String numeroTarjeta;
    private String titular;

    public TarjetaDebito(double monto, String numeroTarjeta, String titular) {
        super(monto);
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
    }

    @Override
    public boolean procesarPago() {
        System.out.println("Pago con tarjeta débito procesado.");
        System.out.println("Titular: " + titular);
        System.out.println("Monto: $" + getMonto());
        return true;
    }

    @Override
    public String getDetalle() {
        return "Método de pago: Tarjeta Débito" +
               " | Titular: " + titular +
               " | Tarjeta: **** **** **** " + 
               numeroTarjeta.substring(numeroTarjeta.length() - 4) +
               " | Monto: $" + getMonto();
    }

    // Getters y Setters
    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { 
        this.numeroTarjeta = numeroTarjeta; 
    }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }
}
