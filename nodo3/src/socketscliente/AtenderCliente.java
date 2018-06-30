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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GOMEZ
 */
public class AtenderCliente implements Runnable {

    Socket _socket;
    ServerSocket _serverSocket;
    ObjectOutputStream _out;
    ObjectInputStream _in;
    Thread _threadCliente;
    Cliente _cliente;
    
    public AtenderCliente (Socket _socket, ServerSocket _serverSocket, Cliente _cliente){
        this._socket = _socket;
        this._serverSocket = _serverSocket;
        this._cliente = _cliente;
    }
    
    @Override
    synchronized public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AtenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
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
                        int paquetes = _transporte.getPaquete();
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Transporte" +_socket.getInetAddress()+ " con "+_transporte.getPaquete()+ " paquetes");
                            System.out.println("Bajando Paquete");
                            Thread.sleep(10000);
                            paquetes--;
                            System.out.println("Paquetes Restantes : "+paquetes);
                            System.out.println("Paquete Bajado");
                        }
                        _transporte.setPaquete(paquetes);
                        _cliente.enviarTransporte(_transporte);
                        _threadCliente = new Thread(_cliente);
                        _threadCliente.start(); 
                    }
                    _dataOutputStream.close();
                   
                    
                    
                    
                      
                } catch (ClassNotFoundException ex) { 
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(AtenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AtenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
