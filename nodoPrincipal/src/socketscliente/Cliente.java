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
import java.util.TimerTask;

/**
 *
 * @author leona
 */
public class Cliente implements Runnable {
    

    @Override
    public void run() {
        Socket _socket;
            try {
                _socket = new Socket(Variables.nodo2, 1232);
                DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream());
                DataOutputStream _dataOutputStream = new DataOutputStream(_socket.getOutputStream());

                _dataOutputStream.writeUTF("sync");

                String _respuesta = _dataInputStream.readUTF();
                System.out.println(_respuesta); 

                if(_respuesta.equals("ack")){
                    ObjectOutputStream _out = new ObjectOutputStream(_socket.getOutputStream());
                    ObjectInputStream _in = new ObjectInputStream(_socket.getInputStream());

                    Transporte _transporte = new Transporte();

                    _out.writeObject(_transporte);
                    _dataInputStream.close();
                    _dataOutputStream.close();
                    
                }
                
                if(_respuesta.equals("close")){
                    _socket.close();
                }



            } catch (IOException ex) {

                System.out.println("Error al crear los stream de entradas y salidad: "
                        + ex.getMessage());

            }catch(Exception e){

                System.out.println(e.getMessage());

            }
        
        
    }
}
