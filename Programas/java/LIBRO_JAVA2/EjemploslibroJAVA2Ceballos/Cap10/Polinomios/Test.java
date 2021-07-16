// Suma de polinomios dependientes de dos variables.
// Esta aplicación utiliza la clase Leer.
//
public class Test
{
  public static CTermino leerTermino()
  {
    CTermino ptx = null;
    double coef;
    int expx, expy;
    System.out.print("Coeficiente:    ");
    coef = Leer.datoDouble();
    System.out.print("Exponente en X: ");
    expx = Leer.datoInt();
    System.out.print("Exponente en Y: ");
    expy = Leer.datoInt();
    System.out.println();
    if ( coef == 0 && expx == 0 && expy == 0 ) return null;
    ptx = new CTermino( coef, expx, expy );
    return ptx;
  }

  public static void main(String[] args)
  {
    // Definir los polinomios a sumar
    CPolinomio polinomioA = new CPolinomio();
    CPolinomio polinomioB = new CPolinomio();
    // Declarar una referencia al polinomio resultante
    CPolinomio polinomioR;
    // Declarar una referencia a un término cualquiera
    CTermino ptx = null; // puntero a un término
    // Leer los términos del primer sumando
    System.out.print("Términos del polinomio A "
         + "(para finalizar introduzca 0 para el\n"
         + "coeficiente y para los exponentes).\n\n");
    ptx = leerTermino();
    while ( ptx != null )
    {
      polinomioA.insertarTermino( ptx );
      ptx = leerTermino();
    }
    // Leer los términos del segundo sumando
    System.out.println("Términos del polinomio B "
         + "(para finalizar introduzca 0 para el\n"
         + "coeficiente y para los exponentes).\n\n");
    ptx = leerTermino();
    while ( ptx != null )
    {
      polinomioB.insertarTermino( ptx );
      ptx = leerTermino();
    }
    // Sumar los dos polinomios leídos
    polinomioR = polinomioA.sumar(polinomioB);
    
    // Visualizar el primer sumando
    System.out.print("Polinomio A: ");
    polinomioA.mostrarPolinomio();
    System.out.println();
    // Visualizar el segundo sumando
    System.out.print("Polinomio B: ");
    polinomioB.mostrarPolinomio();
    System.out.println();
    // Visualizar el polinomio suma
    System.out.print("Polinomio R: ");
    polinomioR.mostrarPolinomio();
    System.out.println();
    
    // Visualizar el valor del polinomio suma para x = 1 e y = 1
    System.out.println("Para x = 1 e y = 1, el valor es: " + 
                       polinomioR.valorPolonomio(1, 1));
  }
}
