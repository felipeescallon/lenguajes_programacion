
import java.lang.String;
import java.util.Random;

class ThreadBasico implements Runnable
{
	private String frase;
	private Random aleatorio;
	
	public ThreadBasico (String frase)
	{
		this.frase = frase;
		aleatorio = new Random();
	}
	
	public void run()
	{
		try
		{
		do{
			System.out.println(frase);
			Thread.sleep((long)(Math.abs(aleatorio.nextInt())%10));	
		  }
		while(true);	
		}
		catch(InterruptedException e){}
	}	
}