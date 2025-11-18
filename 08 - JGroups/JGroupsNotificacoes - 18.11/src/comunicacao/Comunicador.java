package comunicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.PhysicalAddress;

public class Comunicador extends ReceiverAdapter {

    JChannel channel;
    List<Address> listaMembros;
    View viewAtual; // Guarda a view atual para acessar endereços completos
    Map<Address, String> mapaEnderecosCompletos; // Mapa para guardar Address -> endereço completo
    String frase;
    Message mensagem;
    JFrame_chatJGROUPS meuFrame;
    StringBuffer membrosStringBuffer;
    ArrayList<String> historicoMensagens; //nova variavel, lista para guardar historico de mensagems

    public void iniciar(JFrame_chatJGROUPS meuFrame) throws Exception {

        System.setProperty("java.net.preferIPv4Stack", "true");//desabilita ipv6, para que só sejam aceitas conexões via ipv4
        /*
         * JGroups utiliza um JChannel como principal forma de conectar
         * a um cluster/grupo. É atraves dele que enviaremos e recebermos mensagens
         * bem como registrar os eventos callback quando acontecer alguma
         * mudança (por exemplo, entrada de um membro no grupo).
         * 
         * Neste caso, criamos uma instancia deste objeto, utilizando configurações default.
         */
        this.channel = new JChannel();
        /*
         * Definimos através do método setReceiver qual classe implementará
         * o método callback receive, que será chamado toda vez que alguém
         * enviar uma mensagem ao cluster/grupo. Neste caso, a própria classe
         * implementa o método receive mais abaixo.
         */
        this.channel.setReceiver(this);
        /*
         * O método connect faz com que este processo entre no cluster/grupo ChatCluster.
         * Não há a necessidade de se criar explicitamente um cluster, pois o método connect
         * cria o cluster caso este seja o primeiro membro a entrar nele.
         */
        this.meuFrame = meuFrame;

        //inicializa o historico de mensagens
        this.historicoMensagens = new java.util.ArrayList<String>();
        //inicializa o mapa de endereços completos
        this.mapaEnderecosCompletos = new HashMap<Address, String>();

        this.channel.setName(meuFrame.getjTextField_apelido().getText());
        this.channel.connect(meuFrame.getTitle());
        this.meuFrame.getjTextArea_listaMembros().setText(membrosStringBuffer.toString());
    }

    public void enviar(String frase, String participante) {
        try {
            if (participante == null) {
                /*
                 * cria uma instancia da classe Message do JGrupos com a mensagem.
                 * O primeiro parâmetro é o endereço do destinatário. Caso seja null, a mensagem é enviada para todos do grupo
                 * O segundo parâmetro é a mensagem enviada através de um buffer de bytes (convertida automaticamente)
                 */
                this.mensagem = new Message(null, frase);
            } else {
                for (int i = 0; i < this.listaMembros.size(); i++) {
                    if (participante.equals(listaMembros.get(i).toString())) {
                        System.out.println("Achouuuu");
                        this.mensagem = new Message(listaMembros.get(i), frase);
                        break;
                    }
                }
            }
            /*
            * envia a mensagem montada acima ao grupo
             */
            this.channel.send(this.mensagem);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(meuFrame, "Algo ocorreu de errrado ao enviar sua mensagem!!");
        }
    }

    public void finalizar() {
        this.channel.close();
    }

    /*
     * O método abaixo é callback, e é chamado toda vez que alguem
     * envia uma mensagem ao processo/grupo/canal. Esta mensagem é recebida no parâmetro
     * Message msg. Nessa implementação, mostramos na tela o originador
     * da mensagem em msg.getSrc() e a mensagem propriamente dita em
     * msg.getObject
     */
    @Override
    public void receive(Message msg) {
        String conteudo = msg.getObject().toString();

        //se é um historico sendo enviado, processa de forma especial
        if (conteudo.startsWith("**HISTÓRICO**")) {
            //remove o marcador e mostra o historico
            String historico = "";
            if (conteudo.length() > "**HISTÓRICO**\n".length()) {
                historico = conteudo.substring("**HISTÓRICO**\n".length());
            }
            this.meuFrame.getjTextArea_mensagensGerais().append("--- Mensagens Anteriores ---\n");
            if (!historico.isEmpty()) {
                this.meuFrame.getjTextArea_mensagensGerais().append(historico);
            } else {
                this.meuFrame.getjTextArea_mensagensGerais().append("(Nenhuma mensagem anterior)\n");
            }
            this.meuFrame.getjTextArea_mensagensGerais().append("--- Fim das mensagens anteriores ---\n");

        } else {
            //mensagem normal
            Date dt = new Date();
            Address remetenteAddress = msg.getSrc();
            String remetenteCompleto = remetenteAddress.toString();
            
            // Busca o endereço completo no mapa (que foi preenchido no viewAccepted)
            if (this.mapaEnderecosCompletos != null && this.mapaEnderecosCompletos.containsKey(remetenteAddress)) {
                remetenteCompleto = this.mapaEnderecosCompletos.get(remetenteAddress);
            } else {
                // Se não encontrou no mapa, tenta obter o endereço físico
                try {
                    Object result = this.channel.down(new org.jgroups.Event(org.jgroups.Event.GET_PHYSICAL_ADDRESS, remetenteAddress));
                    if (result != null && result instanceof PhysicalAddress) {
                        PhysicalAddress physAddr = (PhysicalAddress) result;
                        String ipPorta = physAddr.toString();
                        remetenteCompleto = remetenteAddress.toString() + "@" + ipPorta;
                    }
                } catch (Exception e) {
                    // Se falhar, usa apenas o nome
                }
            }
            
            String mensagemFormatada = "[" + dt.toString() + "] " + remetenteCompleto + " disse: "
                    + msg.getObject().toString() + "\n";

            //mostra na tela
            this.meuFrame.getjTextArea_mensagensGerais().append(mensagemFormatada);

            //guarda no historico
            if (!msg.getObject().toString().startsWith("**HISTÓRICO**")) {
                this.historicoMensagens.add(mensagemFormatada);
            }
        }
    }

    /*
     * O método abaixo é callback, e é chamado toda vez que uma nova
     * instancia entra no grupo, ou se alguma instancia sai do grupo.
     * Ele recebe uma View como parâmetro. Este objeto possui informações
     * sobre todos os membros do grupo.
     * Na nossa implementação, quando damos um print no objeto new_view
     * ele mostra, respectivamente:
     *      [Criador do grupo | ID da View]  [Membros do grupo]
     * 
     * Cada View possui uma ID, que a identifica. 
     * O ID da View é um Relógio de Lamport que marca a ocorrência de eventos.
     
    
    MODIFICAÇÕES ATIVIDADE:
     ao exibir os membros, deve extrair e mostrar o IP de cada Address.
    1.  ao montar a lista de membros, extrair o IP do Address.
    2. O Address ja tem ip, vou usar toString e extrair a parte do ip
    
    
     */
    @Override
    public void viewAccepted(View view_atual) {
        //guarda a view atual para usar no receive
        this.viewAtual = view_atual;
        
        //guarda a lista anterior para comparar depois
        List<Address> listaMembrosAnterior = null;
        if (this.listaMembros != null) {
            listaMembrosAnterior = new java.util.ArrayList<Address>(this.listaMembros);
        }

        this.listaMembros = view_atual.getMembers();
        this.membrosStringBuffer = new StringBuffer();
        this.meuFrame.getjTextArea_listaMembros().setText("");
        this.meuFrame.getjComboBox_listaParticipantesGrupo().removeAllItems();
        this.meuFrame.getjComboBox_listaParticipantesGrupo().addItem("Selecione o participante");
        
        // Limpa e preenche o mapa de endereços completos
        if (this.mapaEnderecosCompletos == null) {
            this.mapaEnderecosCompletos = new HashMap<Address, String>();
        } else {
            this.mapaEnderecosCompletos.clear();
        }

        for (int i = 0; i < listaMembros.size(); i++) {
            Address membro = listaMembros.get(i);
            String nomeMembro = membro.toString();
            String membroComEndereco = nomeMembro;
            
            // Tenta obter o endereço físico (IP:porta)
            try {
                Object result = this.channel.down(new org.jgroups.Event(org.jgroups.Event.GET_PHYSICAL_ADDRESS, membro));
                if (result != null && result instanceof PhysicalAddress) {
                    PhysicalAddress physAddr = (PhysicalAddress) result;
                    String ipPorta = physAddr.toString();
                    membroComEndereco = nomeMembro + "@" + ipPorta;
                }
            } catch (Exception e) {
                // Se falhar, usa apenas o nome
            }
            
            // Guarda no mapa para usar no receive
            this.mapaEnderecosCompletos.put(membro, membroComEndereco);

            membrosStringBuffer.append(membroComEndereco + "\n");
            this.meuFrame.getjComboBox_listaParticipantesGrupo().addItem(membroComEndereco);
        }
        this.meuFrame.getjTextArea_listaMembros().setText(membrosStringBuffer.toString());

        // Verifica se alguém saiu (para a nova funcionalidade de notificação )
        if (listaMembrosAnterior != null) {
            verificarMembrosSairam(listaMembrosAnterior, this.listaMembros);
        }

        //verifica se alguem entrou para enviar historico
        // Se listaAnterior tem menos membros que a atual, alguém entrou
        if (listaMembrosAnterior != null && listaMembrosAnterior.size() < this.listaMembros.size()) {
            enviarHistoricoParaNovosMembros(listaMembrosAnterior, this.listaMembros);
        }
    }

    /*
     * Este método callback é chamado toda vez que um membro é 
     * suspeito de ter falhado, porém ainda não foi excluído
     * do grupo. Esse método só é executado no coordenador do grupo.
     */
    @Override
    public void suspect(Address mbr) {
        JOptionPane.showMessageDialog(meuFrame, "PROCESSO SUSPEITO DE FALHA: " + mbr);
    }

    /**
     * *
     * Metodo que verifica quem saiu do grupo compara a lista anterior com a
     * atual e identifica quem saiu
     */
    private void verificarMembrosSairam(List<Address> listaAnterior, List<Address> listaAtual) {
        if (listaAnterior != null && listaAnterior.size() > listaAtual.size()) {
            //procura quem estava na lista anterior e não está na atual
            for (int i = 0; i < listaAnterior.size(); i++) {
                Address membroAnterior = listaAnterior.get(i);
                boolean estaNoGrupo = false;

                //verifica se esse membro ainda esta na lista atual
                for (int j = 0; j < listaAtual.size(); j++) {
                    if (membroAnterior.equals(listaAtual.get(j))) {
                        estaNoGrupo = true;
                        break;
                    }
                }
                //se nao esta mais no grupo, mostra notificacao
                if (!estaNoGrupo) {
                    Date dt = new Date();
                    String notificacao = "[" + dt.toString() + "] *** " + membroAnterior.toString() + " saiu do grupo ***\n";
                    this.meuFrame.getjTextArea_mensagensGerais().append(notificacao);
                }
            }
        }
    }

    private void enviarHistoricoParaNovosMembros(List<Address> listaAnterior, List<Address> listaAtual) {
        // Se não tem histórico, não precisa enviar nada
        if (this.historicoMensagens == null || this.historicoMensagens.isEmpty()) {
            return;
        }
        
        // Verifica se eu estava no grupo antes
        Address meuEndereco = this.channel.getAddress();
        if (meuEndereco == null) {
            return;
        }
        
        boolean euEstavaNoGrupo = false;
        if (listaAnterior != null) {
            for (int i = 0; i < listaAnterior.size(); i++) {
                if (meuEndereco.equals(listaAnterior.get(i))) {
                    euEstavaNoGrupo = true;
                    break;
                }
            }
        }
        
        // Se eu não estava no grupo antes, não envio histórico (sou o novo membro)
        if (!euEstavaNoGrupo) {
            return;
        }
        
        // Encontra quem é novo e envia histórico
        for (int i = 0; i < listaAtual.size(); i++) {
            Address membroAtual = listaAtual.get(i);
            
            // Pula se for eu mesmo
            if (membroAtual.equals(meuEndereco)) {
                continue;
            }
            
            // Verifica se é novo (não estava na lista anterior)
            boolean ehNovo = true;
            if (listaAnterior != null) {
                for (int j = 0; j < listaAnterior.size(); j++) {
                    if (membroAtual.equals(listaAnterior.get(j))) {
                        ehNovo = false;
                        break;
                    }
                }
            }

            // Se é novo, envia o histórico
            if (ehNovo) {
                try {
                    // Monta o histórico como uma string
                    StringBuffer historicoCompleto = new StringBuffer();
                    historicoCompleto.append("**HISTÓRICO**\n");
                    for (int k = 0; k < this.historicoMensagens.size(); k++) {
                        historicoCompleto.append(this.historicoMensagens.get(k));
                    }
                    // Envia mensagem privada para o novo membro
                    Message msgHistorico = new Message(membroAtual, historicoCompleto.toString());
                    this.channel.send(msgHistorico);
                } catch (Exception e) {
                    // Se der erro ignora
                }
            }
        }
    }
}
