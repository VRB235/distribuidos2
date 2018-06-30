/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author GOMEZ
 */
public class EscribirFichero {
    
    synchronized public void escribir(long _valor)
    {
      try
        {
            FileWriter _writer = new FileWriter("C:\\Users\\GOMEZ\\Downloads\\ProyectoDistribuidos2\\promedios.txt",true);
            PrintWriter _printWriter = new PrintWriter(_writer);

                _printWriter.println(_valor);

                _printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
