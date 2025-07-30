# Fundamentos de Sistemas Distribuidos e Paralelos
- Comunicação é troca, baseado no TCP/IP
- Comunicação é bloqueante 
  - quando um fala o outro escuta, mesmo funcionamento do oktok
- Sincronismo para não haver interferência na comunicação
- Concomitante não é ao mesmo tempo, diferença curta de tempo
- Pararelo é ao mesmo tempo, no tempo 1 ocorre várias coisas

## Sistemas Distribuidos
- Heterogêneos: diferentes arquiteturas, so e linguagem
- Fracamente acoplados
  - distribuídos geograficamente via protocolos do modelo TCP/IP: endereço de rede, porta lógica, máscara de rede, protocolosde transporte)
  - GRID computacional
  - Arquiteturas: Cliente-Servidor; Ponto-a-Ponto
      - Tolerância a falhas (mas nem tanto)
        - se precisar trocar uma memória, precisa desligar) 
      - Escalabilidade
      - Segurança
      - Manutenção/atualização
- Objetivo: compartilhar recursos (processador e memória)
      - ao compartilhar recurso, é necessário controlar SINCRONISMO (fila):
      - relógio: lógico e físico
        - Cada um em um tempo
      - recurso: exclusão mútua
        -só sai depois de terminar o serviço
- SD são fortemente dependente de Sistemas Operacional: gestor de processamento; gestor de comunicação, ou um gestor das camadas de serviço;
  - Se tem comunicação pode ter invasão
- Observação: Sistemas distribuídos, na essência, têm comunicação via SOCKET (ip, porta, márcara, objetos escritores/leitores) que é BLOQUEANTE.
  - socket tem leitor e escritor nas extremidades 
  - SOLUÇÃO COMPUTACIONAL EM TEMPO DE PROGRAMAÇÃO é o uso de THREADS.
    
- Características básicas:
  - arquitetura:
    - cliente-servidor
    - ponto-a-ponto (P2P)
    - híbrido
  - comunicação bloqueante
    - escrever (bloqueia quando alguem escreve)
    - ler
  - programação multitarefa (thread)
    - thread é um mini processo dentro de um processo
    - thread pode ser com memória compartilhada (variavel)
        - sincronismo: monitor, semáforo
    - thread sem memória compartilhada (uma não depende da outra)
    - importância: execução de processos concomitantemente. E em SD, para liberar comunicação bloqueante


## Sistemas Paralelos
-Homogêneos: arquitetura, SO e linguagem idênticas
-Fortemente acoplados
  -fixo no mesmo lugar via tcp/ip: endereço da rede, porta lógica, máscara de rede, protocolos de transporte
- Cluster Computacional (aglomerado)
  - Cluster conectados distribuidos
- Arquitetura ponto-a-ponto
  - Tolerância a falhas
    - se precisar trocar uma memoria, muda o processamento para o espelho troca e volta 
  - Escalabilidade
    - capacidade de crescer, posso por mais uma máquina
  - Segurança
  - Manutenção/atualização
- Objetivo: compartilhar recurso (processador, memória e placa)

-Tendem a ser mais rapidos pois não tem latência da rede
-Java mão suporta, pois a JVM roda em somente um core
