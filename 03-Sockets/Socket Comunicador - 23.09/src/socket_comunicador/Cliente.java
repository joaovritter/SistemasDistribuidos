package socket_comunicador;

import java.io.IOException;
import java.net.Socket;

public class Cliente {

    Socket socket;

    public Cliente() {
        criarClienteSocket();
        ThreadRecebedora tr = new ThreadRecebedora(socket);
        ThreadEnviadora te = new ThreadEnviadora(socket);
        tr.start();
        te.start();
    }

    public void criarClienteSocket() {
        try {
            socket = new Socket("localhost", 1234);
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Cliente c = new Cliente();
    }
    
    

//  public static void main(String[] args) {
//    try {
//      int porta = 50000;      
//      String nome = JOptionPane.showInputDialog(null,"Nome completo");
//      
//      Socket cliente = new Socket("localhost",porta);   
//      //enviar o nome do cliente
//      
//      ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
//      saida.flush();
//      saida.writeObject(nome);
//      
//      //receber o objeto criado
//      ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
//      Pessoa p = (Pessoa)entrada.readObject();
//      if (p == null) {
//        JOptionPane.showMessageDialog(null, "Seu nome já está na lista com um email gerado");
//      } else {
//        JOptionPane.showMessageDialog(null,"Pessoa criada e recebida: " + p);
//      }
//      entrada.close();
//      System.out.println("Conexão encerrada");
//    }
//    catch(HeadlessException | IOException | ClassNotFoundException e) {
//      System.out.println("Erro: " + e.getMessage());
//    }
//  }
}
