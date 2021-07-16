/////////////////////////////////////////////////////////////////
// Aplicaci�n que utiliza la clase CFecha
//
public class Test
{
  // Visualizar una fecha
  public static void visualizarFecha(CFecha fecha)
  {
    int[] f = new int[3];
    
    fecha.obtenerFecha(f);
    System.out.println(f[0] + "/" + f[1] + "/" + f[2]);
  }
  
  // Establecer una fecha, verificarla y visualizarla
  public static void main(String[] args)
  {
    CFecha fecha = new CFecha(); // objeto de tipo CFecha
    int d�a, mes, a�o;

    do
    {
      System.out.print("d�a, ##   :  ");  d�a = Leer.datoInt();
      System.out.print("mes, ##   :  ");  mes = Leer.datoInt();
      System.out.print("a�o, #### :  ");  a�o = Leer.datoInt();
      fecha.asignarFecha(d�a, mes, a�o);
    }
    while (!fecha.fechaCorrecta());

    visualizarFecha( fecha );
  }
}
