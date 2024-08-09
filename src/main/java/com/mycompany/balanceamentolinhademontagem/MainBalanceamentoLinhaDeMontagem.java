/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.balanceamentolinhademontagem;

import static com.mycompany.balanceamentolinhademontagem.LeituraInstancia.lerInstancia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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
        
        /*
        ArrayList<Integer> adicionados = BalanceamentoLinhaDeMontagem.getAdicionados();
    
        for(Integer ad: adicionados){
            System.out.println(ad);
        }
        */
        
        
        Map<Integer, Integer> maquinas = BalanceamentoLinhaDeMontagem.getMaquinas();
        for(int key : maquinas.keySet()){
            System.out.println(key + ":" + maquinas.get(key));
        }
        
        System.out.println("-------------------------------------------------------");
        
        teste.gerarSolucao();
        
        System.out.println("-------------------------------------------------------");
        
        /*
        teste.estruturaDeVizinhanca(6);
        for(int key : maquinas.keySet()){
            System.out.println(key + ":" + maquinas.get(key));
        }*/
        
        for(int i = 1; i<BalanceamentoLinhaDeMontagem.somaMaquinas.length;i++){
            System.out.println(BalanceamentoLinhaDeMontagem.somaMaquinas[i]);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println(teste.getMax(BalanceamentoLinhaDeMontagem.somaMaquinas));
        System.out.println("-------------------------------------------------------");
        
        teste.heuristicaDePrimeiraMelhora(3);
    
        //System.out.println(teste.getMax(BalanceamentoLinhaDeMontagem.getSomaMaquinas()));
        
        for(int key : maquinas.keySet()){
            System.out.println(key + ":" + maquinas.get(key));
        }
        
        System.out.println("-------------------------------------------------------");
        
        for(int i = 1; i<BalanceamentoLinhaDeMontagem.somaMaquinas.length;i++){
            System.out.println(BalanceamentoLinhaDeMontagem.somaMaquinas[i]);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println(teste.getMax(BalanceamentoLinhaDeMontagem.somaMaquinas));
    }
}
