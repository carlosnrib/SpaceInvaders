/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import javafx.scene.image.Image;

/** Classe para objetos do tipo Invasor Especial.
 * Esta classe é herdeira da classe Elementos.
 *
 * @author carlos
 */
public class InvasorEspecial extends Elementos{
    /**
     * Inteiro do tipo do invasor.
     */
    private int tipo;
    
    /**
     * Booleanos para definir o sentido de movimento do invasor.
     */
    public boolean esquerda, direita;
    

    /**
     * O construtor define a posição inicial, a velociade, o tipo, a imagem, e 
     * inicia o invasor para se movimentar para a direita.
     * @param x - Coordenada x inicial.
     * @param y - Coordenada y inicial.
     */
    public InvasorEspecial(int x, int y) {
        this.x = x;
        this.y = y;
        this.direita = true;
        this.esquerda = false;
        this.velocidade = 1;
        this.tipo = 7;
        this.imagem = new Image("invasore.png", 71, 71, false, false);
    }

    
    /**
     * Método para retorno do tipo do invasor.
     * @return int - Tipo do invasor
     */
    public int getTipo(){
        return tipo;
    }
    
    
    /**
     * Método para mover o invasor. 
     * A movimentação é feita verificando qual o lado que o invasor deve ir,
     * se for para a direita a posição é incrementada da velocidade e, caso seja
     * para a esquerda, a posição é decrementada da velocidade.
     */
    public void move() {
      if(direita){
        this.x += velocidade;
      } else if(esquerda){
        this.x -= velocidade;
      }
    }
    
    /**
     * Método para mudar a direção de movimento.
     * A mudança de direção é feita alterando os estados dos booleanos. 
     * Essa função é chamada na engine quando o
     * invasor atinge um determinado limite, maior que o da tela.
     */
    public void mudaDir() {
        if (direita) {
            this.direita = false;
            this.esquerda = true;
        } else if (esquerda) {
            this.direita = true;
            this.esquerda = false;
        }
        
    }   
}
