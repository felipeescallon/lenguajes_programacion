import java.io.*;
import java.lang.*;


class ClaseDemonio extends Thread  
{

public ClaseDemonio()
	{
	setDaemon(true);
	start();
	}

public void run()
	{
	char bip ='\u0007';
	while(true)
	{
	try
		{
		sleep(1000);
		}
	
	catch(InterruptedException e)
	{
	}

	System.out.print(bip);
	}
	}
}