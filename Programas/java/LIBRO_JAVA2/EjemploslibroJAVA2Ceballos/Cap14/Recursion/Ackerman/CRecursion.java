/////////////////////////////////////////////////////////////////
// Recursividad
//
public class CRecursion
{
  // Método recursivo de Ackerman:
  //   A(0,n) = n+1
  //   A(m,0) = A(m-1,1)               (m > 0)
  //   A(m,n) = A(m-1,A(m,n-1))        (m,n > 0)
  public static int Ackerman(int m, int n)
  {
    if (m == 0)
      return n+1;
    else if (n == 0)
      return Ackerman(m-1, 1);
    else
      return Ackerman(m-1, Ackerman(m,n-1));
  }
  
  public static int AckermanNR(int m, int n)
  {
    CPila pila = new CPila(); // pila de elementos (m,n)
    CDatos dato;
    int Ackerman_m_n = 0;
    pila.meterEnPila(new CDatos(m, n));
    while (true)
    {
      // Tomar los datos de la cima de la pila
      dato = (CDatos)pila.sacarDePila();
      m = dato.obtenerM();
      n = dato.obtenerN();
      if (m == 0) // Ackerman(0,n) = n+1
      {
        Ackerman_m_n = n+1;
        if (pila.tamaño() != 0)
        {
          // Sacar m y n de la pila
          dato = (CDatos)pila.sacarDePila();
          m = dato.obtenerM();
          n = dato.obtenerN();
          // Meter m y Ackerman_m_n en la pila
          pila.meterEnPila(new CDatos(m, Ackerman_m_n));
        }
        else
          return Ackerman_m_n;
      }
      else if (n == 0) // Ackerman(m-1,1)
        // Meter m-1 y 1 en la pila
        pila.meterEnPila(new CDatos(m-1, 1));
      else // Ackerman(m-1,Ackerman(m,n-1))
      {
        // Meter m-1 y Ackerman_m_n en la pila
        pila.meterEnPila(new CDatos(m-1, Ackerman_m_n)); // n=Ackerman(m,n-1)
        // Meter m y n-1 en la pila
        pila.meterEnPila(new CDatos(m, n-1));
      }
    }
  }
}
/////////////////////////////////////////////////////////////////
