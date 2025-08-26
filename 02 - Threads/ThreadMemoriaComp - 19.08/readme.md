# 🧵 Conceito Threads com Memória Compartilhada

## 🔹 O que são threads?
- Fluxo de execução independente dentro do mesmo processo.
- Compartilham o **mesmo recurso**.

## 🔹 Memória Compartilhada
- Todas as threads acessam a memória do processo principal.
- Variáveis globais e estruturas alocadas são visíveis a todas.
- Comunicação simples entre threads.

## 🔹 Benefícios
- ✅ Comunicação rápida.
- ✅ Menor consumo de memória.
- ✅ Compartilhamento direto de dados.

## 🔹 Problemas
- ⚠️ Condições de corrida (race conditions).
    - Quando dois ou mais threads ou processos acessam e modificam simultaneamente um recurso compartilhado, resultando em comportamento inesperado ou erros devido à ordem imprevisível em que as operações são executadas.
- ⚠️ Inconsistência de dados.
- ⚠️ Deadlocks.
  - quando duas ou mais threads ficam presas, cada uma esperando que a outra libere um recurso já mantido, impedindo que qualquer uma delas possa continuar.
      - Exemplo: um cruzamento onde todos carros querem virar à esquerda, mas para isso precisa que o carro da frente libere o caminho
      - Cada carro ocupa uma parte do cruzamento e espera que outro saia primeiro.
      - Resultando em um deadlock onde não conseguem se mover e ficam bloqueados esperando indefinidamente.

## 🔹 Soluções (Sincronização)
- 🔒 Mutex: exclusão mútua.
- 🚦 Semáforos: controle de acesso.
- 📌 Monitores: bloqueio + espera.
- 🕐 Variáveis de condição: espera por eventos.

## 🔹 Threads sem x com Memória Compartilhada
- Threads SEM memória compartilhada
    - Cada thread recebe parâmetros próprios (ex.: números, strings, objetos independentes).
    - As variáveis não são acessadas em comum.
    - Mais fáceis de implementar, sem necessidade de sincronização.
    - Menos propensas a condições de corrida.
- Threads com memória compartilhada
    - Duas ou mais threads acessam a mesma estrutura de dados (lista, dicionário, objeto).
    - Necessário uso de mecanismos de sincronização (Java synchronized, C# lock, Python threading.Lock).
    - Mais eficientes em alguns casos, MAS REQUEREM cuidado com concorrência (deadlocks, race conditions).

---

## 🔹 Exemplo em **Java**
```java
class ListaCompartilhada {
    private final List<Integer> numeros = new ArrayList<>();

    public synchronized void adicionarNumero(int n) {
        numeros.add(n);
        System.out.println(Thread.currentThread().getName() + " adicionou: " + n);
    }
    public synchronized List<Integer> retornar() {
        return new ArrayList<>(numeros);
    }
}

class Trabalho extends Thread {
    private final ListaCompartilhada lista;
    public Trabalho(ListaCompartilhada lista) { this.lista = lista; }
    public void run() {
        for (int i = 0; i < 5; i++) lista.adicionarNumero(i);
    }
}

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        ListaCompartilhada lista = new ListaCompartilhada();
        Thread t1 = new Trabalho(lista);
        Thread t2 = new Trabalho(lista);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Lista final: " + lista.retornar());
    }
}
```

---

## 🔹 Exemplo em **Python**
```python
import threading, time

class ListaCompartilhada:
    def __init__(self):
        self.numeros = []
        self.lock = threading.Lock()
    def adicionar(self, n):
        with self.lock:
            self.numeros.append(n)
            print(f"{threading.current_thread().name} adicionou: {n}")
    def retornar(self):
        with self.lock: return list(self.numeros)

def trabalho(lista):
    for i in range(5):
        lista.adicionar(i)
        time.sleep(0.05)

if __name__ == "__main__":
    lista = ListaCompartilhada()
    t1 = threading.Thread(target=trabalho, args=(lista,))
    t2 = threading.Thread(target=trabalho, args=(lista,))
    t1.start(); t2.start()
    t1.join(); t2.join()
    print("Lista final:", lista.retornar())
```
➡️ Ambos os exemplos mostram threads adicionando números em uma lista **compartilhada**, com uso de **sincronização** para evitar problemas de concorrência.

---

## 🔹 Quando usar?
- Alta performance e baixa latência.
- Necessidade de compartilhar dados.
- Exemplos: servidores web, simulações científicas, jogos.
