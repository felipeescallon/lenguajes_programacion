import java.math.*;
//////////////////////////////////////////////////////////////////
// Clase CPolinomio. Un objeto CPolinomio consta de uno o m�s
//                   objetos CTermino.
//
public class CPolinomio
{

  private CTermino[] t�rminos; // matriz de objetos
  private int nElementos; // n�mero de elementos de la matriz
  
  public CPolinomio()
  {
    // Crear una matriz vac�a
    nElementos = 0;
    t�rminos = new CTermino[nElementos];
  }
  
  private void unElementoM�s(CTermino[] t�rminosAct)
  {
    nElementos = t�rminosAct.length;
    // Crear una matriz con un elemento m�s
    t�rminos = new CTermino[nElementos + 1];
    // Copiar los t�rminos que hay actualmente
    for ( int i = 0; i < nElementos; i++ )
      t�rminos[i] = t�rminosAct[i];
    nElementos++;
  }
  
  private void unElementoMenos(CTermino[] t�rminosAct)
  {
    if (t�rminosAct.length == 0) return;
    int k = 0;
    nElementos = t�rminosAct.length;
    // Crear una matriz con un elementos menos
    t�rminos = new CTermino[nElementos - 1];
    // Copiar los t�rminos no nulos que hay actualmente
    for ( int i = 0; i < nElementos; i++ )
      if (t�rminosAct[i] != null)
        t�rminos[k++] = t�rminosAct[i];
    nElementos--;
  }

  public void insertarTermino(CTermino obj)
  {
    // Insertar un nuevo t�rmino en orden ascendente del
    // exponente de x; y a igual exponente de x, en orden
    // ascendente del exponente de y.
    if ( obj.obtenerCoeficiente() == 0 ) return;
    int k = 10, i;
    int expX = obj.obtenerExponenteDeX();
    int expY = obj.obtenerExponenteDeY();
    
    // Si el t�rmino en xy existe, sumar los coeficientes
    for ( i = nElementos - 1; i >= 0; i-- )
    {
      if ( expX == t�rminos[i].obtenerExponenteDeX() &&
          expY == t�rminos[i].obtenerExponenteDeY() )
      {
        double coef = t�rminos[i].obtenerCoeficiente() +
                      obj.obtenerCoeficiente();
        if ( coef != 0 )
          t�rminos[i].asignarCoeficiente(coef);
        else
          eliminarTermino(i);
        return;
      }
    }
        
    // Si el t�rmino en xy no existe, insertarlo.
    while (Math.abs(expX) > k || Math.abs(expY) > k) k = k*10;
    // Se a�ade un elemento vac�o
    unElementoM�s(t�rminos);
    i = nElementos - 2; // i = nElementos - 1 vale null
    while ( i >= 0 && (expX * k + expY <
                       t�rminos[i].obtenerExponenteDeX() * k + 
                       t�rminos[i].obtenerExponenteDeY()) )
    {
      t�rminos[i+1] = t�rminos[i];
      i--;
    }
    t�rminos[i+1] = obj;
  }
  
  public boolean eliminarTermino(int i)
  {
    // Eliminar el objeto que est� en la posici�n i
    if (i >= 0 && i < nElementos)
    {
      t�rminos[i] = null; // enviar el objeto a la basura
      unElementoMenos(t�rminos);
      return true;
    }
    return false;
  }
  
  public CTermino t�rminoEn(int i)
  {
    // Devolver la referencia al objeto i de la matriz
    if (i >= 0 && i < nElementos)
      return t�rminos[i];
    else
    {
      System.out.println("�ndice fuera de l�mites");
      return null;
    }
  }
  
  public int longitud() { return nElementos; }
  
  public CPolinomio copiar(CPolinomio p) // asignaci�n
  {
    // Copiar el origen en el nuevo destino
    nElementos = p.nElementos;
    t�rminos = new CTermino[nElementos];
    for (int i = 0; i < nElementos; i++)
      t�rminos[i] = new CTermino(p.t�rminos[i]);
    return this;
  }
  
  public CPolinomio sumar(CPolinomio pB)
  {
    // pR = pA.sumar(pB). pA es this y pR el resultado.
    int ipa = 0, ipb = 0, k = 0;
    int na = nElementos, nb = pB.nElementos;
    double coefA, coefB;
    int expXA, expYA, expXB, expYB;
    CPolinomio pR = new CPolinomio(); // polinomio resultante

    // Sumar pA con pB
    while ( ipa < na && ipb < nb )
    {
      coefA = t�rminos[ipa].obtenerCoeficiente();
      expXA = t�rminos[ipa].obtenerExponenteDeX();
      expYA = t�rminos[ipa].obtenerExponenteDeY();
      coefB = pB.t�rminos[ipb].obtenerCoeficiente();
      expXB = pB.t�rminos[ipb].obtenerExponenteDeX();
      expYB = pB.t�rminos[ipb].obtenerExponenteDeY();
      k = 10;
      while (Math.abs(expXA) > k || Math.abs(expYA) > k) k = k*10;
    
      if ( expXA == expXB && expYA == expYB )
      {
        pR.insertarTermino(new CTermino(coefA + coefB, expXA, expYA));
        ipa++; ipb++;
      }
      else if (expXA * k + expYA < expXB * k + expYB)
      {
        pR.insertarTermino(new CTermino(coefA, expXA, expYA));
        ipa++;
      }
      else
      {
        pR.insertarTermino(new CTermino(coefB, expXB, expYB));
        ipb++;
      }
    }

    // T�rminos restantes en el pA
    while ( ipa < na )
    {
      coefA = t�rminos[ipa].obtenerCoeficiente();
      expXA = t�rminos[ipa].obtenerExponenteDeX();
      expYA = t�rminos[ipa].obtenerExponenteDeY();
      pR.insertarTermino(new CTermino(coefA, expXA, expYA));
      ipa++;
    }
    // T�rminos restantes en el pB
    while ( ipb < nb )
    {
      coefB = pB.t�rminos[ipb].obtenerCoeficiente();
      expXB = pB.t�rminos[ipb].obtenerExponenteDeX();
      expYB = pB.t�rminos[ipb].obtenerExponenteDeY();
      pR.insertarTermino(new CTermino(coefB, expXB, expYB));
      ipb++;
    }
    // Quitar los t�rminos con coeficiente 0
    k = 0;
    while ( k < pR.nElementos )
    {
      if (pR.t�rminos[k].obtenerCoeficiente() == 0)
      {
        pR.eliminarTermino(k);
        pR.nElementos--;
      }
      else
        k++;
    }
    return pR;
  }
  
  public void mostrarPolinomio()
  {
    int i = nElementos;
  
    while ( i-- != 0 )
      t�rminos[i].mostrarTermino();
  }
  
  public double valorPolonomio(double x, double y)
  {
    double v = 0;
    for ( int i = 0; i < nElementos; i++ )
      v += t�rminos[i].obtenerCoeficiente() *
           Math.pow(x, t�rminos[i].obtenerExponenteDeX()) *
           Math.pow(y, t�rminos[i].obtenerExponenteDeY());
    return v;
  }
}
//////////////////////////////////////////////////////////////////
