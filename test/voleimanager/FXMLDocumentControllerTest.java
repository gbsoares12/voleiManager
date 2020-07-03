/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voleimanager;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import voleimanager.model.Equipe;
import voleimanager.model.PartidaVolei;

/**
 *
 * @author gabriel
 */
public class FXMLDocumentControllerTest {

    public FXMLDocumentControllerTest() {
    }

    /**
     * Test do método indentificaTerminoDoSet, da classe FXMLDocumentController.
     * Onde deve respeitar as condições apontadas no Estudo de Caso:
     * "Lembrando que os 4 primeiros sets terminam em 25 ou até um dos adversários tiverem 2 pontos de vantagem.
       O quinto (set) termina em 15 pontos ou até um dos adversários terem 2 pontos de vantagem."
     */
    @Test
    public void testIndentificaTerminoDoSetCom2PontosDeVantagem() {
        System.out.println("indentificaTerminoDoSet com 2 pontos de vantagem");

        Equipe equipeAtual = new Equipe("Testador 1", 2, 2);
        Equipe equipeAdversaria = new Equipe("Testador 2", 0, 0);
        PartidaVolei partidaAtual = new PartidaVolei(equipeAtual, equipeAdversaria, "00:00:00");
        FXMLDocumentController instance = new FXMLDocumentController();
        boolean expResult = true;
        boolean result = instance.identificaTerminoDoSet(equipeAtual, equipeAdversaria, partidaAtual);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIndentificaTerminoDoSetCom25Pontos() {
        System.out.println("indentificaTerminoDoSet ao atingir 25 pontos");

        Equipe equipeAtual = new Equipe("Testador 1", 24, 2);
        Equipe equipeAdversaria = new Equipe("Testador 2", 0, 0);
        PartidaVolei partidaAtual = new PartidaVolei(equipeAtual, equipeAdversaria, "00:00:00");
        partidaAtual.setSetAtual(1);
        FXMLDocumentController instance = new FXMLDocumentController();
        boolean expResult = true;
        boolean result = instance.identificaTerminoDoSet(equipeAtual, equipeAdversaria, partidaAtual);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testIndentificaTerminoDoSetCom15Pontos() {
        System.out.println("indentificaTerminoDoSet ao atingir 15 pontos");

        Equipe equipeAtual = new Equipe("Testador 1", 14, 2);
        Equipe equipeAdversaria = new Equipe("Testador 2", 0, 0);
        PartidaVolei partidaAtual = new PartidaVolei(equipeAtual, equipeAdversaria, "00:00:00");
        partidaAtual.setSetAtual(5);
        FXMLDocumentController instance = new FXMLDocumentController();
        boolean expResult = true;
        boolean result = instance.identificaTerminoDoSet(equipeAtual, equipeAdversaria, partidaAtual);
        assertEquals(expResult, result);
    }
}
