<<<<<<< HEAD
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PopulaLista implements Runnable {  //uso de threads sem memória compartilhada
    private List<Integer> lista;
    Integer tamanho;

    //construtor
    public PopulaLista(List<Integer> lista, Integer tamanho) {
        this.lista = lista;
        this.tamanho = tamanho;
    }

    @Override
    public void run() {
        //aqui que está a tarefa ou rotina a ser concomitada
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para popular a lista com tamanho: " + tamanho);
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            lista.add(random.nextInt(tamanho));
        }
        System.out.println("Thread finalizada " + t.getName());
    }
}


class OrdenaBolha implements Runnable { //uso de threads sem memória compartilhada
    private List<Integer> lista;

    public OrdenaBolha(List<Integer> lista) {
        this.lista = lista;
    }
    @Override
    public void run() {
        //aqui está a tarefa ou rotina a ser concomitada - no caso, bubble sort
        boolean houveTroca;
        int tmp;
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para ordenar com bolha a lista com tamanho: " + lista.size());
        do {
            houveTroca = false;
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i) > lista.get(i+1)) {
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i+1));
                    lista.set(i+1, tmp);
                }
            }
        } while (houveTroca);
    }
}

class OrdenaPente implements Runnable { //uso de threads sem memória compartilhada
    private List<Integer> lista;

    public OrdenaPente(List<Integer> lista) {
        this.lista = lista;
    }

    public void run() {
        //aqui está a tarefa ou rotina a ser concomitada - no caso, bubble sort
        boolean houveTroca;
        int tmp;
        int distancia = lista.size();
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para ordenar com pente a lista com tamanho: " + lista.size());
        do {
            distancia = (int) (distancia / 1.3);
            houveTroca = false;
            for (int i = 0; i < lista.size() - distancia; i++) {
                if (lista.get(i) > lista.get(i+distancia)) {
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i+distancia));
                    lista.set(i+distancia, tmp);
                }
            }
        } while (distancia > 1 || houveTroca);
    }
}

public class Java {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> lista1 = new ArrayList<>();
        List<Integer> lista2 = new ArrayList<>();
        List<Integer> lista3 = new ArrayList<>();

        Thread threadPopula1 = new Thread(new PopulaLista(lista1, 10000), "PopulaLista1");
        Thread threadPopula2 = new Thread(new PopulaLista(lista2, 1000), "PopulaLista2");
        Thread threadPopula3 = new Thread(new PopulaLista(lista3, 5000), "PopulaLista3");



        threadPopula1.start();
        threadPopula2.start();
        threadPopula3.start();

        threadPopula1.join();
        threadPopula2.join();
        threadPopula3.join();


        Thread threadOrdena1 = new Thread(new OrdenaPente(lista1), "OrdenaPente1");
        Thread threadOrdena2 = new Thread(new OrdenaBolha(lista2), "OrdenaBolha1");
        Thread threadOrdena3 = new Thread(new OrdenaBolha(lista3), "OrdenaBolha2");

        threadOrdena1.start();
        threadOrdena2.start();
        threadOrdena3.start();
    }


}
=======
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PopulaLista implements Runnable {  //uso de threads sem memória compartilhada
    private List<Integer> lista;
    Integer tamanho;

    //construtor
    public PopulaLista(List<Integer> lista, Integer tamanho) {
        this.lista = lista;
        this.tamanho = tamanho;
    }

    @Override
    public void run() {
        //aqui que está a tarefa ou rotina a ser concomitada
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para popular a lista com tamanho: " + tamanho);
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            lista.add(random.nextInt(tamanho));
        }
        System.out.println("Thread finalizada " + t.getName());
    }
}


class OrdenaBolha implements Runnable { //uso de threads sem memória compartilhada
    private List<Integer> lista;

    public OrdenaBolha(List<Integer> lista) {
        this.lista = lista;
    }
    @Override
    public void run() {
        //aqui está a tarefa ou rotina a ser concomitada - no caso, bubble sort
        boolean houveTroca;
        int tmp;
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para ordenar com bolha a lista com tamanho: " + lista.size());
        do {
            houveTroca = false;
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i) > lista.get(i+1)) {
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i+1));
                    lista.set(i+1, tmp);
                }
            }
        } while (houveTroca);
    }
}

class OrdenaPente implements Runnable { //uso de threads sem memória compartilhada
    private List<Integer> lista;

    public OrdenaPente(List<Integer> lista) {
        this.lista = lista;
    }

    public void run() {
        //aqui está a tarefa ou rotina a ser concomitada - no caso, bubble sort
        boolean houveTroca;
        int tmp;
        int distancia = lista.size();
        Thread t = Thread.currentThread();
        System.out.println("Thread " + t.getName() + " iniciada para ordenar com pente a lista com tamanho: " + lista.size());
        do {
            distancia = (int) (distancia / 1.3);
            houveTroca = false;
            for (int i = 0; i < lista.size() - distancia; i++) {
                if (lista.get(i) > lista.get(i+distancia)) {
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i+distancia));
                    lista.set(i+distancia, tmp);
                }
            }
        } while (distancia > 1 || houveTroca);
    }
}

public class Java {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> lista1 = new ArrayList<>();
        List<Integer> lista2 = new ArrayList<>();
        List<Integer> lista3 = new ArrayList<>();

        Thread threadPopula1 = new Thread(new PopulaLista(lista1, 10000), "PopulaLista1");
        Thread threadPopula2 = new Thread(new PopulaLista(lista2, 1000), "PopulaLista2");
        Thread threadPopula3 = new Thread(new PopulaLista(lista3, 5000), "PopulaLista3");



        threadPopula1.start();
        threadPopula2.start();
        threadPopula3.start();

        threadPopula1.join();
        threadPopula2.join();
        threadPopula3.join();


        Thread threadOrdena1 = new Thread(new OrdenaPente(lista1), "OrdenaPente1");
        Thread threadOrdena2 = new Thread(new OrdenaBolha(lista2), "OrdenaBolha1");
        Thread threadOrdena3 = new Thread(new OrdenaBolha(lista3), "OrdenaBolha2");

        threadOrdena1.start();
        threadOrdena2.start();
        threadOrdena3.start();
    }


}
>>>>>>> a8daaaf5fac36a9b95b03f55c1ad08d1eecd28b2
