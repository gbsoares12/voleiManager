/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voleimanager.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import voleimanager.model.Equipe;
import voleimanager.model.PartidaVolei;

/**
 *
 * @author gabriel
 */
public class FXMLDocumentController implements Initializable {

    Date dataHoraAtual = new Date();

    private PartidaVolei partida;

    @FXML
    private Label horaLabel;

    @FXML
    private Pane pontuacoesEquipe1Pane;

    @FXML
    private Pane pontuacoesEquipe2Pane;

    @FXML
    private Button iniciarButton;

    @FXML
    private Button adicionarPontoEquipe1Button;

    @FXML
    private Button adicionarPontoEquipe2Button;

    @FXML
    private TextField horaTextField;

    @FXML
    private TextField equipe1TextField;

    @FXML
    private TextField equipe2TextField;

    @FXML
    private Label nomeEquipe1Label;

    @FXML
    private Label nomeEquipe2Label;

    @FXML
    private Label pontuacaoEquipe1Label;

    @FXML
    private Label setsEquipe1Label;

    @FXML
    private Label pontuacaoEquipe2Label;

    @FXML
    private Label setsEquipe2Label;

    @FXML
    private void IniciarHandleButtonAction(ActionEvent event) {

        if (equipe1TextField.getText().isEmpty()) {
            equipe1TextField.setText("Equipe A");
        }
        if (equipe2TextField.getText().isEmpty()) {
            equipe2TextField.setText("Equipe B");
        }
        if (horaTextField.getText().isEmpty()) {
            String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
            horaTextField.setText(hora);
        }

        Equipe equipe1 = new Equipe(equipe1TextField.getText(), 0, 0);
        Equipe equipe2 = new Equipe(equipe2TextField.getText(), 0, 0);
        partida = new PartidaVolei(equipe1, equipe2, horaTextField.getText());

        nomeEquipe1Label.setText(partida.getEquipe1().getNome());
        nomeEquipe2Label.setText(partida.getEquipe2().getNome());

        pontuacaoEquipe1Label.setText(equipe1.getPontuacao() + "");
        pontuacaoEquipe2Label.setText(equipe2.getPontuacao() + "");

        setsEquipe1Label.setText(equipe1.getSetsGanhos() + "");
        setsEquipe2Label.setText(equipe2.getSetsGanhos() + "");

        pontuacoesEquipe1Pane.disableProperty().set(false);
        pontuacoesEquipe2Pane.disableProperty().set(false);

        adicionarPontoEquipe1Button.disableProperty().set(false);
        adicionarPontoEquipe2Button.disableProperty().set(false);

        equipe1TextField.disableProperty().set(true);
        equipe2TextField.disableProperty().set(true);

        horaTextField.disableProperty().set(true);
    }

    @FXML
    private void AdicionarPontoEquipe1HandleButtonAction(ActionEvent event) {
        adicionaPontoParaEquipeAtual(this.partida.getEquipe1(), this.partida.getEquipe2());
    }

    @FXML
    private void AdicionarPontoEquipe2HandleButtonAction(ActionEvent event) {
        adicionaPontoParaEquipeAtual(this.partida.getEquipe2(), this.partida.getEquipe1());
    }

    void adicionaPontoParaEquipeAtual(Equipe equipeAtual, Equipe equipeAdversaria) {
        if (identificaTerminoDoSet(equipeAtual, equipeAdversaria, partida)) {
            // Adiciona os pontos no histórico da partida
            equipeAtual.adicionarPontuacaoHistorico(equipeAtual.getPontuacao() != 0
                    ? equipeAtual.getPontuacao() + 1 : equipeAtual.getPontuacao(),
                    this.partida.getSetAtual());
            equipeAdversaria.adicionarPontuacaoHistorico(equipeAdversaria.getPontuacao(),
                    this.partida.getSetAtual());
            equipeAdversaria.setPontuacao(0);
            equipeAtual.setPontuacao(0);
            // Encerra o set
            if (this.partida.getEquipe1() == equipeAtual) {
                this.pontuacaoEquipe1Label.setText(equipeAtual.getPontuacao() + "");
                this.pontuacaoEquipe2Label.setText(equipeAdversaria.getPontuacao() + "");
                equipeAtual.incrementarSetGanho();
                this.setsEquipe1Label.setText(equipeAtual.getSetsGanhos() + "");
            } else {
                this.pontuacaoEquipe1Label.setText(equipeAdversaria.getPontuacao() + "");
                this.pontuacaoEquipe2Label.setText(equipeAtual.getPontuacao() + "");
                equipeAtual.incrementarSetGanho();
                this.setsEquipe2Label.setText(equipeAtual.getSetsGanhos() + "");
            }
            this.partida.incrementarSetPartida();
            // Encerra a partida
            if (this.partida.getSetAtual() > 5 || equipeAtual.getSetsGanhos() > 2) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Partida Encerrada");
                alert.setHeaderText("Partida ganha pelo time: \n"
                        + equipeAtual.getNome());
                alert.setContentText("Historico da partida:\n\n"
                        + "Equipe : " + equipeAtual.getNome()
                        + "\n set 01: " + equipeAtual.getPontuacaoHistorico()[0]
                        + "\n set 02: " + equipeAtual.getPontuacaoHistorico()[1]
                        + "\n set 03: " + equipeAtual.getPontuacaoHistorico()[2]
                        + "\n set 04: " + equipeAtual.getPontuacaoHistorico()[3]
                        + "\n set 05: " + equipeAtual.getPontuacaoHistorico()[4]
                        + "\n\nTotal de sets ganhos: "
                        + equipeAtual.getSetsGanhos()
                        + " \n\n\n"
                        + "Equipe: " + equipeAdversaria.getNome()
                        + "\n set 01: " + equipeAdversaria.getPontuacaoHistorico()[0]
                        + "\n set 02: " + equipeAdversaria.getPontuacaoHistorico()[1]
                        + "\n set 03: " + equipeAdversaria.getPontuacaoHistorico()[2]
                        + "\n set 04: " + equipeAdversaria.getPontuacaoHistorico()[3]
                        + "\n set 05: " + equipeAdversaria.getPontuacaoHistorico()[4]
                        + "\n\nTotal de sets ganhos: "
                        + equipeAdversaria.getSetsGanhos());
                alert.showAndWait();
                resetaTextFields();
            }
        } else {
            // Incrementar ponto para equipe atual
            equipeAtual.incrementarPonto();
            if (this.partida.getEquipe1() == equipeAtual) {
                this.pontuacaoEquipe1Label.setText(equipeAtual.getPontuacao() + "");
            } else {
                this.pontuacaoEquipe2Label.setText(equipeAtual.getPontuacao() + "");
            }
        }
    }

    // Verifica se a equipe atual ganhou o set.
    public boolean identificaTerminoDoSet(Equipe equipeAtual,
            Equipe equipeAdversaria, PartidaVolei partidaAtual) {
        return ((equipeAtual.getPontuacao() - equipeAdversaria.getPontuacao()) >= 1
                || (partidaAtual.getSetAtual() < 5) && (equipeAtual.getPontuacao() == 24)
                || (partidaAtual.getSetAtual() == 4) && (equipeAtual.getPontuacao() == 14));
    }

    public void resetaTextFields() {
        adicionarPontoEquipe1Button.disableProperty().set(true);
        adicionarPontoEquipe2Button.disableProperty().set(true);
        horaTextField.clear();
        equipe1TextField.clear();
        equipe2TextField.clear();
        horaTextField.disableProperty().set(false);
        equipe1TextField.disableProperty().set(false);
        equipe2TextField.disableProperty().set(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
