# 🚿 Thread Pool (Piscina de Threads)

---

**🧠 O que é:** 
- Um Thread Pool é um conjunto de threads pré-criadas que ficam esperando para executar tarefas.
- Em vez de criar e destruir threads o tempo todo (custoso!), você reaproveita threads já existentes.
- Ideal para sistemas que recebem muitas tarefas pequenas e rápidas.

---

** ⛏ Como funciona**
- Você cria um pool com N threads fixas ou configuráveis.
- As tarefas são colocadas em uma fila (queue).
- Cada thread do pool pega tarefas da fila e executa.
- Depois de terminar, a thread volta para o pool, pronta para outra tarefa.

---

** 🟢Vantagens**
- Desempenho melhorado: evita custo de criar/destruir threads constantemente.
- Controle de recursos: limita o número de threads rodando simultaneamente, evitando sobrecarga da CPU.
- Gerenciamento centralizado: fácil controlar prioridade e execução das tarefas.

---

**🛑Desvantagens**
- Se todas as threads estiverem ocupadas e a fila crescer muito → possível atraso na execução.
- Complexidade extra se você precisa de tarefas com prioridades diferentes.
- 
---
**💉 Como se aplica**
- Servidores web → cada requisição HTTP é tratada por uma thread do pool.
- Processamento de filas → worker threads pegam tarefas da fila e processam.
- Sistemas distribuídos → comunicação com múltiplos clientes sem criar threads constantemente.

---
## 🔹 Exemplo em **Java** com o código de ListasCompartilhadas
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

public class PrincipalThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ListaCompartilhada lista = new ListaCompartilhada();

        // Criando um pool com 3 threads
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Submetendo 5 tarefas ao pool
        for (int i = 0; i < 5; i++) {
            int valor = i;
            pool.submit(() -> lista.adicionarNumero(valor));
        }

        // Submetendo mais 5 tarefas
        for (int i = 5; i < 10; i++) {
            int valor = i;
            pool.submit(() -> lista.adicionarNumero(valor));
        }

        pool.shutdown();            // Não aceita mais tarefas
        pool.awaitTermination(1, TimeUnit.MINUTES); // Espera todas terminarem

        System.out.println("Lista final: " + lista.retornar());
    }
}

```

**🔍 O que mudou**

1. ExecutorService → usado para criar o Thread Pool (newFixedThreadPool(3) cria 3 threads fixas).
2. submit() → envia tarefas para o pool em vez de criar threads manualmente.
3. shutdown() + awaitTermination() → espera que todas as tarefas terminem antes de continuar.
4. O restante do código (ListaCompartilhada) não mudou, porque o Thread Pool usa threads normais para executar as tarefas.

**✅ Benefícios nesse exemplo**
- Apenas 3 threads executando todas as 10 tarefas → evita criar 10 threads separadas.
- Reaproveita threads existentes.
- Melhor performance em tarefas frequentes ou em sistemas com alto volume de requisições.
  
---

**📌 Resumo**
Thread Pool = threads reaproveitáveis.
Evita criar/destruir threads constantemente.
Controla concorrência e melhora desempenho.
