import java.util.*;
/////////////////////////////////////////////////////////////////
// Definición de la clase CFecha
//
public class CFecha
{
  // Atributos
  private int día, mes, año;
  
  // Métodos
  protected boolean bisiesto()
  {
    return ((año % 4 == 0) && (año % 100 != 0) || (año % 400 == 0));
  }
  
  public void asignarFecha()
  {
    // Asignar, por omisión, la fecha actual.
    GregorianCalendar fechaActual = new GregorianCalendar();
    día = fechaActual.get(Calendar.DAY_OF_MONTH);
    mes = fechaActual.get(Calendar.MONTH)+1;
    año = fechaActual.get(Calendar.YEAR);
  }
  
  public void asignarFecha(int dd)
  {
    asignarFecha(); // asignar fecha actual
    día = dd;
  }
  
  public void asignarFecha(int dd, int mm)
  {
    asignarFecha(); // asignar fecha actual
    día = dd; mes = mm;
  }
  
  public void asignarFecha(int dd, int mm, int aaaa)
  {
    día = dd; mes = mm; año = aaaa;
  }

  public void obtenerFecha(int[] fecha)
  {
    fecha[0] = día;
    fecha[1] = mes;
    fecha[2] = año;
  }
  
  public boolean fechaCorrecta()
  {
    boolean díaCorrecto, mesCorrecto, añoCorrecto;
    // ¿año correcto?
    añoCorrecto = (año >= 1582);
    // ¿mes correcto?
    mesCorrecto = (mes >= 1) && (mes <= 12);
    switch (mes)
    // ¿día correcto?
    {
      case 2:
        if (bisiesto())
          díaCorrecto = (día >= 1 && día <= 29);
        else
          díaCorrecto = (día >= 1 && día <= 28);
        break;
      case 4: case 6: case 9: case 11:
        díaCorrecto = (día >= 1 && día <= 30);
        break;
      default:
        díaCorrecto = (día >= 1 && día <= 31);
    }
    return díaCorrecto && mesCorrecto && añoCorrecto;
  }
}
