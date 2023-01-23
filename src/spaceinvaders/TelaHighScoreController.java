/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controlador do FXML da tela de highscore 
 *
 * @author carlos
 */
public class TelaHighScoreController implements Initializable {
    /**
     * Parent com a tela atual.
     */
    private Parent tela;
    
    /**
     * Inteiro da pontuação mais alta.
     */
    private int highscore;

    /**
     * Botão de voltar para o menu.
     */
    @FXML
    private Button bVoltar;
    
    /**
     * Label com o valor da pontuação mais alta.
     */
    @FXML
    private Label lPontuacao;
    
    /**
     * Texto de título.
     */
    @FXML
    private Text tTitulo;

    /**
     * Inicializador da classe controle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tTitulo.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 15));
        try {
            this.lPontuacao.setText(lerHighScore() + " pontos");
        } catch (IOException ex) {
            Logger.getLogger(TelaHighScoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.lPontuacao.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 15));
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
    
    /**
     * Método que retorna o highscore salvo em um arquivo de texto.
     * @return int - Pontuação mais alta.
     * @throws IOException 
     */
    private int lerHighScore() throws IOException {
        try {
            FileInputStream arq = new FileInputStream("HighScore.txt");
            InputStreamReader leitor = new InputStreamReader(arq);
            BufferedReader bleitor = new BufferedReader(leitor);
            String linha = bleitor.readLine();
            
            while (linha != null) {
                String pontuacao = linha.substring(0, linha.indexOf('|'));
                this.highscore = Integer.parseInt(pontuacao);

                linha = bleitor.readLine();
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao abrir o arquivo.");
            e.printStackTrace();
        }
        return highscore;
    }
    
}
