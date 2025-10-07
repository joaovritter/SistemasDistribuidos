// Arquivo: Comunicador.java
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comunicador {

    // "Guarda" o bico e o receptor para serem usados em toda a comunicação
    private ObjectOutputStream escritor;
    private ObjectInputStream leitor;

    /**
     * O construtor. Ele é chamado UMA VEZ quando a conexão começa.
     * Ele cria o escritor e o leitor que serão usados para sempre.
     */
    public Comunicador(Socket socket) throws IOException {
        // A ordem é importante: SAÍDA primeiro, depois ENTRADA.
        this.escritor = new ObjectOutputStream(socket.getOutputStream());
        this.leitor = new ObjectInputStream(socket.getInputStream());
    }


    public String recebeMensagem() throws IOException, ClassNotFoundException {
        // Usa o leitor que já foi criado no construtor
        return (String) leitor.readObject();
    }

    public void enviaMensagem(String mensagem) throws IOException {
        // Usa o escritor que já foi criado no construtor
        escritor.flush();
        escritor.writeObject(mensagem);
    }


    public Componente recebeComponente() throws IOException, ClassNotFoundException {
        // Usa o mesmo leitor de antes
        return (Componente) leitor.readObject();
    }

    public void enviaComponente(Componente componente) throws IOException {
        // Usa o mesmo escritor de antes
        escritor.flush();
        escritor.writeObject(componente);
    }
}