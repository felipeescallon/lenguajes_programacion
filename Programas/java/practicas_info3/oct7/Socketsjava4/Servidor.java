import java.io.*;
import java.net.*;

//traspasando archivos (Servidor-Recibidor)

class Servidor {

public static void main(String[] args) throws IOException 
{

Socket cs = null;
ServerSocket ss = new ServerSocket(4444);
FileOutputStream out = null;
InputStream in = null;
System.out.println("esperando que alguien entre");

	cs = ss.accept();
	in = cs.getInputStream();

	// abrir archivo de escritura
	out = new FileOutputStream("yyy");
	System.out.println("escribiendo");
	int b; long i=0;
	byte ab[] = new byte[100];
	
	while ((b= in.read(ab,0,100) ) != -1) 
	{
	out.write(ab,0,b);
	System.out.println(++i);
	}

out.close();
in.close();
cs.close(); ss.close();
System.out.println("listo");

}
}