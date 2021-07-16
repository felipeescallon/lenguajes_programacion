interface Interfaz
{
  public abstract void p();
  public abstract void m();
}

class Clase2
{
  private int i;
  
  // ...
  public Interfaz metClase2()
  {
    return new Interfaz()
    {
      public void p() { System.out.println("método p"); }
      public void m() { System.out.println("método m"); }
    };
  }
}

public class Clase1
{
  public static void main (String[] args)
  {
    Clase2 obj = new Clase2();
    Interfaz iobj = obj.metClase2(); // devuelve un objeto Clase3
    iobj.m();
  }
}