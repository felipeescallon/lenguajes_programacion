
public class ThreadBasicoMain
{
	public static void main(String [] args)
	{
		Thread prihil= new Thread (new ThreadBasico (args[0]));
		Thread seghil= new Thread (new ThreadBasico (args[1]));
		
		prihil.start();
		seghil.start();
	}
}