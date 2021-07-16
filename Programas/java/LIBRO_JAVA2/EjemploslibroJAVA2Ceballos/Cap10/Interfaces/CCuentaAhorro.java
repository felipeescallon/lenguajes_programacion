import java.util.*;
//////////////////////////////////////////////////////////////////
// Clase CCuentaAhorro: clase derivada de CCuenta
//
public class CCuentaAhorro extends CCuenta implements IFecha
{
  // Atributos
  private double cuotaMantenimiento;
  
  // M�todos
  public CCuentaAhorro() {} // constructor sin par�metros
  
  public CCuentaAhorro(String nom, String cue, double sal,
                       double tipo, double mant)
  {
    super(nom, cue, sal, tipo); // invoca al constructor CCuenta
    asignarCuotaManten(mant);   // inicia cuotaMantenimiento
  }

  public void asignarCuotaManten(double cantidad)
  {
    if (cantidad < 0)
    {
      System.out.println("Error: cantidad negativa");
      return;
    }
    cuotaMantenimiento = cantidad;
  }
  
  public double obtenerCuotaManten()
  {
    return cuotaMantenimiento;
  }
  
  public void comisiones()
  {
    // Se aplican mensualmente por el mantenimiento de la cuenta
    if (d�a() == 1) reintegro(cuotaMantenimiento);
  }
  
  public double intereses()
  {
    if (d�a() != 1) return 0.0;
    // Acumular los intereses por mes s�lo los d�as 1 de cada mes
    double interesesProducidos = 0.0;
    interesesProducidos = estado() * obtenerTipoDeInter�s() / 1200.0;
    ingreso(interesesProducidos);
    
    // Devolver el inter�s mensual por si fuera necesario
    return interesesProducidos;
  }
  
  // Implementaci�n de los m�todos de la interfaz IFecha
  public int d�a()
  {
    GregorianCalendar fechaActual = new GregorianCalendar();
    return fechaActual.get(DIA_DEL_MES);
  }
  public int mes() { return 0; } // no se necesita
  public int a�o() { return 0; } // no se necesita
}
//////////////////////////////////////////////////////////////////

