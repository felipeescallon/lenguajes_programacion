import java.util.*;
//////////////////////////////////////////////////////////////////
// Clase CCuentaAhorro: clase derivada de CCuenta
//
public class CCuentaAhorro extends CCuenta
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
    GregorianCalendar fechaActual = new GregorianCalendar();
    int d�a = fechaActual.get(Calendar.DAY_OF_MONTH);
    
    if (d�a == 1) reintegro(cuotaMantenimiento);
  }
}
//////////////////////////////////////////////////////////////////

