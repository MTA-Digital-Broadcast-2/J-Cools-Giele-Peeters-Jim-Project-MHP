public class Main {

	public static void main(String args[])
    {
     	Werknemer werknemer1 = new Werknemer("Ronny", "Tersago", 1, 20.0f);
     	PartTimeWerknemer partTimeWerknemer1 = new PartTimeWerknemer("Ma", "Tersago", 2, 30.0f, 2);

     	werknemer1.setRSZ(10.0f);
     	partTimeWerknemer1.setRSZ(20.0f);

     	System.out.println("Het RSZ-percentage van werknemer " + werknemer1.voornaam + ": " + werknemer1.getRSZ() +"%.");
     	System.out.println("Het RSZ-percentage van halftijdse werknemer " + partTimeWerknemer1.voornaam + ": " + partTimeWerknemer1.getRSZ() +"%.");
    }
}