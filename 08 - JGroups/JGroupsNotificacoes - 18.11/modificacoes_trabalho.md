# 📌 Resumo das Modificações Implementadas

## 1. Mostrar IP ao lado dos membros
- **Onde:** `viewAccepted()` (linhas 166–172)
- **O que foi feito:**
  - Membros exibidos usando `membro.toString()`, incluindo nome + IP + porta.
  - Endereço completo aparece na lista de membros e no combo box.
- **Resultado:**  
  A lista mostra entradas completas, ex.: `nome@192.168.0.10:7800`.

---

## 2. Notificar quando um membro sair
- **Onde:**  
  - `viewAccepted()` (linhas 175–178)  
  - Novo método `verificarMembrosSairam()` (linhas 202–224)
- **O que foi feito:**
  - Cópia da lista anterior de membros (linhas 155–158).
  - Comparação entre lista anterior e atual.
  - Identificação dos membros que saíram.
  - Exibição de notificação na área de mensagens gerais.

---

## 3. Atualizar mensagens para quem entra atrasado
- **Onde:** várias partes do código  
- **O que foi feito:**
- Criada variável `historicoMensagens` — `ArrayList` (linha 23).
- Inicialização no `iniciar()` (linha 52).
- **receive() (linhas 98–130):**
  - Detecta mensagens de histórico (`**HISTÓRICO**` no início).
  - Exibe histórico com marcadores:
    - --- Mensagens Anteriores ---
    - --- Fim das mensagens anteriores ---
  - Mensagens normais: exibidas e armazenadas no histórico.
- **viewAccepted() (linhas 180–184):**
  - Detecta novos membros comparando quantidade.
  - Chama método de envio do histórico.
- **Novo método `enviarHistoricoParaNovosMembros()` (linhas 226–290):**
  - Verifica se há histórico.
  - Evita envio para si mesmo.
  - Identifica novos membros comparando listas.
  - Monta histórico completo prefixado com `**HISTÓRICO**\n`.
  - Envia mensagem privada apenas ao novo membro.

- **Resultado:**  
Quando um membro entra, recebe automaticamente todas as mensagens anteriores com marcadores visuais.

---

# 🛠️ Resumo Técnico

## ✔️ Nova variável
- `historicoMensagens` — `ArrayList<String>` (linha 23)

## ✔️ Novos métodos
- `verificarMembrosSairam()` — linhas 202–224  
- `enviarHistoricoParaNovosMembros()` — linhas 226–290  

## ✔️ Métodos modificados
- `iniciar()` — inicialização do histórico (linha 52)  
- `receive()` — processamento de histórico e armazenamento de mensagens (linhas 98–130)  
- `viewAccepted()` — cópia da lista anterior, detecção de novos membros e envio de histórico (linhas 153–185)  

---

# 🔍 Detalhes de Implementação

- **Cópia segura da lista:**  
`new ArrayList<>(this.listaMembros)` evita problemas de referência (linha 157).

- **Proteção contra autoenvio:**  
Verifica se o membro atual já estava no grupo antes (linhas 238–251).

- **Mensagens privadas:**  
Histórico é enviado individualmente via `Message(enderecoDestino, conteudo)` (linha 283).

- **Formato do histórico:**  
Prefixo `**HISTÓRICO**\n` seguido das mensagens formatadas.
