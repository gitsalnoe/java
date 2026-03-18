package tictactoe;
import tictactoe.TestScannerErweitert;
import tictactoe.TicTacToe;

public class TicTacToeSingle {

	public static void main(String[] args) {
		
		 TicTacToe t = new TicTacToe(3);
	        System.out.println("Tic Tac Toe");
	        System.out.println(t.toString());

	        int gewinner = 0;
	        int aktuellerSpieler = 1;
		 while (gewinner == 0) {
	            int zug = TestScannerErweitert.readInt(aktuellerSpieler + ". Spieler: ");

	            int ergebnis;
	            if (aktuellerSpieler == 1) {
	                ergebnis = t.setZugSpieler1(zug);
	            } else {
	                ergebnis = t.setZugSpieler2(zug);
	            }

	            if (ergebnis == 0) { // Zug erfolgreich
	                System.out.println(t.toString());
	                gewinner = t.getGewonnen();

	                // Spieler wechseln wenn noch kein Gewinner
	                if (gewinner == 0) {
	                    aktuellerSpieler = (aktuellerSpieler == 1) ? 2 : 1;
	                }
	            } else {
	                System.out.println("bitte erneut versuchen!");
	            }
	        }

	        // Spielende auswerten
	        if (gewinner == -1) {
	            System.out.println("Spieler 1 hat gewonnen!");
	        } else if (gewinner == -2) {
	            System.out.println("Spieler 2 hat gewonnen!");
	        }
	    }
}
