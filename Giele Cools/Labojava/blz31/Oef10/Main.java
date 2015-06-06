public class Main {

	public static void main(String args[])
    {
     	Faktuur faktuur1 = new Faktuur(10, 280);
     	Werknemer werknemer1 = new Werknemer("Ronny", "Tersago", 1, 20.0f);

     	faktuur1.betaal();
     	werknemer1.betaal();
    }
}