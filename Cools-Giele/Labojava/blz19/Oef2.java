public class Oef2
{
        public static void main(String args[])
        {
		int weekdag=1;
		for(int i=1; i<29; i++)
		{
			String dag="";
			switch (weekdag)
			{
				case 1: dag="zondag";
					break;
				case 2: dag="maandag";
					break;
				case 3: dag="dinsdag";
					break;
				case 4: dag="woensdag";
					break;
				case 5: dag="donderdag";
					break;
				case 6: dag="vrijdag";
					break;
				case 7: dag="zaterdag";
					break;
				default: dag="onbekend";
					break;
			}
			weekdag++;
			if(weekdag > 7)
			{
				weekdag = 1;
			}
		
		System.out.println(dag + " " + i+ " " + "februari 2009");
		
		}
        }

}


