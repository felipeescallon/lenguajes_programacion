import java.util.Random;  

public class ThreadBasico implements Runnable {

private String Frase;
private Random Aleatorio; 

public ThreadBasico (String Frase) {
   this.Frase = Frase;
   Aleatorio = new Random(); 
} // Constructor

public void run() {
  try {
    do {
       System.out.println (Frase);
       Thread.sleep( (long) (Math.abs(Aleatorio.nextInt()) % 500)); 
    } while (true);
  } catch (InterruptedException e) {} 
} // run

}   // class