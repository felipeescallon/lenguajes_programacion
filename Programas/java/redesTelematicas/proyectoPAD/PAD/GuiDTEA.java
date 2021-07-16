//Title:        Terminal Modo Paquetes
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Jaime Andrés Cubillos
//Company:      CUBITEL
//Description:  Your description

package PAD;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import javax.swing.event.*;


public class GuiDTEA extends JFrame {
  JButton Conectar = new JButton();
  JButton Enviar = new JButton();

  JLabel Direccion_IP_PAD = new JLabel();
  JTextField DireccionIP = new JTextField();

  JLabel Nombre_Archivo = new JLabel();
  JTextField RutaArchivo = new JTextField();

  TitledBorder titledBorder1;

   //  Se declaran las entradas y salidas de streams
   //  que permiten manipular los archivos.

   Socket client;
   DataOutputStream enviar_paquetes = null;
   DataInputStream recibir_paquetes = null;
   FileInputStream fis = null;
   FileOutputStream fos = null;
   File file = null;
   PrintStream ps=null;
   Retardo t;
   GuiDTEA m;
   String ruta = "";
   boolean a_tx=false,salir=true;
   int tiem=5;
   char terminal;
   int cont=0;
   String Cadena=null;
   TextArea Mensajes = new TextArea();
   JFileChooser fc = new JFileChooser();
   JButton SeleccionarArchivo = new JButton();
   JButton SolicitudConexion = new JButton();

   //*****************************************************************************
//                          CONSTRUCTOR

  public GuiDTEA() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    Conectar.setEnabled(true);
    Enviar.setEnabled(false);
    SolicitudConexion.setEnabled(false);
    SeleccionarArchivo.setEnabled(false);
    fc.setApproveButtonText("Aceptar");
     }

//*****************************************************************************

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    this.setSize(new Dimension(440, 383));
    Conectar.setBackground(Color.lightGray);
    Conectar.setFont(new java.awt.Font("SansSerif", 1, 12));
    Conectar.setForeground(SystemColor.desktop);
    Conectar.setMaximumSize(new Dimension(69, 27));
    Conectar.setMinimumSize(new Dimension(69, 27));
    Conectar.setPreferredSize(new Dimension(69, 27));
    Conectar.setText("Conectar");
    Conectar.setBounds(new Rectangle(267, 26, 148, 30));
    Conectar.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        Conectar_actionPerformed(e);
      }
    });

    this.getContentPane().setLayout(null);
    this.getContentPane().setBackground(SystemColor.desktop);
    this.setTitle("Terminal Modo Paquetes DTE-A");
    this.setFont(new java.awt.Font("SansSerif", 1, 12));
    Enviar.setBackground(Color.lightGray);
    Enviar.setFont(new java.awt.Font("SansSerif", 1, 12));
    Enviar.setForeground(SystemColor.desktop);
    Enviar.setToolTipText("");
    Enviar.setText("Enviar");
    Enviar.setBounds(new Rectangle(255, 140, 100, 34));
    Enviar.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        Enviar_actionPerformed(e);
      }
    });
    DireccionIP.setForeground(SystemColor.desktop);
    DireccionIP.setBounds(new Rectangle(123, 9, 112, 30));
    Direccion_IP_PAD.setFont(new java.awt.Font("SansSerif", 1, 12));
    Direccion_IP_PAD.setForeground(Color.white);
    Direccion_IP_PAD.setText("Direccion IP PAD:");
    Direccion_IP_PAD.setBounds(new Rectangle(13, 7, 110, 37));
    RutaArchivo.setForeground(SystemColor.desktop);
    RutaArchivo.setBounds(new Rectangle(119, 74, 115, 31));
    Nombre_Archivo.setFont(new java.awt.Font("SansSerif", 1, 12));
    Nombre_Archivo.setForeground(Color.white);
    Nombre_Archivo.setText("Archivo a Tx:");
    Nombre_Archivo.setBounds(new Rectangle(9, 75, 101, 28));
    Direccion_IP_PAD.setBounds(new Rectangle(15, 8, 110, 37));
    Direccion_IP_PAD.setBounds(new Rectangle(15, 13, 110, 37));
    Direccion_IP_PAD.setText("Direccion IP PAD:");
    Direccion_IP_PAD.setForeground(Color.white);
    Direccion_IP_PAD.setFont(new java.awt.Font("SansSerif", 1, 12));
    DireccionIP.setBounds(new Rectangle(120, 11, 112, 30));
    DireccionIP.setForeground(SystemColor.desktop);
    DireccionIP.setBounds(new Rectangle(121, 15, 112, 30));
    DireccionIP.setBounds(new Rectangle(120, 11, 112, 30));
    DireccionIP.setBounds(new Rectangle(121, 23, 112, 30));
    Mensajes.setBackground(Color.white);
    Mensajes.setBounds(new Rectangle(27, 209, 371, 120));
    Mensajes.setFont(new java.awt.Font("SansSerif", 0, 12));
    Mensajes.setForeground(SystemColor.desktop);
    SeleccionarArchivo.setBackground(Color.lightGray);
    SeleccionarArchivo.setFont(new java.awt.Font("SansSerif", 1, 12));
    SeleccionarArchivo.setForeground(SystemColor.desktop);
    SeleccionarArchivo.setText("Seleccionar Archivo");
    SeleccionarArchivo.setBounds(new Rectangle(265, 75, 151, 32));
    SeleccionarArchivo.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        SeleccionarArchivo_mouseClicked(e);
      }
    });
    SolicitudConexion.setBackground(Color.lightGray);
    SolicitudConexion.setFont(new java.awt.Font("SansSerif", 1, 12));
    SolicitudConexion.setForeground(SystemColor.desktop);
    SolicitudConexion.setText("Solicitud Conexion");
    SolicitudConexion.setBounds(new Rectangle(68, 141, 151, 34));
    SolicitudConexion.addMouseListener(new java.awt.event.MouseAdapter() {

      public void _mouseClicked(MouseEvent e) {
        SolicitudConexion_mouseClicked(e);
      }

      public void mouseClicked(MouseEvent e) {
        SolicitudConexion_mouseClicked(e);
      }
    });
    fc.setAutoscrolls(true);
    fc.setBackground(SystemColor.desktop);
    fc.setForeground(Color.black);
    fc.setName("");
    fc.setFont(new java.awt.Font("Dialog", 1, 12));
    fc.setApproveButtonToolTipText("Cancelar");
    fc.setCurrentDirectory(null);
    fc.setDialogTitle("Seleccionar Archivo");
    fc.setDialogType(2);
    this.getContentPane().add(DireccionIP, null);
    this.getContentPane().add(Direccion_IP_PAD, null);
    this.getContentPane().add(RutaArchivo, null);
    this.getContentPane().add(Nombre_Archivo, null);
    this.getContentPane().add(Conectar, null);
    this.getContentPane().add(DireccionIP, null);
    this.getContentPane().add(Direccion_IP_PAD, null);
    this.getContentPane().add(SeleccionarArchivo, null);
    this.getContentPane().add(SolicitudConexion, null);
    this.getContentPane().add(Enviar, null);
    this.getContentPane().add(Mensajes, null);
    Direccion_IP_PAD.setBounds(new Rectangle(15, 8, 110, 37));
    Direccion_IP_PAD.setBounds(new Rectangle(16, 20, 110, 37));
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
//                        EVENTOS

  void Conectar_actionPerformed(ActionEvent e) {
  if(cont<1)
         {
            String nombre_servidor= " ";
            nombre_servidor=DireccionIP.getText();
            conectar(nombre_servidor);
            Conectar.setEnabled(true);
            Enviar.setEnabled(true);
            SolicitudConexion.setEnabled(true);
            SeleccionarArchivo.setEnabled(true);
         }
      cont=cont+1;
  }


  void SolicitudConexion_mouseClicked(MouseEvent e) {
      Cadena=Cadena + "Enviando solicitud de transmision de paquetes\n";
      Mensajes.setText(Cadena);
      Conectar.setEnabled(true);
      Enviar.setEnabled(true);
      SolicitudConexion.setEnabled(true);
      enviar_solicitud_transmision();

  }


  void Enviar_actionPerformed(ActionEvent e) {
      if(a_tx==true)
         {
            enviarArchivo();
         }
  }

void SeleccionarArchivo_mouseClicked(MouseEvent e) {
     Enviar.setEnabled(true);
     int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
          {
            file = fc.getSelectedFile();
            ruta=file.getAbsolutePath();
            RutaArchivo.setText(ruta);
           }
  }

//****************************************************************************
//                        FUNCIONES PRINCIPALES

   public void conectar(String server)
{
   char a;

   try
      {
         Cadena = "Conectando al PAD...\n";
         Mensajes.setText(Cadena);
         client=new Socket(InetAddress.getLocalHost(),5000);
         ps=new PrintStream(client.getOutputStream());
         recibir_paquetes=new DataInputStream(client.getInputStream());
         enviar_paquetes=new DataOutputStream(client.getOutputStream());
         t=new Retardo();
         enviar_solicitud_conexion();
      }

   catch(IOException e)
      {
         System.out.println("Nombre incorrecto del Archivo");
         e.printStackTrace();
      }

   new Recibir2(recibir_paquetes,m).start();
}

public void enviar_solicitud_conexion()
{
    char a='p';
    try
       {
          enviar_paquetes.writeChar(a);
       }
    catch(IOException e)
       {
          e.printStackTrace();
       }
}

public void enviar_solicitud_transmision()
{
    char a='s';
    try
       {
          System.out.println(a);
          enviar_paquetes.writeChar(a);
       }
    catch(IOException e)
       {
          System.out.println("Jio");
          e.printStackTrace();
       }
}

/*public void enviar_otro()
{
    char a='j';
    try
       {
          System.out.println(a);
          enviar_paquetes.writeChar(a);
       }
    catch(IOException e)
       {
          e.printStackTrace();
       }
}*/


public void recibi_aceptacion()
{
   Cadena = Cadena + "Recepción Confirmación Conexión Terminal DTE-B\n";
   Mensajes.setText(Cadena);
   a_tx=true;
}

public void enviarArchivo()
{
   int parcial,cont=0,longitud=0,i;
   String trama1="   ";
   StringBuffer paquete = new StringBuffer(32);
   char a;
   //enviar_otro();

   if(a_tx==true)
      {
         try
            {
               System.out.println(ruta);
               fis = new FileInputStream(ruta);
               Cadena = Cadena + "Enviando datos al PAD...\n";
               Mensajes.setText(Cadena);

               while (( parcial=fis.read())!=-1)
                  {
                     cont+=1;
                     a=(char)parcial;
                     paquete.append(a);
                     if(cont==32){
                     trama1=empaquetar(paquete,33);
                     t.run(tiem);
                     ps.print(trama1);
                     cont=0;
                     paquete=new StringBuffer("");
                  }
            }

         longitud=paquete.length();
         trama1=empaquetar(paquete,longitud+1);
         t.run(tiem);
         ps.print(trama1);
         Cadena = Cadena + "Transmision Terminada\n";
         Mensajes.setText(Cadena);
         fis.close();
         ps.close();
      }

      catch(FileNotFoundException fnfe)
         {
            Cadena = Cadena + "Archivo no encontrado\n";
            Mensajes.setText(Cadena);
         }

      catch(IOException e)
         {
            e.printStackTrace();
         }
}

}

public String empaquetar(StringBuffer s,int i)
{
   String x=s.toString();
   StringBuffer paquete = new StringBuffer(x);
   paquete.insert(0,"H");
   paquete.insert(i,"T");
   return paquete.toString();
}

//******************************************************************************
//                              ZIZU

public String des_empaquetar(StringBuffer s,int i)
{
   char a;
   int j;
   StringBuffer aux=new StringBuffer("");
   String x=s.toString();

   for(j=1;j<i;j++)
      {
         a=s.charAt(j);
         aux.append(a);}
         return aux.toString();
      }

   void actualizar(char c)
      {
         terminal=c;
      }

   public void marco(GuiDTEA n)
      {
         m=n;
      }

}

//******************************************************************************

class Retardo extends Thread
{

   public void run(int a)
      {
         try
            {
               sleep(200*a);
            }

         catch (InterruptedException e) {}
      }
}

class Recibir2 extends Thread
{
   DataInputStream rx;
   GuiDTEA ma;
   boolean salir=true;

   public Recibir2(DataInputStream r,GuiDTEA m)
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
               ma.actualizar(c);
               if(c=='q')
                  {
                  ma.recibi_aceptacion();
                  }
            }

         catch(IOException e)
            {
               e.printStackTrace();
            }
      }
}

//***************************************************************************
