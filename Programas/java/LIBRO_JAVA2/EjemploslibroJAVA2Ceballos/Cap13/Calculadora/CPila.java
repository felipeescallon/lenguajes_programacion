//////////////////////////////////////////////////////////////////
// Pila: lista en la que todas las inserciones y supresiones se
// hacen en un extremo de la misma.
//
public class CPila extends CListaCircularSE
{
  public CPila() {}
  
  public void meterEnPila(Object obj)
  {
    añadirAlPrincipio(obj);
  }
  
  public Object sacarDePila()
  {
    return borrar();
  }
}
//////////////////////////////////////////////////////////////////
