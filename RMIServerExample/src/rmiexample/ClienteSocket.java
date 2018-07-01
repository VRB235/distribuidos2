/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author GOMEZ
 */
public class ClienteSocket implements Runnable{
 
    @Override
    public void run(){
        
        Socket _socket;
        while(1==1){
            try {
                _socket = new Socket("192.168.1.100", 4321);
                DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream());
                DataOutputStream _dataOutputStream = new DataOutputStream(_socket.getOutputStream());
                Thread.sleep(1000);
                _dataOutputStream.writeUTF("sync");
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
    
}
