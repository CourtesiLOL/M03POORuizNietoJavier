/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03pooruiznietojavier.AparellsCasa;

/**
 *
 * @author javier
 */
public class Aparell {

    private String descript;
    private int potencia;
    private boolean ences = false;

    public Aparell(String descript, int potencia) {
        this.descript = descript;
        this.potencia = potencia;
    }

    public String getDescript() {
        return descript;
    }

    public int getPotencia() {
        return potencia;
    }

    public boolean isEnces() {
        return ences;
    }

    public void onAparell() {
        ences = true;
    }

    public void offAparell() {
        ences = false;
    }

}
