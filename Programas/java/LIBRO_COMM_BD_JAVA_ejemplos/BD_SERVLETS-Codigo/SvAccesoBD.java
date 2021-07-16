import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SvAccesoBD extends HttpServlet {

 private int PosicionBuscada = 1;
 private String AreaDeListado="";
 PrintWriter out;

 public void doGet(HttpServletRequest request, HttpServletResponse response)
 throws IOException, ServletException  {

   String DNI = request.getParameter("DNI");
   String Nombre = request.getParameter("Nombre");
   String Apellido = request.getParameter("Apellido");
   String Edad = request.getParameter("Edad");

   String BotonConsultar = request.getParameter("BotonConsultar");
   String BotonInsertar = request.getParameter("BotonInsertar");
   String BotonModificar = request.getParameter("BotonModificar");
   String BotonBorrar = request.getParameter("BotonBorrar");
   String BotonListar = request.getParameter("BotonListar");

   String BotonPrimero = request.getParameter("BotonPrimero");
   String BotonMenosCinco = request.getParameter("BotonMenosCinco");
   String BotonAnterior = request.getParameter("BotonAnterior");
   String BotonSiguiente = request.getParameter("BotonSiguiente");
   String BotonMasCinco = request.getParameter("BotonMasCinco");
   String BotonUltimo = request.getParameter("BotonUltimo");

   AreaDeListado = request.getParameter("AreaDeListado");



        response.setContentType("text/html");
        out = response.getWriter();
        out.println("<html>");
        out.println("<body>");

        if (BotonConsultar!=null)
          ConsultarDatos(DNI);
        if (BotonInsertar!=null)
          InsertarDatos(DNI,Nombre,Apellido,Edad);
        if (BotonModificar!=null)
          ModificarDatos(DNI,Nombre,Apellido,Edad);
        if (BotonBorrar!=null)
          BorrarDatos(DNI);
        if (BotonListar!=null)
          ListarDatos(DNI);

        if (BotonPrimero!=null)
          Controles("1");
        if (BotonMenosCinco!=null)
          Controles("2");
        if (BotonAnterior!=null)
          Controles("3");
        if (BotonSiguiente!=null)
          Controles("4");
        if (BotonMasCinco!=null)
          Controles("5");
        if (BotonUltimo!=null)
          Controles("0");
  
 
        out.println("</body>");
        out.println("</html>");
  }



     private void ConsultarDatos(String DNI) {
        ObConsulta InstanciaConsulta = new ObConsulta(DNI); 
        PosicionBuscada = InstanciaConsulta.PosicionEncontrada(); 
        if (PosicionBuscada!=0) {
          ObRegistro InstanciaFila = InstanciaConsulta.DameDatos();
          GenerarRespuesta(InstanciaFila.DameDNI(),InstanciaFila.DameNombre(),
                           InstanciaFila.DameApellido(),
                           String.valueOf(InstanciaFila.DameEdad()));
        } else {
          GenerarRespuesta(DNI,"----","-----","---"); 
          PosicionBuscada = 1;  
       }
    }


    public void ListarDatos(String DNI) {
      ObListado InstanciaListado = new ObListado();
      AreaDeListado = InstanciaListado.DameResultado(); 
      ConsultarDatos(DNI);
      PosicionBuscada = 1;
    }


  public void BorrarDatos(String DNI){
    ObConsulta InstanciaConsulta = new ObConsulta(DNI); 
    PosicionBuscada = InstanciaConsulta.PosicionEncontrada(); 

    if (PosicionBuscada!=0) {
      ObBorrado InstanciaBorrado = new ObBorrado(PosicionBuscada);
      GenerarRespuesta("","","",""); 
    } else {
      GenerarRespuesta("----","----","----","----");  
    }
    PosicionBuscada = 1;
  }


  public void ModificarDatos(String DNI, String Nombre, String Apellido, String Edad) {
    ObConsulta InstanciaConsulta = new ObConsulta(DNI); 
    PosicionBuscada = InstanciaConsulta.PosicionEncontrada(); 

    if (PosicionBuscada!=0) {
      ObRegistro InstanciaFila = new ObRegistro(DNI, Nombre, Apellido, Integer.parseInt(Edad));
      ObModificacion InstanciaModificacion = new ObModificacion(PosicionBuscada, InstanciaFila);
      GenerarRespuesta(DNI,Nombre,Apellido,Edad); 
    } else {
      GenerarRespuesta("----","----","----","----");
      PosicionBuscada = 1;  
    } 
  }


  public void InsertarDatos(String DNI, String Nombre, String Apellido, String Edad) {
    ObRegistro InstanciaFila = new ObRegistro(DNI, Nombre, Apellido, Integer.parseInt(Edad));
    ObInserccion InstanciaInserccion = new ObInserccion(InstanciaFila); 
    GenerarRespuesta("","","",""); 
    PosicionBuscada=1; 
  }


  public void Controles(String Identificador) {
    ObPosicionamiento InstanciaPosicionamiento = new ObPosicionamiento(Identificador,PosicionBuscada);
    ObRegistro InstanciaFila = InstanciaPosicionamiento.DameDatos();
    GenerarRespuesta(InstanciaFila.DameDNI(),InstanciaFila.DameNombre(),
                     InstanciaFila.DameApellido(),
                     String.valueOf(InstanciaFila.DameEdad()));

    PosicionBuscada = InstanciaPosicionamiento.Posicion();    
  }


    private void GenerarRespuesta(String DNI, String Nombre, String Apellido, String Edad) {
     out.println("<table border=1>");  
     out.println("<tr>");
     out.println("<td>");    
     out.println("<form action='http://localhost:8080/examples/servlet/SvAccesoBD' method='get'>");
     out.println(" DNI: <input type='text' name='DNI' value="+DNI+"><br>");
     out.println(" Nombre: <input type='text' name='Nombre' value="+Nombre+"><br>");
     out.println(" Apellido: <input type='text' name='Apellido' value="+Apellido+"><br>");
     out.println(" Edad: <input type='text' name='Edad' value="+Edad+"><br><br>");

     out.println(" <input type='submit' value='Consultar' name='BotonConsultar'>");
     out.println("  <input type='submit' value='Insertar' name='BotonInsertar'>");
     out.println("  <input type='submit' value='Modificar' name='BotonModificar'>");
     out.println("  <input type='submit' value='Borrar' name='BotonBorrar'>");
     out.println("  <input type='submit' value='Listar' name='BotonListar'> <br>");
     out.println("  <center>");      
     out.println("  <input type='submit' value='   |<   ' name='BotonPrimero'>");
     out.println("  <input type='submit' value='   --   ' name='BotonMenosCinco'>");
     out.println("  <input type='submit' value='   -   ' name='BotonAnterior'>");
     out.println("  <input type='submit' value='   +   ' name='BotonSiguiente'>");
     out.println("  <input type='submit' value='   ++   ' name='BotonMasCinco'>");
     out.println("  <input type='submit' value='   >|   ' name='BotonUltimo'>");
     out.println("  </center>");
     out.println(" </td>");
     out.println(" <td>");
     out.println("  <textarea name='AreaDeListado' rows='10' cols='35'>"+AreaDeListado+"</textarea>");
     out.println(" </td>");
     out.println(" </tr>");
     out.println(" </form>");
     out.println(" </table>");  
    }


}