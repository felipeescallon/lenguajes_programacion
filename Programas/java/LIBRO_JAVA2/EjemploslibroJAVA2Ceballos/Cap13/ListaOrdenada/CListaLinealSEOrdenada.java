public class CListaLinealSEOrdenada extends CListaLinealSEO
{
  // Permite comparar dos elementos de la lista por
  // el atributo nombre.
  public int comparar(Object obj1, Object obj2)
  {
    String str1 = new String(((CDatos)obj1).obtenerNombre());
    String str2 = new String(((CDatos)obj2).obtenerNombre());
    return str1.compareTo(str2);
  }
}
