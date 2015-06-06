
public class Oef3
{
        public static void main(String args[])
        {
			double pi = 0;					//Alles in doubles, want anders ontstaan er afrondingsfouten door de berekeningen tussen ints en doubles
			double som = 0;
			double teller = 1;
			double noemer = 1;
			for(int i=0; i<10000; i++)
			{
				som+=(teller/noemer);
				//System.out.println("BREUK: " + teller +"/"+noemer);
				noemer += 2.0;
				teller *= -1.0;
			}

			pi = 4.0*som;
			System.out.println("PI IS: " + pi);
        }

}
