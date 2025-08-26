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

