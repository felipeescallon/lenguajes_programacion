/////////////////////////////////////////////////////////////////
// Aplicación para trabajar con CCuenta...
//
public class Test
{
  public static void main(String[] args)
  {
    CCuentaAhorro cliente01 = new CCuentaAhorro(
                   "Un nombre", "Una cuenta", 10000, 3.5, 30);

    System.out.println(cliente01.obtenerNombre());
    System.out.println(cliente01.obtenerCuenta());
    System.out.println(cliente01.estado());
    System.out.println(cliente01.obtenerTipoDeInterés());
    System.out.println(cliente01.intereses());
    
    CCuentaCorrienteConIn cliente02 = new CCuentaCorrienteConIn();
    cliente02.asignarNombre("cliente 02");
    cliente02.asignarCuenta("1234567890");
    CCuentaCorrienteConIn.asignarTipoDeInterés(3.0);
    cliente02.asignarTransExentas(0);
    cliente02.asignarImportePorTrans(1.0);

    cliente02.ingreso(20000);
    cliente02.reintegro(10000);
    cliente02.intereses();
    cliente02.comisiones();
    System.out.println(cliente02.obtenerNombre());
    System.out.println(cliente02.obtenerCuenta());
    System.out.println(cliente02.estado());
  }
}
