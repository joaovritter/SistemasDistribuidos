# 📖 Glossário de Conceitos - Sistemas Distribuídos e Concorrência

Este arquivo contém termos e expressões aprendidos na disciplina, com definições resumidas e objetivas.

---

## 🔹 Comunicação Bloqueante
Quando um processo ou thread **fica aguardando** até que a operação de comunicação (ex.: envio ou recebimento de mensagem) seja concluída.  
➡️ Exemplo: um `recv()` que só retorna quando a mensagem chega.

---

## 🔹 Paralelo
Execução simultânea de múltiplas tarefas em diferentes processadores ou núcleos.  
➡️ Difere de concorrência, que pode existir mesmo em um único processador (com alternância rápida).

---

## 🔹 Diferença entre Sistema Distribuído e Paralelo
- **Paralelo**: múltiplos processadores/núcleos **em uma mesma máquina** resolvendo partes de um problema.  
- **Distribuído**: múltiplos computadores conectados em rede, cooperando como se fossem um sistema único.

---

## 🔹 Thread
Fluxo de execução dentro de um processo.  
Todas as threads compartilham o **mesmo espaço de memória** e recursos do processo.

---

## 🔹 Race Condition (Condição de Corrida)
Quando duas ou mais threads/processos acessam um recurso compartilhado **ao mesmo tempo**, e o resultado depende da ordem de execução (imprevisível).

---

## 🔹 Deadlock
Situação em que dois ou mais processos/threads ficam **bloqueados indefinidamente**, esperando recursos uns dos outros.  
➡️ Analogia: cruzamento de carros em que todos entram e ficam esperando que o outro saia primeiro.

---

## 🔹 Concomitante
Quando duas ou mais tarefas ocorrem **ao mesmo tempo** ou de forma **sobreposta no tempo**.  
➡️ Pode se referir tanto a execução paralela quanto concorrente.

---

## 🔹 Relógio Físico
Baseado em um relógio de hardware (tempo real). Usado para sincronizar eventos com a passagem de tempo do mundo real.  
➡️ Exemplo: NTP (Network Time Protocol).  

### Detalhes
- Cada máquina em um sistema distribuído possui seu próprio relógio físico.  
- Esses relógios sofrem **deriva** (atrasam ou adiantam) em relação ao tempo real.  
- Protocolos como **NTP** ou GPS ajudam a sincronizar, mas nunca com precisão perfeita.  
- Problema: atrasos de rede e diferenças de hardware fazem com que duas máquinas nunca tenham exatamente a mesma hora.  

---

## 🔹 Relógio Lógico
Mecanismo de marcação de eventos em sistemas distribuídos que **não depende do tempo real**, mas sim da ordem dos eventos.  
➡️ Exemplo: Relógios de Lamport e Relógios Vetoriais.  

### Detalhes
- Criado para resolver a questão: *qual evento aconteceu antes do outro?*  
- **Relógio de Lamport**: cada processo mantém um contador que aumenta a cada evento. Ao enviar mensagens, envia o contador junto.  
- **Relógios Vetoriais**: cada processo mantém um vetor com informações de todos os outros processos, permitindo detectar não só a ordem, mas também eventos **concorrentes**.  

### Analogia
- Relógio físico: relógio de parede em casas diferentes (sempre há pequenas diferenças).  
- Relógio lógico: lista de ordem de chegada de pessoas em uma fila (não importa a hora real, importa quem veio antes).  

---

## 🔹 Exclusão Mútua
Mecanismo para garantir que apenas **uma thread ou processo por vez** acesse uma seção crítica (recurso compartilhado).  
➡️ Exemplos: `mutex`, `semaphore`.

---

## 🔹 Eleição
Algoritmo usado em sistemas distribuídos para escolher um **coordenador ou líder** entre vários processos.  
➡️ Exemplo: Algoritmo do Bully.

---
