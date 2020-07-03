/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voleimanager;

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
        Equipe equipe1 = this.partida.getEquipe1();
        Equipe equipe2 = this.partida.getEquipe2();

        if (indentificaTerminoDoSet(equipe1, equipe2, partida)) {
            // Adiciona os pontos no histórico da partida
            equipe1.adicionarPontuacaoHistorico(equipe1.getPontuacao() != 0 ? equipe1.getPontuacao() + 1 : equipe1.getPontuacao(), this.partida.getSetAtual());
            equipe2.adicionarPontuacaoHistorico(equipe2.getPontuacao(), this.partida.getSetAtual());
            // Encerra o set
            equipe1.setPontuacao(0);
            equipe2.setPontuacao(0);
            this.pontuacaoEquipe1Label.setText(equipe1.getPontuacao() + "");
            this.pontuacaoEquipe2Label.setText(equipe2.getPontuacao() + "");
            this.partida.incrementarSetPartida();
            equipe1.incrementarSetGanho();
            this.setsEquipe1Label.setText(equipe1.getSetsGanhos() + "");
            // Encerra a partida
            if (this.partida.getSetAtual() > 5 || equipe1.getSetsGanhos() > 2) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Partida Encerrada");
                alert.setHeaderText("Partida Ganha pela equipe: \n" + this.partida.getEquipe1().getNome());
                alert.setContentText("Historico da partida:\n\n"
                        + "Equipe : " + this.partida.getEquipe1().getNome()
                        + "\n set 01: " + this.partida.getEquipe1().getPontuacaoHistorico()[0]
                        + "\n set 02: " + this.partida.getEquipe1().getPontuacaoHistorico()[1]
                        + "\n set 03: " + this.partida.getEquipe1().getPontuacaoHistorico()[2]
                        + "\n set 04: " + this.partida.getEquipe1().getPontuacaoHistorico()[3]
                        + "\n set 05: " + this.partida.getEquipe1().getPontuacaoHistorico()[4]
                        + "\nTotal de sets ganhos: "
                        + this.partida.getEquipe1().getSetsGanhos()
                        + " \n\n\n"
                        + "Equipe: " + this.partida.getEquipe2().getNome()
                        + "\n set 01: " + this.partida.getEquipe2().getPontuacaoHistorico()[0]
                        + "\n set 02: " + this.partida.getEquipe2().getPontuacaoHistorico()[1]
                        + "\n set 03: " + this.partida.getEquipe2().getPontuacaoHistorico()[2]
                        + "\n set 04: " + this.partida.getEquipe2().getPontuacaoHistorico()[3]
                        + "\n set 05: " + this.partida.getEquipe2().getPontuacaoHistorico()[4]
                        + "\nTotal de sets ganhos: "
                        + this.partida.getEquipe2().getSetsGanhos());
                alert.showAndWait();
                resetaTextFields();
            }
        } else {
            //Incrementa ponto para a equipe 1
            equipe1.incrementarPonto();
            this.pontuacaoEquipe1Label.setText(equipe1.getPontuacao() + "");
        }

    }

    @FXML
    private void AdicionarPontoEquipe2HandleButtonAction(ActionEvent event) {
        Equipe equipe1 = this.partida.getEquipe1();
        Equipe equipe2 = this.partida.getEquipe2();

        if (indentificaTerminoDoSet(equipe2, equipe1, partida)) {
            // Adiciona os pontos no histórico da partida
            equipe2.adicionarPontuacaoHistorico(equipe2.getPontuacao() != 0 ? equipe2.getPontuacao() + 1 : equipe2.getPontuacao(), this.partida.getSetAtual());
            equipe1.adicionarPontuacaoHistorico(equipe1.getPontuacao(), this.partida.getSetAtual());
            equipe1.setPontuacao(0);
            equipe2.setPontuacao(0);
            // Encerra o set
            this.pontuacaoEquipe1Label.setText(equipe1.getPontuacao() + "");
            this.pontuacaoEquipe2Label.setText(equipe2.getPontuacao() + "");
            equipe2.incrementarSetGanho();
            this.setsEquipe2Label.setText(equipe2.getSetsGanhos() + "");
            this.partida.incrementarSetPartida();
            // Encerra a partida
            if (this.partida.getSetAtual() > 5 || equipe2.getSetsGanhos() > 2) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Partida Encerrada");
                alert.setHeaderText("Partida ganha pelo time: \n" + this.partida.getEquipe2().getNome());
                alert.setContentText("Historico da partida:\n\n"
                        + "Equipe : " + this.partida.getEquipe2().getNome()
                        + "\n set 01: " + this.partida.getEquipe2().getPontuacaoHistorico()[0]
                        + "\n set 02: " + this.partida.getEquipe2().getPontuacaoHistorico()[1]
                        + "\n set 03: " + this.partida.getEquipe2().getPontuacaoHistorico()[2]
                        + "\n set 04: " + this.partida.getEquipe2().getPontuacaoHistorico()[3]
                        + "\n set 05: " + this.partida.getEquipe2().getPontuacaoHistorico()[4]
                        + "\n\nTotal de sets ganhos: "
                        + this.partida.getEquipe2().getSetsGanhos()
                        + " \n\n\n"
                        + "Equipe: " + this.partida.getEquipe1().getNome()
                        + "\n set 01: " + this.partida.getEquipe1().getPontuacaoHistorico()[0]
                        + "\n set 02: " + this.partida.getEquipe1().getPontuacaoHistorico()[1]
                        + "\n set 03: " + this.partida.getEquipe1().getPontuacaoHistorico()[2]
                        + "\n set 04: " + this.partida.getEquipe1().getPontuacaoHistorico()[3]
                        + "\n set 05: " + this.partida.getEquipe1().getPontuacaoHistorico()[4]
                        + "\n\nTotal de sets ganhos: "
                        + this.partida.getEquipe1().getSetsGanhos());
                alert.showAndWait();
                resetaTextFields();
            }
        } else {
            // Incrementar ponto para equipe 2
            equipe2.incrementarPonto();
            this.pontuacaoEquipe2Label.setText(equipe2.getPontuacao() + "");
        }
    }

    // Verifica se a equipe atual ganhou o set.
    public boolean indentificaTerminoDoSet(Equipe equipeAtual, Equipe equipeAdversaria, PartidaVolei partidaAtual) {
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
