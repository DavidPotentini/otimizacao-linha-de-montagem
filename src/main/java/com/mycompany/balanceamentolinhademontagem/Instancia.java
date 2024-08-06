/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.balanceamentolinhademontagem;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Instancia {
    private int numTarefas;
    private static int[] custos;
    private static ArrayList<int[]> precedentes;
    
    public Instancia(int numTarefas, int[] custos, ArrayList<int[]> precedentes){
        this.numTarefas = numTarefas;
        this.custos = custos;
        this.precedentes = precedentes;
    }
    
    public int getNumTarefas(){
        return numTarefas;
    }
    
    public static int[] getCustos(){
        return custos;
    }
    
    public static ArrayList<int[]> getPrecedentes(){
        return precedentes;
    }
}
