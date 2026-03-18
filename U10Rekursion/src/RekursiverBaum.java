import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RekursiverBaum extends JFrame
{
	
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
		
		}
	}
	
	private Turtle t = null;
	
	public RekursiverBaum()	{
		setTitle("RekursiverBaum");
		setBounds(0,0,1000,1000);

		// ACHTUNG: Turtle darf erst nachdem Fenster sichtbar gemacht wurde angelegt werden
		// ansonsten wird kein Graphics-Objekt angelegt
		setVisible(true);
		// Positioniert die Turtle vom oberen Rand des Frames 200 Pixel nach rechts und
		// 200 Pixel nach unten
		// 90 bedeutet, dass die Turtle um 90 Grad nach links gedreht wird
		t = new Turtle(this,500,950,90);

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
	
	public void paint(Graphics g) {
		
		// Dadurch wird der Hintergrund des Fensters gezeichnet
		super.paint(g);
		if (t != null)
			baum2(g, 200);
		else
			// Sollte beim ersten Anzeigen des Fensters die Turtle noch nicht existieren, dann 
			// versuche Fenster nochmals zu zeichnen
			repaint();
	}
	
	public void baum2(Graphics g, double laenge)	{
		bremse(1);
		if (laenge < 10)	{
			t.setzeRichtung(270);
			System.out.println("fertig");
			return;
		}
		else	{
			if (laenge == 200)	{
				t.vor(laenge);
				baum2(g, laenge * 0.75);
			}
			else	{
				if (laenge < 200)	{
					double posx = t.aktuellesX();
					double posy = t.aktuellesY();
					double posrichtung = t.aktuelleRichtung();
					t.drehe(30);
					t.vor(laenge);
					baum2(g, laenge * 0.75);
					t.stiftHoch();			
					t.geheZu(posx, posy);
					t.stiftRunter();
					t.setzeRichtung(posrichtung);
					t.drehe(-30);
					t.vor(laenge);
					baum2(g, laenge * 0.75);
				}
			}
		}
	}
	
	public static void main(String[] args) {			// Zum starten des Programms
		RekursiverBaum t = new RekursiverBaum();
	}
}
