/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voleimanager.model;

/**
 *
 * @author gabriel
 */
public class PartidaVolei {

    private Equipe equipe1;
    private Equipe equipe2;
    private String horario;
    private int setAtual;
    private Equipe vencedor;

    public PartidaVolei(Equipe equipe1, Equipe equipe2, String horario) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.horario = horario;
    }

    public Equipe getVencedor() {
        return vencedor;
    }

    public void setVencedor(Equipe vencedor) {
        this.vencedor = vencedor;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getSetAtual() {
        return setAtual;
    }

    public void setSetAtual(int setAtual) {
        this.setAtual = setAtual;
    }

    public void incrementarSetPartida() {
        this.setAtual++;
    }

}
