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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmiexample.RMIClientExample;

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
    int enEspera = 0;
    int recibidos = 0;
    
    public AtenderCliente (Socket _socket, ServerSocket _serverSocket, Cliente _cliente){
        this._socket = _socket;
        this._serverSocket = _serverSocket;
        this._cliente = _cliente;
    }
    
    @Override
    synchronized public void run() {
        /*LeeFichero _leer = new LeeFichero();
        EscribeFichero _escribir = new EscribeFichero();
        if(_leer.leer()<=3){
            _escribir.escribir(_leer.leer()+1); 
        }*/
        String [] _linea;
        LeeFichero _leer = new LeeFichero();
        EscribeFichero _escribir = new EscribeFichero();
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
                        
                   /////RMI
                        RMIClientExample rmi = new RMIClientExample();
                        rmi.sync();
                        
                        
                   ////FIN  RMI
                        
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
                        ArrayList<Paquete> _paquetes = _transporte.getPaquetes();
                        if(_paquetes.size()!=0)
                        {
                            if(_paquetes.size()>2){
                                for (int i = 0; i < 2; i++) {
                                    System.out.println("Transporte" +_socket.getInetAddress()+ " con "+_transporte.getPaquetes().size()+ " paquetes");
                                    System.out.println("Bajando Paquete");
                                    Thread.sleep(10000);
                                    rmi.enviarpromedio(new Date().getTime() - _transporte.getPaquetes().get(i).getTime());
                                    _paquetes.remove(i);
                                    System.out.println("Paquetes Restantes : "+_transporte.getPaquetes().size());
                                    System.out.println("Paquete Bajado");
                                }
                            }else{
                                for (int i = 0; i < _paquetes.size(); i++) {
                                    _paquetes.removeAll(_paquetes);
                                }
                            }

                            if(!_paquetes.isEmpty()){
                                if(_paquetes.size()<5){
                                    for (int i = 0; i < 1; i++) {
                                        System.out.println("Transporte" +_socket.getInetAddress()+ " con "+_transporte.getPaquetes().size()+ " paquetes");
                                        System.out.println("Subiendo Paquete");
                                        Thread.sleep(10000);
                                        _paquetes.add(new Paquete());
                                        System.out.println("Subiendo Restantes : "+_transporte.getPaquetes().size());
                                        System.out.println("Paquete Subido");
                                    }
                                }
                            }
                        }
                        
                        
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
        } catch (NotBoundException ex) {
            Logger.getLogger(AtenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
