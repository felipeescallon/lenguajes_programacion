import java.io.*;
// Se utiliza también la clase Leer modificada en este capítulo

public class Crear
{ 
private String nombre[] ;
private  int tam,j,i;
public Crear()
   {  
 tam=3;
 i=-1;
 
 nombre=new String[tam];
   }

  public void crearFichero(File fichero)
    throws IOException
  {
    PrintStream flujoS = System.out; // salida estándar
    DataOutputStream dos = null;// salida de datos hacia el fichero
    
    try
  {
      // Crear un flujo hacia el fichero que permita escribir
      // datos de tipos primitivos y que utilice un buffer.
      dos = new DataOutputStream(new BufferedOutputStream(
                                 new FileOutputStream(fichero)));
      // Declarar los datos a escribir en el fichero
      
     
     // Leer datos de la entrada estándar y escribirlos
      //en el fichero

        dos.writeInt(i);
     
        for(int j=0;j<3;j++){
	
        
        dos.writeUTF(nombre[j]);
        
    }  
     
   }
    finally
    {
      //Cerrar el flujo
      if (dos != null) dos.close();
    }
  }


public void ingresar(String cad) throws Desborde
   {
try{
 i++;
 nombre[i]=cad;
   }catch(ArrayIndexOutOfBoundsException err){
i--;
throw new Desborde("\nCOLA LLENA");
 
}
   }


    public  void mostrarFichero(String nombreFichero)
    throws IOException
  { 
    PrintStream flujoS = System.out;
    DataInputStream dis = null;// entrada de datos desde el fichero
    File fichero = null;       // objeto que identifica el fichero
    
    try
    {
      // Crear un objeto File que identifique al fichero
      fichero = new File(nombreFichero);

      // Verificar si el fichero existe
      if (fichero.exists())
      {
        // Si existe, abrir un flujo desde el mismo
        dis = new DataInputStream(new BufferedInputStream(
                                  new FileInputStream(fichero)));
        
        // Declarar los datos a escribir en el fichero
        
       
        do
        {
          // Leer un nombre, una dirección y un teléfono desde el
          // fichero. Cuando se alcance el final del fichero Java
          // lanzará una excepción del tipo EOFException.
          i=dis.readInt();
          for(int k=0;k<=i;k++){
          nombre[k] = dis.readUTF();
          flujoS.println(nombre[k]);
                 }
         
          // Mostrar los datos nombre, dirección y teléfono
          
          flujoS.println(i);
          flujoS.println();

        }while(true);
        
      }
      else
        flujoS.println("El fichero no existe");
    }
    catch(EOFException e)
    {
      flujoS.println("Fin del listado");
    }
    finally
    {
      // Cerrar el flujo
      if (dis != null) dis.close();
    }
  }
  

public String sacar() throws Desborde
        {
        
	String aux=nombre[0];
	   if(nombre[0]==null){
           System.out.println("cola vacia");}
	   nombre[0]=null;
	   for(int j=0;j<(tam-1);j++){
	   nombre[j]=nombre[j+1];}
           nombre[i]=null;
	   i--;
           return aux;}

  
}
