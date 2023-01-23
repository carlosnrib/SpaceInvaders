/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/** Classe da engine do jogo.
 *
 * @author carlos
 */
public class Jogo {

    /**
     * Inteiro do nível.
     */
    private int nivel;
    
    /**
     * Booleano de estado de gameOver.
     */
    private boolean gameOver;
    
    /**
     * Booleano de estado de gameWinner.
     */
    private boolean gameWinner;
    
    /**
     * Inteiro de auxílio no controle do sleep das Threads.
     */
    private int tempo;
    
    /**
     * Inteiro de pontuação mais alta.
     */
    private int highscore;
    
    /**
     * Inteiro auxiliar para poscionamento dos invasores.
     */
    private int descida;
    
    /**
     * Inteiro de controle da quantidade de tiros do canhão.
     */
    private int quantTiros = 0;
    
    // Variáveis da tela

    /**
     * Inteiro de linhas da matriz de grid.
     */
    public static int linhasMatriz = 24;
    
    /**
     * Inteiro de colunas da matriz de grid.
     */
    public static int colunasMatriz = 16;
    
    /**
     * Inteiro da largura da tela.
     */
    public static int xTela = 4800;
    
    /**
     * Inteiro da altura da tela.
     */
    public static int yTela = 2700;

    // Variáveis da interface
    
    /**
     * Matriz de grid.
     */
    int[][] telaPrincipal = new int[linhasMatriz][colunasMatriz];
    
    /**
     * Stage da tela do jogo.
     */
    private Stage telaJogo;
    
    /**
     * Stage da tela de vencedor do nível.
     */
    private Stage telaVencedor;
    
    /**
     * Stage da tela de perdedor do jogo.
     */
    private Stage telaPerdedor;
    
    /**
     * Scene da cena do jogo.
     */
    private Scene cenaJogo;
    
    /**
     * Canvas da interface do jogo.
     */
    private Canvas canvasJogo;
    
    /**
     * Gráficos do jogo.
     */
    private GraphicsContext graficosJogo;
    
    /**
     * Grupo para agrupar todos os elementos a serem exibidos.
     */
    private Group grupoElementos;
    
    /**
     * Imagem de fundo.
     */
    private Image background;

    // Elementos
    
    /**
     * Canhão - jogador.
     */
    private Canhao jogador;
    
    /**
     * Array List de invasores comuns.
     */
    private ArrayList<Invasores> inimigos;
    
    /**
     * Array List de invasor especial.
     */
    private ArrayList<InvasorEspecial> inimigoEspecial;
    
    /**
     * Array List de barreiras.
     */
    private ArrayList<Barreiras> barreiras;
    
    /**
     * Array List de tiros.
     */
    private ArrayList<Tiro> tiros;
    
    /**
     * Textos com as informações auxiliares exibidas na tela.
     */
    private Text informacoes;
    

    // Threads
    
    /**
     * Thread de movimentação dos invasores.
     */
    private Thread tInvasores;
    
    /**
     * Thread de movimentação do invasor especial.
     */
    private Thread tInvasorEspecial;
    
    /**
     * Thread dos tiros do canhão.
     */
    private Thread tTiros;
    
    /**
     * Thread dos tiros aleatórios dos invasores.
     */
    private Thread tTiroInimigo;
    
    /**
     * Thread dos tiros alinhados dos invasores.
     */
    private Thread tTiroAlinhaInimigo;
    
    /**
     * Thread de animações do jogo.
     */
    private AnimationTimer timer;
    
    
    /**
     * O construtor define algumas variáveis necessárias para o funcionamento do
     * jogo, cria e posiciona os elementos.
     * @param nivel - Nível atual do jogo.
     * @param pontuacao - Pontuação atual do jogador.
     * @param telaVencedor - Tela em caso de vitória do nível.
     * @param telaPerdedor - Tela em caso de derrota.
     */
    public Jogo(int nivel, int pontuacao, Stage telaVencedor, Stage telaPerdedor) {
        this.nivel = nivel;
        this.telaVencedor = telaVencedor;
        this.telaPerdedor = telaPerdedor;
        this.gameOver = false;
        this.gameWinner = false;

        this.jogador = new Canhao(pontuacao);
        this.barreiras = new ArrayList<>();
        this.inimigos = new ArrayList<>();
        this.inimigoEspecial = new ArrayList<>();
        this.tiros = new ArrayList<>();
        

        this.informacoes = new Text();

        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(5 + i, colunasMatriz - 4));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(11 + i, colunasMatriz - 4));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(17 + i, colunasMatriz - 4));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(23 + i, colunasMatriz - 4));
        }

        for (int i = 1; i < 12; i++) {
            if(nivel <= 5)
                descida = nivel - 1;
            else
                descida = 5;
            
            inimigos.add(new Invasores(3, i, descida));
            inimigos.add(new Invasores(2, i, descida + 1));
            inimigos.add(new Invasores(2, i, descida + 2));
            inimigos.add(new Invasores(1, i, descida + 3));
            inimigos.add(new Invasores(1, i, descida + 4));
        }

        this.background = new Image("jogoBackground.jpg");

        criaTela();
        run();
    }
    

    /**
     * Método para criar a tela do jogo.
     */
    private void criaTela() {
        telaJogo = new Stage();
        telaJogo.setMaximized(true);

        grupoElementos = new Group();
        cenaJogo = new Scene(grupoElementos);
        telaJogo.setScene(cenaJogo);

        canvasJogo = new Canvas(xTela, yTela);
        grupoElementos.getChildren().add(canvasJogo);

        graficosJogo = canvasJogo.getGraphicsContext2D();

        telaJogo.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                System.exit(0);
            }
        });

        acoesJogador();
        telaJogo.show();

    }

    /**
     * Método que cria e controla as Threads do jogo.
     * Essa função é o loop do jogo.
     */
    private void run() {
        this.tInvasores = new Thread(() -> {
            while (!gameWinner && !gameOver) {
                try {
                    sleep(850 - (nivel * 60));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                acoesInvasores();
            }
        });
        
        this.tInvasorEspecial = new Thread(() -> {
            while (!gameWinner && !gameOver) {
                try {
                    sleep(850 - (nivel * 60));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                colocaInvasorEspecial();
                if (!inimigoEspecial.isEmpty())
                    acoesInvasorEspecial();
            }
        });

        this.tTiros = new Thread(() -> {
            while (!gameWinner && !gameOver) {
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < tiros.size(); i++) {
                    tiros.get(i).movimento();
                }
            }
        });
        
        this.tTiroInimigo = new Thread(() -> {
            while (!gameWinner && !gameOver) {
                try {
                    if (4000 - nivel * 400 > 1000) {
                        tempo = 4000 - nivel * 400;
                    } else {
                        tempo = 1000;
                    }
                    sleep(tempo);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                atiraInimigo();
            }
        });
        
        this.tTiroAlinhaInimigo = new Thread(() -> {
            while (!gameWinner && !gameOver) {
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                atiraAlinhaInimigo();
            }
        });

        
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                graficosJogo.drawImage(background, 0, 0);
                for (int i = 0; i < tiros.size(); i++) {
                    if (tiros.get(i).y < 0 || tiros.get(i).y > colunasMatriz - 1) {
                        if(tiros.get(i).getTipo() == 1) quantTiros--;
                        tiros.remove(i);
                        i--;
                    }
                }
                
                if (gameWinner || gameOver) {
                    if (gameOver) {
                        nivel = 1;
                        jogador.reinicio();
                    }
                    try {
                        salvaPartida();
                    } catch (IOException ex) {
                        Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        if(jogador.getPontuacao() >= lerHighScore())
                            salvaPontuacao();
                    } catch (IOException ex) {
                        Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    timer.stop();
                    telaJogo.close();
                    
                    if(gameWinner) {
                        telaVencedor.show();
                    }  else {
                        telaPerdedor.show();
                    }
                    
                }              
                acoesTiros();
                desenhaTela();
            }

        };
        
        tTiroInimigo.start();
        tTiroAlinhaInimigo.start();
        tTiros.start();
        tInvasores.start();
        tInvasorEspecial.start();
        timer.start();

    }

    /**
     * Método que desenha todos os elementos do jogo nas posições especificadas.
     */
    private void desenhaTela() {
        graficosJogo.drawImage(jogador.imagem, (jogador.x - 1) * 70, (jogador.y - 2) * 70);

        for (int i = 0; i < barreiras.size(); i++) {
            graficosJogo.drawImage(barreiras.get(i).imagem, (barreiras.get(i).x - 1) * barreiras.get(i).imagem.getWidth(), barreiras.get(i).y * barreiras.get(i).imagem.getHeight());

        }

        for (int i = 0; i < inimigos.size(); i++) {
            graficosJogo.drawImage(inimigos.get(i).imagem, (inimigos.get(i).x - 1) * inimigos.get(i).imagem.getWidth(), inimigos.get(i).y * inimigos.get(i).imagem.getHeight());
        }
        
        for (int i = 0; i < inimigoEspecial.size(); i++) {
            graficosJogo.drawImage(inimigoEspecial.get(i).imagem, (inimigoEspecial.get(i).x - 1) * inimigoEspecial.get(i).imagem.getWidth(), inimigoEspecial.get(i).y * inimigoEspecial.get(i).imagem.getHeight());
        }

        informacoes.setText("Pontuacao: " + jogador.getPontuacao() + "\nVidas: " + jogador.getVida() + "\nNivel: " + this.nivel);
        graficosJogo.setFont(Font.loadFont("file:resources/fonts/ARCADE_N.ttf", 18));
        graficosJogo.setFill(Color.YELLOW);
        graficosJogo.fillText(informacoes.getText(), 0, yTela - 1750);

        for (int i = 0; i < tiros.size(); i++) {
            graficosJogo.drawImage(tiros.get(i).imagem, (tiros.get(i).x - 1) * 70 + 20, (tiros.get(i).y) * 70);
        }
    }

    /**
     * Método que controla a movimentação do canhão a partir do teclado.
     */
    private void acoesJogador() {
        cenaJogo.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCharacter()) {
                    case "a":
                        jogador.moveEsq();
                        break;
                    case "d":
                        jogador.moveDir();
                        break;
                }
            }
        });

        cenaJogo.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.SPACE && quantTiros <= 1) {
                tiros.add(jogador.atira());
                quantTiros++;
            }
        });
    }

    /**
     * Método que controla a movimentação dos invasores.
     */
    private void acoesInvasores() {  
        for (int i = 0; i < inimigos.size(); i++) {
            if (inimigos.get(i).x == 1 && inimigos.get(i).esquerda == true) {
                for (int j = 0; j < inimigos.size(); j++) {
                    inimigos.get(j).direita = true;
                    inimigos.get(j).esquerda = false;
                    inimigos.get(j).y += 1;
                }
                break;
            } else if (inimigos.get(i).x == linhasMatriz + 3 && inimigos.get(i).direita == true) {
                for (int j = 0; j < inimigos.size(); j++) {
                    inimigos.get(j).direita = false;
                    inimigos.get(j).esquerda = true;
                    inimigos.get(j).y += 1;
                }
                break;
            } else {
                inimigos.get(i).move();
            }
                       
            if (inimigos.get(i).y == colunasMatriz - 5) {
                gameOver = true;
            }
        }
           
        if(inimigos.isEmpty()) {
                gameWinner = true;
                nivel++;
        }
    }
    
    /**
     * Método para colocar o invasor especial na tela, em um certo momento
     * da partida.
     */
    private void colocaInvasorEspecial(){
        if (!inimigos.isEmpty())
            if (inimigos.get(0).y == nivel && inimigos.get(0).x == 18) {
                inimigoEspecial.add(new InvasorEspecial(0,0));
            }
    }
    
    /**
     * Método de movimentação do invasor especial.
     */
    private void acoesInvasorEspecial() {
        for (int i = 0; i < inimigoEspecial.size(); i++) {
            if (inimigoEspecial.get(i).x == 1 && inimigoEspecial.get(i).esquerda == true) {
                for (int j = 0; j < inimigoEspecial.size(); j++) {
                    inimigoEspecial.get(j).direita = true;
                    inimigoEspecial.get(j).esquerda = false;
                }
                break;
            } else if (inimigoEspecial.get(i).x == linhasMatriz + 8 && inimigoEspecial.get(i).direita == true) {
                for (int j = 0; j < inimigoEspecial.size(); j++) {
                    inimigoEspecial.get(j).direita = false;
                    inimigoEspecial.get(j).esquerda = true;
                }
                break;
            } else {
                inimigoEspecial.get(i).move();
            }
        }
    }
    
    /**
     * Método dos tiros aleatórios dos invasores.
     */
    private void atiraInimigo() {
        if(!inimigos.isEmpty()) {
                Random aleatorio = new Random();
                int valor = aleatorio.nextInt(inimigos.size());
                tiros.add(inimigos.get(valor).atirar());
        }
    }
    
    /**
     * Método dos tiros alinhados dos invasores.
     */
    private void atiraAlinhaInimigo() {
        if(!inimigos.isEmpty()) {
                Random aleatorio = new Random();
                int valor = aleatorio.nextInt(inimigos.size());
                if (inimigos.get(valor).x == jogador.x)
                    tiros.add(inimigos.get(valor).atirar());
        }
    
    }

    /**
     * Método da movimentação dos tiros e verificação de colisões.
     */
    private void acoesTiros() {
        for (int i = 0; i < tiros.size(); i++) {
            if (tiros.get(i).getTipo() == 1) {
                for (int j = 0; j < inimigos.size(); j++) {
                    if (tiros.get(i).acerta(inimigos.get(j))) {
                        jogador.pontua(inimigos.get(j).getTipo(), nivel);
                        tiros.remove(i);
                        quantTiros--;
                        inimigos.remove(j); 
                        j--;
                        break;
                    }
                }               
            } else if (tiros.get(i).getTipo() == 2) {
                if (tiros.get(i).acerta(jogador)) {
                    jogador.tomarTiro();
                    tiros.remove(i);
                }
            }
        }
        
        for (int i = 0; i < tiros.size(); i++) {
            if (tiros.get(i).getTipo() == 1 && !inimigoEspecial.isEmpty()) {
                for (int j = 0; j < inimigoEspecial.size(); j++) {
                    if (tiros.get(i).acerta(inimigoEspecial.get(j))) {
                        jogador.pontua(inimigoEspecial.get(j).getTipo(), nivel);
                        tiros.remove(i);
                        quantTiros--;
                        inimigoEspecial.remove(j); 
                        j--;
                        break;
                    }
                }               
            }
        }
        
        for (int i = 0; i < tiros.size(); i++) {
            for (int k = 0; k < barreiras.size(); k++) {
                if (tiros.get(i).acerta(barreiras.get(k))) {
                    barreiras.get(k).atingido();
                    if (barreiras.get(k).colisoes == 1) {
                        barreiras.get(k).imagem = new Image("asteroides1.png", 70, 70, false, false);
                    }
                    if (barreiras.get(k).colisoes == 2) {
                        barreiras.remove(k);
                    }
                    if(tiros.get(i).getTipo() == 1) quantTiros--;
                    tiros.remove(i);
                    
                    break;
                }
            }
        }

        if (jogador.getVida() <= 0) {
            this.gameOver = true;
        }

    }
    
    /**
     * Método que salva as informações da partida.
     * @throws IOException 
     */
    private void salvaPartida() throws IOException{
        String infos = this.nivel + "|" + jogador.getPontuacao();
        FileWriter arq = new FileWriter("InfoPartida.txt");
        PrintWriter escrever = new PrintWriter(arq);
        escrever.printf(infos);
        arq.close();
        
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
    
    /**
     * Método que salva a pontuação do jogador.
     * Esse método só é chamado quando a pontuação do jogador for maior do que
     * o último highscore salvo.
     * @throws IOException 
     */
    private void salvaPontuacao() throws IOException{
        String pontuacao = jogador.getPontuacao() + "|";
        FileWriter arq = new FileWriter("HighScore.txt");
        PrintWriter escrever = new PrintWriter(arq);
        escrever.printf(pontuacao);
        arq.close();
        
    }

}
