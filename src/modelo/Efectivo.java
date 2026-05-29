/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

public class Efectivo extends MetodoPago {

    public Efectivo(double monto) {
        super(monto);
    }

    @Override
    public boolean procesarPago() {
        System.out.println("Pago en efectivo procesado por: $" + getMonto());
        return true;
    }

    @Override
    public String getDetalle() {
        return "Método de pago: Efectivo | Monto: $" + getMonto();
    }
}
