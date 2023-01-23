/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.scene.image.Image;


/** Classe abstrata para moldar os elementos do jogo.
 *
 * @author carlos
 */
public abstract class Elementos {
    /**
     * Inteiros de coordenadas x e y.
     */
    public int x, y;
    
    /**
     * Inteiro da velocidade dos elementos.
     */
    public int velocidade;
    
    /**
     * Imagem do elemento.
     */
    public Image imagem;

    
    /**
     * Método para retornar a coordenada x. 
     * @return int - Coordenada x.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Método para retornar a coordenada y.
     * @return int - Coordenada y.
     */
    public int getY() {
        return y;
    }

    /**
     * Método para retornar a velocidade.
     * @return int - Valor da velocidade.
     */
    public int getVelocidade() {
        return velocidade;
    }
    
    /**
     * Método para definir a posição.
     * @param x int - Coordenada x.
     * @param y int - Coordenada y.
     */
    public void setPosicao(int x, int y) {
        this.x = x;
        this.y = y;
    }    
}
