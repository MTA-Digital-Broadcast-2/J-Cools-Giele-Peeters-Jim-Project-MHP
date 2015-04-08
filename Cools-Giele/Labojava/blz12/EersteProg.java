import java.lang.*;

/**
* Deze klasse is een java programma.
* @author Giele Cools
*/

public class EersteProg
{
/**
* Dit is de main methode, hier start het programma. 
* @param args Dit zijn de command line parameters.
*/
	public static void main(String args[])
	{
		drukaf(100);
	}	

/**
* Dit is de drukaf methode, hierin worden alle getallen van 0 tot
* (niet tot en met) het via de parameter meegegeven getal afgedrukt.
*/
	private static void drukaf(int m)
	{
		int a;
		for(a=0; a<m; a++)
		{
			System.out.println(a);
		}
	}
}
