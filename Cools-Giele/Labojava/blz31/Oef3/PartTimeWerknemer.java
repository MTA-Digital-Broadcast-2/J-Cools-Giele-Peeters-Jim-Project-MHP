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
}