import java.io.*;
// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CCalculadora
{
  // Simulaci�n de una calculadora 
  static int men�()
  {
    int op;
    do
    {
      System.out.println("\t1. sumar");
      System.out.println("\t2. restar");
      System.out.println("\t3. multiplicar");
      System.out.println("\t4. dividir");
      System.out.println("\t5. salir");
      System.out.print("\nSeleccione la operaci�n deseada: ");
      op = Leer.datoInt();
    }
    while (op < 1 || op > 5);
    return op;
  }
  
  public static void main(String[] args)
  {
    double dato1 = 0, dato2 = 0, resultado = 0;
    int operaci�n = 0;
    
    try
    {
      while (true)
      {
        operaci�n = men�();
        if (operaci�n != 5)
        {
          // Leer datos
          System.out.print("Dato 1: "); dato1 = Leer.datoDouble();
          System.out.print("Dato 2: "); dato2 = Leer.datoDouble();
          // Limpiar el buffer del flujo de entrada
          System.in.skip(System.in.available());
          // Realizar la operaci�n
          switch (operaci�n)
          {
            case 1:
              resultado = dato1 + dato2;
              break;
            case 2:
              resultado = dato1 - dato2;
              break;
            case 3:
              resultado = dato1 * dato2;
              break;
            case 4:
              resultado = dato1 / dato2;
              break;
          }
          // Escribir el resultado
          System.out.println("Resultado = " + resultado);
          // Hacer una pausa
          System.out.println("Pulse [Entrar] para continuar");
          System.in.read();
          // Limpiar el buffer del flujo de entrada
          System.in.skip(System.in.available());
        }
        else
          break;
      }
    }
    catch(IOException ignorada) {}
  }
}
