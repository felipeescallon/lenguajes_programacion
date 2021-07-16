import java.math.*;
//////////////////////////////////////////////////////////////////
// Clase CTermino: expresión de la forma a.x^n.y^m
//                 a es el coeficiente de tipo double.
//                 n y m son los exponentes enteros de x e y.
//
public class CTermino
{
  private double coeficiente = 0.0; // coeficiente
  private int exponenteDeX = 1;     // exponente de x
  private int exponenteDeY = 1;     // exponente de y

  public CTermino() {}
  public CTermino( double coef, int expx, int expy ) // constructor
  {
    coeficiente = coef;
    exponenteDeX = expx;
    exponenteDeY = expy;
  }
  public CTermino(CTermino t) // constructor copia
  {
    coeficiente = t.coeficiente;
    exponenteDeX = t.exponenteDeX;
    exponenteDeY = t.exponenteDeY;
  }
  public CTermino copiar(CTermino t) // asignación
  {
    coeficiente = t.coeficiente;
    exponenteDeX = t.exponenteDeX;
    exponenteDeY = t.exponenteDeY;
    return this;
  }
  public void asignarCoeficiente(double coef) {coeficiente = coef;}
  public double obtenerCoeficiente() {return coeficiente;}
  public void asignarExponenteDeX(int expx) {exponenteDeX = expx;}
  public int obtenerExponenteDeX() {return exponenteDeX;}
  public void asignarExponenteDeY(int expy) {exponenteDeY = expy;}
  public int obtenerExponenteDeY() {return exponenteDeY;}
  public void mostrarTermino()
  {
    if (coeficiente == 0) return;
    // Signo
    String sterm = (coeficiente < 0) ? " - " : " + ";
    // Coeficiente
    if (Math.abs(coeficiente) != 1)
      sterm = sterm + Math.abs(coeficiente);
    // Potencia de x
    if (exponenteDeX > 1 || exponenteDeX < 0)
      sterm = sterm + "x^" + exponenteDeX;
    else if (exponenteDeX == 1)
      sterm = sterm + "x";
    // Potencia de y
    if (exponenteDeY > 1 || exponenteDeY < 0)
      sterm = sterm + "y^" + exponenteDeY;
    else if (exponenteDeY == 1)
      sterm = sterm + "y";
    // Mostrar término
    System.out.print(sterm);
  }
}
//////////////////////////////////////////////////////////////////
