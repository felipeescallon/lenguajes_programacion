/////////////////////////////////////////////////////////////////
// Definición de la clase CBaseDeDatos.
//
import java.io.*;
public class CBaseDeDatos
{
  // Atributos
  private File ficheroActual;     // objeto File (nombre del fichero)
  private RandomAccessFile fes;   // flujo hacia/desde el fichero
  private int nregs;              // número de registros
  private int tamañoReg = 50;     // tamaño del registro en bytes
  private boolean borrar = false; // true si se borran registros

  // Métodos
  public CBaseDeDatos(File fichero) throws IOException
  {
    // ¿Existe el fichero?
    if (fichero.exists() && !fichero.isFile())
      throw new IOException(fichero.getName() + " no es un fichero");
    // Asignar valores a los atributos
    ficheroActual = fichero;
    fes = new RandomAccessFile(fichero, "rw");
    // El último registro no ocupa el tamaño especificado.
    // Por esta causa utilizamos ceil, para redondear por encima.
    nregs = (int)Math.ceil((double)fes.length() / (double)tamañoReg);
  }
  
  public void cerrar() throws IOException { fes.close(); }
  
  public int longitud() { return nregs; } // número de registros
  
  public boolean ponerValorEn( int i, CRegistro objeto )
    throws IOException
  {
    if (i >= 0 && i <= nregs)
    {
      // Los 2 primeros bytes que escribe writeUTF indican la
      // longitud de la String que escribe a continuación. Esta
      // información es utilizada por readUTF.
      if (objeto.tamaño() + 2 > tamañoReg)
        System.err.println("tamaño del registro excedido");
      else
      {
        // Situar el puntero de L/E en el registro i.
        fes.seek(i * tamañoReg);
        // Sobreescribir el registro con la nueva información
        fes.writeUTF(objeto.obtenerReferencia());
        fes.writeDouble(objeto.obtenerPrecio());
        return true;
      }
    }
    else
      System.err.println("número de registro fuera de límites");
    return false;
  }
  
  public void añadir(CRegistro obj) throws IOException
  {
    // Añadir un registro al final del fichero e incrementar
    // el número de registros
    if (ponerValorEn( nregs, obj )) nregs++;
  }

  public CRegistro valorEn( int i ) throws IOException
  {
    if (i >= 0 && i < nregs)
    {
      // Situar el puntero de L/E en el registro i.
      fes.seek(i * tamañoReg);
      
      String referencia;
      double precio;
      // Leer la información correspondiente al registro i.
      referencia = fes.readUTF();
      precio = fes.readDouble();
      
      // Devolver el objeto CRegistro correspondiente.
      return new CRegistro(referencia, precio);
    }
    else
    {
      System.out.println("número de registro fuera de límites");
      return null;
    }
  }
  
  public int buscar(String str, int nreg) throws IOException
  {
    // Buscar un registro por una subcadena de la referencia
    // a partir de un registro determinado. Si se encuentra,
    // se devuelve el número de registro, o -1 en otro caso.
    CRegistro obj;
    String ref;
    if (str == null) return -1;
    if (nreg < 0) nreg = 0;
    for ( int reg_i = nreg; reg_i < nregs; reg_i++ )
    {
      // Obtener el registro reg_i
      obj = valorEn(reg_i);
      // Obtener su referencia
      ref = obj.obtenerReferencia();
      // ¿str está contenida en referencia?
      if (ref.indexOf(str) > -1)
        return reg_i; // devolver el número de registro
    }
    return -1; // la búsqueda falló
  }

  public boolean eliminar(String ref) throws IOException
  {
    CRegistro obj;
    // Buscar la referencia y marcar el registro correspondiente
    // para poder eliminarlo en otro proceso.
    for ( int reg_i = 0; reg_i < nregs; reg_i++ )
    {
      // Obtener el registro reg_i
      obj = valorEn(reg_i);
      // ¿Tiene la referencia ref?
      if (ref.compareTo(obj.obtenerReferencia()) == 0)
      {
        // Marcar el registro con la referencia "borrar"
        obj.asignarReferencia("borrar");
        borrar = true;
        // Grabarlo
        ponerValorEn( reg_i, obj );
        return true;
      }
    }
    return false;
  }
  
  public boolean tieneRegsEliminados()
  {
    return borrar;
  }

  public void actualizar() throws IOException
  {
    // Crear un fichero temporal.
    File ficheroTemp = new File("articulos.tmp");
    CBaseDeDatos ftemp = new CBaseDeDatos(ficheroTemp);
    
    // Copiar en el fichero temporal todos los registros del
    // fichero actual que no estén marcados para "borrar"
    CRegistro obj;
    for ( int reg_i = 0; reg_i < nregs; reg_i++ )
    {
      obj = valorEn(reg_i);
      if (obj.obtenerReferencia().compareTo("borrar") != 0)
        ftemp.añadir(obj);
    }
    // Borrar el fichero actual y renombrar el temporal con el
    // nombre del actual. Para hacer estas operaciones los ficheros
    // no pueden estar en uso.
    this.cerrar();          // cerrar el fichero actual
    ftemp.cerrar();         // cerrar el fichero temporal
    ficheroActual.delete(); // borrar el fichero actual
    if (!ficheroTemp.renameTo(ficheroActual)) // renombrar
      throw new IOException("no se actualizó el fichero");
  }
}
