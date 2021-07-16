/////////////////////////////////////////////////////////////////
// Definici�n de la clase CPersona
//
import java.io.*;
public class CPersona implements Serializable
{
  // Atributos
  private String nombre;
  private String direcci�n;
  private long tel�fono;
  
  // M�todos
  public CPersona() {}
  public CPersona(String nom, String dir, long tel)
  {
    nombre = nom;
    direcci�n = dir;
    tel�fono = tel;
  }
  
  public void asignarNombre(String nom)
  {
    nombre = nom;
  }
  
  public String obtenerNombre()
  {
    return nombre;
  }
  
  public void asignarDirecci�n(String dir)
  {
    direcci�n = dir;
  }
  
  public String obtenerDirecci�n()
  {
    return direcci�n;
  }
  
  public void asignarTel�fono(long tel)
  {
    tel�fono = tel;
  }
  
  public long obtenerTel�fono()
  {
    return tel�fono;
  }
}
