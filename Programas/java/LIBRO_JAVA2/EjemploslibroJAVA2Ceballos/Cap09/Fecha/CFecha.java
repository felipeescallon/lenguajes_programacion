import java.util.*;
/////////////////////////////////////////////////////////////////
// Definici�n de la clase CFecha
//
public class CFecha
{
  // Atributos
  private int d�a, mes, a�o;
  
  // M�todos
  protected boolean bisiesto()
  {
    return ((a�o % 4 == 0) && (a�o % 100 != 0) || (a�o % 400 == 0));
  }
  
  public void asignarFecha()
  {
    // Asignar, por omisi�n, la fecha actual.
    GregorianCalendar fechaActual = new GregorianCalendar();
    d�a = fechaActual.get(Calendar.DAY_OF_MONTH);
    mes = fechaActual.get(Calendar.MONTH)+1;
    a�o = fechaActual.get(Calendar.YEAR);
  }
  
  public void asignarFecha(int dd)
  {
    asignarFecha(); // asignar fecha actual
    d�a = dd;
  }
  
  public void asignarFecha(int dd, int mm)
  {
    asignarFecha(); // asignar fecha actual
    d�a = dd; mes = mm;
  }
  
  public void asignarFecha(int dd, int mm, int aaaa)
  {
    d�a = dd; mes = mm; a�o = aaaa;
  }

  public void obtenerFecha(int[] fecha)
  {
    fecha[0] = d�a;
    fecha[1] = mes;
    fecha[2] = a�o;
  }
  
  public boolean fechaCorrecta()
  {
    boolean d�aCorrecto, mesCorrecto, a�oCorrecto;
    // �a�o correcto?
    a�oCorrecto = (a�o >= 1582);
    // �mes correcto?
    mesCorrecto = (mes >= 1) && (mes <= 12);
    switch (mes)
    // �d�a correcto?
    {
      case 2:
        if (bisiesto())
          d�aCorrecto = (d�a >= 1 && d�a <= 29);
        else
          d�aCorrecto = (d�a >= 1 && d�a <= 28);
        break;
      case 4: case 6: case 9: case 11:
        d�aCorrecto = (d�a >= 1 && d�a <= 30);
        break;
      default:
        d�aCorrecto = (d�a >= 1 && d�a <= 31);
    }
    return d�aCorrecto && mesCorrecto && a�oCorrecto;
  }
}
