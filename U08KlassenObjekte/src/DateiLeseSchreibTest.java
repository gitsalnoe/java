/**
 * Bindet die nachfolgend verwendeten Klassen BufferedReader, BufferedWriter,
 * FileReader, FileWriter und die verwendeten Exceptions ein
 */
import java.io.*;

/**
 * Diese Klasse soll das zeilenweise Lesen aus einer Datei (quelle) und das 
 * zeilenweise Schreiben in eine Datei (ziel) veranschaulichen 
 * @author Michael Wild
 *
 */
public class DateiLeseSchreibTest
{
	public static void main(String[] args) {
		// Pfad zu den Textdateien
		String quelle = "H:\\quelle.txt";
		String ziel = "H:\\ziel.txt";
		
		// Zeilenweises Lesen aus einer Datei
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quelle));
			while (true) {
				String zeile = reader.readLine();
				if (zeile == null)
					// Dateiende erkannt
					break;
				else
					System.out.println(zeile);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Lesefehler in Datei");
		}
		
		// Zeilenweises Schreiben in eine Datei
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ziel));
			// ACHTUNG: Am Ende jeder Zeile muss eine Zeilenschaltung \n eingef�gt werden
			writer.write("1. Zeile: Hallo\n");
			writer.write("2. Zeile: Ziel\n");
			writer.write("3. Zeile: Datei");
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht angelegt");
		}
	}
}