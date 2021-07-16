public class TestTiposDatos
{
  // Tipos de datos
  public static void main(String[] args)
  {
    String sCadena = "Lenguaje Java";
    char[] cMatrizCars = { 'a', 'b', 'c' }; // matriz de caracteres
    int dato_int = 4;
    long dato_long = Long.MIN_VALUE;    // mínimo valor long
    float dato_float = Float.MAX_VALUE; // máximo valor float
    double dato_double = Math.PI;       // 3.1415926
    boolean dato_boolean = true;

    System.out.println(sCadena);
    System.out.println(cMatrizCars);
    System.out.println(dato_int);
    System.out.println(dato_long);
    System.out.println(dato_float);
    System.out.println(dato_double);
    System.out.println(dato_boolean);
  }
}
