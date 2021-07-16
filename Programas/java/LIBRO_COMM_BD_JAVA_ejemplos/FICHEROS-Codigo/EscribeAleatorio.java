import java.io.*;

public class EscribeAleatorio {

   private int NumPrimos;
   private int[] Primos;

   EscribeAleatorio(int NumPrimos) {      
       this.NumPrimos = NumPrimos;
       Primos = new int[NumPrimos];
       GeneraPrimos(NumPrimos);
       try {
          RandomAccessFile FicheroPrimos = new RandomAccessFile("Primos.txt", "rw");
          try {
             for (int i=0;i<NumPrimos;i++)
                FicheroPrimos.writeInt(Primos[i]);
             FicheroPrimos.close();
           } catch (IOException e){  
                System.out.println("Error escribiendo los datos o cerrando el fichero");   
           }
       } catch (FileNotFoundException e) {
          System.out.println("Fichero no encontrado");
       }
   }


private void GeneraPrimos(int NumPrimos) {
     Primos[0] = 2;
     int PrimosHallados = 1;
     int PosiblePrimo = 3; 
     int Indice=0; 
     boolean Primo = true;
     
    do {
     while (Primo && (Indice<PrimosHallados)) {
        if (PosiblePrimo % Primos[Indice]  == 0)
          Primo = false;
        else
          Indice++;
     }

     if (Primo) {
       Primos[PrimosHallados] = PosiblePrimo;
       PrimosHallados++;
     }

     Primo = true;
     PosiblePrimo++;
     Indice = 0;

  } while (PrimosHallados<NumPrimos );    // while exterior  


  } // GeneraPrimos


} //clase




