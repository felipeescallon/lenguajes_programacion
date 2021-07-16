import java.util.*;
import java.text.*;

public class Obtener
{
  static public String FormatoLocal(double dato)
  {
    NumberFormat formato = NumberFormat.getCurrencyInstance();
    String salida = formato.format(dato);
    return salida;
  }

  static public String FormatoPer(String patrón, double dato)
  {
    DecimalFormat formato = new DecimalFormat(patrón);
    String salida = formato.format(dato);
    return salida;
  }

  static public StringBuffer AlinDer(String patrón, double dato)
  {
    FieldPosition fp = new FieldPosition(NumberFormat.FRACTION_FIELD);
    DecimalFormat formato = new DecimalFormat(patrón);
    StringBuffer salida = new StringBuffer();
    formato.format(dato, salida, fp);
    for (int i = 0; i < (patrón.length() - fp.getEndIndex()); i++)
      salida.insert(0, ' ');
    return salida;
  }

  static public String FormatoPaís(String patrón, double dato, Locale lugar)
  {
    DecimalFormat df = (DecimalFormat)DecimalFormat.getNumberInstance(lugar);
    df.applyPattern(patrón);
    String salida = df.format(dato);
    return salida;
  }
}
