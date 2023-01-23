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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controlador do FXML da tela de vencedor do nível
 *
 * @author carlos
 */
public class TelaVencedorController implements Initializable {
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
    
    /**
     * Botão de jogar próxima fase.
     */
    @FXML
    private Button bProxFase;
    
    /**
     * Botão de sair.
     */
    @FXML
    private Button bSair;
    
    /**
     * Texto principal.
     */
    @FXML
    private Text tParabens;
    
    /**
     * Texto secundário.
     */
    @FXML
    private Text tPergunta;
    
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
        this.tParabens.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 20));
        this.tPergunta.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 20));
        this.iFundo = new ImageView("menuBackground.gif");
    }    

    /**
     * Função para jogar a próxima fase quando o botão for pressionado.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickNovoJogo(MouseEvent event) throws IOException {
        telaP = FXMLLoader.load(getClass().getResource("TelaPerdedor.fxml"));
        Scene sceneP = new Scene(telaP);
        Stage stageP = new Stage();
        stageP.setScene(sceneP);
        
        Stage stage = (Stage) bProxFase.getScene().getWindow();
        stage.close();
        lerDados();
        
        Jogo jogo = new Jogo(nivel, pontuacao, stage, stageP);
    }

    /**
     * Função para voltar para o menu do jogo quando o botão for pressionado.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickSair(MouseEvent event) throws IOException {
        tela = FXMLLoader.load(getClass().getResource("MenuJogo.fxml"));
        Scene scene = new Scene(tela);

        Stage stage = (Stage) bSair.getScene().getWindow();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hoverExit1(MouseEvent event) {
        this.bProxFase.setStyle("-fx-background-color: transparent; -fx-font-size: 16; -fx-text-fill: white; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hover1(MouseEvent event) {
        this.bProxFase.setStyle("-fx-background-color: transparent; -fx-font-size: 16; -fx-text-fill: yellow; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hoverExit2(MouseEvent event) {
        this.bSair.setStyle("-fx-background-color: transparent; -fx-font-size: 12; -fx-text-fill: white; -fx-font-family: Press Start 2P;");
    }

    /**
     * Função de efeito de hover no botão.
     * @param event 
     */
    @FXML
    private void hover2(MouseEvent event) {
        this.bSair.setStyle("-fx-background-color: transparent; -fx-font-size: 12; -fx-text-fill: yellow; -fx-font-family: Press Start 2P;");
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
            System.out.println("Ocorreu um erro ao abrir o save.");
            e.printStackTrace();
        }
    }
    
}
