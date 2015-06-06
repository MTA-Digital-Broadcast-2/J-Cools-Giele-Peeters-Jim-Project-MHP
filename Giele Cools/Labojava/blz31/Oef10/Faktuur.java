public class Faktuur implements Betaalbaar{

	public int faktuurNr;
	public float faktuurBedrag;

	public Faktuur(int faktuurnummer, float faktuurBedrag)
	{
		this.faktuurNr = faktuurnummer;
		this.faktuurBedrag = faktuurBedrag;
	}

	public void betaal()
	{
		System.out.println("Betaal het faktuur met nummer " + faktuurNr + " voor een bedrag van €" + faktuurBedrag + ".");
	}
}