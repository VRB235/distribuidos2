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
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author leona
 */
public class Cliente implements Runnable {
    
    public Transporte _transporte;
    public int enviados = 0;
    public int recibidos = 0;
    public int enEspera = 0;
    
    public void enviarTransporte(Transporte _transporte){
        this._transporte = _transporte;
    }

    @Override
    public void run() {
        LeeFichero _leer = new LeeFichero();
        String [] _linea =_leer.leer().split(":");
        recibidos = Integer.parseInt(_linea[2]) ;
        recibidos++;
        EscribeFichero _escribir = new EscribeFichero();
        _escribir.escribir(_linea[1]+":"+recibidos+":"+_linea[3]);
        ArrayList <String> _nodos;
        ArrayList <String> _puertos;
        _nodos = new ArrayList<>();
        _nodos.add(Variables.nodo1);
        _nodos.add(Variables.nodo2);
        _nodos.add(Variables.nodo3);
        _nodos.add(Variables.nodo4);
        _puertos = new ArrayList<>();
        _puertos.add(String.valueOf(Variables.puerto1));
        _puertos.add(String.valueOf(Variables.puerto2));
        _puertos.add(String.valueOf(Variables.puerto3));
        _puertos.add(String.valueOf(Variables.puerto4));
        int i = 0;
        Socket _socket;
        while(true){
            try {
                _socket = new Socket(_nodos.get(i), Integer.parseInt(_puertos.get(i))) ;
                DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream());
                DataOutputStream _dataOutputStream = new DataOutputStream(_socket.getOutputStream());

                _dataOutputStream.writeUTF("sync");
                enEspera++;
                String _respuesta = _dataInputStream.readUTF();
                System.out.println(_respuesta); 

                if(_respuesta.equals("ack")){
                    ObjectOutputStream _out = new ObjectOutputStream(_socket.getOutputStream());
                    ObjectInputStream _in = new ObjectInputStream(_socket.getInputStream());
                    _linea =_leer.leer().split(":");
                    enEspera = Integer.parseInt(_linea[2]) ;
                    enEspera++;
                    _escribir.escribir(enEspera+":"+_linea[2]+":"+_linea[3]);
                    Thread.sleep(20000);
                    System.out.println("Enviando Trasporte con: "+_transporte.getPaquetes().size()+ " paquetes");
                    _out.writeObject(_transporte);
                    _linea =_leer.leer().split(":");
                    enviados = Integer.parseInt(_linea[3]) ;
                    enviados++;
                    _escribir.escribir(_linea[1]+":"+_linea[2]+":"+enviados);
                    System.out.println("En Espera: "+enEspera+" Recibidos  "+recibidos+" Enviados: "+enviados);
                    _dataInputStream.close();
                    _dataOutputStream.close();
                    _socket.close();
                    break;
                    
                }



            } catch (IOException ex) {

                System.out.println("Error al crear los stream de entradas y salidad: "
                        + ex.getMessage());
                System.out.println("i="+i);
                if(i==3){
                    i=0;
                }
                else{
                    if(i==0){
                        i=1;
                    }else{
                        if(i==1){
                            i=2;
                        }else{
                            if(i==2){
                                i=3;
                            }
                        }
                    }
                }
                
                
                

            }catch(InterruptedException | NumberFormatException e){

                System.out.println(e.getMessage());

            }
        
        
    }
}
}
