public class LetrasHilos {

public LetrasHilos (String Frase) {
   Thread[] Hilo = new Thread[Frase.length()];
   for (int i=0; i!=Frase.length();i++) {
     Hilo[i] = new Thread (new CHilo(Frase.substring(i,i+1)));
     Hilo[i].start();  
   }
   return;
} 

}  