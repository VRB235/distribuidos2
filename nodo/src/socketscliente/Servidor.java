/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketscliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author leona
 */
public class Servidor extends Thread {
    
    Socket _socket;
    
    public Servidor (){
        _socket = null;
    }
    
    @Override
    public void start (){
        
        try {
            
            while(true){
                ServerSocket _serverSocket = new ServerSocket(Variables.puerto+1);
                System.out.println("Esperando conexion...");
                _socket = _serverSocket.accept();
                System.out.println("Cliente aceptado: "+_socket.getInetAddress());
                
                DataOutputStream _dataOutputStream;
                try (DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream())) {
                    
                    _dataOutputStream = new DataOutputStream(_socket.getOutputStream());
                    String _peticion = _dataInputStream.readUTF();
                    System.out.println(_peticion + "Cliente: "+_socket.getInetAddress());
                    _dataOutputStream.writeUTF("Chao");
                    
                      
                } 
        _dataOutputStream.close();
        _socket.close();
        
        Cliente _cliente = new Cliente();
                    _cliente.start();

        }
            
            
        } catch (IOException e) {
        
            System.out.println("Error "+e.getMessage());
        }
        
    }
    
}
