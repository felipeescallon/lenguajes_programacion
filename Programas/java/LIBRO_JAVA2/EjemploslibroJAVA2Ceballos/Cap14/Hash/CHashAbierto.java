//////////////////////////////////////////////////////////////////
// Clase abstracta CHashAbierto: método hash abierto.
// Para utilizar los métodos proporcionados por esta clase,
// tendremos que crear una subclase de ella y redefinir los
// métodos: fa (función de acceso) y comparar.
//
public abstract class CHashAbierto
{
  // Atributos
  private Object[] matrizhash;
  
  // Métodos
  public CHashAbierto()
  {
    matrizhash = new Object[101];
  }
  
  public CHashAbierto(int númeroDeElementos)
  {
    if (númeroDeElementos < 1)
      númeroDeElementos = 101;
    else
      númeroDeElementos = númeroPrimo(númeroDeElementos);
    matrizhash = new CAlumno[númeroDeElementos];
  }
  
  public int númeroDeElementos() { return matrizhash.length; }
  
  // Buscar un número primo a partir de un número dado ///////////
  public int númeroPrimo(int n)
  {
    boolean primo = false;
    int i, r = (int)Math.sqrt((double)n);
    
    if (n % 2 == 0) n++;
    while (!primo)
    {
      primo = true;
      for (i = 3; i <= r; i += 2)
        if (n % i == 0) primo = false;
      if (!primo) n += 2; // siguiente impar
    }
    return n;
  }
  
  // Función de acceso ///////////////////////////////////////////
  // Este método debe ser redefinido en una subclase para poder
  // definir la función de acceso que el usuario desee aplicar.
  public abstract int fa(Object obj);
  
  // Método comparar ///////////////////////////////////////////
  // Este método debe ser redefinido en una subclase para que
  // permita comparar las claves de dos objetos de los 
  // referenciados por la matriz hash.
  public abstract int comparar(Object obj1, Object obj2);
  
  // Método hash abierto /////////////////////////////////////////
  public void hashIn(Object x)
  {
    int i;         // índice para acceder a un elemento
    int conta = 0; // contador
    boolean insertado = false;
    
    i = fa(x);     // función de acceso
    while (!insertado && conta < matrizhash.length)
    {
      if (matrizhash[i] == null) // elemento libre
      {
        matrizhash[i] = x;
        insertado = true;
      }
      else // clave duplicada
        if (comparar(x, matrizhash[i]) == 0)
        {
          System.out.println("error: clave duplicada");
          insertado = true;
        }
        else // colisión
        {
          // Siguiente elemento libre
          i++; conta++;
          if (i == matrizhash.length) i = 0;
        }
    }
    if (conta == matrizhash.length)
      System.out.println("error: matriz llena\n");
  }

  public Object hashOut(Object x)
  {
    // x proporcionará el atributo utilizado para buscar. El resto
    // de los atributos no interesan (son los que se desea conocer)
    int i;         // índice para acceder a un elemento
    int conta = 0; // contador
    boolean encontrado = false;
    
    i = fa(x);     // función de acceso
    while (!encontrado && conta < matrizhash.length)
    {
      if (matrizhash[i] == null) return null;
      if (comparar(x, matrizhash[i]) == 0)
      {
        x = matrizhash[i];
        encontrado = true;
      }
      else // colisión
      {
        // Siguiente elemento libre
        i++; conta++;
        if (i == matrizhash.length) i = 0;
      }
    }
    if (conta == matrizhash.length) // no existe
      return null;
    else
      return x;
  }
}
//////////////////////////////////////////////////////////////////
