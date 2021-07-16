public class ThreadBasicoMain {

public static void main(String[] args) {
  Thread PrimerHilo =  new Thread (new ThreadBasico (args[0]));
  Thread SegundoHilo = new Thread (new ThreadBasico (args[1]));
 
  PrimerHilo.start();  
  SegundoHilo.start(); 

} 

} 