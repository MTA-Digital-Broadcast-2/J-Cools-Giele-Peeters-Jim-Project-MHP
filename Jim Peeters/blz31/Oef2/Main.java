public class Main {

	public static void main(String args[])
    {
        Werknemer werknemer1 = new Werknemer("Ronny", "Tersago", 1, 20.0f);
        Werknemer werknemer2 = new Werknemer("Mike", "Den dwerg", 2, 10.0f);
        Werknemer werknemer3 = new Werknemer("Lou", "De Man", 3, 5.0f);
        Werknemer werknemer4 = new Werknemer("Wesley", "Tersago", 4, 20.0f);

    	werknemer1.salarisVerhogen(10);
    	werknemer2.salarisVerhogen(10);

    	System.out.println("Salaris " + werknemer1.voornaam + ": €" + werknemer1.salaris);
    	System.out.println("Salaris " + werknemer2.voornaam + ": €" + werknemer2.salaris);
    	System.out.println("Salaris " + werknemer3.voornaam + ": €" + werknemer3.salaris);
    	System.out.println("Salaris " + werknemer4.voornaam + ": €" + werknemer4.salaris);
    }
}