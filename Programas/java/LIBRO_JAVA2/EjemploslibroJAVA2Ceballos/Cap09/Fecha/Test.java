/////////////////////////////////////////////////////////////////
// Aplicación que utiliza la clase CFecha
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
    int día, mes, año;

    do
    {
      System.out.print("día, ##   :  ");  día = Leer.datoInt();
      System.out.print("mes, ##   :  ");  mes = Leer.datoInt();
      System.out.print("año, #### :  ");  año = Leer.datoInt();
      fecha.asignarFecha(día, mes, año);
    }
    while (!fecha.fechaCorrecta());

    visualizarFecha( fecha );
  }
}
