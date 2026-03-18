package u12package;
import java.util.Scanner;

public class Fahrzeugverwaltung {

	public static void main(String[] args)	{
		System.out.println("Fahrzeugverwaltung");
		System.out.println("==================");	
		System.out.println("1 - Eingabe");
		System.out.println("2 - Suchen");
		System.out.println("3 - Ändern");
		System.out.println("4 - Löschen");
		System.out.println("5 - Liste");
		System.out.println("6 - Ende");
		int wahl = TestScannerErweitert.readInt("Ihre Wahl (1 - 6):");
		
		switch(wahl)	{
			case 1:
				System.out.println("1 - Fahrrad");
				System.out.println("2 - Auto");
				System.out.println("3 - Lastwagen");
				int fahrzeugwahl = TestScannerErweitert.readInt("Ihre Wahl (1 - 3)");
				
				switch(fahrzeugwahl)	{
					case 1:
						int geschwindigkeit = TestScannerErweitert.readInt("Geschwindigkeit:");
						char beleuchtungScanner = TestScannerErweitert.readChar("beleuchtung(j/n):");
						System.out.println(beleuchtungScanner);
						while (beleuchtungScanner != 'j' && beleuchtungScanner != 'n')	{
							System.out.println("Geben sie nur j (ja) oder n (nein) ein");
							beleuchtungScanner = TestScannerErweitert.readChar("beleuchtung(j/n):");
						}
						boolean beleuchtung = false;
						if (beleuchtungScanner == 'j')	{
							beleuchtung = true;
						}
						int kilometerstand = TestScannerErweitert.readInt("Kilometerstand:");
						Fahrrad f = new Fahrrad(geschwindigkeit);
						f.setKilometer(kilometerstand);
						f.setBeleuchtung(beleuchtung);
						
				}
		}
	}
}
