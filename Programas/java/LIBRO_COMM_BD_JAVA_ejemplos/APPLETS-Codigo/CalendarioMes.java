import java.applet.Applet;
import java.awt.Label;

public class CalendarioMes extends Applet {
  
  public void init() {
    String MesSeleccionado = getParameter("Mes");
    String AnioSeleccionado = getParameter("Anio");
  
    int ValorMes = Integer.parseInt(MesSeleccionado);
    int ValorAnio = Integer.parseInt(AnioSeleccionado); 
  
    add(new Label(MesSeleccionado));
    add(new Label(AnioSeleccionado));

    // aqui se programaria la visualizacion del calendario

  }
}
