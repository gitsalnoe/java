import java.applet.*;
import java.awt.*;

/**
 * Applet welches die Klasse Ball verwendet und insgesamt 10 B�lle im 
 * Zeichenbereich bewegt
 * @author Michael Wild
 */
public class JumpingBalls extends Applet
{
	/** 
	 * Statische Membervariable, welche sich in einem Array die zu bewegenden B�lle
	 * merkt. Diese Variable darf nicht als lokale Variable innerhalb der paint-Methode
	 * definiert werden, weil sie ansonsten nach jedem Aufruf der Methode zerst�rt 
	 * w�rde. Da aber die einzelnen Ballobjekte nach dem Ende der Methode und beim
	 * n�chten Auruf der paint-Methode noch leben m�ssen, werden diese in einer
	 * statischen Membervariable der Klasse Applet abgelegt, die auch dann noch
	 * existiert und ihre Inhalte beh�lt, wenn ein Aufruf der paint-Methode fertig 
	 * abgearbeitet wurde 
	 */
	private static Ball[] ball = null;
	/**
	 * Statische Konstante, welche sich die Anzahl der zu bewegenden B�lle merkt
	 */
	private static	final int ANZAHL_BAELLE = 10;

	/**
	 * Diese Methode wird immer dann aufgerufen, wenn der Inhalt des Applets neu 
	 * gezeichnet werden soll
	 */
	public void paint(Graphics g) {
		// Falls noch keine B�lle vorhanden sind, wird ein Array angelegt, und
		// dieses Array nimmt dann die Ballobjekte auf, welche mit zuf�lligen Werten
		// gef�llt werden
		if (ball == null) {
			ball = new Ball[ANZAHL_BAELLE];
			for (int i = 0; i < ANZAHL_BAELLE; i = i + 1) {
				ball[i] = new Ball();
				ball[i].setZufaellig();
				System.out.println(ball[i].toString());
			}
		}

		// Dann werden die B�lle um einen Schritt �ber den Zeichenbereich bewegt
		for (int i = 0; i < ANZAHL_BAELLE; i = i + 1)
			ball[i].bewege(g, getWidth(), getHeight());
		// Das Programm wartet eine gewisse Anzahl von Millisekunden
		bremse(10);
		// Dann wird die repaint-Methode aufgerufen, welche nichts anderes macht als
		// die paint-Methode wieder zu starten
		repaint();
	}
	
	/**
	 * Veranlasst dass das Programm millis Millisekunden pausiert
	 * @param millis Anzahl der Millisekunden die gewartet werden
	 */
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}