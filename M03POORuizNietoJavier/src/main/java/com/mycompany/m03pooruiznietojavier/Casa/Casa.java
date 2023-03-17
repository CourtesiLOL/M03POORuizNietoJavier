/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03pooruiznietojavier.Casa;

import com.mycompany.m03pooruiznietojavier.AparellsCasa.Aparell;
import com.mycompany.m03pooruiznietojavier.AparellsCasa.Placa;
import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class Casa {

    private String nif;
    private String nom;
    private int superficie;
    private boolean plomos = true;
    private ArrayList<Placa> placasC = new ArrayList();
    private ArrayList<Aparell> aparellC = new ArrayList();

    public Casa(String nif, String nom, int superficie) {
        this.nif = nif;
        this.nom = nom;
        this.superficie = superficie;
    }

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public int getSuperficie() {
        return superficie;
    }

    public boolean getPlomos() {
        return plomos;
    }

    public ArrayList getTotPlacas() {
        return placasC;
    }

    public ArrayList getTotAparells() {
        return aparellC;
    }

    public Aparell getUnAparell(int x) {
        return aparellC.get(x);
    }

    public int getPositionAparell(String descript) {
        for (int i = 0; i < aparellC.size(); i++) {
            if (descript.equals(aparellC.get(i).getDescript())) {
                return i;
            }

        }
        return -1;
    }

    public ArrayList aparellEnces() {
        ArrayList<Integer> v = new ArrayList();
        for (int i = 0; i < aparellC.size(); i++) {
            if (aparellC.get(i).isEnces() == true) {
                v.add(i);
            }
        }
        return v;
    }

    public void addplaca(Placa x) {
        placasC.add(x);
    }

    public void addaparell(Aparell x) {
        aparellC.add(x);
    }

    public void plomosOn() {
        plomos = true;
    }

    public void plomosOff() {
        plomos = false;
    }

    //Apaga todot els aparell de la llista
    public void aparellsOff() {
        for (Aparell x : aparellC) {
            x.offAparell();
        }
    }

    public boolean espacioLibre(int sp) {
        int x = 0;
        for (Placa o : placasC) {
            x += o.getSuperficie();
        }
        x += sp;
        if (x > superficie) {
            return true;
        } else {
            return false;
        }
    }
    //Calcula el precio total sumando el valor 'precio' de todas las placas
    public float PlacaPreuTotal() {
        float x = 0;
        for (int i = 0; i < placasC.size(); i++) {
            x += placasC.get(i).getPreu();
        }
        return x;
    }
    
    public int PlacaPotenciaTotal() {
        int x = 0;
        for (int i = 0; i < placasC.size(); i++) {
            x += placasC.get(i).getPotencia();
        }
        return x;
    }
    //Calcula la potencia total sumando el valor 'potencia' de todas las placas
    public int AparellConsumTotal(int y) {
        int x = 0;
        for (int i = 0; i < aparellC.size(); i++) {
            if (aparellC.get(i).isEnces() == true) {
                x += aparellC.get(i).getPotencia();
            } else {
                x += 0;
            }

        }
        x += y;
        return x;
    }
    
    public int teuladaLliure() {
        int x = 0;
        for (Placa o : placasC) {
            x += o.getSuperficie();
        }
        return (superficie-x);
    }

}
