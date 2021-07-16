import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUISistemaDeFicheros {

  private Choice Ficheros;
  private File Fichero;
  Checkbox PermisoEscritura;
  Checkbox PermisoLectura;
  Checkbox EsDirectorio;
  Checkbox EstaOculto;

  GUISistemaDeFicheros() {
    Frame MiMarco = new Frame();
    Panel MiPanel = new Panel(new GridLayout(1,2));
    Panel PanelInformacion = new Panel(new GridLayout(4,1));
    PanelInformacion.setBackground(Color.green);
    PermisoEscritura = new Checkbox("Permiso de escritura");
    PermisoEscritura.setEnabled(false);
    PanelInformacion.add(PermisoEscritura);
    PermisoLectura = new Checkbox("Permiso de lectura");
    PermisoLectura.setEnabled(false);
    PanelInformacion.add(PermisoLectura);
    EsDirectorio = new Checkbox("Directorio");
    EsDirectorio.setEnabled(false);
    PanelInformacion.add(EsDirectorio);
    EstaOculto = new Checkbox("Oculto");
    EstaOculto.setEnabled(false);
    PanelInformacion.add(EstaOculto );

    Ficheros = new Choice();
    Ficheros.setBackground(Color.green);

    Fichero = new File("c:/");
    EstableceLista(Fichero.list());
    Ficheros.addItemListener(new ElementoSeleccionado());

    MiPanel.add(Ficheros);
    MiPanel.add(PanelInformacion);
    MiMarco.add(MiPanel);
    MiMarco.setSize(400,200);
    MiMarco.setTitle("Navegación por el sistema de ficheros");  
    MiMarco.setVisible(true);
  }

  private void EstableceLista(String[] NombreFicheros) {
    Ficheros.removeAll();
    if (Fichero.getParent()!=null)
      Ficheros.add("../");
    for (int i=0;i<NombreFicheros.length;i++)
      Ficheros.add(NombreFicheros[i]);
  }

  private class ElementoSeleccionado implements ItemListener {
    public void itemStateChanged(ItemEvent Evento){
      String Camino=Fichero.getAbsolutePath()+"/"+Ficheros.getSelectedItem();
      Fichero = new File(Camino);
      if (Fichero.isDirectory()){
        EstableceLista(Fichero.list());
        Ficheros.setBackground(Color.green);
      }
      else
        Ficheros.setBackground(Color.orange);

      PermisoEscritura.setState(Fichero.canWrite());
      PermisoLectura.setState(Fichero.canRead()); 
      EsDirectorio.setState(Fichero.isDirectory()); 
      EstaOculto.setState(Fichero.isHidden());  
    }

  }

}