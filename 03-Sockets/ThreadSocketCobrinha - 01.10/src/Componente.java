import java.io.Serializable;
/**
 *
 * @author alexandrezamberlan
 */
public class Componente implements Serializable {
    // Tipos de componentes a serem enviados
    public static final int FRUTA = 1, JOGADOR = 2, PLACAR = 3, REINICIAR = 4, BARREIRA=5;
    
    // Dados de posição (para FRUTA e JOGADOR)
    public int x;
    public int y;
    public int largura;
    public int altura;
    
    public int tipo; // Indica o que este objeto Componente representa (FRUTA, JOGADOR, PLACAR, etc.)

    // --- NOVOS CAMPOS PARA PLACAR E ESTADO DO JOGO ---
    public int pontosJ1;
    public int pontosJ2;
    public boolean jogoFinalizado;
    // ----------------------------------------------------
    
    public int id; //id para barreira

    // Construtor para componentes de posição (FRUTA e JOGADOR)
    public Componente(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.pontosJ1 = 0; // Inicializa para evitar NullPointer ou valores estranhos
        this.pontosJ2 = 0;
        this.jogoFinalizado = false;
    }

    // Construtor dedicado para enviar o PLACAR/Estado do Jogo
    public Componente(int tipo, int pontosJ1, int pontosJ2, boolean jogoFinalizado) {
        this.tipo = tipo;
        this.pontosJ1 = pontosJ1;
        this.pontosJ2 = pontosJ2;
        this.jogoFinalizado = jogoFinalizado;
    }
    public Componente(int x, int y, int largura, int altura, int id) {
        this.tipo = BARREIRA; // Define o tipo automaticamente
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.id = id;
    }

}