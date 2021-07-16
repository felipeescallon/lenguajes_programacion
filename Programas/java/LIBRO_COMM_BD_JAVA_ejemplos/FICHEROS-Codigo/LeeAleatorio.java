import java.io.*;

public class LeeAleatorio {
  
   public static void main(String[] args) { 
       int Primo;
       long LongitudFichero, Puntero;
     
       try {
          RandomAccessFile FicheroPrimos = new RandomAccessFile("Primos.txt", "r");
          try {
             LongitudFichero = FicheroPrimos.length();
             FicheroPrimos.seek(LongitudFichero/2);
             Puntero = FicheroPrimos.getFilePointer();
             while (Puntero<LongitudFichero) {
                Primo = FicheroPrimos.readInt();
                System.out.print(Primo+"  ");
                Puntero = FicheroPrimos.getFilePointer();
             }
             FicheroPrimos.close();
           } catch (IOException e){  
                System.out.println("Error leyendo los datos o cerrando el fichero");   
           }
       } catch (FileNotFoundException e) {
          System.out.println("Fichero no encontrado");
       }
   }

} //clase




