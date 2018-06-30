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
    int  enEspera = 0;
    
    public Servidor (){
        _socket = null;
    }
    
    @Override
    public void run (){
        /*LeeFichero _leer = new LeeFichero();
        EscribeFichero _escribir = new EscribeFichero();
        if(_leer.leer()<=3){
            _escribir.escribir(_leer.leer()+1); 
        }*/
        Cliente _cliente = new Cliente();
        String [] _linea;
        LeeFichero _leer = new LeeFichero();
        EscribeFichero _escribir = new EscribeFichero();
        
        try {
            
            ServerSocket _serverSocket = new ServerSocket(1232);
            AtenderCliente _atenderCliente;
            Thread _threadAtenderCliente;
            while(true){
                
                System.out.println("Esperando conexion...");
                _socket = _serverSocket.accept();
                _atenderCliente = new AtenderCliente(_socket, _serverSocket, _cliente);
                _linea =_leer.leer().split(":");
                    enEspera = Integer.parseInt(_linea[1]) ;
                    enEspera++;
                    _escribir.escribir(enEspera+":"+_linea[2]+":"+_linea[3]);
                    System.out.println("En Espera: "+enEspera+" Recibidos  "+_linea[2]+" Enviados: "+_linea[3]);
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
