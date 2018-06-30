/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
    
    //private static final int PUERTO = 5555; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente

    @Override
    public void run() {
        EscribirFichero _escribirFichero = new EscribirFichero();
        
        
        Remote remote;
        Registry registry;
        try {
            remote = UnicastRemoteObject.exportObject(new TestRemote() {
                @Override
                public String enviar(String saludo) throws RemoteException {
                    System.out.println("Menesaje Recibido : "+saludo);
                    return "Servidor Principal ACK";
                }
                
                @Override
                public String primedio(long tiempo) throws RemoteException {
                    System.out.println("Promedio Recibido: "+tiempo);
                    _escribirFichero.escribir(tiempo);
                    return "Servidor ACK/promedio";
                }
                
            }
                    
                    /*
                    Sobrescribir opcionalmente los métodos que escribimos en la interfaz
                    */ , 0);
            registry = LocateRegistry.createRegistry(5555);
            registry.bind("Test", remote);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       	System.out.println("Servidor escuchando en el puerto " + 5555);
    }
}