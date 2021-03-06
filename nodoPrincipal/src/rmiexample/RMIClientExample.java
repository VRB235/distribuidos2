/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author karol
 */
public class RMIClientExample {
	private static final String IP = "192.168.1.100"; // Puedes cambiar a localhost
	//private static final int PUERTO = 5555; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
	
    public  void sync () throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP,5555);
        TestRemote testRemote = (TestRemote) registry.lookup("Test"); //Buscar en el registro...
        System.out.println(testRemote.enviar("sync"));
    }
    
    public  void enviarpromedio (long i) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP,5555);
        TestRemote testRemote = (TestRemote) registry.lookup("Test"); //Buscar en el registro...
        System.out.println(testRemote.promedio(i));
    }
    public  void enviarFallidos (int i) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP,5555);
        TestRemote testRemote = (TestRemote) registry.lookup("Test"); //Buscar en el registro...
        System.out.println(testRemote.fallidos(i));
    }
    public  void enviarExitosos (int i) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP,5555);
        TestRemote testRemote = (TestRemote) registry.lookup("Test"); //Buscar en el registro...
        System.out.println(testRemote.exitosos(i));
    }
}
