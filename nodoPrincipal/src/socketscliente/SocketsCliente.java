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

/**
 *
 * @author leona
 */
public class SocketsCliente {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        Servidor _servidor = new Servidor();
        Cliente _cliente = new Cliente();
        
        Thread _threadServidor = new Thread(_servidor);
        Thread _threadCliente = new Thread(_cliente);
        
        
        _threadServidor.start();
        
        for (int i = 0; i < 3; i++) {
            _threadCliente.sleep(5000);
            _threadCliente = new Thread(_cliente);
            _threadCliente.start();
        }
        
        
      
        
        
    }
    
}
