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
public class BalanceamentoLinhaDeMontagem {
    protected static final int[][] matrizPrecedentes = new int[54][54];
    private static ArrayList<Integer> maquina = new ArrayList<>();
    private static ArrayList<Integer> precTemp = new ArrayList<>();
    private static ArrayList<Integer> adicionados = new ArrayList<>();
    private static ArrayList<Integer> candidatos = new ArrayList<>();
    
    ArrayList<int[]> precedentes = Instancia.getPrecedentes();
    int[] custos = Instancia.getCustos();

    public static ArrayList<Integer> getAdicionados() {
        return adicionados;
    }
    
    public void gerarMatriz(){
        for(int i=0; i<precedentes.size();i++){
            int[] elemento = precedentes.get(i);
            
            int precedente = elemento[0];
            int atual = elemento[1];
            
            matrizPrecedentes[atual][precedente] = 1;
        }
    }
    
    
    public void LinhaDeMontagem() {
    int i = 1;    
    while(i < 54) {
        int cont = 0;
        int precedentesPendentes;
        int j = 1;

        // Limpar a lista precTemp antes de cada iteração
        precTemp.clear();

        while (j < 54) {
            if (matrizPrecedentes[i][j] == 1) {
                precTemp.add(j);
                cont++;
            }
            j++;
        }

        if (cont == 0) {
            // Se não há precedentes, adicione diretamente
            maquina.add(i);
            adicionados.add(i);
        } else {
            // Verifique os precedentes pendentes
            precedentesPendentes = verificarPrecedentesAdicionados();
            if (precedentesPendentes == 0) {
                // Se todos os precedentes foram adicionados, adicione à lista de candidatos
                candidatos.add(i);
            }else{
                valorMinimo(); // Adiciona o candidato com o menor valor 
                continue;
            }
        }
        i++;
    }
    
    if (candidatos.contains(53)) {
            maquina.add(53);
            adicionados.add(53);
            candidatos.remove((Integer) 53);
        }
}

    
    private int verificarPrecedentesAdicionados() {
        int cont = 0;
        for(int k=0; k<precTemp.size(); k++){
            if(!adicionados.contains(precTemp.get(k))){
                cont++;
            }
        }
        return cont;
    }

    private void valorMinimo() {
        while(!candidatos.isEmpty()){
        int minimo = custos[candidatos.get(0)-1];
        int posicao = 0;
        
        for(int m=1;m<candidatos.size();m++){
            if(minimo>custos[candidatos.get(m)-1]){
                minimo = custos[candidatos.get(m)-1];
                posicao = m;
            }
        }
        maquina.add(candidatos.get(posicao));
        adicionados.add(candidatos.get(posicao));
        candidatos.remove(posicao);
        }
    }
}

