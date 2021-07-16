//////////////////////////////////////////////////////////////////
// Clase CCuenta: clase abstracta que agrupa los datos comunes a
// cualquier tipo de cuenta bancaria.
//
public abstract class CCuenta
{
  // Atributos
  private String nombre;
  private String cuenta;
  private double saldo;
  private double tipoDeInterés;
  
  // Métodos
  public CCuenta() {};
  public CCuenta(String nom, String cue, double sal, double tipo)
  {
    asignarNombre(nom);
    asignarCuenta(cue);
    ingreso(sal);
    asignarTipoDeInterés(tipo);
  }
  
  public void asignarNombre(String nom)
  {
    if (nom.length() == 0)
    {
      System.out.println("Error: cadena vacía");
      return;
    }
    nombre = nom;
  }
  
  public String obtenerNombre()
  {
    return nombre;
  }
  
  public void asignarCuenta(String cue)
  {
    if (cue.length() == 0)
    {
      System.out.println("Error: cuenta no válida");
      return;
    }
    cuenta = cue;
  }
  
  public String obtenerCuenta()
  {
    return cuenta;
  }
  
  public double estado()
  {
    return saldo;
  }
  
  public abstract void comisiones();
  
  public void ingreso(double cantidad)
  {
    if (cantidad < 0)
    {
      System.out.println("Error: cantidad negativa");
      return;
    }
    saldo += cantidad;
  }
  
  public void reintegro(double cantidad)
  {
    if (saldo - cantidad < 0)
    {
      System.out.println("Error: no dispone de saldo");
      return;
    }
    saldo -= cantidad;
  }

  public void asignarTipoDeInterés(double tipo)
  {
    if (tipo < 0)
    {
      System.out.println("Error: tipo no válido");
      return;
    }
    tipoDeInterés = tipo;
  }
  
  public double obtenerTipoDeInterés()
  {
    return tipoDeInterés;
  }
}
