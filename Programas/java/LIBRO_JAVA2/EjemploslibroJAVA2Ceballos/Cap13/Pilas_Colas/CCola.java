//////////////////////////////////////////////////////////////////
// Cola: lista en la que todas las inserciones se hacen por un
// extremo de la lista (por el final) y todas las supresiones se
// hacen por el otro extremo (por el principio).
//
public class CCola extends CListaCircularSE
{
  public CCola() {}
  
  public void meterEnCola(Object obj)
  {
    añadirAlFinal(obj);
  }
  
  public Object sacarDeCola()
  {
    return borrar();
  }
}
//////////////////////////////////////////////////////////////////
