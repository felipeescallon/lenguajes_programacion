
 import java.io.*;
 import java.net.*;
 import java.awt.event.*;

 //  Clase del equipo ESCLAVO

 public class Esclavo extends java.awt.Frame
 {
 static Socket kkSocket = null;    		 //Definicion del socket
 public static PrintStream os = null; 		 //flujo de salida del socket
 public static DataInputStream is = null;      	//flujo de entrada del socket
 private static String stringServer="Nada desde el Servidor";
 static String Trama = "";       			// primera trama con texto de 1 a 20 caracteres
 static String Trama1 = "";      			// trama para el texto entre 21 a 40 caracteres
 static String Trama2 = "";      			// trama para el texto entre 31 a 60 caracteres
 static String Desc = "";
 static int band = 0;      	          
 static int band2 = 0;           			 //bandera para saber si envia o recibe mensaje
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

//CREACION DE LA VENTANA ESCLAVO

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
             os.close();            	   // inhabilitado flujo de salida
             is.close();              	  // inhabilitado flujo de entrada
             kkSocket.close();             //  cerrar la conexion con Socket
         }
         catch (IOException e)
         {
             System.err.println("IOException: " + e);
         }
         System.exit(0);
     }

     private void exitForm(java.awt.event.WindowEvent evt)	//para salir de la aplicacion
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

     private void conecActionPerformed(java.awt.event.ActionEvent evt)    // Si se presiona conectar
     {
         try
         {
             dir=ipservidor.getText();                            		  //se da la direcciona IP
             kkSocket = new Socket(dir,7000);            	                   // conexion soket con el puerto  7000
             os = new PrintStream(kkSocket.getOutputStream());      // declaracion del flujo de los datos de salida
             is = new DataInputStream(kkSocket.getInputStream());   //declaracion del flujo de datos de entrada
             salir.setVisible(false);
             salir2.setVisible(true);
             conec.setVisible(false);
             enviar.setVisible(true);
             label2.setText("Mensaje:");
             label2.setBounds(10, 70, 60, 20);
             ipservidor.setText("");
             ipservidor.setBounds(70, 70, 200, 20);
             label1.setText("Esclavo Conectado");                   // Dice si se obtuvo la conexion con el equipo cliente
         }
         catch (UnknownHostException e)
         {
             info.setText("No se puede localizar el Servidor.....");   // Mensaje de no localizacion
         }
         catch (IOException e)
         {
             info.setText("Error en la coneccion al Servidor");       //  Mensaje cuando hay error en la conexion
         }
     }

     private void recibActionPerformed(java.awt.event.ActionEvent evt)
     {
         enviar.setVisible(false);
         band2 = 2;
     }

     private void enviarActionPerformed(java.awt.event.ActionEvent evt)   //Si se desea enviar la informacion
     {
         recib.setVisible(false);
         String SYN1 = "S";                           // Declaracion de los campos para formar las respectivas tramas
         String SYN2 = "S";
         String SOH = "E";
         String ID = "";
         String IP = "0";
         String STX = "T";
         String Texto = ipservidor.getText();
         String ETX = "F";
         String ETB = "C";
         String EOT = "D";
         x = Texto.length();       //Tomamos la longitud del texto para saber el numero de bloques que se debe formar
         int z = 3*x;                    //Generamos el polinomio para crear el BCC y hacer deteccion de errores
         int w = 30;
         int y = z + w;
         int BCC = y;                 // El valor del polinomio se lo asignamos a BCC
         int a, b, c, d;

         if (x <=20)                //Comparamos si la longitud del texto es menor de 20 para generar la trama respectiva
         {
             t = 1;                     //Generacion de la trama con un solo bloque
             ID = "0";
             Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto + ETX + BCC;
             Desc = SYN1 + SYN2 + EOT;    //Generacion de la trama de desconexion
             band2 = 1;     	             // Enviamos la trama
         }
         else
         {
             aux1 = x/20;             // Para saber cuantos bloques necesitamos si el texto si es mayor de 20 caracteres
             aux1++;                    //  nota: Solo se podran enviar un maximo de 60 caracteres
             MTrama Textodiv = new MTrama();   // llamamos a la funcion donde se calcula el #de bolques que se 			                      //genera a partir del valor aux1
             String Texto1, Texto2, Texto3;
             int i = 20;
             if ((aux1 == 2)|(x == 40))          	// Si la longitud del texto es hasta 40 caracteres
             {                                  		 // se debe generar dos tramas
                 t = 2;
                 Texto1 = Textodiv.div(Texto, 0, i);
                 ID = "0";	                   	 //Generamos la primera trama
                 BCC = 90;                    	//Este es el valor generado por el polinomio
                 Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto1 + ETB + BCC;
                 Texto2 = Textodiv.div(Texto, i, x);
                 ID = "1";	                		    //generamos la segunda trama
                 a = Texto2.length();    	  //tomamos la longitud del texto de la segunda trama
                 b = 3*a;                     		 //generamos el polinomio detector de errores
                 c = 30;
                 d = b + c;
                 BCC = d;
                 Trama1 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto2 + ETX + BCC;
                 Desc = SYN1 + SYN2 + EOT;
                 band2 = 1;                     
             }
             else if ((aux1 == 3)|(x == 60))            // Si la longitud del texto es hasta 60 caracteres
             {                                    		  //Se generan tres tramas
                 t = 3;
                 i = 20;
                 Texto1 = Textodiv.div(Texto, 0, i);
                 ID = "0";                    		  //Generamos la primera trama
                 BCC = 90;
                 Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto1 + ETB + BCC;
                 Texto2 = Textodiv.div(Texto, i, i+20);
                 ID = "1";                      		//Generacion de la segunda trama
                 BCC = 90;
                 Trama1 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto2 + ETB + BCC;
                 Texto3 = Textodiv.div(Texto, i+20, x);
                 ID = "2";
                 a = Texto3.length();          	 //Tomamos la longitud del texto de la 3 trama
                 b = 3*a;                     		  //generacion del polinomio detector de errores
                 c = 30;
                 d = b + c;
                 BCC = d;
                 Trama2 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto3 + ETX + BCC;     //generacion de la tercera 								  //trama
                 Desc = SYN1 + SYN2 + EOT;
                 band2 = 1;                 
             }
         }
     }

//PROGRAMA PRINCIPAL

     public static void main(String args[])
     {
         new Esclavo().show();
         while(true)
         {
             if (kkSocket != null)// && os != null && is != null)
             {
                 String fromServer;
                 if (band2 == 2)   		    // Si el esclavo recibe el mensaje
                 {
                 try
                 {
                     	String Texto1 = "";
                     	String Texto2 = "";
                     	String Texto3 = "";
                     char SYN1 = '0';           	 //Definicion de los campos de las tramas para compararcion
                     char SYN2 = '0';
                     char SOH = '0';
                     char ID = '0';
                     char IP = '0';
                     char STX = '0';
                     char ETX = '0';
                     char EOT = '0';
                     int BCC = 0;

                 	while ((fromServer = is.readLine()) != null)   //leemos los campos de la trama que estan en el maestro
                     	{                                                      //que es el que esta enviando la informacion
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
                         char m[]=new char[lon-9];                   //Para sacar el campo de texto de la trama
                         fromServer.getChars(6,lon2,m,0);
                         String Texto = new String(m);
                         ETX = fromServer.charAt(lon2);
                         char n[]=new char[2];
                         fromServer.getChars(lon-2,lon,n,0);
                         String BCC1 = new String(n);
                         int x = Texto.length();                       //tomamos la longitus del texto que llega
                         int z = 3*x;                                        //generamos el polinomio 
                         int w = 30;
                         int y = z + w;
                         BCC = y;                                         //BCC le asignamos el valor del polinomio
                         String BCC2 = BCC + "";
                         BCC1 = BCC1 + "";
                         String ACK;
                         if ((SYN1=='S') & (SYN2=='S') & (SOH=='E'))     //comparamos los valores de los campos de las 						//tramas que llegan
                         {
                             if ((IP=='1') & (STX=='T'))
                             {
                                 if(BCC2.equals(BCC1))                     // si el valor del BCC de tx y rx son iguales envia la 					     //confirmacion
                                 {   	                                      //debe haber recibido la trama correctamente
                                     if ((ID=='0')|(ID=='2')) 	 //Analiza el ID para verificacion de paridad
                                     {
                                         ACK = "SS00";		//Bloques pares, caracter de paridad=0
                                     }
                                     else                                 
                                     {                                    
                                         ACK = "SS01";		//Bloques impares, caracter de paridad=1
                                         label3.setText("");
                                         label4.setText("");
                                         label3.setText("Bloque 2: " + Tram);  //imprimimos la trama que va llegando
                                     }
                                     os.println(ACK);
                                     os.flush();
                                     if (ETX == 'F')          		      // Etiqueta para indicar el fin del mensaje
                                     {
                                        fromServer = is.readLine();         
                                        if ((fromServer.equals("SSD"))&(cont == 1))
                                        {
                                            Texto1 = Texto;
                                            info.setText("");
                                            label3.setText("");
                                            label4.setText("");
                                            info.setText("Bloque 1: " + Tram);   //imprimimos la trama que llega
                                        }
                                        else
                                        {
                                            if (cont == 2)                     //si son dos las tramas que se envian
                                                Texto2 = Texto;
                                            if (cont == 3)                     //si son tres las tramas que se envian
                                            {
                                                Texto3 = Texto;
                                                label4.setText("");
                                                label4.setText("Bloque 3: " + Tram);   //imprimimos la trama que llega
                                            }
                                        }
                                        band = 1;
                                     }
                                     else
                                     {
                                         switch(cont)  		//para capturar el campo de texto de cada bloque
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
                                     String NACK = "SS11";               // Si llega mal la trama 
                                     os.println(NACK);
                                     os.flush();
                                     info.setText("Error en Recepcion de la Informacion");
                                 }
                             }
                             else
                             {
                                     String NACK = "SS12";                    // Si llega mal la trama
                                     os.println(NACK);
                                     os.flush();
                                     info.setText("Error en Recepcion de la Informacion");
                             }
                         }
                         else
                         {
                                     String NACK = "SS13";                       // Si llega mal la trama 
                                     os.println(NACK);
                                     os.flush();
                                     info.setText("Error en Recepcion de la Informacion");
                         }
                     if (band == 1)
                     {                                              //Para capturar el texto que envia el Maestro al esclavo y armar el 
                         String TextoF = "";            //mensaje completo	
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
                 catch (UnknownHostException e)                                //Error cuando no reconoce el maestro
                 {
                     System.err.println("Trying to connect to unknown host: " + e);
                 }
                 catch (IOException e)
                 {
                 }
                 }
                 else if (band2 == 1)                		           // Si se va a enviar
                 {
                    try
                    {
                    label3.setText("Enviando: " + Trama);          //Visualiza la trama completa que se esta enviando
                    os.println(Trama);  			//envia el primer bloque
                    os.flush();
                    while ((fromServer = is.readLine()) == null)
                    {
                    }
                    if (fromServer.equals("SS00"))
                    {
                        info.setText("Trama Recibida Correctamente");   //Anuncia si recibe correctamente la trama que le 						//envia el maestro
                        if ((t == 2)|(t==3))
                        {
                            label3.setText("Enviando: " + Trama1);
                            os.println(Trama1);			//envia el segundo bloque 
                            os.flush();
                            while ((fromServer = is.readLine()) == null)
                            {
                            }
                            if (fromServer.equals("SS01"))                     //Tramas impares
                            {
                                info.setText("Trama Recibida Correctamente");
                                if (t == 3)
                                {
                                    label3.setText("Enviando: " + Trama2);
                                    os.println(Trama2); 			//envia el tercer bloque
                                    os.flush();
                                    while ((fromServer = is.readLine()) == null)
                                    {
                                    }
                                    if (fromServer.equals("SS00"))                   //Tramas pares
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
                                info.setText("Error de recepción");         //Si  le llego mal la trama
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
