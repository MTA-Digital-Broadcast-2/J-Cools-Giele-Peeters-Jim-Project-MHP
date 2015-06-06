public class Main {

	public static void main(String args[])
    {
        PartTimeWerknemer partTimeWerknemer1 = new PartTimeWerknemer("Ma", "Tersago", 5, 30.0f, 2);
        PartTimeWerknemer partTimeWerknemer2 = new PartTimeWerknemer("Jos", "den Dief", 6, 50.0f, 2); 

        partTimeWerknemer1.salarisVerhogen(10);
          
        System.out.println("Salaris halftijdse werknemer " + partTimeWerknemer1.voornaam + ": €" +partTimeWerknemer1.salaris);
        System.out.println("Salaris halftijdse werknemer " + partTimeWerknemer2.voornaam + ": €" +partTimeWerknemer2.salaris);
    }
}