import java.io.*;
import java.text.*;
import java.util.*;

public class CDemoFormatoFechaHora
{
  static String FechaLocal(Date fechaHora)
  {
    String salida;
    DateFormat formato = DateFormat.getDateInstance();
    salida = formato.format(fechaHora);
    return salida;
  }
  
  static String HoraLocal(Date fechaHora)
  {
    String salida;
    DateFormat formato = DateFormat.getTimeInstance();
    salida = formato.format(fechaHora);
    return salida;
  }
  
  static public String FechaHoraPer(String patrón, Date fechaHora)
  {
    SimpleDateFormat formato = new SimpleDateFormat(patrón);
    String salida = formato.format(fechaHora);
    return salida;
  }

  static public void main(String[] args)
  {
    PrintStream flujoS = System.out;
    Date hoy = new Date();    flujoS.println(FechaLocal(hoy));
    flujoS.println(HoraLocal(hoy));
    flujoS.println(FechaHoraPer("EEEE dd-MMM-yyyy, HH:mm:ss", hoy));
    
    Object[] argumentos = {new Long(1234), "C:", new Long(125)};
    MessageFormat mensaje = new MessageFormat("Fueron verificados {0} " + 
                            "ficheros de la unidad {1} en {2} segundos");
    System.out.println(mensaje.format(argumentos));
  }
}
