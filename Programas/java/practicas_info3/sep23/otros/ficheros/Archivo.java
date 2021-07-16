
import java.io.*;

class Archivo
{


public static void main(String[] args)
  { String cad;

   
    int opcion=0;
    PrintStream flujoS = System.out; // salida estándar
    String nombreFichero = null;     // nombre del fichero
    File fichero = null; // objeto que identifica el fichero
    Crear obj=new Crear();
    
      
      // Verificar si el fichero existe
	
	char res = 's';
      char resp = 's';


while (opcion != 5){
try
{	System.out.println("\n\tMENU");
 	System.out.println("\n\t1. INGRESAR ELEMENTO");
 	System.out.println("\n\t2. SACAR ELEMENTO ");
 	System.out.println("\n\t3.  GUARDAR");
        System.out.println("\n\t4.  LEER ARCHIVO");
 	System.out.println("\n\t5. SALIR");
        
 	System.out.println("ELEGIR OPCION, OPRIMIENDO 1,2,3,4 o 5");

 	opcion=Leer.datoInt();

switch(opcion)
{

case 1:
                System.out.println("ingrese elemento");
		cad=Leer.dato();
                System.out.println("");
                System.out.println("");
 		try
			{		
			obj.ingresar(cad);
			}catch(Desborde e)
					 	{
	       System.out.println(e.getMessage());}
break;


                     
                case 2:
		System.out.println("el objeto sacado es");
 		try
					  {		
					  
	 	 System.out.println(obj.sacar());
		 System.out.println("");
                 System.out.println("");
                 }catch(Desborde e)
					 	{
					 			System.out.println(e.getMessage());
					 	}

						break;

case 3: 
       try
    {
      // Crear un objeto File que identifique al fichero
      flujoS.print("Nombre del fichero: ");
      nombreFichero = Leer.dato();
      fichero = new File(nombreFichero);
          

	 if (fichero.exists())
           {
        flujoS.print("El fichero existe ¿desea sobreescribirlo? (s/n) ");
        resp = Leer.carácter();
        Leer.limpiar();
      }
        if (resp == 's')
      {

        
        obj.crearFichero(fichero);
       
     

      }
      }
      catch(IOException e)
    {
      flujoS.println("Error: " + e.getMessage());
    }
	
      break;

      case 4:
            try
      {
        obj.mostrarFichero("archivo.txt");
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.getMessage());
      }

      break;



      case 5:
      break;    
      
      default:
      System.out.println("opcion no valida");
       }
	}catch(Exception e)
					 	{
	       }
}

}}