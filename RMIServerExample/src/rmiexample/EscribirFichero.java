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
    
    synchronized public void escribirPromedios(long _valor)
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
    
    synchronized public void escribirFallidos(int _valor)
    {
      try
        {
            FileWriter _writer = new FileWriter("C:\\Users\\GOMEZ\\Downloads\\ProyectoDistribuidos2\\fallidos.txt",true);
            PrintWriter _printWriter = new PrintWriter(_writer);

                _printWriter.println(_valor);

                _printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    synchronized public void escribirExitosos(long _valor)
    {
      try
        {
            FileWriter _writer = new FileWriter("C:\\Users\\GOMEZ\\Downloads\\ProyectoDistribuidos2\\exitosos.txt",true);
            PrintWriter _printWriter = new PrintWriter(_writer);

                _printWriter.println(_valor);

                _printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
