//Title:        Terminal Modo Paquetes X.29
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Jaime Andres Cubillos Pati?o
//Company:      Cubitel
//Description:  Your description

package PAD;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.*;


public class GuiDTEB extends JFrame {
  JTextField RutaGrabarArchivo = new JTextField();
  JLabel Direccion_IP_PAD = new JLabel();
  JTextField DireccionIP = new JTextField();
  JButton Recibir = new JButton();
  JButton Conectar = new JButton();

  Socket client;
  TextArea texto;
  DataOutputStream enviar_caracteres = null;
  DataInputStream recibir_caracteres = null;
  FileInputStream fis = null;
  FileOutputStream fos = null;
  DataOutputStream aceptar1 = null;
  File file = null;
  String ruta = "";
  String Cadena = null;
  int cont=0,tiem=0;
  boolean a_tx=false;
  String tiemp=" ";
  GuiDTEB m;
  char terminal;
  boolean salir=true;
  TextArea Mensajes = new TextArea();
  JList jList1 = new JList();
  JButton GuardarComo = new JButton();
  JFileChooser fc = new JFileChooser();

  public GuiDTEB() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    Conectar.setEnabled(true);
    Recibir.setEnabled(false);
    fc.setApproveButtonText("Aceptar");
  }

  private void jbInit() throws Exception {
    RutaGrabarArchivo.setForeground(SystemColor.desktop);
    RutaGrabarArchivo.setBounds(new Rectangle(199, 64, 170, 29));
    this.setSize(new Dimension(400, 383));
    this.getContentPane().setBackground(SystemColor.desktop);
    this.setTitle("Terminal Modo Caracter");
    this.getContentPane().setLayout(null);
    this.setForeground(Color.white);
    this.setFont(new java.awt.Font("SansSerif", 1, 12));
    Direccion_IP_PAD.setFont(new java.awt.Font("SansSerif", 1, 12));
    Direccion_IP_PAD.setForeground(Color.white);
    Direccion_IP_PAD.setText("Direccion IP PAD:");
    Direccion_IP_PAD.setBounds(new Rectangle(31, 19, 107, 34));
    DireccionIP.setBounds(new Rectangle(199, 22, 170, 29));
    DireccionIP.setBounds(new Rectangle(199, 74, 170, 29));
    DireccionIP.setForeground(SystemColor.desktop);
    Recibir.setBackground(Color.lightGray);
    Recibir.setFont(new java.awt.Font("SansSerif", 1, 12));
    Recibir.setForeground(SystemColor.desktop);
    Recibir.setText("Recibir");
    Recibir.setBounds(new Rectangle(215, 134, 143, 33));
    Recibir.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        Recibir_mouseClicked(e);
      }
    });
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(204, 158, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setText("Conectar");
    Conectar.setForeground(SystemColor.desktop);
    Conectar.setFont(new java.awt.Font("SansSerif", 1, 12));
    Conectar.setBackground(Color.lightGray);
    Conectar.setBounds(new Rectangle(204, 158, 143, 33));
    Conectar.setBounds(new Rectangle(0, 0, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(204, 158, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(36, 160, 143, 33));
    Conectar.setBounds(new Rectangle(204, 158, 143, 33));
    Conectar.setBounds(new Rectangle(27, 135, 143, 33));
    Conectar.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        Conectar_mouseClicked(e);
      }
    });
    Mensajes.setBackground(Color.white);
    Mensajes.setBounds(new Rectangle(39, 214, 319, 113));
    Mensajes.setFont(new java.awt.Font("SansSerif", 0, 12));
    Mensajes.setForeground(SystemColor.desktop);
    jList1.setBounds(new Rectangle(324, 86, 0, 0));
    GuardarComo.setBackground(Color.lightGray);
    GuardarComo.setFont(new java.awt.Font("SansSerif", 1, 12));
    GuardarComo.setForeground(SystemColor.desktop);
    GuardarComo.setText("Guardar Como");
    GuardarComo.setBounds(new Rectangle(29, 61, 142, 31));
    GuardarComo.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        GuardarComo_mouseClicked(e);
      }
    });
    fc.setAutoscrolls(true);
    fc.setBackground(SystemColor.desktop);
    fc.setForeground(Color.black);
    fc.setName("");
    fc.setFont(new java.awt.Font("Dialog", 1, 12));
    fc.setApproveButtonToolTipText("Cancelar");
    fc.setCurrentDirectory(null);
    fc.setDialogTitle("Guardar Como");
    fc.setDialogType(2);
    this.getContentPane().add(DireccionIP, null);
    this.getContentPane().add(Direccion_IP_PAD, null);
    this.getContentPane().add(RutaGrabarArchivo, null);
    this.getContentPane().add(Conectar, null);
    this.getContentPane().add(Recibir, null);
    this.getContentPane().add(jList1, null);
    this.getContentPane().add(GuardarComo, null);
    this.getContentPane().add(Mensajes, null);
    DireccionIP.setBounds(new Rectangle(199, 22, 170, 29));
    DireccionIP.setBounds(new Rectangle(199, 22, 170, 29));
  }

//*****************************************************************************
//    Modificado para poder salir cuando se cierra la ventana

   protected void processWindowEvent(WindowEvent e)
   {
      super.processWindowEvent(e);

      if (e.getID() == WindowEvent.WINDOW_CLOSING)
         {
            System.exit(0);
         }
   }

//****************************************************************************
//                          EVENTOS

  void Recibir_mouseClicked(MouseEvent e) {
        recibirArchivo();
  }

  void VerArchivo_mouseClicked(MouseEvent e) {

  }

  void Conectar_mouseClicked(MouseEvent e) {
      if(cont<1)
           {
              String nombre_servidor= " ";
              nombre_servidor=DireccionIP.getText();
              conectar(nombre_servidor);
           }
        cont=cont+1;
  }

  void GuardarComo_mouseClicked(MouseEvent e) {
       int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
          {
            file = fc.getSelectedFile();
            ruta=file.getAbsolutePath();
            RutaGrabarArchivo.setText(ruta);
            System.out.println(ruta);
           }
  }

//******************************************************************************
//                      FUNCIONES PRINCIPALES

public void conectar(String server)
   {
      char a;
      try
         {
            Cadena = "Conectando al PAD...\n";
            Mensajes.setText(Cadena);

            client=new Socket(InetAddress.getLocalHost(),5000);
            enviar_caracteres=new DataOutputStream(client.getOutputStream());
            recibir_caracteres=new DataInputStream(client.getInputStream());
            enviar_solicitud_conexion();

            Conectar.setEnabled(true);
            Recibir.setEnabled(true);
         }
      catch(IOException e)
         {
            e.printStackTrace();
         }
      new Recibir1(recibir_caracteres,m).start();
   }

public void enviar_solicitud_conexion()
   {
      char a='c';
      try
         {
            enviar_caracteres.writeChar(a);
         }
      catch(IOException e)
         {
            e.printStackTrace();
         }
   }

public void recibio_solicitud_transmision()
   {
      Cadena = Cadena + "Terminal Modo Paquetes desea transmitir\n";
      Mensajes.setText(Cadena);
   }

public void recibirArchivo()
   {
      int parcial;
      String ruta="  ";
      try
         {
            ruta=RutaGrabarArchivo.getText();
            fos = new FileOutputStream(ruta);
            enviar_aceptacion();
            Cadena = Cadena + "Enviando Confirmaci?n Conexi?n en modo caracter\n";
            Mensajes.setText(Cadena);
            while ((parcial=recibir_caracteres.read())!=-1)
               {
                  fos.write(parcial);
                  //trama.setText("  "+(char) parcial+"  ");
               }
            Cadena = Cadena + "Recepcion Terminada\n";
            Mensajes.setText(Cadena);
            recibir_caracteres.close();
            fos.close();
         }
      catch(FileNotFoundException fnfe)
         {
            Cadena = Cadena + "Ruta no Valida\n";
            Mensajes.setText(Cadena);
         }
      catch(IOException e)
         {
            e.printStackTrace();
         }
   }

public void enviar_aceptacion()
   {
      char a='w';
      try
         {
            enviar_caracteres.writeChar(a);
         }
      catch(IOException e)
         {
            e.printStackTrace();
         }
   }

void actualizar(char c)
   {
      terminal=c;
   }

public void marco(GuiDTEB n)
   {
      m=n;
   }
}

//**************************************************************************
//                           HILOS

class Recibir1 extends Thread
   {
      DataInputStream rx;
      GuiDTEB ma;
      boolean salir=true;
      public Recibir1(DataInputStream r, GuiDTEB m)
         {
            rx=r;
            ma=m;
         }

   public void run()
      {
         char c='x';
         try
            {
               c=rx.readChar();
               System.out.println(c);
               ma.actualizar(c);
               if(c=='x')
                  {
                     ma.recibio_solicitud_transmision();
                  }
            }
         catch(IOException e)
            {
               e.printStackTrace();
            }
      }
}

