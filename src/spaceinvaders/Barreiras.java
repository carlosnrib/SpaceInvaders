/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.scene.image.Image;

/** Classe para objetos do tipo Barreiras. 
 * Esta classe é herdeira da classe Elementos.
 *
 * @author carlos
 */
public class Barreiras extends Elementos{
    /**
     * Contador de colisões com a barreira.
     */
    public int colisoes;
    
    /**
     * O construtor define as posicões das barreiras e inicializa 
     * a contagem de colisões.
     * @param posX - Coordenada x em que a barreira será posicionada.
     * @param posY - Coordenada y em que a barreira será posicionada.
     */
    public Barreiras(int posX, int posY) {
        this.setPosicao(posX, posY-1);
        this.colisoes = 0;
        this.imagem = new Image("asteroides" + colisoes + ".png", 70, 70, false, false);
    }
    
    /** 
     * Método que incrementa o atributo da quantidade de colisões na barreira.
     */
    public void atingido() {
        colisoes++;
    }
    
    /**
     * Método que retorna se a barreira foi acertada duas vezes, e, portanto,
     * ela deve ser considerada explodida.
     * 
     * @return boolean - se a quantidade de colisões é igual a 1 ou seja, 
     * atingida duas vezes.
     */
    public boolean explodido() {
        return colisoes == 1;
    }  
}
