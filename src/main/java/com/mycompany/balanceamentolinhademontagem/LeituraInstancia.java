/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.balanceamentolinhademontagem;

import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;


 

/**
 *
 * @author USER
 */
public class LeituraInstancia {
    
    public static Instancia lerInstancia(String arquivo) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        
        int numTarefas = parseInt(br.readLine().trim());
        
        int[] custos = new int[numTarefas];
        
        for(int i=0; i<numTarefas;i++){
            custos[i] = parseInt(br.readLine().trim());
        }
        
        ArrayList<int[]> precedentes = new ArrayList<>();
        String linha;
        
        while((linha = br.readLine()) != null && !linha.equals("-1,-1")){
        String[] parte = linha.split(",");
        
        int i = parseInt(parte[0].trim());
        int j = parseInt(parte[1].trim());
        
        precedentes.add(new int[] {i, j});
        }
    
        return new Instancia(numTarefas, custos, precedentes);
    }
}
