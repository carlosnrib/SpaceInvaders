/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.scene.image.Image;

/** Classe para objetos do tipo Tiro. 
 * Esta classe é herdeira da classe Elementos.
 *
 * @author carlos
 */
public class Tiro extends Elementos{
    /**
     * Inteiro do tipo do tiro.
     * 1 - tiro da nave.
     * 2 - tiro dos inimigos.
     */
    private int tipo;
    
    /**
     * O construtor do define a posição do tiro, tipo, imagem e velocidade.
     * @param tipo - Tipo do tiro.
     * @param x - Coordenada x do tiro.
     * @param y - Coordenada y do tiro.
     */
    public Tiro(int tipo, int x, int y){
        this.x = x;
        this.y = y;
        this.velocidade = 1;
        this.tipo = tipo;
        this.imagem = new Image("tiro" + tipo + ".png", 40, 40, false, false);
    }

    /**
     * Método para retorno do tipo de tiro.
     * @return int - Tipo do tiro.
     */
    public int getTipo() {      
        return tipo;
    }

    /** 
    * Método para a movimentação do tiro.
    * Altera a posicao y do objeto.
    */
    public void movimento() {
        if (tipo == 1){
            this.y -= velocidade;
        } else {
            this.y += velocidade;
        }
    }
    
    /**
     * Método para verificar a colisão do tiro com algum elemento.
     * @param elemento - Elemento do jogo, pode ser o canhão, barreiras ou 
     * invasores.
     * @return boolean - Se o tiro colidiu com algum elemento do jogo.
     */
    public boolean acerta(Elementos elemento) {
        return (x == elemento.x && y == elemento.y);
    }
}