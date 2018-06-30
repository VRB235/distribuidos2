package socketscliente;

import java.io.*;

public class EscribeFichero
{
    synchronized public void escribir(String _texto)
    {
        String[] _lineas = _texto.split(":");
        
        try
        {
            PrintWriter writer = new PrintWriter("C:\\Users\\Andy\\Desktop\\Proyecto2 Distribuidos\\distribuidos2\\stats2.txt", "UTF-8");
            
            for (int i = 0; i < 3; i++) {
                writer.println(_lineas[i]);
            }
                writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}