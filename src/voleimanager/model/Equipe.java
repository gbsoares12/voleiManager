/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voleimanager.model;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Equipe {

    private String nome;
    private int pontuacao;
    private int setsGanhos;
    private int[] pontuacaoHistorico = new int[5];

    public Equipe(String nome, int pontuacao, int setsGanhos) {
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.setsGanhos = setsGanhos;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarPontuacaoHistorico(int pontuacao, int setAtual) {
        
        this.pontuacaoHistorico[setAtual] = pontuacao;
    }

    public int[] getPontuacaoHistorico() {
        return pontuacaoHistorico;
    }
    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getSetsGanhos() {
        return setsGanhos;
    }

    public void setSetsGanhos(int setsGanhos) {
        this.setsGanhos = setsGanhos;
    }

    public void incrementarPonto() {
        this.pontuacao++;
    }

    public void incrementarSetGanho() {
        this.setsGanhos++;
    }

}
