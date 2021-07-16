import java.io.*;
/////////////////////////////////////////////////////////////////
// Aplicaci�n para trabajar con la clase CBanco y la jerarqu�a
// de clases derivadas de CCuenta
//
public class Test
{
  // Para la entrada de datos se utiliza Leer.class
  public static CCuenta leerDatos(int op)
  {
    CCuenta obj = null;
    String nombre, cuenta;
    double saldo, tipoi, mant;
    System.out.print("Nombre.................: ");
    nombre = Leer.dato();
    System.out.print("Cuenta.................: ");
    cuenta = Leer.dato();
    System.out.print("Saldo..................: ");
    saldo = Leer.datoDouble();
    System.out.print("Tipo de inter�s........: ");
    tipoi = Leer.datoDouble();
    if (op == 1)
    {
      System.out.print("Mantenimiento..........: ");
      mant = Leer.datoDouble();
      obj = new CCuentaAhorro(nombre, cuenta, saldo, tipoi, mant);
    }
    else
    {
      int transex;
      double imptrans;
      System.out.print("Importe por transacci�n: ");
      imptrans = Leer.datoDouble();
      System.out.print("Transacciones exentas..: ");
      transex = Leer.datoInt();
      if (op == 2)
        obj = new CCuentaCorriente(nombre, cuenta, saldo, tipoi,
                                   imptrans, transex);
      else
        obj = new CCuentaCorrienteConIn(nombre, cuenta, saldo,
                                        tipoi, imptrans, transex);
    }
    return obj;
  }
  
  public static int men�()
  {
    System.out.print("\n\n");
    System.out.println("1. Saldo");
    System.out.println("2. Buscar siguiente");
    System.out.println("3. Ingreso");
    System.out.println("4. Reintegro");
    System.out.println("5. A�adir");
    System.out.println("6. Eliminar");
    System.out.println("7. Mantenimiento");
    System.out.println("8. Salir");    
    System.out.println();
    System.out.print("   Opci�n: ");
    int op;
    do
      op = Leer.datoInt();
    while (op < 1 || op > 8);
    return op;
  }
  
  public static void main(String[] args)
  {
    // Definir una referencia al flujo est�ndar de salida: flujoS
    PrintStream flujoS = System.out;
    
    // Crear un objeto banco vac�o (con cero elementos)
    CBanco banco = new CBanco();

    int opci�n = 0, pos = -1;
    String cadenabuscar = null;
    String nombre, cuenta;
    double cantidad;
    boolean eliminado = false;

    do
    {
      opci�n = men�();
      switch (opci�n)
      {
        case 1: // saldo
          flujoS.print("Nombre o cuenta, total o parcial ");
          cadenabuscar = Leer.dato();
          pos = banco.buscar(cadenabuscar, 0);
          if (pos == -1)
            if (banco.longitud() != 0)
              flujoS.println("b�squeda fallida");
            else
              flujoS.println("no hay clientes");
          else
          {
            flujoS.println(banco.clienteEn(pos).obtenerNombre());
            flujoS.println(banco.clienteEn(pos).obtenerCuenta());
            flujoS.println(banco.clienteEn(pos).estado());
          }
          break;
        case 2: // buscar siguiente
          pos = banco.buscar(cadenabuscar, pos + 1);
          if (pos == -1)
            if (banco.longitud() != 0)
              flujoS.println("b�squeda fallida");
            else
              flujoS.println("no hay clientes");
          else
          {
            flujoS.println(banco.clienteEn(pos).obtenerNombre());
            flujoS.println(banco.clienteEn(pos).obtenerCuenta());
            flujoS.println(banco.clienteEn(pos).estado());
          }
          break;
       case 3: // ingreso
       case 4: // reintegro
          flujoS.print("Cuenta: "); cuenta = Leer.dato();
          pos = banco.buscar(cuenta, 0);
          if (pos == -1)
            if (banco.longitud() != 0)
              flujoS.println("b�squeda fallida");
            else
              flujoS.println("no hay clientes");
          else
          {
            flujoS.print("Cantidad: "); cantidad = Leer.datoDouble();
            if (opci�n == 3)
              banco.clienteEn(pos).ingreso(cantidad);
            else
              banco.clienteEn(pos).reintegro(cantidad);
          }
          break;
       case 5: // a�adir
          flujoS.print("Tipo de cuenta: 1-(CA), 2-(CC), 3-CCI  ");
          do
            opci�n = Leer.datoInt();
          while (opci�n < 1 || opci�n > 3);
          banco.a�adir(leerDatos(opci�n));
          break;
        case 6: // eliminar
          flujoS.print("Cuenta: "); cuenta = Leer.dato();
          eliminado = banco.eliminar(cuenta);
          if (eliminado)
            flujoS.println("registro eliminado");
          else
            if (banco.longitud() != 0)
              flujoS.println("cuenta no encontrada");
            else
              flujoS.println("no hay clientes");
          break;
        case 7: // mantenimiento
          for (pos = 0; pos < banco.longitud(); pos++)
          {
            banco.clienteEn(pos).comisiones();
            banco.clienteEn(pos).intereses();
          }
          break;
        case 8: // salir
          banco = null;
      }
    }
    while(opci�n != 8);
  }
}
/////////////////////////////////////////////////////////////////
