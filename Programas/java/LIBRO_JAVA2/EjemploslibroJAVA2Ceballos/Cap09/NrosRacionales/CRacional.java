/////////////////////////////////////////////////////////////////
// Clase para operar con n�meros racionales (utiliza la clase Leer)
//
public class CRacional
{
  // Atributos
  private long numerador;
  private long denominador;
  
  // M�todos
  protected CRacional Simplificar()
  {
    // M�ximo com�n divisor
    long mcd, temp, resto;
    mcd = Math.abs( numerador );
    temp = Math.abs( denominador );
    while ( temp > 0 )
    {
      resto = mcd % temp;
      mcd = temp;
      temp = resto;
    }
    // Simplificar
    if ( mcd > 1 )
    {
      numerador /= mcd;
      denominador /= mcd;
    }
    return this;
  }

  public CRacional() // constructor
  {
    numerador = 0;
    denominador = 1;
  }

  public CRacional( long num ) // constructor
  {
    numerador = num;
    denominador = 1;
  }

  public CRacional( long num, long den ) // constructor
  {
    numerador = num;
    denominador = den;
    if ( denominador == 0 )
    {
      System.out.println("Error: denominador 0. Se asigna 1.");
      denominador = 1;
    }
    if ( denominador < 0 )
    {
      numerador = -numerador;
      denominador = -denominador;
    }
    Simplificar();
  }
  
  public CRacional( CRacional r ) // constructor copia
  {
     numerador = r.numerador;
     denominador = r.denominador;
  }

  // Sumar n�meros racionales
  public CRacional sumar( CRacional r )
  {
    return new CRacional(numerador * r.denominador +
                         denominador * r.numerador,
                         denominador * r.denominador );
  }
  
  // Restar n�meros racionales
  public CRacional restar( CRacional r )
  {
    return new CRacional(numerador * r.denominador -
                         denominador * r.numerador,
                         denominador * r.denominador );
  }
  
  // Multiplicar n�meros racionales
  public CRacional multiplicar( CRacional r )
  {
    return new CRacional(numerador * r.numerador,
                         denominador * r.denominador );
  }
  
  // Dividir n�meros racionales
  public CRacional dividir( CRacional r )
  {
    return new CRacional(numerador * r.denominador,
                         denominador * r.numerador );
  }
  
  // Verificar si dos n�meros racionales son iguales
  public boolean equals( CRacional r )
  {
    return ( numerador * r.denominador ==
             denominador * r.numerador );
  }

  // Verificar si un racional es menor que otro
  public boolean menor( CRacional r )
  {
    return ( numerador * r.denominador <
             denominador * r.numerador );
  }

  // Verificar si un racional es mayor que otro
  public boolean mayor( CRacional r )
  {
    return ( numerador * r.denominador >
             denominador * r.numerador );
  }

  // Devolver un n�mero racional como cadena
  public String toString()
  {
    return new String(numerador + "/" + denominador);
  }

  // Establecer un n�mero racional
  public static CRacional leer()
  {
    long num, den;
    int i, barras;
    boolean car�cterV�lido;
    String racional;

    do
    {
      barras = 0;
      System.out.print("[-]entero[/entero]: ");
      racional = Leer.dato(); // leer el racional
      if (racional.length() == 0)
         car�cterV�lido = false;
      else
      {
        // El primer car�cter puede ser un d�gito o el signo menos
        car�cterV�lido =
          (racional.charAt(0) >= '0' && racional.charAt(0) <= '9') ||
          (racional.charAt(0) == '-' && racional.length() > 1);
        // El �ltimo car�cter no puede ser una /
        if (racional.charAt(racional.length()-1) == '/')
          car�cterV�lido = false;
      }
      // El resto de los caracteres pueden ser d�gitos o / (s�lo una)
      for (i = 1; car�cterV�lido && i < racional.length(); i++)
      {
        car�cterV�lido = racional.charAt(i) >= '0' &&
                         racional.charAt(i) <= '9' ||
                         racional.charAt(i) == '/';
        if (racional.charAt(i) == '/') barras++;
        if (barras > 1) car�cterV�lido = false;
      }
      if (!car�cterV�lido) System.out.println("Entrada no v�lida.");
    }
    while (!car�cterV�lido);
    // Extraer el numerador y el denominador
    if ((i = racional.indexOf('/')) == -1) // no hay denominador
    {
      num = Long.parseLong(racional);
      den = 1;
    }
    else
    {
      num = Long.parseLong(racional.substring(0, i)); // 0 a i-1
      den = Long.parseLong(racional.substring(i+1));
    }
    // Construir y devolver el objeto CRacional
    return new CRacional(num, den);
  }

  // Copiar un racional en otro
  public CRacional copiar( CRacional r )
  {
     numerador = r.numerador;
     denominador = r.denominador;
     return this;
  }

  // Verificar si es 0
  public boolean esCero()
  {
    return numerador == 0;
  }
  
  // Incrementar en 1
  public CRacional incrementar()
  {
    numerador += denominador;
    return this;
  }
  
  // Decrementar en 1
  public CRacional decrementar()
  {
    numerador -= denominador;
    return this;
  }

  // - unario
  public CRacional cambiadoDeSigno()
  {
    CRacional temp = new CRacional( -numerador, denominador );
    return temp;
  }
}
