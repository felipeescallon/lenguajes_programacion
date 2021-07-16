class ClaseA
{
  public int atributo_x = 1;

  public int m�todo_x()
  {
    return atributo_x * 10;
  }

  public int m�todo_y()
  {
    return atributo_x + 100;
  }

  protected void finalize() throws Throwable // destructor
  {
    System.out.println("Recursos de ClaseA liberados");
  }
}

class ClaseB extends ClaseA
{
  protected int atributo_x = 2;

  public int m�todo_x()
  {
    return atributo_x * -10;
  }

  public int m�todo_z()
  {
    atributo_x = super.atributo_x + 3;
    return super.m�todo_x() + atributo_x;
  }
  
  protected void finalize() throws Throwable // destructor
  {
    System.out.println("Recursos de ClaseB liberados");
    super.finalize();
  }
}

public class Test
{
  public static void main(String[] args)
  {
    ClaseB objClaseB = new ClaseB();
    System.out.println(objClaseB.atributo_x); // escribe 2
    System.out.println(objClaseB.m�todo_y()); // escribe 101
    System.out.println(objClaseB.m�todo_x()); // escribe -20
    System.out.println(objClaseB.m�todo_z()); // escribe 14 
    objClaseB = null;
    // Ejecutar el recolector de basura
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    runtime.runFinalization();
  }
}
