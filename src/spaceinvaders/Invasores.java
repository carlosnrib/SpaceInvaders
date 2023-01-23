/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.scene.image.Image;

/** Classe para objetos do tipo Invasores. 
 * Esta classe é herdeira da classe Elementos.
 *
 * @author carlos
 */
public class Invasores extends Elementos{
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
     * @param tipo - Tipo do invasor criado.
     * @param x - Coordenada x inicial.
     * @param y - Coordenada y inicial.
     */
    public Invasores(int tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        this.direita = true;
        this.esquerda = false;
        this.velocidade = 1;
        this.imagem = new Image("invasor" + tipo + ".png", 71, 71, false, false);
    }

    
    /**
     * Método para retorno do tipo do invasor.
     * @return int - Tipo do invasor.
     */
    public int getTipo(){
        return tipo;
    }
       
    /**
     * Método para mover o invasor. 
     * A movimentação é feita verificando qual o lado que os invasor deve ir,
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
     * Essa função é chamada na engine quando algum dos
     * invasores atinge os limites da tela.
     */
    public void mudaDir() {
        if (direita) {
            this.direita = false;
            this.esquerda = true;
            this.y += 1;
        } else if (esquerda) {
            this.direita = true;
            this.esquerda = false;
            this.y += 1;
        }
        
    }
    
    /**
     * Método para o invasor atirar.
     * O tipo 2 é para indicar que o tiro é de um invasor.
     * @return Tiro - Objeto do tipo tiro na posição que o invasor está.
     */
    public Tiro atirar(){
        Tiro novoTiro = new Tiro(2, x, y+1);
        return novoTiro;
    }
    
}
