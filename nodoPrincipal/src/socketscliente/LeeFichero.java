package socketscliente;

import java.io.*;

public class LeeFichero {
    
   public String leer() {
      File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
 
        try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			/*archivo = new File ("archivo.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);*/
                        br = new BufferedReader(new FileReader(new File("C:\\Users\\GOMEZ\\Downloads\\ProyectoDistribuidos2\\stats1.txt")));

			// Lectura del fichero
			System.out.println("Leyendo el contendio del archivo.txt");
			String linea;
                        String salida = "";
			while((linea=br.readLine())!=null)
				salida = salida +":"+linea;
                        return salida;
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{
              if( null != fr ){
                 fr.close();
              }
           }catch (Exception e2){
              e2.printStackTrace();
           }
        }
        return null;
   }
}