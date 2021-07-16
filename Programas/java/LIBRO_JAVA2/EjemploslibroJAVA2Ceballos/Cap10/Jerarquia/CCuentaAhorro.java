import java.util.*;
//////////////////////////////////////////////////////////////////
// Clase CCuentaAhorro: clase derivada de CCuenta
//
public class CCuentaAhorro extends CCuenta
{
  // Atributos
  private double cuotaMantenimiento;
  
  // Métodos
  public CCuentaAhorro() {} // constructor sin parámetros
  
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
    int día = fechaActual.get(Calendar.DAY_OF_MONTH);
    
    if (día == 1) reintegro(cuotaMantenimiento);
  }
  
  public double intereses()
  {
    GregorianCalendar fechaActual = new GregorianCalendar();
    int día = fechaActual.get(Calendar.DAY_OF_MONTH);
  
    if (día != 1) return 0.0;
    // Acumular los intereses por mes sólo los días 1 de cada mes
    double interesesProducidos = 0.0;
    interesesProducidos = estado() * obtenerTipoDeInterés() / 1200.0;
    ingreso(interesesProducidos);
    
    // Devolver el interés mensual por si fuera necesario
    return interesesProducidos;
  }
}
//////////////////////////////////////////////////////////////////

