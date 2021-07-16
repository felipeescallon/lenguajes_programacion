//Title:        Terminal Modo Paquetes X.29
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Jaime Andres Cubillos Patiño
//Company:      Cubitel
//Description:  Your description

package PAD;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.border.*;
import javax.swing.event.*;

public class GuiPAD extends JFrame {
  JTextField FormatoPaquetes = new JTextField();
  JTextField FormatoTramas = new JTextField();
  JLabel FormatoPaquetesDTEA = new JLabel();
  JLabel FormatoTramasDTEB = new JLabel();
  TextArea Mensajes = new TextArea();

  String a;
  DTE terminales[];
  Socket coneccion;
  ServerSocket server;
  PrintStream ps=null;
  DataInputStream rx_info_paquetes = null;
  DataInputStream rx_info_caracteres= null;
  DataOutputStream tx_info_paquetes = null;
  DataOutputStream tx_info_caracteres = null;
  PrintStream enviar_paquete=null;
  int contador=0;
  char identificacion,aux=' ',terminal;
  boolean salir=false;
  boolean esperar=true,escoger=true;
  String Cadena = null;
  GuiPAD m;
  HiloPrincipal t = new HiloPrincipal();
  Recibir t1;
  Recibir t2;

  public GuiPAD() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setSize(new Dimension(426, 305));
    this.getContentPane().setBackground(SystemColor.desktop);
    this.setTitle("Terminal Ensamblador/Desemsamblador de Paquetes PAD");
    this.getContentPane().setLayout(null);
    this.setFont(new java.awt.Font("SansSerif", 1, 12));
    FormatoPaquetes.setBounds(new Rectangle(196, 19, 157, 27));
    FormatoPaquetes.setForeground(SystemColor.desktop);
    FormatoPaquetes.setBounds(new Rectangle(197, 71, 157, 27));
    FormatoPaquetes.setBounds(new Rectangle(196, 19, 157, 27));
    FormatoPaquetes.setBounds(new Rectangle(175, 70, 231, 27));
    FormatoTramas.setBounds(new Rectangle(196, 19, 157, 27));
    FormatoTramas.setForeground(SystemColor.desktop);
    FormatoTramas.setBounds(new Rectangle(196, 123, 157, 27));
    FormatoTramas.setBounds(new Rectangle(196, 19, 157, 27));
    FormatoTramas.setBounds(new Rectangle(174, 20, 235, 27));
    FormatoPaquetesDTEA.setBounds(new Rectangle(30, 20, 146, 27));
    FormatoPaquetesDTEA.setBounds(new Rectangle(30, 72, 146, 27));
    FormatoPaquetesDTEA.setText("Formato Paquetes DTE-A:");
    FormatoPaquetesDTEA.setForeground(Color.white);
    FormatoPaquetesDTEA.setFont(new java.awt.Font("SansSerif", 1, 12));
    FormatoTramasDTEB.setBounds(new Rectangle(30, 20, 146, 27));
    FormatoTramasDTEB.setBounds(new Rectangle(29, 123, 146, 27));
    FormatoTramasDTEB.setText("Formato Tramas DTE-B:");
    FormatoTramasDTEB.setForeground(Color.white);
    FormatoTramasDTEB.setFont(new java.awt.Font("SansSerif", 1, 12));
    Mensajes.setBackground(Color.white);
    Mensajes.setBounds(new Rectangle(15, 129, 385, 125));
    Mensajes.setForeground(SystemColor.desktop);
    this.getContentPane().add(FormatoTramasDTEB, null);
    this.getContentPane().add(FormatoPaquetesDTEA, null);
    this.getContentPane().add(FormatoTramas, null);
    this.getContentPane().add(FormatoPaquetes, null);
    this.getContentPane().add(Mensajes, null);
    FormatoPaquetesDTEA.setBounds(new Rectangle(30, 20, 146, 27));
    FormatoPaquetesDTEA.setBounds(new Rectangle(13, 69, 146, 27));
    FormatoTramasDTEB.setBounds(new Rectangle(30, 20, 146, 27));
    FormatoTramasDTEB.setBounds(new Rectangle(16, 20, 146, 27));
  }

//***************************************************************************
//           Modificado para poder salir cuando se cierra la ventana

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }


//***************************************************************************
//                     FUNCIONES PRINCIPALES

/* la siguiente funciòn permite la conexion de 2 terminales
a un equipo central, identificando el terminal que se conecta.*/



public void conectar()
   {
      char b='x',h=' ';
      try
         {
            terminales=new DTE[2];
            server=new ServerSocket(5000,100);    //Apertura del socket Servidor
            Cadena="Esperando conexion...\n";
            Mensajes.setText(Cadena);

            for(int i=0;i<terminales.length;i++)
               {
                  terminales[i]=new DTE(server.accept()); //Se espera la conexiòn de 2 terminales.
                  if(i==0)
                     {
                        rx_info_paquetes=terminales[0].devolver_recibir();
                        identificacion=rx_info_paquetes.readChar();
                        aux=identificacion;                          //y su modo de trabajo.
                        if(aux=='c')
                           {
                              //FormatoTramas.setText("HX.28-Relleno-TX.28");
                              Cadena=Cadena + "Terminal Modo Caracteres Conectado\n";
                              Mensajes.setText(Cadena);
                           }
                        else
                           {
                              Cadena=Cadena + "Terminal Modo Paquetes Conectado\n";
                              Mensajes.setText(Cadena);
                           }
                     }
                  if(i==1)
                     {
                        rx_info_paquetes=null;
                        rx_info_paquetes=terminales[1].devolver_recibir();
                        identificacion=rx_info_paquetes.readChar();
                        if(aux=='c')
                           {
                              Cadena=Cadena + "Terminal Modo Paquetes Conectado\n";
                              Mensajes.setText(Cadena);
                           }
                        else
                           {
                              Cadena=Cadena + "Terminal Modo Caracteres Conectado\n";
                              Mensajes.setText(Cadena);
                           }
                     }
               }

         }

      catch(IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
     rx_info_paquetes=null;
     asignar();
}

public void asignar()
   {  // esta funciòn permite definir la direcciòn de tx y rx
      if(aux=='c')
         {           // asignando las correspondientes entradas y salidas.
            escoger=false;
            tx_info_caracteres=terminales[0].devolver_enviar();
            rx_info_caracteres=terminales[0].devolver_recibir();
            tx_info_paquetes=terminales[1].devolver_enviar();
            rx_info_paquetes=terminales[1].devolver_recibir();
            ps=terminales[1].devolver_ps();
         }
      else
         {
            escoger=true;
            tx_info_caracteres=terminales[1].devolver_enviar();
            rx_info_caracteres=terminales[1].devolver_recibir();
            tx_info_paquetes=terminales[0].devolver_enviar();
            rx_info_paquetes=terminales[0].devolver_recibir();
            ps=terminales[0].devolver_ps();
         }
      t1 = new Recibir(rx_info_paquetes,m);
      t2 = new Recibir(rx_info_caracteres,m);
      t1.start();
      t2.start();
      //new Recibir(rx_info_paquetes,m).start();
      //new Recibir(rx_info_caracteres,m).start();
}

public void enviar_solicitud_transmision_paquetes()
   {
      char a='x';
      try
         {
            Cadena = Cadena  + "Conectando DTE-A con DTE-B...\n";
            Mensajes.setText(Cadena);
            tx_info_caracteres.writeChar(a);
         }
      catch(IOException e)
         {
            e.printStackTrace();
         }
   }


public void enviar_aceptacion_recepcion_paquetes()
   {
      char a='q';
      try
         {
            Cadena = Cadena + "Conexion Establecida...\n";
            Mensajes.setText(Cadena);
            tx_info_paquetes.writeChar(a);
            //recibirArchivo();
            t.start();
         }
      catch(IOException e)
         {
            e.printStackTrace();
         }
   }

class HiloPrincipal extends Thread
   {
      char a,m;
      int parcial=0,cont=0,i,longitud;
      String paquete="   ";
      StringBuffer trama = new StringBuffer(34);
      boolean help=true;

   public void run()
     {
      try
         {
            while ((parcial=rx_info_paquetes.read())!=-1)
               {
                  if(help)
                     {
                        Cadena = Cadena + "Transmitiendo...\n";
                        Mensajes.setText(Cadena);
                        help = false;
                     }
                  cont+=1;
                  a=(char)parcial;
                  trama.append(a);
                  if(cont==34)
                     {
                        FormatoPaquetes.setText(trama.toString());
                        paquete=des_empaquetar(trama,33);
                        for (i=0;i<32;i++)
                           {
                              parcial=(int) paquete.charAt(i);
                              FormatoTramas.setText("  "+paquete.charAt(i)+"  ");
                              tx_info_caracteres.write(parcial);
                              try
                                 {
                                    t.sleep(500);
                                 }
                              catch(InterruptedException e)
                                 {
                                 }
                           }
                        cont=0;
                        trama=new StringBuffer("");
                     }
               }
            FormatoPaquetes.setText(trama.toString());
            longitud=trama.length();
            paquete=des_empaquetar(trama,longitud-1);
            for (i=0;i<longitud-2;i++)
               {
                  parcial=(int) paquete.charAt(i);
                  FormatoTramas.setText("  "+paquete.charAt(i)+"  ");
                  tx_info_caracteres.write(parcial);
               }
            Cadena = Cadena + "Recepcion Terminada\n";
            Mensajes.setText(Cadena);
            tx_info_caracteres.close();
            rx_info_paquetes.close();
            //rx_info_paquetes=null;
            //tx_info_caracteres=null;
            //rx_info_caracteres=null;
            //tx_info_paquetes=null;
            //System.gc();
            help = true;
         }
      catch(IOException e)
         {
            System.out.println("Ala");
            e.printStackTrace();
         }
}
}

void actualizar(char c)
   {
      terminal=c;
   }


public void marco(GuiPAD n)
   {
      m=n;
   }

public String des_empaquetar(StringBuffer s,int i)
   {
      char a;
      StringBuffer aux=new StringBuffer("");
      String x=s.toString();
      for(int j=1;j<i;j++)
         {
            a=s.charAt(j);
            aux.append(a);
         }
      return aux.toString();
   }
}


//******************************************************************************
//            Creacion de flujos con cada cliente

class DTE
   {
      Socket coneccion;
      PrintStream ps=null;
      DataOutputStream enviar = null;
      DataInputStream recibir = null;

      public DTE(Socket s)
         {
            int i;
            try
               {
                  coneccion=s;
                  recibir=new DataInputStream(coneccion.getInputStream());
                  enviar=new DataOutputStream(coneccion.getOutputStream());
                  ps=new PrintStream(coneccion.getOutputStream());
               }
            catch(IOException e)
               {
                  e.printStackTrace();
                  System.exit(1);
               }
         }

public DataInputStream devolver_recibir()
   {
      return recibir;
   }

public DataOutputStream devolver_enviar()
   {
      return enviar;
   }

public PrintStream devolver_ps()
   {
      return ps;
   }
}

//******************************************************************************
//                  Maneja cada terminal como un hilo.

class Recibir extends Thread
   {
      DataInputStream rx;
      char c='x';
      boolean esperar=true;
      GuiPAD ma;
      public Recibir(DataInputStream r, GuiPAD m)
         {
            rx=r;
            ma=m;
         }

      public void run()
         {
           // while(esperar)
            //{
            try
               {
                  c=rx.readChar();
                  System.out.println(c);
                  if(c=='s')
                     {
                        ma.enviar_solicitud_transmision_paquetes();
                     }
                  if(c=='w')
                     {
                        ma.enviar_aceptacion_recepcion_paquetes();
                     }
                  if(c=='j')
                     {
                        //ma.recibirArchivo();
                     }

               }
            catch(IOException e)
               {
                  esperar=false;
                  System.out.println("Hola");
                  //e.printStackTrace();
               }
          // }
         }
   }

//******************************************************************************

