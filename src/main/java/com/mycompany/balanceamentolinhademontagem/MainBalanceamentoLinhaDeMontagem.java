/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.balanceamentolinhademontagem;

import static com.mycompany.balanceamentolinhademontagem.LeituraInstancia.lerInstancia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author USER
 */
public class MainBalanceamentoLinhaDeMontagem {
    public static void main(String args[]) throws IOException{
    Instancia instancia = lerInstancia("C:\\Users\\USER\\Downloads\\HAHN\\HAHN.IN2");
    
    instancia.getNumTarefas();
    
        System.out.println(instancia.getNumTarefas());
        
        System.out.println("-------------------------------------------------------");
        
        int[] custos = Instancia.getCustos();
        for(int custo: custos){
            System.out.println(custo);
        }
        
        System.out.println("-------------------------------------------------------");
    
        ArrayList<int[]> precedentes = Instancia.getPrecedentes();
        
        for(int[] precedente: precedentes){
            System.out.println(Arrays.toString(precedente));
        }
        
        BalanceamentoLinhaDeMontagem teste = new BalanceamentoLinhaDeMontagem();
        
        teste.gerarMatriz();
        teste.LinhaDeMontagem();
        
        System.out.println("-------------------------------------------------------");
        
        for(int i=0; i<54;i++){
            String escrita = "";
            for(int j=0; j<54;j++){
                escrita += BalanceamentoLinhaDeMontagem.matrizPrecedentes[i][j] + " ";
            }
            System.out.println(escrita);
        }
        
        System.out.println("-------------------------------------------------------");
        
        ArrayList<Integer> adicionados = BalanceamentoLinhaDeMontagem.getAdicionados();
    
        for(Integer ad: adicionados){
            System.out.println(ad);
        }
    }
}
