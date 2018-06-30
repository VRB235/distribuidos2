package socketscliente;

import java.io.*;

public class EscribeFichero
{
    synchronized public void escribir(String _texto)
    {
        String[] _lineas = _texto.split(":");
        
        try
        {
            PrintWriter writer = new PrintWriter("C:\\Users\\GOMEZ\\Downloads\\ProyectoDistribuidos2\\stats4.txt", "UTF-8");
            
            for (int i = 0; i < 3; i++) {
                writer.println(_lineas[i]);
            }
                writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}