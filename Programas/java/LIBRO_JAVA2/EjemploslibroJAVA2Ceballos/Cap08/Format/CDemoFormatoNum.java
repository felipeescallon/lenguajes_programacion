import java.io.*;
import java.util.*;

public class CDemoFormatoNum
{
  static public void main(String[] args)
  {
    PrintStream flujoS = System.out;
    
    flujoS.println(Obtener.FormatoLocal(123456));
    flujoS.println(Obtener.FormatoLocal(123456.789));
    flujoS.println(Obtener.FormatoLocal(123.45));
    flujoS.println();

    flujoS.println(Obtener.FormatoPer("###,###.##", 123456));
    flujoS.println(Obtener.FormatoPer("########", 123456));
    flujoS.println(Obtener.FormatoPer("###.##", 123456.789));
    flujoS.println(Obtener.FormatoPer("000000.000", 123.45));
    flujoS.println(Obtener.FormatoPer("$###,###.###", 12345.67));
    flujoS.println(Obtener.FormatoPer("###,###.###", 12.34));
    flujoS.println();

    String patr�n = new String("###,###,##0.00");
    flujoS.println(Obtener.AlinDer(patr�n, 1.234));
    flujoS.println(Obtener.AlinDer(patr�n, 12.345));
    flujoS.println(Obtener.AlinDer(patr�n, -123.456));
    flujoS.println(Obtener.AlinDer(patr�n, 123.456));
    flujoS.println(Obtener.AlinDer(patr�n, 1234.567));
    flujoS.println(Obtener.AlinDer(patr�n, 12345.678));
    flujoS.println(Obtener.AlinDer(patr�n, -12345));
    flujoS.println();

    Locale[] pa�s =
    {
      new Locale("es", "ES"),
      new Locale("en", "US"),
    };
    for (int i = 0; i < pa�s.length; i++)
      flujoS.println(Obtener.FormatoPa�s("###,###.###", 123456.789, pa�s[i]));
  }
}
