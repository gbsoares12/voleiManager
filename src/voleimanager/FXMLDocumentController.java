/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voleimanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author gabriel
 */
public class FXMLDocumentController implements Initializable {
    
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
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        horaLabel.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
