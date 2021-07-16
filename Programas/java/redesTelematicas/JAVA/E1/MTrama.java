
 import java.io.*;
 import java.net.*;
 
 public class MTrama
 {
 
     public MTrama()
     {
     }
     
     public String div(String Texto, int ini, int fin)
     {
         int lon = fin - ini;
         char m[]=new char[lon];
         Texto.getChars(ini, fin, m, 0);
         String Texto1 = new String(m);         
         return (Texto1);
     }
     
     public void enviar(Socket socket)
     {
     
     }
 } 
