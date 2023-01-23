/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.scene.image.Image;


/** Classe para objetos do tipo Canhao. 
 * Esta classe é herdeira da classe Elementos.
 *
 * @author carlos
 */
public class Canhao extends Elementos {
    /**
     * Inteiro da vida do jogador.
     */
    private int vida;
    
    /**
     * Inteiro da pontuação do jogador.
     */
    private int pontuacao;
   
    /**
     * O construtor define a quantidade de vidas, posição, velocidade e a
     * imagem do canhão.
     * @param pontuacao - Pontuação atual do jogador.
     */
    public Canhao(int pontuacao) {
        this.vida = 3;
        this.setPosicao(Jogo.linhasMatriz/2, Jogo.colunasMatriz-1);
        this.velocidade = 1;
        this.pontuacao = pontuacao;
        this.imagem = new Image("jogador.png", 71, 71, false, false);
    }   

    /**
     * Método para retorno da vida do canhão.
     * @return int - Vida.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método para retorno da pontuação do canhão.
     * @return int - Pontuação do jogador.
     */
    public int getPontuacao() {
        return pontuacao;
    }
    
    /**
     * Método para aumentar a pontuação feita pelo canhão.
     * @param tipo - Tipo do invasor atingido.
     * @param nivel - Nível em que o jogador está.
     */
    public void pontua(int tipo, int nivel){
        if (tipo == 1) {
            this.pontuacao += nivel * 10;
        } else if (tipo == 2) {
            this.pontuacao += nivel * 20;
        } else if (tipo == 3) {
            this.pontuacao += nivel * 40;
        } else if (tipo == 7) {
            this.pontuacao += 100 + 50 * nivel;
        }
    }
     
    /**
     * Método para reiniciar a posição e a vida do canhão.
     * Este método é chamado quando o jogador vence um nível.
     */
    public void reinicio() {
        this.vida = 3;
        this.pontuacao = 0;
    }
    
    /**
     * Método para alterar atributos do canhão quando ele leva 
     * um tiro.
     */
    public void tomarTiro(){
        vida--;
    }
    
    /**
     * Método para movimentar o canhão para a direita, verificando os limites
     * da tela.
     */
    public void moveDir() {
        if (this.x < Jogo.linhasMatriz + 3) {
            this.x += velocidade;
        } else {
            this.x = Jogo.linhasMatriz + 3;
        }
    }
    
    /**
     * Método para movimentar o canhão para a esquerda, verificando os limites
     * da tela.
     */
    public void moveEsq() {
        if (this.x > 1) {
            this.x -= velocidade;
        } else {
            this.x = 1;
        }
    }
    
    /**
     * Método para fazer o canhão atirar.
     * O tipo 1 é para indicar que o tiro é de um canhão.
     * @return tiro - Objeto do tipo tiro na posição em que o canhão está. 
     */
    public Tiro atira() {
        Tiro tiro = new Tiro(1, this.x, this.y - 3);
        return tiro;
    }

}
