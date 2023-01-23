/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controlador do FXML da tela de sobre
 *
 * @author carlos
 */
public class SobreController implements Initializable {
    /**
     * Parent com a tela atual.
     */
    private Parent tela;

    /**
     * Botão de voltar para o menu.
     */
    @FXML
    private Button bVoltar;
    
    /**
     * Texto principal.
     */
    @FXML
    private Text tTitulo;
    
    /**
     * Texto secundário.
     */
    @FXML
    private Text tExplicacao;
    
    /**
     * Texto dos comandos.
     */
    @FXML
    private Text tControles;
    
    /**
     * Texto dos comandos.
     */
    @FXML
    private Text tControle1;
    
    /**
     * Texto dos comandos.
     */
    @FXML
    private Text tControle2;
    
    /**
     * Texto dos comandos.
     */
    @FXML
    private Text tControle3;

    /**
     * Inicializador da classe controle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.tTitulo.setFont(Font.loadFont("file:resources/fonts/ARCADE_R.ttf", 40));
         this.tExplicacao.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 20));
         this.tControles.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 20));
         this.tControle1.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 15));
         this.tControle2.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 15));
         this.tControle3.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 15));
    }    

    /**
     * Função para voltar para o menu do jogo quando o botão for pressionado.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickVoltar(MouseEvent event) throws IOException {
        tela = FXMLLoader.load(getClass().getResource("MenuJogo.fxml"));
        Scene scene = new Scene(tela);

        Stage stage = (Stage) bVoltar.getScene().getWindow();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
}
