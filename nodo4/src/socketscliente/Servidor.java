/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketscliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leona
 */
public class Servidor implements Runnable {
    
    Socket _socket;
    
    public Servidor (){
        _socket = null;
    }
    
    @Override
    public void run (){
        Cliente _cliente = new Cliente();
        
        try {
            
            ServerSocket _serverSocket = new ServerSocket(1234);
            AtenderCliente _atenderCliente;
            Thread _threadAtenderCliente;
            while(true){
                
                System.out.println("Esperando conexion...");
                _socket = _serverSocket.accept();
                _atenderCliente = new AtenderCliente(_socket, _serverSocket, _cliente);
                
                _threadAtenderCliente = new Thread(_atenderCliente);
                _threadAtenderCliente.start();
                
                if(1==2){
                    break;
                }
                
            }
            
            
        
        _socket.close();
        
        /*Cliente _cliente = new Cliente();
                    _cliente.start();*/

        
            
            
        } catch (IOException e) {
        
            System.out.println("Error "+e.getMessage());
        }
        
    }
    
}
