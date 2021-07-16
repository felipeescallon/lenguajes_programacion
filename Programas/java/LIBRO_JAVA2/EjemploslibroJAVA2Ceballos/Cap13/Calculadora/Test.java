//////////////////////////////////////////////////////////////////
// Calculadora utilizando una pila. Esta aplicación, además de las
// clases necesarias de la biblioteca de Java, utiliza las clases:
// CPila derivada de CListaCircularSE, CDatos y CLeer. 
//
public class Test
{
  private static CPila pila = new CPila();   // pila de operandos
  private static double[] operando = {0, 0}; // operando 0 y 1

  public static void obtenerOperandos()
  {
    if (pila.tamaño() < 2) throw new NullPointerException();
    operando[1] = ((Double)pila.sacarDePila()).doubleValue();
    operando[0] = ((Double)pila.sacarDePila()).doubleValue();
  }
  
  public static void main(String[] args)
  {
    // oper almacena la entrada realizada desde el teclado
    String oper = null;

    System.out.println("Operaciones: + - * /\n");
    System.out.println("Forma de introducir los datos:");
    System.out.println(">primer operando [Entrar]");
    System.out.println(">segundo operando [Entrar]");
    System.out.println(">operador [Entrar]\n");
    System.out.println("Para salir pulse q\n");
    do
    {
      try
      {
        System.out.print("> ");
        oper = Leer.dato();      // leer un operando o un operador
        switch (oper.charAt(0))  // verificar el primer carácter
        {
          case '+':
            obtenerOperandos();
            System.out.println(operando[0] + operando[1]);
            pila.meterEnPila(new Double(operando[0]+operando[1]));
            break;
          case '-':
            obtenerOperandos();
            System.out.println(operando[0] - operando[1]);
            pila.meterEnPila(new Double(operando[0]-operando[1]));
            break;
          case '*':
            obtenerOperandos();
            System.out.println(operando[0] * operando[1]);
            pila.meterEnPila(new Double(operando[0]*operando[1]));
            break;
          case '/':
            obtenerOperandos();
            if (operando[1] == 0)
            {
              System.out.println("\nError: división por cero");
              break;
            }
            System.out.println(operando[0] / operando[1]);
            pila.meterEnPila(new Double(operando[0]/operando[1]));
            break;
          case 'q':
            // salir
            break;
          default :  // es un operando
            pila.meterEnPila(new Double(oper));
        }
      }
      catch(NumberFormatException e)
      {
        System.out.print("Error: dato no es válido. Teclee otro: ");
      }
      catch(NullPointerException e)
      {
        System.out.print("Error: teclee " + (2-pila.tamaño()) +
                         " operando(s) más");
      }
    }
    while (oper.charAt(0) != 'q');
  }
}
