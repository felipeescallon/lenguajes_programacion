//////////////////////////////////////////////////////////////////
// Sincronización de hilos.
//
public class Test
{
  public static void main(String[] args)
  {
    CMensaje mensaje = new CMensaje();
    Productor productor1 = new Productor(mensaje);
    Consumidor consumidor1 = new Consumidor(mensaje);
    //Consumidor consumidor2 = new Consumidor(mensaje);

    productor1.start();
    consumidor1.start();
    //consumidor2.start();
  }
}
//////////////////////////////////////////////////////////////////
