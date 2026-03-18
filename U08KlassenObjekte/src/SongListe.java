import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class SongListe
{

	public static void main(String[] args) {
	// Pfad zu den Textdateien
			String quelle = "C:\\Users\\salno\\Desktop\\Informatik\\Rohdateien\\Rohdateien8\\Rohdateien\\tracklist.csv";
			String ziel = "C:\\Users\\salno\\Desktop\\Informatik\\Rohdateien\\Rohdateien8\\Rohdateien\\sortlist.csv";
			
			String[] songs = new String[613];
			
			int zeilen = 0;
			// Zeilenweises Lesen aus einer Datei
			try {
				BufferedReader reader = new BufferedReader(new FileReader(quelle));
				songs[0] = reader.readLine();
				
				/*
				String zeile = reader.readLine();
				for (int i = 1; i <= 613; i++)	{
					String vorzeile = reader.readLine();
					if (zeile.compareTo(vorzeile) > 0)	{
						songs[i] = vorzeile;
					}
					else	{
						songs[i] = zeile;
						break;
					}
				}
				*/
				
				reader.close();
				System.out.println(zeilen);
				
			} catch (FileNotFoundException e) {
				System.out.println("Datei nicht gefunden");
			} catch (IOException e) {
				System.out.println("Lesefehler in Datei");
			}
	}

}
