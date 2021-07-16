import java.util.*;
//////////////////////////////////////////////////////////////////
// Clase CCuentaCorriente: clase derivada de CCuenta
//
public class CCuentaCorriente extends CCuenta implements IFecha
{
  // Atributos
  private int transacciones;
  private double importePorTrans;
  private int transExentas;
  
  // Métodos
  public CCuentaCorriente() {} // constructor sin parámetros
  
  public CCuentaCorriente(String nom, String cue, double sal,
                          double tipo, double imptrans, int transex)
  {
    super(nom, cue, sal, tipo);       // invoca al constructor CCuenta
    transacciones = 0;                // inicia transacciones
    asignarImportePorTrans(imptrans); // inicia importePorTrans
    asignarTransExentas(transex);     // inicia transExentas
  }

  public void decrementarTransacciones()
  {
    transacciones--;
  }
  
  public void asignarImportePorTrans(double imptrans)
  {
    if (imptrans < 0)
    {
      System.out.println("Error: cantidad negativa");
      return;
    }
    importePorTrans = imptrans;
  }
  
  public double obtenerImportePorTrans()
  {
    return importePorTrans;
  }
  
  public void asignarTransExentas(int transex)
  {
    if (transex < 0)
    {
      System.out.println("Error: cantidad negativa");
      return;
    }
    transExentas = transex;
  }
  
  public int obtenerTransExentas()
  {
    return transExentas;
  }
  
  public void ingreso(double cantidad)
  {
    super.ingreso(cantidad);
    transacciones++;
  }
  
  public void reintegro(double cantidad)
  {
    super.reintegro(cantidad);
    transacciones++;
  }

  public void comisiones()
  {
    // Se aplican mensualmente por el mantenimiento de la cuenta
    if (día() == 1)
    {
      int n = transacciones - transExentas;
      if (n > 0) reintegro(n * importePorTrans);
      transacciones = 0;
    }
  }
  
  public double intereses()
  {
    if (día() != 1) return 0.0;
      
    // Acumular los intereses por mes sólo los días 1 de cada mes
    double interesesProducidos = 0.0;
    // Hasta 3000 euros al 0.5%. El resto al interés establecido.
    if (estado() <= 3000)
      interesesProducidos = estado() * 0.5 / 1200.0;
    else
    {
      interesesProducidos = 3000 * 0.5 / 1200.0 +
           (estado() - 3000) * obtenerTipoDeInterés() / 1200.0;
    }
    ingreso(interesesProducidos);
    // Este ingreso no debe incrementar las transacciones
    decrementarTransacciones(); 
    
    // Devolver el interés mensual por si fuera necesario
    return interesesProducidos;
  }
  
  // Implementación de los métodos de la interfaz IFecha
  public int día()
  {
    GregorianCalendar fechaActual = new GregorianCalendar();
    return fechaActual.get(DIA_DEL_MES);
  }
  public int mes() { return 0; } // no se necesita
  public int año() { return 0; } // no se necesita
}
//////////////////////////////////////////////////////////////////
