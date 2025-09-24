
package socket_comunicador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta é uma classe "ajudante" ou "utilitária".
 * O objetivo dela é simplificar o trabalho de enviar e receber mensagens
 * através de uma conexão de rede (Socket), escondendo os detalhes técnicos.
 *
 * @author laboratorio
 */
public class Comunicador {

    /** Envia uma String pela conexão do Socket. */
    public static void enviaMensagem(Socket s, String mensagem) {
        try {
            // Cria um "empacotador" que envia objetos pelo canal de saída do socket.
            ObjectOutputStream escritor = new ObjectOutputStream(s.getOutputStream());
            
            // "Empacota" a mensagem e a envia pela rede.
            escritor.writeObject(mensagem);

            //desliga a conexão após o envio. Impede mais conversas.
            escritor.close();

        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Espera e recebe uma String pela conexão do Socket. */
    public static String recebeMensagem(Socket s) {
        try {
            // Prepara um "desempacotador" para ler objetos do canal de entrada do socket.
            ObjectInputStream leitor = new ObjectInputStream(s.getInputStream());
            
            // Espera a mensagem chegar, "desempacota" e a transforma em String.
            String mensagem = (String) leitor.readObject();

            // desliga a conexão após receber.
            leitor.close();
            
            return mensagem;

        } catch (Exception e) {
            return null;
        }
    }

}