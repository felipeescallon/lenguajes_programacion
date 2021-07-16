//////////////////////////////////////////////////////////////////
// Clase que define un hilo que cuenta ascendentemente mientras
// que el atributo continuar sea true.
//
public class ContadorAdelante extends Thread
{
  private boolean continuar = true;
  
  public ContadorAdelante()
  {
    start();
  }
  
  public ContadorAdelante(String nombreHilo)
  {
    setName(nombreHilo);
    start();
  }
  
  public void run()
  {
    int i = 1;
    while (continuar)
    {
      System.out.print(getName() + " " + i++ + "\r");
    }
    System.out.println();
  }

  public void terminar()
  {
    continuar = false;
  }
}
//////////////////////////////////////////////////////////////////
