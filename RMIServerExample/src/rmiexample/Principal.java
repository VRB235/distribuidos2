/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;

import java.util.Scanner;

/**
 *
 * @author GOMEZ
 */
public class Principal {
    
    public static void main(String [] arg){
        
        Server _server = new Server();
        Consola _consola = new Consola();
        //ClienteSocket _clienteSocket = new ClienteSocket();
        Thread _threadConsola = new Thread(_consola);
        Thread _threadServer = new Thread(_server);
       // Thread _threadClienteSocket = new Thread(_clienteSocket);
        _threadServer.start();
        _threadConsola.start();
        //_threadClienteSocket.start();
        
        
        
        
    }
    
}
