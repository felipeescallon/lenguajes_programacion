public class CPersona
{
  private String nombre;
  private CFecha fechaNacimiento;
  
  private class CFecha
  {
    private int día, mes, año;
    
    private CFecha(int dd, int mm, int aa)
    {
      día = dd; mes = mm; año = aa;
    }
  }
  
  public CPersona() {}
  public CPersona(String nom, int dd, int mm, int aa)
  {
    nombre = nom;
    fechaNacimiento = new CFecha(dd, mm, aa);
  }
  public String obtenerNombre() { return nombre; }
  public String obtenerFechaNa()
  {
    return fechaNacimiento.día + "/" +
           fechaNacimiento.mes + "/" +
           fechaNacimiento.año;
  }
}
