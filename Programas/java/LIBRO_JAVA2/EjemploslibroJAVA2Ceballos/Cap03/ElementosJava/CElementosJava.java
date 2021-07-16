/**
 * La ejecución del programa comienza con el método main().
 * La llamada al constructor de clase no tiene lugar a menos
 * que se cree un objeto del tipo 'CElementosJava'
 * en el método main().
 */
class CElementosJava
{
  /**
   * Punto de entrada principal para la aplicación.
   * 
   * Parámetros:
   *   args: Matriz de parámetros pasados a la aplicación
   *         a través de la línea de órdenes.
   */
  final static int cte1 = 1;
  final static String cte2 = "Pulse una tecla para continuar";
  short día, mes, año;
  
  void Test()
  {
    final double cte3 = 3.1415926;
    int contador;
    String Nombre = "", Apellidos;
    día = 20;
    Apellidos = "Ceballos";
    //System.out.println(contador); // error: variable no iniciada
    System.out.println(día);      // correcto: día es igual a 0
  }

  public static void main(String[] args)
  {
  /*
    // Conversión implícita
    byte bDato = 1; short sDato = 0; int iDato = 0; long lDato = 0;
    float fDato = 0; double dDato = 0;
    
    sDato = bDato;
    iDato = sDato;
    lDato = iDato;
    fDato = lDato;
    dDato = fDato + lDato - iDato * sDato / bDato;
    System.out.println(dDato); // resultado: 1.0
    
    // Conversión explícita (cast)
    dDato = 2;
    fDato = (float)dDato;
    lDato = (long)fDato;
    iDato = (int)lDato;
    sDato = (short)iDato;
    bDato = (byte)(sDato + iDato - lDato * fDato / dDato);
    System.out.println(bDato); // resultado: 2
    
    float r;
    r = (float)Math.sqrt(10);
    System.out.println(r);
   
    // Operadores aritméticos
    int a = 10, b = 3, c;
    float x = 2.0F, y;
    y = x + a;        // El resultado es 12.0 de tipo float
    c = a / b;        // El resultado es 3 de tipo int
    c = a % b;        // El resultado es 1 de tipo int
    y = a / b;        // El resultado es 3 de tipo int. Se
                      // convierte a float para asignarlo a y
    c = (int)(x / y); // El resultado es 0.6666667 de tipo float. Se
                      // convierte a int para asignarlo a c (c = 0)
    System.out.println(x/y);

    // Operadores de relación o de comparación
    int x = 10, y = 0;
    boolean r;
    r = x == y; // da como resultado false
    r = x > y;  // da como resultado true
    r = x != y; // da como resultado true
    
    // Operadores lógicos
    int p = 10, q = 0;
    boolean r;
    r = p != 0 && q != 0;   // da como resultado false
    r = p != 0 || q > 0;    // da como resultado true
    r = q < p && p <= 10;   // da como resultado true
    r = !r;                 // si r es true, el resultado es false

    // Operadores unitarios
    int a = 2, b = 0, c = 0;
    c = -a;   // resultado c = -2
    c = ~b;   // resultado c = -1

    // Operadores a nivel de bits
    int a = 255, r = 0, m = 32;
    
    r = a & 017; // r=15. Pone a cero todos los bits de a
                 // excepto los 4 bits de menor peso.
    r = r | m;   // r=47. Pone a 1 todos los bits de r que
                 // estén a 1 en m.
    r = a & ~07; // r=248. Pone a 0 los 3 bits de menor peso de a.
    r = a >> 7;  // r=1. Desplazamiento de 7 bits a la derecha.

    // Operadores de asignación
    int x = 0, n = 10, i = 1;
    x++;         // Incrementa el valor de x en 1.
    ++x;         // Incrementa el valor de x en 1.
    x = --n;     // Decrementa n en 1 y asigna el resultado a x.
    x = n--;     // Asigna el valor de n a x y después
                 // decrementa n en 1.
    i += 2;      // Realiza la operación i = i + 2.
    x *= n - 3;  // Realiza la operación x = x * (n-3) y no
                 // x = x * n - 3.
    n >>= 1;     // Realiza la operación n = n >> 1 la cual desplaza
                 // el contenido de n 1 bit a la derecha.

    // Operador condicional
    double a = 10.2, b = 20.5, mayor = 0;
    mayor = (a > b) ? a : b;
*/
    // Priopridad y orden de evaluación
    int x = 0, y = 0, z = 15;
    x = y = z;     // resultado x = y = z = 15
    System.out.println("x = " + x + ", " + "y = " + y + ", " + "z = " + z);
  }
}
