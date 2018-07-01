/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
	Declarar firma de métodos que serán sobrescritos
*/
public interface TestRemote extends Remote {
    String enviar(String saludo) throws RemoteException;
    String promedio(long tiempo) throws RemoteException;
    String fallidos(int valor) throws RemoteException;
}