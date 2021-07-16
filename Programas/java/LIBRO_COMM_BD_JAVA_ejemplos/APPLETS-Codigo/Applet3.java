import java.applet.Applet;
import java.awt.Label;

public class CalendarioMes extends Applet {
  
  public void start() {
    String MesSeleccionado = getParameter("Mes");
    String AnioSeleccionado = getParameter("Anio");
  
    int ValorMes = Integer.parseInt(MesSeleccionado);
    int ValorAnio = Integer.parseInt(AnioSeleccionado); 
  
    add(new Label(MesSeleccionado));    // aqui se programaria la
    add(new Label(AnioSeleccionado));   // visualizacion del calendario

  }
}
