import java.util.Random;

public class CHilo implements Runnable {
private String Caracter; 

public CHilo (String Caracter) {
  this.Caracter = Caracter;
} 

public void run() {
  try {
    Thread.sleep( (long) (Math.abs(new Random().nextInt())%1000));
    System.out.print(Caracter);
  } catch (InterruptedException e) {}
}

}  