/////////////////////////////////////////////////////////////////
// Definición de la clase CListaTfnos.
//
import java.io.*;
public class CListaTfnos
{
  private RandomAccessFile fes; // flujo
  private int nregs;            // número de registros
  private int tamañoReg = 140;  // tamaño del registro en bytes
  
  public CListaTfnos(File fichero) throws IOException
  {
    if (fichero.exists() && !fichero.isFile())
      throw new IOException(fichero.getName() + " no es un fichero");
    fes = new RandomAccessFile(fichero, "rw");
    // El último registro no ocupa el tamaño especificado.
    // Por esta causa utilizamos ceil, para redondear por encima.
    nregs = (int)Math.ceil((double)fes.length() / (double)tamañoReg);
  }
  
  public void cerrar() throws IOException { fes.close(); }
  
  public int longitud() { return nregs; } // número de registros
  
  public boolean ponerValorEn( int i, CPersona objeto ) throws IOException
  {
    if (i >= 0 && i <= nregs)
    {
      if (objeto.tamaño() + 4 > tamañoReg)
        System.err.println("tamaño del registro excedido");
      else
      {
        fes.seek(i * tamañoReg); // situar el puntero de L/E
        fes.writeUTF(objeto.obtenerNombre());
        fes.writeUTF(objeto.obtenerDirección());
        fes.writeLong(objeto.obtenerTeléfono());
        return true;
      }
    }
    else
      System.err.println("número de registro fuera de límites");
    return false;
  }
  
  public CPersona valorEn( int i ) throws IOException
  {
    if (i >= 0 && i < nregs)
    {
      fes.seek(i * tamañoReg); // situar el puntero de L/E
      
      String nombre, dirección;
      long teléfono;
      nombre = fes.readUTF();
      dirección = fes.readUTF();
      teléfono = fes.readLong();
      
      return new CPersona(nombre, dirección, teléfono);
    }
    else
    {
      System.out.println("número de registro fuera de límites");
      return null;
    }
  }
  
  public void añadir(CPersona obj) throws IOException
  {
    if (ponerValorEn( nregs, obj )) nregs++;
  }

  public boolean eliminar(long tel) throws IOException
  {
    CPersona obj;
    // Buscar el teléfono y marcar el registro para
    // posteriormente eliminarlo
    for ( int reg_i = 0; reg_i < nregs; reg_i++ )
    {
      obj = valorEn(reg_i);
      if (obj.obtenerTeléfono() == tel)
      {
        obj.asignarTeléfono(0);
        ponerValorEn( reg_i, obj );
        return true;
      }
    }
    return false;
  }
  
  public int buscar(String str, int pos) throws IOException
  {
    // Buscar un registro por una subcadena del nombre
    // a partir de un registro determinado
    CPersona obj;
    String nom;
    if (str == null) return -1;
    if (pos < 0) pos = 0;
    for ( int reg_i = pos; reg_i < nregs; reg_i++ )
    {
      obj = valorEn(reg_i);
      nom = obj.obtenerNombre();
      // ¿str está contenida en nom?
      if (nom.indexOf(str) > -1)
        return reg_i;
    }
    return -1;
  }
  
  // Método quicksort para ordenar el fichero ////////////////////
  public void quicksort() throws IOException
  {
    qs(0, nregs - 1);
  }

  private void qs(int inf, int sup) throws IOException
  {
    int izq = inf, der = sup;
    String mitad;

    // Obtener del registro mitad, el campo por el que
    // se va a ordenar el fichero.
    mitad = campo((izq + der)/2);
    do
    {
      while (campo(izq).compareTo(mitad) < 0 && izq < sup) izq++;
      while (mitad.compareTo(campo(der)) < 0 && der > inf) der--;
      if (izq <= der)
      {
        permutarRegistros(izq, der);
        izq++; der--;
      }
    }
    while (izq <= der);
    if (inf < der) qs(inf, der);
    if (izq < sup) qs(izq, sup);
  }

  // Permutar los registros de las posiciones i y j
  private void permutarRegistros(int i, int j) throws IOException
  {
    CPersona x, y;
    // Leer los registros de las posiciones i y j
    x = valorEn(i);
    y = valorEn(j);
    // Escribirlos en las posiciones j e i
    ponerValorEn(j, x);
    ponerValorEn(i, y);
  }

  // Obtener el campo utilizado para ordenar, del registro nreg
  private String campo(int nreg) throws IOException
  {
    fes.seek(nreg * tamañoReg); // situar el puntero de L/E
    return fes.readUTF();       // devuelve el nombre
  }
}
