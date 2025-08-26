# ⏱️ Relógios e Mecanismos em Sistemas Distribuídos

## ⏱️ Relógio Físico
**O que é:** o relógio de hardware do computador (igual ao relógio do PC ou celular).  

**Problema:** cada máquina tem o seu, e eles nunca batem exatamente iguais — alguns adiantam, outros atrasam.  

**Exemplo prático:**  
- Duas pessoas combinando de se ligar às 15:00.  
- Uma está com o relógio adiantado 2 min e liga às 14:58, achando que já são 15:00.  
- A outra ainda vê 14:58 no seu relógio.  
- Esse desencontro causa confusão.  

**Como corrigem:** usam protocolos de sincronização, como o **NTP (Network Time Protocol)** ou sinais de **GPS**. Eles ajustam os relógios, mas ainda existe um pequeno erro por conta de:
- atraso de rede (no tempo de transporte da mensangem);  
- diferenças de hardware.  

👉 Em sistemas distribuídos, isso significa que não dá para confiar 100% no tempo real para decidir a ordem de eventos.

---

## ⏳ Relógio Lógico
**O que é:** um sistema de contagem para organizar eventos sem depender do horário real.  

**Objetivo:** ele responde a pergunta: **“qual evento aconteceu antes do outro?”**  

**Exemplo prático:**  
- duas pessoas mandam mensagem quase ao mesmo tempo num grupo do WhatsApp.  
- O servidor precisa mostrar uma ordem: "Mensagem A antes da Mensagem B".  
- Não importa se os celulares estão com horários diferentes; importa a ordem de chegada dos eventos.  

### 🔹 Relógio de Lamport
- Cada processo mantém um contador (um número inteiro).  
- Toda vez que acontece algo (evento local), incrementa esse número.  
- Quando manda uma mensagem, envia junto o valor do contador.  
- Quem recebe, ajusta seu contador para garantir uma ordem coerente.  

👉 Ele resolve a ordem causal (“A aconteceu antes de B”), mas não detecta concorrência.  

### 🔹 Relógios Vetoriais
- Cada processo guarda um vetor de contadores (um contador para cada processo).  
- Assim, dá para saber não só se um evento veio antes, mas também se dois eventos são concorrentes (independentes).  

👉 Com vetor = mais informação = mais precisão.  

**Analogia:**  
- Relógio físico = relógios de parede em casas diferentes (sempre com atraso/adianto).  
- Relógio lógico = lista de ordem de chegada na fila do cinema (não importa a hora, importa quem chegou antes).  

---

## 🔒 Exclusão Mútua
**Problema:** vários processos querem acessar ao mesmo tempo um recurso compartilhado (ex: banco de dados, arquivo, impressora).  

**Objetivo:** garantir que só um por vez use o recurso (evita conflito e corrupção de dados).  

- Pyhton e C# utilizam lock
- Java utiliza synchronized

**Exemplos:**  
- **Mutex (Mutual Exclusion Lock):** só uma thread/processo por vez pode segurar a "chave" (lock).  
- **Semáforo:** como um semáforo de carros. Pode permitir só 1 (como o mutex) ou um número limitado de acessos simultâneos.  

👉 **Exemplo do mundo real:**  
- Duas pessoas querendo sacar dinheiro ao mesmo tempo em caixas diferentes da mesma conta.  
- Se não houver exclusão mútua, os dois podem ver saldo de R$1000 e sacar R$800 cada, deixando a conta negativa.  

---

## 👑 Eleição
**Problema:** em sistemas distribuídos, muitas vezes é preciso um líder/coordenador (por exemplo, quem organiza as tarefas, resolve conflitos, etc.).  
- Se o líder atual cai, é necessário escolher outro.  
- Algoritmos de eleição fazem esse processo de forma distribuída, sem um chefe fixo. 

**Exemplo: Algoritmo do Bully**  
- Cada processo tem um número de identificação.  
- Se o líder cai, um processo dispara uma eleição.  
- Ganha o processo com o maior ID (o mais forte).  
- Ele se anuncia como novo líder.  

👉 **Exemplo do mundo real:**  
- Um grupo de colegas está fazendo um trabalho.  
- Se o colega que estava liderando some do grupo, outro com mais autoridade assume.  

---

## 📌 Resumo intuitivo
- **Relógio físico:** tenta sincronizar com o tempo real, mas nunca é perfeito.  
- **Relógio lógico:** organiza eventos pela ordem, sem precisar do tempo real.  
- **Exclusão mútua:** garante que só um processo acesse recurso compartilhado por vez.  
- **Eleição:** escolhe quem vai ser o líder entre vários processos.  
