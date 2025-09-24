/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket_comunicador;

import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author laboratorio
 */
public class ThreadEnviadora extends Thread{
    
    Socket socket; //conexao com outro computador 

    public ThreadEnviadora(Socket socket) {
        this.socket = socket;
    }
    
    public void run(){ //executa quando a thread for iniciada (thread.start())
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Digite: ");
            while(true){ //loop infinito, thread fica presa esperando por novas mensagens para enviar.
                String mensagem = scanner.nextLine();
                Comunicador.enviaMensagem(socket, mensagem); //envia mensagem pela rede, usa o socket para saber onde enviar
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
