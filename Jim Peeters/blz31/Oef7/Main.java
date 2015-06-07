public class Main {

	public static void main(String args[])
    {
     	StudentWerknemer studentWerknemer1 = new StudentWerknemer("Amber", "Tersago", 5, 10.0f, 3);

     	System.out.println("Het RSZ-percentage van student werknemer " + studentWerknemer1.voornaam + ": " + studentWerknemer1.getRSZ() +"%.");
    }
}