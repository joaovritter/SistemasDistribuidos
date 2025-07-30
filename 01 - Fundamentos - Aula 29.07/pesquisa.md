**1- O que é um processo**
  - é uma instância de um programa em execução;
  - possui sem próprio espaço de memória;
  - contém threads;
  - tem o contador de programa (próxima instrução a executar), pilha e dados.
  
  - criado quando um programa é iniciado;
  - o SO reserva recursos do processador para o processo;
  - o processador executa as instruções do processo;
  - o SO altera rapidamente entre todos processos (escalonamento);
  - um processo pode se comunicar com outro, mas somente através de mecanismos (socket, pipes, memória compartilhada).

**2- O que é um thread**
  - é uma linha de execução;
  - representa um fluxo de execução dentro de um processo;
  - um processo pode ter um ou vários threads;
  - eles compartilham os mesmos recursos do processo.

  - thread principal é criado junto com a criação do processo;
  - o compartilhamento de recursos permite comunicação rápida mas exige sincronização para evitar conflitos;
  - permite execução paralala (se houver vários núcleos de CPU);
  - termina quando conclui a tarefa.

**3- Diferença entre processo e thread**
  - Processo tem recursos isolados X threads compartilham recursos do processo;
  - Memória própria X compartilham memória do processo;
  - comunicação mais lenta e complexa X mais rapida, compartilham o mesmo espaço de memória.

**4-	Diferença entre ponto a ponto e cliente servidor**
  - Ponto a Ponto
    - todos dispositivos podem ser clientes e servidores ao mesmo tempo;
    - cada ponta pode ler e escrever diretamente de outras pontas;
    - estrutura distribuída;
    - escalabilidade;
    - tolerancia a falhas;
    - Ex: download no uTorrent, um usuário baixa uma parte, essa parte é enviada para outro usuário baixar, ele le e escreve
  - Cliente Servidor
    - clientes solicitam recursos de um servidor centralizado;
    - servidor sempre ativo esperando conexões;
    - escalabilidade limitada ao servidor;
    - estrutura centralizada;
    - seguranca mais facil de controlar;
    - nem tanto tolerante a falhas, se o servidor cair, já era.

    - Cliente solicita;
    - servidor escuta o pedido e responde;
    - Ex: cliente entra no youtube, clica em um vídeo e o servidor do youtube envia.
    
