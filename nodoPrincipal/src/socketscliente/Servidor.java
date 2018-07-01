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
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmiexample.RMIClientExample;

/**
 *
 * @author leona
 */
public class Servidor implements Runnable {
    
    Socket _socket;
    int enEspera = 0;
    int recibidos = 0;
   
    
    public Servidor (){
        _socket = null;
    }

    @Override
    public void run() {
       /* LeeFichero _leer = new LeeFichero();
        EscribeFichero _escribir = new EscribeFichero();
        if(_leer.leer()<=3){
            _escribir.escribir(_leer.leer()+1); 
        }*/
        ObjectOutputStream _out;
        ObjectInputStream _in;
        try {
            ServerSocket _serverSocket;
            DataOutputStream _dataOutputStream = null;
            _serverSocket = new ServerSocket(1231);
            PasarACliente _pasarACliente;
            Thread _theadPasarACliente;
            String [] _linea;
            LeeFichero _leer = new LeeFichero();
            EscribeFichero _escribir = new EscribeFichero();
            while(true){
                System.out.println("Esperando conexion...");
                _socket = _serverSocket.accept();
                System.out.println("Cliente aceptado: "+_socket.getInetAddress());
                
                
                try (DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream())) {
                    
                    _dataOutputStream = new DataOutputStream(_socket.getOutputStream());
                    String _peticion = _dataInputStream.readUTF();
                    System.out.println(_peticion + "Cliente: "+_socket.getInetAddress());
                    
                    if(_peticion.equals("sync"))
                    {
                        
                        _linea =_leer.leer().split(":");
                        System.out.println("En espera "+_linea[1]);
                        enEspera = Integer.parseInt(_linea[1]) ;
                        enEspera++;
                        _escribir.escribir(enEspera+":"+_linea[2]+":"+_linea[3]);
                        System.out.println("En Espera: "+enEspera+" Recibidos  "+_linea[2]+" Enviados: "+_linea[3]);
                        _dataOutputStream.writeUTF("ack");
                        _out = new ObjectOutputStream(_socket.getOutputStream());
                        _in = new ObjectInputStream(_socket.getInputStream());
                        Transporte _transporte = (Transporte) _in.readObject();
                        
                   /////RMI
                        RMIClientExample rmi = new RMIClientExample();
                        rmi.sync();
                        rmi.enviarFallidos(_transporte.getPaquetesFallidos());
                        rmi.enviarExitosos(_transporte.getPaquetesExitosos());
                   ////FIN  RMI
                        
                        System.out.println("EL TRANSPORTE HA LLEGADO CON "+_transporte.getPaquetes().size() + " paquetes");
                        _linea =_leer.leer().split(":");
                        System.out.println("En espera "+_linea[1]);
                        enEspera = Integer.parseInt(_linea[1]) ;
                        enEspera--;
                        _escribir.escribir(enEspera+":"+_linea[2]+":"+_linea[3]);
                        System.out.println("En Espera: "+enEspera+" Recibidos  "+_linea[2]+" Enviados: "+_linea[3]);
                        recibidos = Integer.parseInt(_linea[2]) ;
                        recibidos++;
                        _escribir.escribir(_linea[1]+":"+recibidos+":"+_linea[3]);
                        System.out.println("En Espera: "+_linea[1]+" Recibidos  "+recibidos+" Enviados: "+_linea[3]);
                        if(_transporte.getPaquetes().size()!=0){
                            _pasarACliente = new PasarACliente();
                            _pasarACliente.enviarTransporte(_transporte);
                                _theadPasarACliente = new Thread(_pasarACliente);
                                _theadPasarACliente.start();
                            
                        }else{
                            System.out.println("SIMULACIÓN TERMINADA");
                        }
                        
                        
                        
                    }
                    
                    if(_peticion.equals("close")){
                        break;
                    }
                    
                    
                    
                      
                } catch (ClassNotFoundException ex) { 
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
        _dataOutputStream.close();
        _socket.close();
        
        /*Cliente _cliente = new Cliente();
                    _cliente.start();*/

        }
            
            
        } catch (IOException e) {
        
            System.out.println("Error "+e.getMessage());
        }
    }
    
}
