public class Main {

	public static void main(String args[])
    {
     	Werknemer werknemer1 = new Werknemer("Ronny", "Tersago", 1, 20.0f);
     	PartTimeWerknemer partTimeWerknemer1 = new PartTimeWerknemer("Jos", "den Dief", 6, 50.0f, 2); 
     	StudentWerknemer studentWerknemer1 = new StudentWerknemer("Amber", "Tersago", 5, 10.0f, 3);

     	werknemer1.betaal();
     	partTimeWerknemer1.betaal();
     	studentWerknemer1.betaal();
    }
}