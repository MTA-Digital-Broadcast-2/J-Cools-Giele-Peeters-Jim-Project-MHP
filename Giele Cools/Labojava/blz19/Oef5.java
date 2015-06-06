public class Oef5
{
        public static void main(String args[])
        {
			boolean priemgetalOfNiet = true;

			System.out.println("Onderstaande getallen zijn priemgetallen vanaf 3 t.e.m. 99");

			for(int getal=3; getal<100; getal++)
			{ 
				priemgetalOfNiet = true;

				for(int i=2; i<=(getal-1); i++)
				{
					//System.out.println("MODULUS: " + getal%i + " GETAL IS: " + getal + " EN I IS: " + i);

					if(getal%i == 0)
					{
						priemgetalOfNiet = false;
						break;							//gaat uit de lus van zodra het geen priemgetal is, en berekent dan voor de rest niet verder met het getal en de deler
					}
				}

				if(priemgetalOfNiet==true)
				{
					//System.out.println("PRIEMGETAL: " + getal);

					System.out.println(getal);
				}
			}
        }
}


