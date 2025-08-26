# Sistemas Distribuídos

Este repositório contém conteúdos e exemplos da disciplina de **Sistemas Distribuídos**.

## O que são Sistemas Distribuídos?

Um **sistema distribuído** é um conjunto de computadores independentes que se comunicam e coordenam suas ações através de uma rede, apresentando-se ao usuário como um sistema único e coerente.  

### Características principais:
- **Transparência**: o usuário não precisa saber onde está o recurso (transparência de localização), quem o está usando (transparência de acesso) ou se houve falha e recuperação (transparência de falhas).  
- **Escalabilidade**: a capacidade de crescer (mais máquinas e usuários) sem perder desempenho.  
- **Concorrência**: múltiplos usuários/processos podem acessar recursos simultaneamente.  
- **Falhas**: como a rede e os nós podem falhar, o sistema precisa lidar com **tolerância a falhas**.  

### Exemplos de sistemas distribuídos no dia a dia:
- Redes sociais (Facebook, Instagram, Twitter/X).  
- Sistemas de streaming (Netflix, Spotify, YouTube).  
- Jogos online multiplayer.  
- Serviços de nuvem (Google Drive, AWS, Azure).  
- Aplicações de mensagens instantâneas (WhatsApp, Telegram).  

---

## Conceitos rápidos da disciplina

- **Threads**  
  Fluxos de execução independentes dentro de um processo, permitindo paralelismo e desempenho.

- **Sockets**  
  Interface de comunicação entre processos pela rede, base de muitos protocolos como HTTP, FTP, etc.

- **RPC (Remote Procedure Call)**  
  Execução de funções em outro computador como se fossem locais, escondendo a complexidade da rede.

- **RMI (Remote Method Invocation)**  
  Extensão do RPC em Java, permitindo chamadas de métodos de objetos distribuídos.

- **Multicast**  
  Comunicação em grupo onde uma mensagem é enviada uma vez e entregue a vários receptores interessados.

- **JGroups**  
  Biblioteca Java que facilita a criação de aplicações distribuídas com comunicação em grupo, tolerância a falhas e replicação de estados.

---
