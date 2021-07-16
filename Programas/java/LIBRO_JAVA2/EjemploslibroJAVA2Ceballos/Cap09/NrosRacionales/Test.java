/////////////////////////////////////////////////////////////////
// Aplicación para trabajar con números racionales
//
public class Test
{
  public static void main(String[] args)
  {
    CRacional r1 = new CRacional(1);
    CRacional r2 = new CRacional(1, 4);
    CRacional r3;
    r3 = r1.sumar(r2);
    System.out.println(r3.toString()); // 5/4
    
    long n = 2;
    r3 = new CRacional(n).sumar(r2);
    System.out.println(r3.toString()); // 9/4
    
    CRacional r4 = new CRacional(r2);
    r3.copiar(r2);
    if (r3.equals(r2)) r1 = r3.sumar(r4);
    System.out.println(r1.toString()); // 1/2
    
    CRacional r5, r6, r7;
    r5 = CRacional.leer();
    r6 = CRacional.leer();
    r7 = r5.restar(r6);
    if (r7.esCero())
      System.out.println("racional cero");
    else
      System.out.println(r7.toString());
    
    r2.copiar(r1.incrementar());
    System.out.println(r1.toString()); // 3/2
    System.out.println(r2.toString()); // 3/2
    r2.decrementar();
    System.out.println(r2.toString()); // 1/2
    
    r2.copiar(r1.cambiadoDeSigno());
    System.out.println(r1.toString()); // 3/2
    System.out.println(r2.toString()); // -3/2
  }
}
