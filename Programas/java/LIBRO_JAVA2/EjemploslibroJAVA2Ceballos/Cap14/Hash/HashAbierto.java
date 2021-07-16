//////////////////////////////////////////////////////////////////
// Clase derivada de la clase abstracta CHashAbierto. Redefine
// los m�todos: fa (funci�n de acceso) y comparar.
//
public class HashAbierto extends CHashAbierto
{
  public HashAbierto(int nElementos)
  {
    super(nElementos);
  }
  
  public int fa(Object obj)
  {
    return (int)((CAlumno)obj).obtenerMatr�cula() % n�meroDeElementos();
  }
  
  public int comparar(Object obj1, Object obj2)
  {
    if (((CAlumno)obj1).obtenerMatr�cula() ==
           ((CAlumno)obj2).obtenerMatr�cula())
      return 0;
    else
      return 1;
  }
}
//////////////////////////////////////////////////////////////////