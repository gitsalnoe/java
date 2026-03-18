import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BaumPythagoras extends JFrame
{
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
		
		}
	}
	
	private Turtle t = null;
	
	public BaumPythagoras()	{
		setTitle("BaumPythagoras");
		//setBounds(0,0,500,500);
		setBounds(0,0, 1920, 1080);

		// ACHTUNG: Turtle darf erst nachdem Fenster sichtbar gemacht wurde angelegt werden
		// ansonsten wird kein Graphics-Objekt angelegt
		setVisible(true);
		// Positioniert die Turtle vom oberen Rand des Frames 200 Pixel nach rechts und
		// 200 Pixel nach unten
		// 90 bedeutet, dass die Turtle um 90 Grad nach links gedreht wird
		t = new Turtle(this,250,450,90);

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
			baum(g, 100);
			
		else
			// Sollte beim ersten Anzeigen des Fensters die Turtle noch nicht existieren, dann 
			// versuche Fenster nochmals zu zeichnen
			repaint();
	}
	
	public void baum(Graphics g, double seitec) {
		bremse(100);
		double aposx = 0;											//Position von unten rechts 
		double aposy = 0;
		double aposrichtung = 0;
		for (int i = 0; i <= 3; i++)	{				//Wir starten links unten
			t.vor(seitec);
			if (i == 2) {
				aposx = t.aktuellesX();
				aposy = t.aktuellesY();
				aposrichtung = t.aktuelleRichtung();
			}
			t.drehe(-90);
		}
		t.vor(seitec);												//Jetzt sind wir links oben
		if (seitec <= 5) {
			System.out.println("fertig");
			t.stiftHoch();
			t.geheZu(aposx, aposy);
			t.setzeRichtung(aposrichtung);
			t.stiftRunter();
			return;
		}
		else	{
			t.drehe(40);
			baum(g, seitec * 0.75);		//Quadrat b
			t.stiftHoch();
			t.geheZu(aposx, aposy);
			t.setzeRichtung(aposrichtung);
			t.stiftRunter(); 
			t.drehe(90);
			baum(g, (Math.sin(40*((Math.PI)/180)))*(seitec/0.75));	//Quadrat a
			
		}
	}
	

	public static void main(String[] args) {
			BaumPythagoras t = new BaumPythagoras();
	}
	
	

}
