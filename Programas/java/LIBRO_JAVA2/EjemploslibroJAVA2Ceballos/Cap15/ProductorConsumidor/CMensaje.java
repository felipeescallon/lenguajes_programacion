//////////////////////////////////////////////////////////////////
// Sincronización de hilos: wait y notify.
//
public class CMensaje
{
  private String textoMensaje;
  private int númeroMensaje;
  private boolean disponible = false;

  public synchronized void almacenar(int nmsj)
  {
    while (disponible == true)
    {
      // El último mensaje aún no ha sido mostrado
      try
      {
        wait(); // el hilo se pone a dormir y cede el monitor
      }
      catch (InterruptedException e) { }
    }
    númeroMensaje = nmsj;
    // Suponer operaciones para buscar el mensaje en una tabla
    // de mensajes; resultado:
    textoMensaje = "mensaje";
    disponible = true;
    notifyAll();
  }

  public synchronized String obtener()
  {
    while (disponible == false)
    {
      // No hay mensaje
      try
      {
        wait(); // el hilo se pone a dormir y cede el monitor
      }
      catch (InterruptedException e) { }
    }
    disponible = false;
    notifyAll();
    // Componer el mensaje bajo un determinado formato
    String mensaje;
    mensaje = textoMensaje + " #" + númeroMensaje;
    return mensaje;
  }
}
//////////////////////////////////////////////////////////////////
