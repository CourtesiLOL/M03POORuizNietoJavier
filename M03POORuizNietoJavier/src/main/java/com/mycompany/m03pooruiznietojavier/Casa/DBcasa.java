/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03pooruiznietojavier.Casa;

import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class DBcasa {

    //Guardo las casa
    private ArrayList<Casa> CasaList = new ArrayList();

    public void saveCasa(Casa x) {
        CasaList.add(x);
    }

    //devuelve la posicion de una casa concreta por nif
    public int getPositionCasa(String nif) {
        for (int i = 0; i < CasaList.size(); i++) {
            if (nif.equals(CasaList.get(i).getNif())) {
                return i;
            }

        }
        return -1;
    }

    //devuelve la casa por la posicion en la lista
    public Casa getUnCasa(int x) {
        return CasaList.get(x);
    }

    //devuelve la array completa
    public ArrayList getTotCasa() {
        return CasaList;
    }

}
