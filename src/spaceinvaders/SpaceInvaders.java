/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Main do jogo
 *
 * @author carlos
 */
public class SpaceInvaders extends Application {  
    /**
     * Método que exibe o menu do jogo assim que o programa é inicializado.
     * @param stage - Abre a tela do menu.
     * @throws IOException - Exceção na abertura da tela.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuJogo.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
