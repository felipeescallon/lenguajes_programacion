import java.awt.*;
import java.net.*;
import java.io.*;
 
class minimoCliente 
{
public static void main( String args[] ) throws IOException {

int c;
Socket s=null;
InputStream sIn;
 
// Abrimos una conexión con breogan en el puerto 4321
        
try {
    s = new Socket( "Diego",4321 );
    }

catch( IOException e )
    {
    System.out.println( e );
    }
 
// Obtenemos un controlador de fichero de entrada del socket y
// leemos esa entrada

sIn = s.getInputStream();
while( ( c = sIn.read() ) != -1 )
       System.out.print( (char)c );
    
// Cuando se alcance el fin de fichero, cerramos la conexión y
// abandonamos
    s.close();
    
 }
}

