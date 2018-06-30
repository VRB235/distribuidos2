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
        Thread _threadCliente;
        try {
            ObjectOutputStream _out;
            ObjectInputStream _in;
            ServerSocket _serverSocket = new ServerSocket(1234);
            while(true){
                System.out.println("Esperando conexion...");
                _socket = _serverSocket.accept();
                System.out.println("Cliente aceptado: "+_socket.getInetAddress());
                
                DataOutputStream _dataOutputStream = null;
                try (DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream())) {
                    
                    _dataOutputStream = new DataOutputStream(_socket.getOutputStream());
                    String _peticion = _dataInputStream.readUTF();
                    System.out.println(_peticion + "Cliente: "+_socket.getInetAddress());
                    
                    if(_peticion.equals("sync")){
                        _dataOutputStream.writeUTF("ack");
                        _out = new ObjectOutputStream(_socket.getOutputStream());
                        _in = new ObjectInputStream(_socket.getInputStream());
                        
                        Transporte _transporte = (Transporte) _in.readObject();
                        System.out.println("Transporte con "+_transporte.getPaquete()+ " paquetes");
                        _transporte.setPaquete(_transporte.getPaquete()-1);
                        _cliente.enviarTransporte(_transporte);
                        _threadCliente = new Thread(_cliente);
                        _threadCliente.start(); 
                    }
                    if(_peticion.equals("close")){
                        break;
                    }
                   
                    
                    
                    
                      
                } catch (ClassNotFoundException ex) { 
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                _dataOutputStream.close();
                
            }
        
        _socket.close();
        
        /*Cliente _cliente = new Cliente();
                    _cliente.start();*/

        
            
            
        } catch (IOException e) {
        
            System.out.println("Error "+e.getMessage());
        }
        
    }
    
}
