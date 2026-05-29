/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class TarjetaCredito extends MetodoPago {

    private String numeroTarjeta;
    private String titular;
    private int numeroCuotas;

    public TarjetaCredito(double monto, String numeroTarjeta, 
            String titular, int numeroCuotas) {
        super(monto);
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.numeroCuotas = numeroCuotas;
    }

    @Override
    public boolean procesarPago() {
        System.out.println("Pago con tarjeta crédito procesado.");
        System.out.println("Titular: " + titular);
        System.out.println("Cuotas: " + numeroCuotas);
        System.out.println("Monto por cuota: $" + getMonto() / numeroCuotas);
        return true;
    }

    @Override
    public String getDetalle() {
        return "Método de pago: Tarjeta Crédito" +
               " | Titular: " + titular +
               " | Tarjeta: **** **** **** " +
               numeroTarjeta.substring(numeroTarjeta.length() - 4) +
               " | Cuotas: " + numeroCuotas +
               " | Monto: $" + getMonto();
    }

    // Getters y Setters
    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { 
        this.numeroTarjeta = numeroTarjeta; 
    }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public int getNumeroCuotas() { return numeroCuotas; }
    public void setNumeroCuotas(int numeroCuotas) { 
        this.numeroCuotas = numeroCuotas; 
    }
}