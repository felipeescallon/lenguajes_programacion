// Citas.java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Citas extends HttpServlet implements SingleThreadModel
{
  String dirDelFichResultante = null;

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
    // Parámetro establecido en el fichero servlets.properties
    // del servidor
    dirDelFichResultante = getInitParameter("dirDelFichResultante");
    if (dirDelFichResultante == null)
    {
      System.err.println("Debe existir un parámetro");
    }
  }

  // Método doPost para responder a una petición POST
  public void doPost(HttpServletRequest req,
                     HttpServletResponse res)
      throws ServletException, IOException
  {	
    // Tipo de la respuesta que será enviada al cliente
    res.setContentType("text/html");

    // Obtener un 'PrintWriter' para devolver una respuesta
    // al solicitante
    PrintWriter solicitante = res.getWriter();
    try
    {
      // Abrir el fichero para el registro de citas
      FileWriter fw = new FileWriter(dirDelFichResultante
        + System.getProperty("file.separator")
        + "citas.txt", true);
      PrintWriter fichCitas = new PrintWriter(fw);

      // Tomar los datos recibidos del cliente y escribirlos
      // en el fichero. Delimitamos los registros por las marcas
      // <INICIO> y <FIN> para su posterior identificación.
      fichCitas.println("<INICIO>");
      Enumeration nombresParams = req.getParameterNames();
      while (nombresParams.hasMoreElements())
      {
        String nombre = (String)nombresParams.nextElement();
        String valor = req.getParameterValues(nombre)[0];
        fichCitas.println(nombre + ": " + valor);
      }
      fichCitas.println("<FIN>");

      // Cerrar el fichero
      fichCitas.close(); 
      fw.close();

      // Responder al solicitante
      solicitante.println("<html>");
      solicitante.println("<title>Respuesta a la solicitud</title>");
      solicitante.println("Su cita ha sido registrada<BR>Un saludo");
      solicitante.println("</html>");

    }
    catch(IOException e)
    {
      solicitante.println("Hubo problemas cursando su solicitud." +
                          "<BR>Por favor, inténtelo otra vez.");
    }
    // Cerrar el flujo
    solicitante.close();
  }

  // Método doGet para responder a una petición GET
  public void doGet(HttpServletRequest req,
                    HttpServletResponse res)
      throws ServletException, IOException
  {
    // Escriba aquí su código
  }	

  public void destroy()
  {
    // Liberar recursos
  }	
}
