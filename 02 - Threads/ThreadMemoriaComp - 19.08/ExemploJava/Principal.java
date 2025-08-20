import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Esta classe é o recurso compartilhado. Ela contém a lista de números (numeros) que ambas as threads acessam.
 */
class ListaCompartilhada { //populacao
    private final List<Integer> numeros = new ArrayList<>(); //observem a visibilidade final

    //método set da variavel numeros que é private final - operacao escrever
    //synchronized garante que apenas uma thread por vez executa o metodo
    public synchronized void adicionarNumero(int umNumero) {
        numeros.add(umNumero);
        System.out.println(Thread.currentThread().getName() + " adicionou: " + umNumero);
    }

    //metodo get da variavel numeros que é private final - operacao leitura
    public synchronized List<Integer> retornarNumeros() { //metodo get de acesso tipo leitura da lista numeros
        return new ArrayList<>(numeros);
    }
}
 
class ThreadDeTrabalho extends Thread {
    private final ListaCompartilhada listaCompartilhada;
    private int quantidadeNumeros;
    private int min;
    private int max;
    private boolean isPar; // <<-- Variável adicionada aqui

    public ThreadDeTrabalho(ListaCompartilhada lista, int qtd, int min, int max, boolean isPar) {
        this.listaCompartilhada = lista;
        this.quantidadeNumeros = qtd;
        this.min = min;
        this.max = max;
        this.isPar = isPar;
    }
 
    @Override
    public void run() {
        Random gerador = new Random();
        for (int i = 0; i < quantidadeNumeros; i++) {
            int numeroAleatorio = gerador.nextInt(max - min + 1) + min;
            boolean condicaoPar = (numeroAleatorio % 2 == 0);

            if (isPar == condicaoPar) {
                listaCompartilhada.adicionarNumero(numeroAleatorio);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {}
        }
    }
}
 
public class Principal {
    public static void main(String[] args) throws InterruptedException {
        ListaCompartilhada listaCompartilhada = new ListaCompartilhada();
        
 
        //ha duas threads que populam números inteiros na mesma thread
        Thread t1 = new ThreadDeTrabalho(listaCompartilhada, 5, 100, 200, true);
        Thread t2 = new ThreadDeTrabalho(listaCompartilhada, 5, 1, 99, false);
 
        t1.start();
        t2.start();
 
        t1.join();
        t2.join();
 
        System.out.println("Lista final: " + listaCompartilhada.retornarNumeros());
    }
}