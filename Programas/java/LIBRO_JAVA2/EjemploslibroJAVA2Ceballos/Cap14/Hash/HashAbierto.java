//////////////////////////////////////////////////////////////////
// Clase derivada de la clase abstracta CHashAbierto. Redefine
// los métodos: fa (función de acceso) y comparar.
//
public class HashAbierto extends CHashAbierto
{
  public HashAbierto(int nElementos)
  {
    super(nElementos);
  }
  
  public int fa(Object obj)
  {
    return (int)((CAlumno)obj).obtenerMatrícula() % númeroDeElementos();
  }
  
  public int comparar(Object obj1, Object obj2)
  {
    if (((CAlumno)obj1).obtenerMatrícula() ==
           ((CAlumno)obj2).obtenerMatrícula())
      return 0;
    else
      return 1;
  }
}
//////////////////////////////////////////////////////////////////