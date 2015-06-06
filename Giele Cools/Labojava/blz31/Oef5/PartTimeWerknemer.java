public class PartTimeWerknemer extends Werknemer {

	public int urenGewerkt;

	public PartTimeWerknemer( String voornaam, String achternaam, int werknemerNr, float salaris, int urenGw)
	{
		super(voornaam, achternaam, werknemerNr, salaris);
		this.urenGewerkt = urenGw;
	}

	public float getWeekLoon()
	{
		return this.salaris * (float)this.urenGewerkt;
	}

	public void salarisVerhogen(int percentage)
	{
		if (percentage > 5) 
		{
			percentage = 0;
			System.out.println("Fout: slechts 5% opslag toegestaan.");
		}
		else
		{
			super.salarisVerhogen(percentage);
		}
	}
}