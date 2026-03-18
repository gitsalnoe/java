package tictactoe;

public class TicTacToe {
	
	static int SPIELER1 = -1;
	static int SPIELER2 = -2;
	private int[][] spielfeld;
	
	public TicTacToe(int feldgroesse)	{
		spielfeld = new int[feldgroesse][feldgroesse];
		int zahl = 0;
		for (int i = 0; i < spielfeld.length; i++)	{
			for (int j = 0; j < spielfeld[0].length; j++)	{
				spielfeld[i][j] = zahl;
				zahl++;
			}
		}
	}
	
	public String toString()	{
		String ausgabe = "";
		for (int i = 0; i < spielfeld.length; i++)	{
			for (int j = 0; j < spielfeld[0].length; j++)	{
				if (spielfeld[i][j] == -1)	{
					ausgabe = ausgabe + "X";
				}	else if (spielfeld[i][j] == -2)	{
					ausgabe = ausgabe + "O";
					}	
					else	{
					ausgabe = ausgabe + spielfeld[i][j];
					}
			}
			ausgabe = ausgabe + "\n";
		}
		return ausgabe;
	}
	
	public int getFeldgroesse()	{
		return spielfeld.length;
	}
	
	public int getSpielfeld(int zeile, int spalte) {
		int position = spielfeld[zeile][spalte];
		if (position == SPIELER1)	{
			return SPIELER1;
		}
		if (position == SPIELER2)	{
			return SPIELER2;
		}
		if (zeile > spielfeld.length || spalte > spielfeld[0].length)	{
			return -3;
		}
		if (position != SPIELER1 && position != SPIELER2)	{
			return 0;
		}
		return -1;
	}
	
	private int setZug(int zug, int spielernummer)	{
		if (zug < 0 || zug >= spielfeld.length*spielfeld.length)	{
			return -1;
		}
		for (int i = 0; i < spielfeld.length; i++)	{
			for (int j = 0; j < spielfeld[0].length; j++) {
				if (spielfeld[i][j] == zug)	{
					spielfeld[i][j] = spielernummer;
					return 0;
				}
			}
		}
		return -2;
	}
		
	public int setZugSpieler1(int zug)	{
		return setZug(zug, -1);
	}
	
	public int setZugSpieler2(int zug)	{
		return setZug(zug, -2);
	}
	
	/**
	 * Diese Methode prüft ob einer der beide Spieler gewonnen hat.
	 * Als erstes werden alle Reihen und Spalten angeschaut, dann die Diagonale von Oben links nach unten rechts
	 * und dann die Diagonale von oben rechts bis unten links
	 * @return 0, falls noch niemand gewonnen hat
	 * @return -1, falls SPIELER1 gewonnen hat
	 * @return -2, falls der SPIELER2 gewonnen hat
	 */
	public int getGewonnen()	{
		
	    for (int i = 0; i < spielfeld.length; i++) {	// Schaut Reihen und Spalten an

	        if (alleGleich(spielfeld[i])) {		//Reihe
	            return spielfeld[i][0];
	        }

	        int[] spalte = new int[spielfeld.length];	//Spalte
	        
	        for (int j = 0; j < spielfeld.length; j++) {
	            spalte[j] = spielfeld[j][i];
	        }

	        if (alleGleich(spalte)) {
	            return spalte[0];
	        }
	    }

	    int[] linksrechts = new int[spielfeld.length];	//Diagonale von oben links nach unten rechts
	    for (int i = 0; i < spielfeld.length; i++) {
	        linksrechts[i] = spielfeld[i][i];
	    }

	    if (alleGleich(linksrechts)) {
	        return linksrechts[0];
	    }
	    
	    int[] rechtslinks = new int[spielfeld.length];	//Diagonale von oben rechts bis unten links
	    for (int i = 0; i < spielfeld.length; i++) {
	        rechtslinks[i] = spielfeld[i][spielfeld.length - 1 - i];
	    }

	    if (alleGleich(rechtslinks)) {
	        return rechtslinks[0];
	    }

	    return 0;
	}
	
	/*
	 * Schaut ob alle elemente im Abschnitt vom Spielfeld gleich sind
	 * @return false, falls ein Element im Array noch nicht besetzt oder nicht gleich sind
	 * @return true, falls alle Elemente im Array gleich sind 
	 */
	
	private boolean alleGleich(int[] array) {

	    if (array[0] == 0) {
	        return false;
	    }

	    for (int i = 0; i < array.length; i++) {
	        if (array[i] != array[0]) {
	            return false;
	        }
	    }
	    return true;
	}
}	


