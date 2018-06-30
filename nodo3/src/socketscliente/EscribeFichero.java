package socketscliente;

import java.io.*;

public class EscribeFichero
{
    public void escribir(String _texto)
    {
        String[] _lineas = _texto.split(":");
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:\\Users\\GOMEZ\\Downloads\\ProyectoDistribuidos2\\stats3.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < 3; i++) {
                pw.println(_lineas[i]);
            }
                

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}