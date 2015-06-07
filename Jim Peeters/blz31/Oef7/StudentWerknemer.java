public class StudentWerknemer extends PartTimeWerknemer{

		public StudentWerknemer(String voornaam, String achternaam, int werknemerNr, float salaris, int urenGw)
		{
			super(voornaam, achternaam, werknemerNr, salaris, urenGw);
			setRSZ(5);
		}
}