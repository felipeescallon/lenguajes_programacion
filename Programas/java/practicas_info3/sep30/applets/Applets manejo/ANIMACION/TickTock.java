import java.util.Date;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class TickTock extends Applet implements ActionListener
{    

private Timer  timeDisplay; // crea el reloj
private Button bMode, bHr, bMin, bOff;      

public void init()
{      
 setLayout(new BorderLayout());
 Panel p = new Panel();		//crea panel 

 bMode = new Button("Alarm");		//crea botones
 bHr = new Button("Hour+");
 bMin = new Button("Min+");
 bOff = new Button("OFF");

 p.add(bMode);			//añade botones al panel
 p.add(bHr);
 p.add(bMin); 
 p.add(bOff);
 
add("South", p);			//pone los botones		

 bHr.setEnabled(false);		//inabilita 3 de los botones
 bMin.setEnabled(false);
 bOff.setEnabled(false);

 timeDisplay = new Timer();		//objeto de la clase timer
 add("Center", timeDisplay);        

bMode.addActionListener(this);	//botones programados para
bHr.addActionListener(this);		//obedecer al raton
bMin.addActionListener(this);
bOff.addActionListener(this);
 }	

public void start()	//inicio del applet	
{        
timeDisplay.start();			//llama a start de Timer			
}	

public void stop() 
{ 
timeDisplay.stop();			//llama al metodo stop de Timer 
}	   

public void actionPerformed(ActionEvent e)
{    	

Object source = e.getSource();

 if (source == bMode)		//si pulso set o alarm
  {
            if (bMode.getLabel().equals("Alarm"))	
            {               
 	 bMode.setLabel(" Set ");	//escribe Set en alarma
                	 bHr.setEnabled(true);	//activa botones
                     bMin.setEnabled(true);  
                     timeDisplay.setTimeMode(false); //llama al metodo setTimeMode  
            }           
          else
           {             
           timeDisplay.setAlarm(true);
           bMode.setLabel("Alarm");  
           bHr.setEnabled(false); 
           bMin.setEnabled(false);               
           bOff.setEnabled(true);
           timeDisplay.setTimeMode(true);
          }
 }
else if (source == bHr)	//si pulso aumentar horas
 {            
	timeDisplay.incrementAlarmHour();
  }
 else if (source == bMin)	//si pulso manipular minutos
 {            
	timeDisplay.incrementAlarmMinute();
 }
 else if (source == bOff)	//si pulso off
 {
 	timeDisplay.setAlarm(false);
	bOff.setEnabled(false);
  } 
 }
}
//************************************************************************************************************************
class Timer extends Canvas implements Runnable
{   
 	private Thread  clockThread;    // the thread
	private int     hour = 0,minute = 0,second = 0,aHour = 0,aMinute = 0;
  	private boolean alarmSet = false,timeMode = true;

public Timer()	
    {
      setBackground(Color.black);
      clockThread = null;
    }
	
//------------------------- Thread Methods -------------------------
 public void start()
    {
        if (clockThread == null) 
        {
            clockThread = new Thread(this);	//crea e inicia el hilo
            clockThread.start();
        }
    }

public void stop()
{        
if (clockThread != null)
        {
            clockThread.stop();
            clockThread = null;
        }
}

public void run()
{
        while (clockThread != null)
        {
            if (timeMode)		//VERDADERO
            {
                Date now = new Date();
                updateTime(now.getHours(),now.getMinutes(),now.getSeconds());
            }
            else
            {	
	updateTime(aHour, aMinute, 0);
            }	
            try
            {
                Thread.sleep(250);
            }
            catch (InterruptedException e) { }
        }
    }
    //------------------------- Display Methods ------------------------   
    public void paint(Graphics g)
    {
        String minSep = ":";	// separator for hh:mm
        String secSep = ":";	// separator for mm:ss
        String ampm = " ";	// "A.M." or "P.M."
        int adjHour = hour;	// conversion from 24-hour format.
			// Make the String for A.M./P.M.
        if ((hour >= 0) && (hour < 12))
        {
            ampm = ampm + "A.M.";
        }
        else
        {
            ampm = ampm + "P.M.";
        }	
        // Set the hour displayed to reflect a 12-hour clock.
        if (hour > 12)
        {           
          adjHour = hour - 12;
        }
        if (hour == 0)
        {
            adjHour = 12;
        }		
        if (minute < 10)
        {
            minSep = minSep + "0";
        }
        if (second < 10)
        { 
            secSep = secSep + "0";
        }
      
        int offset;
        if (adjHour >= 10)
        {
            offset = 10;
        }
        else
        {
            offset = 30;
        }
       if (alarmTriggered())
        {
            g.setColor(Color.red);
        }
        else
        {
            g.setColor(Color.green);
        }
        g.setFont(new Font("Helvetica",Font.BOLD,36));
        g.drawString(adjHour + minSep + minute + secSep + second, offset, 50);
        g.setFont(new Font("Helvetica", Font.BOLD, 24));
        g.drawString(ampm, 155, 50);
    }	
private void updateTime(int h, int m, int s)
{       
if ((s != second) || (m != minute) || (h != hour)) 
      {
            hour = h;
            minute = m;
            second = s;
            repaint();
        }
}
public void setTimeMode(boolean timeMode)
 {
       this.timeMode = timeMode;
 }
public void setAlarm(boolean isOn)
{
        alarmSet = isOn;
}	
 public void incrementAlarmHour()
{
        aHour++;
        if (aHour == 24)
        {
            aHour = 0;
        }
        updateTime(aHour, aMinute, 0);
    }
public void incrementAlarmMinute()
{       
        aMinute++;
        if (aMinute == 60)
        {
            aMinute = 0;
        }
        updateTime(aHour, aMinute, 0);
 }
private boolean alarmTriggered()
{
        return alarmSet && ((hour > aHour) || ((hour == aHour) && (minute >= aMinute)));
 }
}