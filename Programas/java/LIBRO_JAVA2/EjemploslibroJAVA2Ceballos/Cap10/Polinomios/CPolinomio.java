import java.math.*;
//////////////////////////////////////////////////////////////////
// Clase CPolinomio. Un objeto CPolinomio consta de uno o más
//                   objetos CTermino.
//
public class CPolinomio
{

  private CTermino[] términos; // matriz de objetos
  private int nElementos; // número de elementos de la matriz
  
  public CPolinomio()
  {
    // Crear una matriz vacía
    nElementos = 0;
    términos = new CTermino[nElementos];
  }
  
  private void unElementoMás(CTermino[] términosAct)
  {
    nElementos = términosAct.length;
    // Crear una matriz con un elemento más
    términos = new CTermino[nElementos + 1];
    // Copiar los términos que hay actualmente
    for ( int i = 0; i < nElementos; i++ )
      términos[i] = términosAct[i];
    nElementos++;
  }
  
  private void unElementoMenos(CTermino[] términosAct)
  {
    if (términosAct.length == 0) return;
    int k = 0;
    nElementos = términosAct.length;
    // Crear una matriz con un elementos menos
    términos = new CTermino[nElementos - 1];
    // Copiar los términos no nulos que hay actualmente
    for ( int i = 0; i < nElementos; i++ )
      if (términosAct[i] != null)
        términos[k++] = términosAct[i];
    nElementos--;
  }

  public void insertarTermino(CTermino obj)
  {
    // Insertar un nuevo término en orden ascendente del
    // exponente de x; y a igual exponente de x, en orden
    // ascendente del exponente de y.
    if ( obj.obtenerCoeficiente() == 0 ) return;
    int k = 10, i;
    int expX = obj.obtenerExponenteDeX();
    int expY = obj.obtenerExponenteDeY();
    
    // Si el término en xy existe, sumar los coeficientes
    for ( i = nElementos - 1; i >= 0; i-- )
    {
      if ( expX == términos[i].obtenerExponenteDeX() &&
          expY == términos[i].obtenerExponenteDeY() )
      {
        double coef = términos[i].obtenerCoeficiente() +
                      obj.obtenerCoeficiente();
        if ( coef != 0 )
          términos[i].asignarCoeficiente(coef);
        else
          eliminarTermino(i);
        return;
      }
    }
        
    // Si el término en xy no existe, insertarlo.
    while (Math.abs(expX) > k || Math.abs(expY) > k) k = k*10;
    // Se añade un elemento vacío
    unElementoMás(términos);
    i = nElementos - 2; // i = nElementos - 1 vale null
    while ( i >= 0 && (expX * k + expY <
                       términos[i].obtenerExponenteDeX() * k + 
                       términos[i].obtenerExponenteDeY()) )
    {
      términos[i+1] = términos[i];
      i--;
    }
    términos[i+1] = obj;
  }
  
  public boolean eliminarTermino(int i)
  {
    // Eliminar el objeto que está en la posición i
    if (i >= 0 && i < nElementos)
    {
      términos[i] = null; // enviar el objeto a la basura
      unElementoMenos(términos);
      return true;
    }
    return false;
  }
  
  public CTermino términoEn(int i)
  {
    // Devolver la referencia al objeto i de la matriz
    if (i >= 0 && i < nElementos)
      return términos[i];
    else
    {
      System.out.println("Índice fuera de límites");
      return null;
    }
  }
  
  public int longitud() { return nElementos; }
  
  public CPolinomio copiar(CPolinomio p) // asignación
  {
    // Copiar el origen en el nuevo destino
    nElementos = p.nElementos;
    términos = new CTermino[nElementos];
    for (int i = 0; i < nElementos; i++)
      términos[i] = new CTermino(p.términos[i]);
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
      coefA = términos[ipa].obtenerCoeficiente();
      expXA = términos[ipa].obtenerExponenteDeX();
      expYA = términos[ipa].obtenerExponenteDeY();
      coefB = pB.términos[ipb].obtenerCoeficiente();
      expXB = pB.términos[ipb].obtenerExponenteDeX();
      expYB = pB.términos[ipb].obtenerExponenteDeY();
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

    // Términos restantes en el pA
    while ( ipa < na )
    {
      coefA = términos[ipa].obtenerCoeficiente();
      expXA = términos[ipa].obtenerExponenteDeX();
      expYA = términos[ipa].obtenerExponenteDeY();
      pR.insertarTermino(new CTermino(coefA, expXA, expYA));
      ipa++;
    }
    // Términos restantes en el pB
    while ( ipb < nb )
    {
      coefB = pB.términos[ipb].obtenerCoeficiente();
      expXB = pB.términos[ipb].obtenerExponenteDeX();
      expYB = pB.términos[ipb].obtenerExponenteDeY();
      pR.insertarTermino(new CTermino(coefB, expXB, expYB));
      ipb++;
    }
    // Quitar los términos con coeficiente 0
    k = 0;
    while ( k < pR.nElementos )
    {
      if (pR.términos[k].obtenerCoeficiente() == 0)
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
      términos[i].mostrarTermino();
  }
  
  public double valorPolonomio(double x, double y)
  {
    double v = 0;
    for ( int i = 0; i < nElementos; i++ )
      v += términos[i].obtenerCoeficiente() *
           Math.pow(x, términos[i].obtenerExponenteDeX()) *
           Math.pow(y, términos[i].obtenerExponenteDeY());
    return v;
  }
}
//////////////////////////////////////////////////////////////////
