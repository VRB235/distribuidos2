/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketscliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.TimerTask;

/**
 *
 * @author leona
 */
public class Cliente implements Runnable {
    
    private Transporte _transporte;
    
    public void enviarTransporte(Transporte _transporte){
        this._transporte = _transporte;
        this._transporte.setPaquete(_transporte.getPaquete()+1);
    }
    
    @Override
    public void run(){
        Socket _socket;
        
        try {
            _socket = new Socket(Variables.nodo4, Variables.puerto);
            DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream());
            DataOutputStream _dataOutputStream = new DataOutputStream(_socket.getOutputStream());
            //sleep(5000);
            _dataOutputStream.writeUTF("Hola Anderson!!!");
            System.out.println(_dataInputStream.readUTF()); 

            
             _dataInputStream.close();
             _dataOutputStream.close();
             _socket.close();
            
  
        } catch (IOException ex) {
            
            System.out.println("Error al crear los stream de entradas y salidad: "
                    + ex.getMessage());
            
        }catch(Exception e){
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
    
    
}
