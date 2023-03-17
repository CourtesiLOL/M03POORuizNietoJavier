/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03pooruiznietojavier.AparellsCasa;

/**
 *
 * @author javier
 */
public class Placa {

    private int superficie;
    private float preu;
    private float potencia;

    public Placa(int superficie, float preu, float potencia) {
        this.superficie = superficie;
        this.preu = preu;
        this.potencia = potencia;
    }

    public int getSuperficie() {
        return superficie;
    }

    public float getPreu() {
        return preu;
    }

    public float getPotencia() {
        return potencia;
    }

}
