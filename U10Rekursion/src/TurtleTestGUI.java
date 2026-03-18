import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TestFrame welches das Anlegen einer Turtle und das Bewegen derselben 
 * veranschaulichen soll
 * @author Michael Wild
 */
public class TurtleTestGUI extends JFrame
{
	
	/**
	 * Turtle-Objekt t kann in allen Methoden des Objekts direkt verwendet werden
	 */
	private Turtle t = null;

	/**
	 * Konstruktor
	 */
	public TurtleTestGUI() {
		setTitle("TurtleTest");
		setBounds(0,0,400,400);

		// ACHTUNG: Turtle darf erst nachdem Fenster sichtbar gemacht wurde angelegt werden
		// ansonsten wird kein Graphics-Objekt angelegt
		setVisible(true);
		// Positioniert die Turtle vom oberen Rand des Frames 200 Pixel nach rechts und
		// 200 Pixel nach unten
		// 90 bedeutet, dass die Turtle um 90 Grad nach links gedreht wird
		t = new Turtle(this,200,200,90);

		// Ereignis: Schlie�en des Fensters
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					setVisible(false);
					dispose();
					System.exit(0);
				}
			}
		);
	}
	
	/**
	 * Zeichnet ein Sechseck indem die Turtle sechsmal um 50 Pixel nach vorne und dann
	 * jeweils um 60 Grad nach rechts positioniert wird
	 */
	public void paint(Graphics g) {
		// Dadurch wird der Hintergrund des Fensters gezeichnet
		super.paint(g);
		if (t != null)
			for (int i = 0; i < 6; i = i + 1) {
				t.vor(50);
				t.drehe(60);
			}
		else
			// Sollte beim ersten Anzeigen des Fensters die Turtle noch nicht existieren, dann 
			// versuche Fenster nochmals zu zeichnen
			repaint();
	}
}
