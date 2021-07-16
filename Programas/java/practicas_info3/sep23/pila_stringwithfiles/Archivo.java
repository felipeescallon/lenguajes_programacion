import java.io.*;

class Archivo
{
String nombrefichero;//default: acceso paquete para que lo pueda usar la aplicacion main 
private File fichero;
	
	
public void guardar (String nom, CEstDatPil pil)
{
  	try
  	{    
      nombrefichero=new String(nom);
      fichero = new File(nombrefichero); 
      				// Crear un objeto File que identifique al fichero
      				// Crear un flujo hacia el fichero que permita escribir
      				// datos de tipos primitivos y que utilice un buffer.
      FileOutputStream fos = new FileOutputStream(fichero);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      DataOutputStream dos = new DataOutputStream(bos);
      				// Declarar los datos a escribir en el fichero
      				// Leer datos de la entrada estándar y escribirlos en el fichero
      dos.writeInt(pil.retornartam());
      for(int j=0;j<=pil.retornari();j++) dos.writeUTF(pil.retornarcad(j));
      if (dos != null) dos.close();
    }
  catch(IOException e)
    {
      System.out.println("Error: " + e.getMessage());
    }
 }
	
public CEstDatPil recuperar (String nom)
{
	
	File fichero = null;
    int band=0;//bandera:si es cero todavia no alcanza al end of file(eof)
    String aux = null;
    CEstDatPil p = null;

	nombrefichero=new String(nom);

	fichero = new File(nombrefichero); // objeto que identifica el fichero

	try
	 {    
    	FileInputStream fis = new FileInputStream(fichero);
    	BufferedInputStream bis = new BufferedInputStream(fis);
    	DataInputStream dis = new DataInputStream(bis);
    						// entrada de datos desde el fichero
        p = new CEstDatPil(dis.readInt());
        while (band==0)
        {
        try
          	{
   				aux = dis.readUTF();
           		p.ingresar(aux);
        					// Declarar los datos a escribir en el fichero
					        // Leer la información desde el fichero.
					        // Cuando se alcance el final del fichero Java
					        // lanzará una excepción del tipo EOFException.
          	}
        catch(EOFException e) {band = 1;}//band=1=>eof
        catch(Desborde e){};
        }
        if (dis != null) dis.close();
  	    return p;
      } 
 catch(FileNotFoundException e) { System.out.println(e.getMessage()); return null;} 
 catch(IOException e) { System.out.println(e.getMessage()); return null;}  
 
  }
}
