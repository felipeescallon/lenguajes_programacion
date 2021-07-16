import java.util.*;
//////////////////////////////////////////////////////////////////
// Interfaz IFecha: métodos y constantes para obtener
//                  el día, mes y año
//
public interface IFecha
{
  public final static int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
  public final static int MES_DEL_AÑO = Calendar.MONTH;
  public final static int AÑO = Calendar.YEAR;
  
  public abstract int día();
  public abstract int mes();
  public abstract int año();
}
//////////////////////////////////////////////////////////////////

/*
  public int día()
  {
    GregorianCalendar fechaActual = new GregorianCalendar();
    return fechaActual.get(DIA_DEL_MES);
  }
  public int mes()
  {
    GregorianCalendar fechaActual = new GregorianCalendar();
    return fechaActual.get(MES_DEL_AÑO)+1;
  }
  public int año()
  {
    GregorianCalendar fechaActual = new GregorianCalendar();
    return fechaActual.get(AÑO);
  }
*/