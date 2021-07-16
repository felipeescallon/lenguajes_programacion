     
 import java.net.*;
 import java.io.*;
 
 //CLASE QUE MANEJA LAS FUNCIONES DEL MAESTRO

 public class Maestro extends java.awt.Frame 
 {

//DECLARACION DE VARIABLES

 static PrintStream os = null;
 static DataInputStream is = null;
 static Socket clientSocket = null;
 static ServerSocket serverSocket = null;
 private static String stringServer="Nada desde el Servidor";
 static String Trama = "";
 static String Trama1 = "";
 static String Trama2 = "";
 static String Desc = "";
 static int band = 0;
 static int band2 = 0;
 static int band3 = 0;
 static int x = 0;
 static int aux1 = 0;
 static int t = 0;
 private static int cont = 0;
 

//METODO CONSTRUCTOR
     public Maestro()  
     {
         initComponents();
     }
 
//INICIALIZACION DE VARIABLES
     private void initComponents()  
     {
         info = new java.awt.Label();
         envi = new java.awt.Button();
         recib = new java.awt.Button();
         salir = new java.awt.Button();
         label1 = new java.awt.Label();
         label2 = new java.awt.Label();
         label3 = new java.awt.Label();
         label4 = new java.awt.Label();
         mensaje = new java.awt.TextField();

//CREACION DE LA VENTANA MAESTRO

         setLayout(null);

         addWindowListener(new java.awt.event.WindowAdapter()
         {
             public void windowClosing(java.awt.event.WindowEvent evt)
             {
                 exitForm(evt);
             }
         });
         
         label1.setAlignment(java.awt.Label.CENTER);
         label1.setFont(new java.awt.Font("Dialog", 1, 16));
         label1.setForeground(new java.awt.Color(0, 102, 255));
         label1.setText("MAESTRO");
         add(label1);
         label1.setBounds(10, 40, 260, 20);
         
         label2.setText("Mensaje: ");
         add(label2);
         label2.setBounds(10, 70, 60, 20);

         add(mensaje);
         mensaje.setBounds(75, 70, 155, 20);
         
         label3.setForeground(java.awt.Color.blue);
         add(label3);
         label3.setBounds(10, 120, 270, 20);
         
         label4.setForeground(java.awt.Color.blue);
         add(label4);
         label4.setBounds(10, 145, 270, 20);
         
         info.setFont(new java.awt.Font("Comic Sans MS", 0, 12));
         info.setForeground(java.awt.Color.blue);
         add(info);
         info.setBounds(10, 95, 270, 20);

         envi.setLabel("Enviar");
         
         envi.addActionListener(new java.awt.event.ActionListener()
         {
             public void actionPerformed(java.awt.event.ActionEvent evt)
             {
                 enviActionPerformed(evt);
             }
         }
         );

         add(envi);
         envi.setBounds(65, 170, 150, 25);
         envi.setVisible(false);
         
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
         recib.setBounds(65, 200, 150, 25);
         recib.setVisible(false);

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
         salir.setBounds(65, 230, 150, 25);

         pack();
         resize(280,280);
     }
     
//PROCEDIMIENTO A REALIZAR CUANDO EL MAESTRO PRESIONA EL BOTON ENVIAR

     public void enviActionPerformed(java.awt.event.ActionEvent evt)
     {

//FORMACION DE LOS CAMPOS DE LA TRAMA CON VALOR FIJO
         recib.setVisible(false);
         String SYN1 = "S";	//CARACTERES DE SINCRONIZACION
         String SYN2 = "S";  
         String SOH = "E";   	//INICIO DE ENCABEZADO
         String ID = "";		//IDENTIFICADOR DE BLOQUE
         String IP = "1";		//IDENTIFICADOR DEL EQUIPO DEL CLIENTE
         String STX = "T";		//INDICA COMIENZO DEL TEXTO
         String Texto = mensaje.getText();     //SE TOMA EL MENSAJE ESCRITO EN LA CAJA DE TEXTO
         String ETX = "F";		//INDICA EL FINAL DE LA TRAMA
         String ETB = "C";		//INDICA EL FINAL DE UN BLOQUE PERO CONTINUACION DE 				//TRAMA
         String EOT = "D";		//CARACTER PARA INDICAR DESCONEXION
         x = Texto.length();		//SACAMOS LA LONGITUD DEL TEXTO
         int z = 3*x;			//DEFINIMOS EL POLINOMIO GENERADOR
         int w = 30;
         int y = z + w;
         int BCC = y;			//CON EL VALOR DE X EN EL POLINOMIO GENERADOR HALLAMOS BCC
         int a, b, c, d;
         
         if (x <=20)		//SI LA LONGITUD DEL TEXTO ES MENOR A 20 CARACTERES, SE PUEDE 
         {			//ENVIAR EN UNA SOLA TRAMA
             t = 1;
             ID = "0";		//IDENTIFICACION DE LA TRAMA 
             Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto + ETX + BCC;	//FORMACION DE TRAMA
             Desc = SYN1 + SYN2 + EOT; 			 //FORMACION DE TRAMA DE DESCONEXION
             band2 = 1;
         }
         else		//SI LA LONGITUD DEL TEXTO ES MAYOR A 20 CARACTERES DEBEMOS ENVIAR 
         {		//EL MENSAJE EN VARIOS BLOQUES
             aux1 = x/20;	//PARA CONOCER EL NUMERO DE BLOQUES
             aux1++;
             MTrama Textodiv = new MTrama();  //INSTANCIAMOS UN OBJETO DE LA CLASE MTrama
             String Texto1, Texto2, Texto3;
             int i = 20;
             if ((aux1 == 2)|(x == 40))
             {
                 t = 2;				//SI SON NECESARIOS 2 BLOQUES
                 Texto1 = Textodiv.div(Texto, 0, i);  	//INVOCAMOS EL METODO DIV PARA HALLAR LA
                 ID = "0";				//LONGITUD DEL TEXTO DEL PRIMER BLOQUE
                 BCC = 90;
                 Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto1 + ETB + BCC;	 //PRIMER BLOQUE
                 Texto2 = Textodiv.div(Texto, i, x); 		 //PARA HALLAR EL TEXTO DEL BLOQUE 2
                 ID = "1";
                 a = Texto2.length();  		//SE CALCULA EL BCC
                 b = 3*a;
                 c = 30;
                 d = b + c;
                 BCC = d;
                 Trama1 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto2 + ETX + BCC; 		 //BLOQUE 2
                 Desc = SYN1 + SYN2 + EOT; 
                 band2 = 1;
             }
             else if ((aux1 == 3)|(x == 60))
             {
                 t = 3;			//SI SON NECESARIOS 3 BLOQUES
                 i = 20;
                 Texto1 = Textodiv.div(Texto, 0, i);
                 ID = "0";
                 BCC = 90;
                 Trama = SYN1 + SYN2 + SOH + ID + IP + STX + Texto1 + ETB + BCC;  	//BLOQUE 1
                 Texto2 = Textodiv.div(Texto, i, i+20);
                 ID = "1";
                 BCC = 90;
                 Trama1 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto2 + ETB + BCC;	  //BLOQUE 2
                 Texto3 = Textodiv.div(Texto, i+20, x);
                 ID = "2";
                 a = Texto3.length();		  //SE CALCULA BCC
                 b = 3*a;
                 c = 30;
                 d = b + c;
                 BCC = d;
                 Trama2 = SYN1 + SYN2 + SOH + ID + IP + STX + Texto3 + ETX + BCC;	 //BLOQUE 3
                 Desc = SYN1 + SYN2 + EOT;
                 band2 = 1;
             }
         }
     }

     public void recibActionPerformed(java.awt.event.ActionEvent evt)
     {
         envi.setVisible(false);		  //OCULTA EL BOTON RECIBIR
         band2 = 2;
     }
     
     /* PARA SALIR DE LA APLICACION*/

     private void salirActionPerformed(java.awt.event.ActionEvent evt)
    {
         System.exit(0);
     }
     
     private void exitForm(java.awt.event.WindowEvent evt) 	//PARA CERRAR LOS SOCKETS
     {
         try
         {
             serverSocket.close();
         }
         catch (IOException e)
         {
             System.err.println("Could not close server socket." + e.getMessage());
         }
         System.exit(0);
     }
     
//PROGRAMA PRINCIPAL

     public static void main(String args[]) 
     {
         new Maestro().show();
         boolean listening = true;
         try
         {
             serverSocket = new ServerSocket(7000);  //CREACION DE UN SOCKET DEL TIPO SERVIDOR
         }
         catch (IOException e)
         {
             System.err.println("Could not listen on port: " + 7000 + ", " + e.getMessage());
             System.exit(1);
         }
         while (listening)  		//INICIANDO LA COMUNICACION ENTRE MAESTRO ESCLAVO
         {
             Socket clientSocket = null;
             try
             {
                 clientSocket = serverSocket.accept();
                 label3.setText("Usuario Conectado desde: " + clientSocket.getInetAddress());
                 envi.setVisible(true);
                 recib.setVisible(true);
             }
             catch (IOException e)
             {
                 System.err.println("Accept failed: " + 7000 + ", " + e.getMessage());
                 continue;
             }
             
             String fromClient = "";
             
             while(band3== 0) 		 //PARA ENVIAR LAS TRAMAS
            {
            if (band2==1)
            {
                try
                {
                    PrintStream os = new PrintStream( new BufferedOutputStream(clientSocket.getOutputStream(), 	  1024), false);
                    DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                    label3.setText("Enviando: " + Trama);
                    os.println(Trama); 		  //ENVIANDO TRAMA 
                    os.flush();
                    while ((fromClient = is.readLine()) == null)
                    {
                    }
                    if (fromClient.equals("SS00"))		  //SI EL CLIENTE ENVIA LA TRAMA DE CONFIRMACION
                    {				  //DE BLOQUE PAR	
                        info.setText("Trama Recibida Correctamente");
                        if ((t == 2)|(t==3))  		   //SI SON MAS BLOQUES
                        {
                            label3.setText("Enviando: " + Trama1);
                            os.println(Trama1); 		 //ENVIANDO BLOQUE 2
                            os.flush();
                            while ((fromClient = is.readLine()) == null)
                            {
                            }
                            if (fromClient.equals("SS01"))	 //CONFIRMACION DE BLOQUE IMPAR
                            {
                                info.setText("Trama Recibida Correctamente");
                                if (t == 3)  //PARA EL BLOQUE 3
                                {
                                    label3.setText("Enviando: " + Trama2);
                                    os.println(Trama2); 		 //ENVIANDO BLOQUE 3
                                    os.flush();
                                    while ((fromClient = is.readLine()) == null)
                                    {
                                    }
                                    if (fromClient.equals("SS00")) 	 //CONFIRMACION DE BLOQUE PAR
                                    {
                                        info.setText("Trama Recibida Correctamente");
                                    }
                                    else 		 //PARA RETRANSMITIR LOS BLOQUES NO CONFIRMADOS
                                    {
                                        info.setText("Error de recepción");
                                        os.println(Trama2);         //RETRANSMITE TERCER BLOQUE
                                        os.flush();
                                    }
                                }
                            }
                            else
                            {
                                info.setText("Error de recepción");
                                os.println(Trama1);		//RETRANSMITE SEGUNDO BLOQUE
                                os.flush();
                            }
                        }
                    }
                    else
                    {
                        info.setText("Error de recepción");
                        os.println(Trama);		//RETRANSMITE PRIMER BLOQUE
                        os.flush();
                    }
                    os.println(Desc); 			 //DESCONECTA
                    os.flush();
                    band2=0;
/*                    is = null;
                    os = null;*/
                }
                catch (IOException e)
                {
                    System.err.println("Error con los streams I/O para la coneccion al Cliente");
                }
            }
            else if (band2 == 2)			  //SI ES EL CLIENTE QUIEN ENVIA UN MENSAJE
            {
                try
                 {
                     PrintStream os = new PrintStream( new BufferedOutputStream(clientSocket.getOutputStream(), 1024), false);
                     DataInputStream is = new DataInputStream(clientSocket.getInputStream());

//SE INICIALIZAN LAS VARIABLES PARA RECIBIR LOS CAMPOS DE LA TRAMA QUE LLEGA

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
                    
                     	while ((fromClient = is.readLine()) != null) 	//RECIBIMOS LA TRAMA POR CAMPOS
                     	{
                         cont++;
                         String Tram = fromClient;
                     	    int lon = fromClient.length();
                     	    int lon2 = lon - 3;
                         SYN1 = fromClient.charAt(0);
                         SYN2 = fromClient.charAt(1);
                         SOH = fromClient.charAt(2);
                         ID = fromClient.charAt(3);
                         IP = fromClient.charAt(4);
                         STX = fromClient.charAt(5);
                         char m[]=new char[lon-9];		//SE SACA EL CAMPO DE TEXTO
                         fromClient.getChars(6,lon2,m,0);
                         String Texto = new String(m);
                         ETX = fromClient.charAt(lon2);
                         char n[]=new char[2];
                         fromClient.getChars(lon-2,lon,n,0);
                         String BCC1 = new String(n);
                         int x = Texto.length();
                         int z = 3*x;
                         int w = 30;
                         int y = z + w;
                         BCC = y;   			//CALCULAMOS EL BCC DEL MENSAJE QUE LLEGA
                         String BCC2 = BCC + "";
                         BCC1 = BCC1 + "";
                         String ACK;
                         if ((SYN1=='S') & (SYN2=='S') & (SOH=='E')) 	 //COMENZAMOS A VERIFICAR SI LA 
                         {				                  //TRAMA ES CORRECTA
                             if ((IP=='0') & (STX=='T'))
                             {
                                 if(BCC2.equals(BCC1))      //COMPARAMOS CAMPO BCC CON EL CALCULADO EN LA
                                 {			         //RECEPCION
                                     if ((ID=='0')|(ID=='2'))
                                     {
                                         ACK = "SS00"; 		 //SI BLOQUES PARES CORRECTOS
                                     }
                                     else
                                     {
                                         ACK = "SS01";  		 //SI BLOQUES IMPARES CORRECTOS
                                         label3.setText("");
                                         label4.setText("");
                                         label3.setText("Bloque 2: " + Tram);
                                     }
                                     os.println(ACK);		  //SE ENVIA LA CONFIRMACION
                                     os.flush();
                                     if (ETX == 'F')  
                                     {
                                        fromClient = is.readLine();
                                        if ((fromClient.equals("SSD"))&(cont == 1))
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
                                         switch(cont) 		 //SE RECIBEN LOS BLOQUES DEL MENSAJE
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
                                 else 			 //SI HAY ERRORES EN LA TRAMA ENVIA  NACK
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
                     if (band == 1)  //SE UNEN LOS BLOQUES DE MENSAJE
                     {
                         String TextoF = "";
                         mensaje.setText("");
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
                         mensaje.setText(TextoF);
                         cont = 0;
                         band = 0;
                         band2 = 0;
/*                       is = null;
                         os = null;*/
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
            }
     }
                    
         try   //SE CIERRAN LOS SOCKETS
         {
             serverSocket.close();
             os.close();
             is.close();
         }
         catch (IOException e)
         {
             System.err.println("Could not close server socket." + e.getMessage());
         }
     }
    
    // Variables declaration
    public static java.awt.Label info;
    public static java.awt.Button envi;
    public static java.awt.Button recib;
    public static java.awt.Label label3;
    public static java.awt.Label label4;
    public static java.awt.TextField mensaje;
    private java.awt.Button salir;
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration
 }
