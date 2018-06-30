/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    
    //private static final int PUERTO = 5555; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new TestRemote() {
            @Override
            public String enviar(String saludo) throws RemoteException {
                System.out.println("Mnesaje Recibido : "+saludo);
                return "Chao";
            }
            
        }
            
         /*
        Sobrescribir opcionalmente los métodos que escribimos en la interfaz
         */ , 0);
        
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
       	System.out.println("Servidor escuchando en el puerto " + Registry.REGISTRY_PORT);
        registry.bind("Test", remote); // Registrar calculadora
    }
}