import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe para gerar e popular as listas de forma concorrente.
 */
class PopulaLista implements Runnable {
    private final List<Integer> lista;
    private final int tamanho;
    private final int min;
    private final int max;

    public PopulaLista(List<Integer> lista, int tamanho, int min, int max) {
        this.lista = lista;
        this.tamanho = tamanho;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para popular a lista com tamanho: " + tamanho);
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) { //adiciona números aleatórios entre min e max
            lista.add(random.nextInt(max - min + 1) + min);
        }
        System.out.println("Thread finalizada " + t.getName());
    }
}

/**
 * Classe para calcular a média de uma lista de números de forma concomitante.
 */
class CalculaMedia implements Runnable {
    private final List<Integer> lista;
    private double media;

    public CalculaMedia(List<Integer> lista) {
        this.lista = lista;
        this.media = 0.0;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para calcular a média da lista com tamanho: " + lista.size());
        if (lista.isEmpty()) {
            this.media = 0.0;
            System.out.println("Thread finalizada " + t.getName() + " - Média: 0.0");
            return;
        }

        // Calcula a média dos números na lista
        double soma = 0;
        for (Integer numero : lista) { //soma todos os números da lista
            soma += numero;
        }
        this.media = soma / lista.size(); //faz a média
        System.out.println("Thread de Média finalizada " + t.getName() + " - Média: " + this.media);
    }

    public double getMedia() { //get para pegar a média calculada e fazer o cálculo final
        return media;
    }
}

public class Java {
    public static void main(String[] args) throws InterruptedException {
        int n = 100000; // Total de números a serem gerados
        int m = 10;     // Número de listas

        List<List<Integer>> listasDeListas = new ArrayList<>();
        List<Thread> populadores = new ArrayList<>(); // Lista para armazenar os threds que vão popular as listas

        int tamanhoPorLista = n / m; // Tamanho de cada lista
        int min = 1000;
        int max = 100000;

        //Distribui os números e cria as threads de população
        for (int i = 0; i < m; i++) {
            List<Integer> lista = new ArrayList<>();
            listasDeListas.add(lista); //adiciona a nova lista na lista de listas

            PopulaLista populaLista = new PopulaLista(lista, tamanhoPorLista, min, max); //cria a tarefa de popular a lista
            Thread t = new Thread(populaLista); //cria a thread
            populadores.add(t); //adiciona a thread na lista de threads

            t.start();
        }

        // Espera todas as threads de população terminarem
        for (Thread t : populadores) {
            t.join();
        }

        System.out.println("\nTodas as listas foram populadas. Iniciando cálculo das médias...");

        List<Thread> calculadores = new ArrayList<>(); // nova lista com as threads que vão calcular as médias
        List<CalculaMedia> tarefasListas = new ArrayList<>(); // lista para armazenar as tarefas de cálculo de média

        // Cria e inicia as threads para calcular a média de cada lista
        for (int i = 0; i < m; i++) {
            CalculaMedia tarefa = new CalculaMedia(listasDeListas.get(i)); // cria a tarefa de calcular a média da lista
            tarefasListas.add(tarefa);
            Thread t = new Thread(tarefa, "CalculaMedia-" + (i + 1)); // cria a thread para calcular a média
            calculadores.add(t);  //adiciona na lista de threads de cálculo
            t.start();
        }

        // Espera todas as threads de cálculo terminarem
        for (Thread t : calculadores) {
            t.join();
        }

        // Calcula a média geral
        double somaDasMedias = 0;
        for (CalculaMedia tarefa : tarefasListas) {
            somaDasMedias += tarefa.getMedia();
        }
        double mediaGeral = somaDasMedias / m;
        System.out.println("\nCálculo finalizado. A média geral das " + m + " listas é: " + mediaGeral);
    }
}