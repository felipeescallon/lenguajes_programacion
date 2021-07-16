import java.util.*;

public class PruebaTimer {

  static private Timer MiTimer;

  public static void main(String[] args) {
    PruebaTimer Instancia = new PruebaTimer();
    try {
     System.in.read();
    } catch (Exception e) {}
    MiTimer.cancel();
  }

  PruebaTimer() {
    MiTimer = new Timer(true);
    MiTimer.schedule(new Tarea(),0l,2000l);
  }

  private class Tarea extends TimerTask {
    public void run() {
      System.out.println("Hola");
    }
  }

}