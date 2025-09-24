/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket_comunicador;

import java.net.Socket;

/**
 *
 * @author laboratorio
 */
public class ThreadRecebedora extends Thread {
    Socket socket;

    public ThreadRecebedora(Socket socket) {
        this.socket = socket;
    }
    
    public void run(){
        System.out.println("Iniciada a Thread para recebimento de dados");
        
        try{
            while(true){
                String mensagem = Comunicador.recebeMensagem(socket);
                if (mensagem == null){
                    System.exit(0);
                }
                System.out.println("Mensagem recebida: " + mensagem);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    
    
}
