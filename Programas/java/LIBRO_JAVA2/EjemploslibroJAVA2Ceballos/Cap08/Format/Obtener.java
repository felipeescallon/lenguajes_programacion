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

  static public String FormatoPer(String patr�n, double dato)
  {
    DecimalFormat formato = new DecimalFormat(patr�n);
    String salida = formato.format(dato);
    return salida;
  }

  static public StringBuffer AlinDer(String patr�n, double dato)
  {
    FieldPosition fp = new FieldPosition(NumberFormat.FRACTION_FIELD);
    DecimalFormat formato = new DecimalFormat(patr�n);
    StringBuffer salida = new StringBuffer();
    formato.format(dato, salida, fp);
    for (int i = 0; i < (patr�n.length() - fp.getEndIndex()); i++)
      salida.insert(0, ' ');
    return salida;
  }

  static public String FormatoPa�s(String patr�n, double dato, Locale lugar)
  {
    DecimalFormat df = (DecimalFormat)DecimalFormat.getNumberInstance(lugar);
    df.applyPattern(patr�n);
    String salida = df.format(dato);
    return salida;
  }
}
