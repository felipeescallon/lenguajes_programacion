/////////////////////////////////////////////////////////////////
// Aplicación para trabajar con CCuenta...
//
public class Test
{
  public static void main(String[] args)
  {
    CCuentaAhorro cliente01 = new CCuentaAhorro();
    CCuentaAhorro cliente02 = new CCuentaAhorro("Un nombre", "Una cuenta", 1000000, 3.5, 300);
    cliente01.asignarNombre("Un nombre");
    cliente01.asignarCuenta("Una cuenta");
    cliente01.asignarTipoDeInterés(2.5);
    cliente01.asignarCuotaManten(300);
    
    cliente01.ingreso(1000000);
    cliente01.reintegro(500000);
    cliente01.comisiones();
    
    System.out.println(cliente01.obtenerNombre());
    System.out.println(cliente01.obtenerCuenta());
    System.out.println(cliente01.estado());
    System.out.println(cliente01.obtenerTipoDeInterés());
  }
}
