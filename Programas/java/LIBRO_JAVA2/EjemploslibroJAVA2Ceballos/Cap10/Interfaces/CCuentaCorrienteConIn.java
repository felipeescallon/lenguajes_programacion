//////////////////////////////////////////////////////////////////
// Clase CCuentaCorrienteConIn: clase derivada de CCuentaCorriente
//
public class CCuentaCorrienteConIn extends CCuentaCorriente
{
  // Métodos
  public CCuentaCorrienteConIn() {} // constructor sin parámetros
  
  public CCuentaCorrienteConIn(String nom, String cue, double sal,
                         double tipo, double imptrans, int transex)
  {
    // Invocar al constructor de la superclase
    super(nom, cue, sal, tipo, imptrans, transex);
  }
  
  public double intereses()
  {
    if (día() != 1 || estado() < 3000) return 0.0;
      
    // Acumular interés mensual sólo los días 1 de cada mes
    double interesesProducidos = 0.0;
    interesesProducidos = estado() * obtenerTipoDeInterés() / 1200.0;
    ingreso(interesesProducidos);
    // Este ingreso no debe incrementar las transacciones
    decrementarTransacciones(); 
    
    // Devolver el interés mensual por si fuera necesario
    return interesesProducidos;
  }
}
//////////////////////////////////////////////////////////////////
