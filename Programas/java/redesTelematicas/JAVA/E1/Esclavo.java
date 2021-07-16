
 import java.io.*;
 import java.net.*;
 import java.awt.event.*;
 
 public class Esclavo extends java.awt.Frame 
 {
 static Socket kkSocket = null;
 public static PrintStream os = null;
 public static DataInputStream is = null;
 private static String stringServer="Nada desde el Servidor";
 static String Trama = "";
 static String Trama1 = "";
 static String Trama2 = "";
 static String Desc = "";
 static int band = 0;
 static int band2 = 0;
 static int x = 0;
 static int aux1 = 0;
 static int t = 0;
 private static String dir = "A";
 private static int cont = 0;
 
     public Esclavo()
     {
         initComponents();
     }
     
     private void initComponents()
     {
         info = new java.awt.Label();
         salir = new java.awt.Button();
         salir2 = new java.awt.Button();
         recib = new java.awt.Button();
         enviar = new java.awt.Button();
         conec = new java.awt.Button();
         label1 = new java.awt.Label();
         label2 = new java.awt.Label();
         label3 = new java.awt.Label();
         label4 = new java.awt.Label();
         ipservidor = new java.awt.TextField();
         
         setLayout(null);
         
         addWindowListener(new java.awt.event.WindowAdapter()
         {
             public void windowClosing(java.awt.event.WindowEvent evt)
             {
                 exitForm(evt);
             }
         }
         );
         
         label1.setAlignment(java.awt.Label.CENTER);
         label1.setFont(new java.awt.Font("Dialog", 1, 16));
         label1.setForeground(new java.awt.Color(0, 102, 255));
         label1.setText("ESCLAVO");
         add(label1);
         label1.setBounds(10, 40, 260, 20);

         label2.setText("IP del Servidor:");
         add(label2);
         label2.setBounds(10, 70, 83, 20);

         add(ipservidor);
         ipservidor.setText("Dir. IP del Servidor");
         ipservidor.setBounds(95, 70, 175, 20);

         info.setFont(new java.awt.Font("Comic Sans MS", 0, 12));
         info.setForeground(java.awt.Color.blue);
         add(info);
         info.setBounds(10, 100, 270, 20);

         label3.setFont(new java.awt.Font("Comic Sans MS", 0, 12));
         label3.setForeground(java.awt.Color.blue);
         add(label3);
         label3.setBounds(10, 125, 270, 20);

         label4.setFont(new java.awt.Font("Comic Sans MS", 0, 12));
         label4.setForeground(java.awt.Color.blue);
         add(label4);
         label4.setBounds(10, 150, 270, 20);

         conec.setLabel("Conectar");
         
         conec.addActionListener(new java.awt.event.ActionListener()
         {
             public void actionPerformed(java.awt.event.ActionEvent evt)
             {
                 conecActionPerformed(evt);
             }
         }
         );

         add(conec);
         conec.setBounds(65, 175, 150, 25);

         enviar.setLabel("Enviar");
         
         enviar.addActionListener(new java.awt.event.ActionListener()
         {
             public void actionPerformed(java.awt.event.ActionEvent evt)
             {
                 enviarActionPerformed(evt);
             }
         }
         );

         add(enviar);
         enviar.setBounds(65, 175, 150, 25);
         enviar.setVisible(false);

         recib.setLabel("Recibir");
         
         recib.addActionListener(new java.awt.event.ActionListener()
         {
             public void actionPerformed(java.awt.event.ActionEvent evt)
             {
                 recibActionPerformed(evt);
             }
         }
         );

         add(recib);
         recib.setBounds(65, 205, 150, 25);

         salir.setLabel("SALIR");
         
         salir.addActionListener(new java.awt.event.ActionListener()
         {
             public void actionPerformed(java.awt.event.ActionEvent evt)
             {
                 salirActionPerformed(evt);
             }
         }
         );
         
         add(salir);
         salir.setBounds(65, 235, 150, 25);
         
         salir2.setLabel("SALIR");
         
         salir2.addActionListener(new java.awt.event.ActionListener()
         {
             public void actionPerformed(java.awt.event.ActionEvent evt)
             {
                 salir2ActionPerformed(evt);
             }
         }
         );
         
         add(salir2);
         salir2.setBounds(65, 235, 150, 25);
       
         pack();
         resize(280,280);
     }
     
     
     /* Exit the Application */
     
     private void salirActionPerformed(java.awt.event.ActionEvent evt)
     {
         System.exit(0);
     }

     private void salir2ActionPerformed(java.awt.event.ActionEvent evt)
     {
         os.println("Salir");
         os.flush();
         try
         {
             os.close();
             is.close();
             kkSocket.close();
         }
         catch (IOException e)
         {
             System.err.println("IOException: " + e);
         }
         System.exit(0);
     }
     
     private void exitForm(java.awt.event.WindowEvent evt)
     {
         os.println("Salir");
         os.flush();
         try
         {
             os.close();
             is.close();
             kkSocket.close();
         }
         catch (IOException e)
         {
             System.err.println("IOException: " + e);
         }
         System.exit(0);
     }
     
     private void conecActionPerformed(java.awt.event.ActionEvent evt)
     {
         try
         {
             dir=ipservidor.getText();
             kkSocket = new Socket(dir,7000);
             os = new PrintStream(kkSocket.getOutputStream());
             is = new DataInputStream(kkSocket.getInputStream());
             salir.setVisible(false);
             salir2.setVisible(true);
             conec.setVisible(false);
             enviar.setVisible(true);
             label2.setText("Mensaje:");
             label2.setBounds(10, 70, 60, 20);
             ipservidor.setText("");
             ipservidor.setBounds(70, 70, 200, 20);
             label1.setText("Esclavo Conectado");
         }
         catch (UnknownHostException e)
         {
             info.setText("No se puede localizar el Servidor.....");
         }
         catch (IOException e)
         {
             info.setText("Error en la coneccion al Servidor");
         }
     }
     
     private void recibActionPerformed(java.awt.event.ActionEvent evt)
     {
         enviar.setVisible(false);
         band2 = 2;
     }
     
     private void enviarActionPerformed(java.awt.event.ActionEvent evt)
     {
         recib.setVisible(false);
         String SYN1 = "S";
         String SYN2 = "S";
         String SOH = "E";
         String ID = "";
         String IP = "0";
         String STX = "T";
         String Texto = ipservidor.getText();
         String ETX = "F";
         String ETB = "C";
         String EOT = "D";
         x = Texto.length();
         int z = 3*x;
         int w = 30;
         int y = z + w;
         int BCC = y;
         int a, b, c, d;
         
         if (x <=20)
         {
             t = 1;
             ID = "0";
             Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto + ETX + BCC;
             Desc = SYN1 + SYN2 + EOT;
             band2 = 1;
         }
         else
         {
             aux1 = x/20;
             aux1++;
             MTrama Textodiv = new MTrama();
             String Texto1, Texto2, Texto3;
             int i = 20;
             if ((aux1 == 2)|(x == 40))
             {
                 t = 2;
                 Texto1 = Textodiv.div(Texto, 0, i);
                 ID = "0";
                 BCC = 90;
                 Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto1 + ETB + BCC;
                 Texto2 = Textodiv.div(Texto, i, x);
                 ID = "1";
                 a = Texto2.length();
                 b = 3*a;
                 c = 30;
                 d = b + c;
                 BCC = d;
                 Trama1 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto2 + ETX + BCC;
                 Desc = SYN1 + SYN2 + EOT;
                 band2 = 1;
             }
             else if ((aux1 == 3)|(x == 60))
             {
                 t = 3;
                 i = 20;
                 Texto1 = Textodiv.div(Texto, 0, i);
                 ID = "0";
                 BCC = 90;
                 Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto1 + ETB + BCC;
                 Texto2 = Textodiv.div(Texto, i, i+20);
                 ID = "1";
                 BCC = 90;
                 Trama1 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto2 + ETB + BCC;
                 Texto3 = Textodiv.div(Texto, i+20, x);
                 ID = "2";
                 a = Texto3.length();
                 b = 3*a;
                 c = 30;
                 d = b + c;
                 BCC = d;
                 Trama2 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto3 + ETX + BCC;
                 Desc = SYN1 + SYN2 + EOT;
                 band2 = 1;
             }
         }
     }
     
     public static void main(String args[])
     {
         new Esclavo().show();
         while(true)
         {
             if (kkSocket != null)// && os != null && is != null)
             {
                 String fromServer;
                 if (band2 == 2)
                 {
                 try
                 {
                     	String Texto1 = "";
                     	String Texto2 = "";
                     	String Texto3 = "";
                     char SYN1 = '0';
                     char SYN2 = '0';
                     char SOH = '0';
                     char ID = '0';
                     char IP = '0';
                     char STX = '0';
                     char ETX = '0';
                     char EOT = '0';
                     int BCC = 0;
                 
                     	while ((fromServer = is.readLine()) != null)
                     	{
                         cont++;
                         String Tram = fromServer;
                     	    int lon = fromServer.length();
                     	    int lon2 = lon - 3;
                         SYN1 = fromServer.charAt(0);
                         SYN2 = fromServer.charAt(1);
                         SOH = fromServer.charAt(2);
                         ID = fromServer.charAt(3);
                         IP = fromServer.charAt(4);
                         STX = fromServer.charAt(5);
                         char m[]=new char[lon-9];
                         fromServer.getChars(6,lon2,m,0);
                         String Texto = new String(m);
                         ETX = fromServer.charAt(lon2);
                         char n[]=new char[2];
                         fromServer.getChars(lon-2,lon,n,0);
                         String BCC1 = new String(n);
                         int x = Texto.length();
                         int z = 3*x;
                         int w = 30;
                         int y = z + w;
                         BCC = y;
                         String BCC2 = BCC + "";
                         BCC1 = BCC1 + "";
                         String ACK;
                         if ((SYN1=='S') & (SYN2=='S') & (SOH=='E'))
                         {
                             if ((IP=='1') & (STX=='T'))
                             {
                                 if(BCC2.equals(BCC1))
                                 {
                                     if ((ID=='0')|(ID=='2'))
                                     {
                                         ACK = "SS00";
                                     }
                                     else
                                     {
                                         ACK = "SS01";
                                         label3.setText("");
                                         label4.setText("");
                                         label3.setText("Bloque 2: " + Tram);
                                     }
                                     os.println(ACK);
                                     os.flush();
                                     if (ETX == 'F')
                                     {
                                        fromServer = is.readLine();
                                        if ((fromServer.equals("SSD"))&(cont == 1))
                                        {
                                            Texto1 = Texto;
                                            info.setText("");
                                            label3.setText("");
                                            label4.setText("");
                                            info.setText("Bloque 1: " + Tram);
                                        }
                                        else
                                        {
                                            if (cont == 2)
                                                Texto2 = Texto;
                                            if (cont == 3)
                                            {
                                                Texto3 = Texto;
                                                label4.setText("");
                                                label4.setText("Bloque 3: " + Tram);
                                            }
                                        }
                                        band = 1;
                                     }
                                     else
                                     {
                                         switch(cont)
                                         {
                                             case 1:
                                                     Texto1 = Texto;
                                                     info.setText("");
                                                     label3.setText("");
                                                     label4.setText("");
                                                     info.setText("Bloque 1: " + Tram);
                                                     break;
                                             case 2:
                                                     Texto2 = Texto;
                                                     break;
                                             case 3:
                                                     Texto3 = Texto;
                                                     label4.setText("");
                                                     label4.setText("Bloque 3: " + Tram);
                                                     break;
                                         }
                                     }
                                 }
                                 else
                                 {
                                     String NACK = "SS11";
                                     os.println(NACK);
                                     os.flush();
                                     info.setText("Error en Recepcion de la Informacion");
                                 }
                             }
                             else
                             {
                                     String NACK = "SS12";
                                     os.println(NACK);
                                     os.flush();
                                     info.setText("Error en Recepcion de la Informacion");
                             }
                         }
                         else
                         {
                                     String NACK = "SS13";
                                     os.println(NACK);
                                     os.flush();
                                     info.setText("Error en Recepcion de la Informacion");
                         }
                     if (band == 1)
                     {
                         String TextoF = "";
                         ipservidor.setText("");
                         switch (cont)
                         {
                             case 1:
                                 TextoF = Texto1;
                                 break;
                             case 2:
                                 TextoF = Texto1 + Texto2;
                                 break;
                             case 3:
                                 TextoF = Texto1 + Texto2 + Texto3;
                                 break;
                         }
                         ipservidor.setText(TextoF);
                         cont = 0;
                         band = 0;
                         band2 = 0;
/*                         os = null;
                         is = null;*/
                     }
                     }
                 }
                 catch (UnknownHostException e)
                 {
                     System.err.println("Trying to connect to unknown host: " + e);
                 }
                 catch (IOException e)
                 {
                 }
                 }
                 else if (band2 == 1)
                 {
                    try
                    {
                    label3.setText("Enviando: " + Trama);
                    os.println(Trama);
                    os.flush();
                    while ((fromServer = is.readLine()) == null)
                    {
                    }
                    if (fromServer.equals("SS00"))
                    {
                        info.setText("Trama Recibida Correctamente");
                        if ((t == 2)|(t==3))
                        {
                            label3.setText("Enviando: " + Trama1);
                            os.println(Trama1);
                            os.flush();
                            while ((fromServer = is.readLine()) == null)
                            {
                            }
                            if (fromServer.equals("SS01"))
                            {
                                info.setText("Trama Recibida Correctamente");
                                if (t == 3)
                                {
                                    label3.setText("Enviando: " + Trama2);
                                    os.println(Trama2);
                                    os.flush();
                                    while ((fromServer = is.readLine()) == null)
                                    {
                                    }
                                    if (fromServer.equals("SS00"))
                                    {
                                        info.setText("Trama Recibida Correctamente");
                                    }
                                    else
                                    {
                                        info.setText("Error de recepción");
                                        os.println(Trama2);
                                        os.flush();
                                    }
                                }
                            }
                            else
                            {
                                info.setText("Error de recepción");
                                os.println(Trama1);
                                os.flush();
                            }
                        }
                    }
                    else
                    {
                        info.setText("Error de recepción");
                        os.println(Trama);
                        os.flush();
                    }
                    os.println(Desc);
                    os.flush();
                    band2 = 0;
     /*               os = null;
                    is = null;*/
                }
                catch (IOException e)
                {
                    System.err.println("Error con los streams I/O para la coneccion al Cliente");
                }
                }
             }
/*         try
         {
             os.close();
             is.close();
             kkSocket.close();
         }
         catch (IOException e)
         {
             System.err.println("Could not close server socket." + e.getMessage());
         }*/
         }
     }
     
    // Variables declaration

    public static java.awt.Label info;
    public static java.awt.Label label3;
    public static java.awt.Label label4;
    public static java.awt.TextField ipservidor;
    private java.awt.Label label2;
    private java.awt.Button salir;
    private java.awt.Button salir2;
    private java.awt.Button conec;
    private java.awt.Button recib;
    private java.awt.Button enviar;
    private java.awt.Label label1;
    // End of variables declaration

 }
