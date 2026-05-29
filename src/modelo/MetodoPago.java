/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public abstract class MetodoPago {

    private double monto;

    public MetodoPago(double monto) {
        this.monto = monto;
    }

    // Métodos abstractos
    public abstract boolean procesarPago();
    public abstract String getDetalle();

    // Getters y Setters
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

}
