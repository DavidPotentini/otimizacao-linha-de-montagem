/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.balanceamentolinhademontagem;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class BalanceamentoLinhaDeMontagem {
    protected static final int[][] matrizPrecedentes = new int[54][54];
    //private static ArrayList<Integer> maquina = new ArrayList<>();
    private static ArrayList<Integer> precTemp = new ArrayList<>();
    private static ArrayList<Integer> adicionados = new ArrayList<>();
    private static ArrayList<Integer> candidatos = new ArrayList<>();
    public static int[] somaMaquinas = new int[11];//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static ArrayList<Integer> chavesParaMover = new ArrayList<>();
    
    ArrayList<int[]> precedentes = Instancia.getPrecedentes();
    int[] custos = Instancia.getCustos();
    
    private static Map<Integer, Integer> maquinas = new LinkedHashMap<>();
    private static int maquinaAtual = 1;
    private static int tarefasNaMaquina = 0;
    
    //public static Map<Integer, Integer> sortedMap = new LinkedHashMap<>();

    public static ArrayList<Integer> getAdicionados() {
        return adicionados;
    }
    
    public static Map<Integer, Integer> getMaquinas(){
        return maquinas;
    }

    public static int[] getSomaMaquinas() {
        return somaMaquinas;
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
            tarefasParaMaquinas(i);
            /*maquina.add(i);
            adicionados.add(i);*/
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
    
    if (!candidatos.isEmpty()) {
        /*
            maquina.add(candidatos.get(0));
            adicionados.add(candidatos.get(0));*/
            tarefasParaMaquinas(candidatos.get(0));
            candidatos.remove(0);
        }
    }

    private void tarefasParaMaquinas(int candidato){
        adicionados.add(candidato);
        maquinas.put(candidato, maquinaAtual);
        tarefasNaMaquina++;
        
        if(tarefasNaMaquina == 5 && maquinaAtual < 10){
        maquinaAtual++;
        tarefasNaMaquina = 0;
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
        /*
        maquina.add(candidatos.get(posicao));
        adicionados.add(candidatos.get(posicao));
        */
        tarefasParaMaquinas(candidatos.get(posicao));
        candidatos.remove(posicao);
        }
    }
    
    public void gerarSolucao(){
        //Limpa o vetor cálculo antes de repopulá-lo
        for(int i=1; i<somaMaquinas.length;i++){
            somaMaquinas[i] = 0;
        }
        
        //Popula o vetor somando todas as tarefas de uma determinada máquina
        //OBS: A posição i em que a soma é feita indica a máquina
        for(int key : maquinas.keySet()){
           int i = maquinas.get(key);
           int j = custos[key - 1];
           
           somaMaquinas[i] += j; 
        }
        /*
        for(int i = 1; i<somaMaquinas.length;i++){
            System.out.println(somaMaquinas[i]);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println(getMax(somaMaquinas));
        */
    }
    
    public int getMax(int[] valorMaquinas){//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int maiorValor = 0;
        
        for(int i=1; i<valorMaquinas.length;i++){
            if(maiorValor<valorMaquinas[i]){
                maiorValor = valorMaquinas[i];
            }
        }
        
        return maiorValor;
    }
    
        public void estruturaDeVizinhanca(int maquinaAtual){
            //Move as duas primeiras chaves de uma maquina para as duas últimas posições da maquina anterior
            Map<Integer, Integer> maquinaTemp = new LinkedHashMap<>();

            if(maquinaAtual != 1){
                if(somaMaquinas[maquinaAtual] > somaMaquinas[maquinaAtual - 1]){

                    for(Map.Entry<Integer, Integer> entrada: maquinas.entrySet()){
                        if(entrada.getValue() == maquinaAtual && chavesParaMover.size()<2){
                        chavesParaMover.add(entrada.getKey());
                        }
                    }

                    for(Integer chave:chavesParaMover){
                        maquinas.remove(chave);
                    }

                    /*for(Integer chave:chavesParaMover){
                        maquinas.put(chave, maquinaAtual - 1);
                    }*/

                    for(Integer chave: maquinas.keySet()){
                        if(maquinas.get(chave) == maquinaAtual){
                            for(int i=0;i<chavesParaMover.size();i++){//modificar caso limitar o numero de valores movidos para 1
                                maquinaTemp.put(chavesParaMover.get(i), maquinaAtual - 1);
                            }
                        }
                        maquinaTemp.put(chave, maquinas.get(chave));
                    }
                }
            }else{
                if(somaMaquinas[maquinaAtual] > somaMaquinas[maquinaAtual + 1]){
                    List<Map.Entry<Integer, Integer>> listaReversa = new ArrayList<>(maquinas.entrySet());

                    Collections.reverse(listaReversa);

                    for(Map.Entry<Integer, Integer> entrada: listaReversa){
                        if(entrada.getValue() == maquinaAtual && chavesParaMover.size() < 2){
                            chavesParaMover.add(entrada.getKey());
                        }
                    }

                    for(Integer chave:chavesParaMover){
                        maquinas.remove(chave);
                    }
                    //Map<Integer, Integer> maquinaTemp = new LinkedHashMap<>();

                    for(Integer chave: maquinas.keySet()){
                        if(maquinas.get(chave) == maquinaAtual + 1){
                            for(int i=chavesParaMover.size()-1;i>=0;i--){//modificar caso limitar o numero de valores movidos para 1
                                maquinaTemp.put(chavesParaMover.get(i), maquinaAtual + 1);
                            }
                        }
                        maquinaTemp.put(chave, maquinas.get(chave));
                    }
                }
            }
            /*
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(maquinas.entrySet());

            // Ordene a lista de entradas pelo valor
            entryList.sort(Map.Entry.comparingByValue());

            // Converta a lista de volta para um LinkedHashMap para preservar a ordem
            maquinas.clear();

            for (Map.Entry<Integer, Integer> entry : entryList) {
                maquinas.put(entry.getKey(), entry.getValue());
            }
            */

           if (!maquinaTemp.isEmpty()) {
                chavesParaMover.clear();
                maquinas.clear();
                maquinas.putAll(maquinaTemp);
            }
        }
    
    public void heuristicaDePrimeiraMelhora(int numeroDeIteracoes){
        int melhorCusto = getMax(somaMaquinas);
        int numeroDeMaquinas = 10;
                
        for(int i=0;i<numeroDeIteracoes;i++){
            for(int j=1;j<=numeroDeMaquinas;j++){
                estruturaDeVizinhanca(j);
                
                gerarSolucao();
                int novoCusto = getMax(somaMaquinas);
                
                if(novoCusto < melhorCusto){
                break;
                } 
                
            }
        }
        
        
        
    }
}

