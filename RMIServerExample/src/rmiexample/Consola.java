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
public class Consola implements Runnable {

    @Override
    public void run() {
        System.out.println("1. Tiempo Promedio que tardan los paquetes de un almacen en llegar a destino");
        System.out.println("0. Salir");
        Scanner _scan;
        while(1==1)
        {
            _scan = new Scanner(System.in);
            if(_scan.nextLine().equals("1")){
                System.out.println("Calculando Promedios...");
                LeerFichero _leeFichero = new LeerFichero();
                String [] _promedios = _leeFichero.leerPromedio().split(":");
                 double _promedio = 0;
                 for (int i = 1; i < _promedios.length; i++) {
                    _promedio += Double.parseDouble(_promedios[i]);
                 }
                 _promedio /= _promedios.length;
                 System.out.println("El promedio es de :"+_promedio);
            }
            if(_scan.nextLine().equals("0")){
                break;
            }
        }
        
    }
    
    
    
}
