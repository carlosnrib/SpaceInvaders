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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controlador do FXML do menu 
 *
 * @author carlos
 */
public class MenuJogoController implements Initializable {
    /**
     * Parent com a tela atual.
     */
    private Parent tela;
    
    /**
     * Parent com a tela de vencedor.
     */
    private Parent telaV;
    
    /**
     * Parent com a tela de perdedor.
     */
    private Parent telaP;

    // Variáveis do FXML
    
    /**
     * Texto principal.
     */
    @FXML
    private Text tTitulo;
    
    /**
     * Botão de jogar.
     */
    @FXML
    private Button bJogar;
    
    /**
     * Botão de highscore.
     */
    @FXML
    private Button bHighScore;
    
    /**
     * Botão de sobre.
     */
    @FXML
    private Button bSobre;
    
    /**
     * Imagem de fundo.
     */
    @FXML
    private ImageView iFundo;
   
    // Variáveis auxiliares
    
    /**
     * Inteiro do nível.
     */
    private int nivel;
    
    /**
     * Inteiro da pontuação.
     */
    private int pontuacao;
    
    /**
     * Inicializador da classe controle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tTitulo.setFont(Font.loadFont("file:resources/fonts/ARCADE_R.ttf", 40));
        this.iFundo = new ImageView("menuBackground.gif");
    }    

    /**
     * Função para abrir um novo jogo quando o botão for pressionado.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickJogo(MouseEvent event) throws IOException {
        telaV = FXMLLoader.load(getClass().getResource("TelaVencedor.fxml"));
        Scene sceneV = new Scene(telaV);
        Stage stageV = new Stage();
        stageV.setScene(sceneV);
        
        
        telaP = FXMLLoader.load(getClass().getResource("TelaPerdedor.fxml"));
        Scene sceneP = new Scene(telaP);
        Stage stageP = new Stage();
        stageP.setScene(sceneP);
        
        Stage stage = (Stage) bJogar.getScene().getWindow();
        stage.close();
        lerDados();
        
        Jogo jogo = new Jogo(1, 0, stageV, stageP);
    }

    /**
     * Função para abrir a tela de highscore quando o botão for pressionado.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickHighScore(MouseEvent event) throws IOException {
        tela = FXMLLoader.load(getClass().getResource("TelaHighScore.fxml"));
        Scene scene = new Scene(tela);

        Stage stage = (Stage) bHighScore.getScene().getWindow();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Função para abrir a tela de sobre quando o botão for pressionado.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickSobre(MouseEvent event) throws IOException {
        tela = FXMLLoader.load(getClass().getResource("Sobre.fxml"));
        Scene scene = new Scene(tela);

        Stage stage = (Stage) bSobre.getScene().getWindow();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }
    
    // Estilizações do menu

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hoverExit1(MouseEvent event) {
        this.bJogar.setStyle("-fx-background-color: transparent; -fx-font-size: 18; -fx-text-fill: white; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hover1(MouseEvent event) {
        this.bJogar.setStyle("-fx-background-color: transparent; -fx-font-size: 18; -fx-text-fill: yellow; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hoverExit2(MouseEvent event) {
        this.bHighScore.setStyle("-fx-background-color: transparent; -fx-font-size: 18; -fx-text-fill: white; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hover2(MouseEvent event) {
        this.bHighScore.setStyle("-fx-background-color: transparent; -fx-font-size: 18; -fx-text-fill: yellow; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hoverExit3(MouseEvent event) {
        this.bSobre.setStyle("-fx-background-color: transparent; -fx-font-size: 18; -fx-text-fill: white; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hover3(MouseEvent event) {
        this.bSobre.setStyle("-fx-background-color: transparent; -fx-font-size: 18; -fx-text-fill: yellow; -fx-font-family: Press Start 2P;");
    }
    
    /**
     * Função que lê o progresso da partida, a partir do arquivo de texto.
     * @throws IOException 
     */
    private void lerDados() throws IOException {
        try {
            FileInputStream arq = new FileInputStream("InfoPartida.txt");
            InputStreamReader leitor = new InputStreamReader(arq);
            BufferedReader bleitor = new BufferedReader(leitor);
            String linha = bleitor.readLine();
            
            while (linha != null) {
                String nivel = linha.substring(0, linha.indexOf('|'));
                String pontuacao = linha.substring(linha.indexOf('|') + 1, linha.length());
                this.nivel = Integer.parseInt(nivel);
                this.pontuacao = Integer.parseInt(pontuacao);

                linha = bleitor.readLine();
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao abrir o arquivo.");
            e.printStackTrace();
        }
    }
    
}
