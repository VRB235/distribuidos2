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
    public static void main(String[] args) {
        // TODO code application logic here

        Servidor _servidor = new Servidor();
        _servidor.start();
        /*Cliente _cliente = new Cliente();
        _cliente.start();*/
      
        
        
    }
    
}
