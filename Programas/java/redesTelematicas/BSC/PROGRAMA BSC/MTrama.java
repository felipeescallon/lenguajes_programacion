
 import java.io.*;
 import java.net.*;


 //  Clase para dividir el texto en varios bloques de longitud 20
 public class MTrama
 {

     public MTrama()
     {
     }

     public String div(String Texto, int ini, int fin)
     {
         int lon = fin - ini;                // Para la longitud de la trama
         char m[]=new char[lon];             // Divide el texto en grupos de 20 para formar cada bloque
         Texto.getChars(ini, fin, m, 0);
         String Texto1 = new String(m);
         return (Texto1);
     }

     public void enviar(Socket socket)
     {

     }
 }
