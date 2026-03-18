import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BaumPythagoras2 extends JFrame
{
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
		
		}
	}
	
	private Turtle t = null;
	
	public BaumPythagoras2()	{
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
			//baum2(g, 100);
			//baum(g, 100);
			//baum3(g, 100);
			baum4(g, 100);
		else
			// Sollte beim ersten Anzeigen des Fensters die Turtle noch nicht existieren, dann 
			// versuche Fenster nochmals zu zeichnen
			repaint();
	}
	
	
	public void baum4(Graphics g, double laenge)	{
		bremse(100);
		for (int i = 0; i <= 3; i++)	{
			t.vor(laenge);
			t.drehe(-90);
		}
		if (laenge <= 5) {
			System.out.println("fertig");
			t.drehe(-40);
			t.vor(-1*(laenge/Math.cos(Math.toRadians(40))));
			t.vor(110);
			return;
		}
		else	{
			t.vor(laenge);
			t.drehe(40);
			double b = Math.cos(Math.toRadians(40))*laenge;
			//System.out.println(b);
			baum4(g, b);
			t.drehe(-90);
			t.vor(-laenge);
			t.drehe(90);
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void baum3(Graphics g, double laenge)	{
		bremse(100);
		double bposx = 0;											//Position von unten rechts 
		double bposy = 0;
		double bposrichtung = 0;
		for (int i = 0; i <= 3; i++)	{
			t.vor(laenge);
			if (i == 3)	{
				bposx = t.aktuellesX();
				bposy = t.aktuellesY();
				bposrichtung = t.aktuelleRichtung();
			}
			t.drehe(-90);
		}
		if (laenge > 5)	{
			t.vor(laenge);
			t.drehe(40);
			double b = Math.cos(Math.toRadians(40))*laenge;
			baum3(g, b);
			t.drehe(90);
			double a = Math.sin(Math.toRadians(40))*laenge;
			baum3(g, a);
		}
		else	{
			System.out.println("fertig");
			t.geheZu(bposx, bposy);
			t.setzeRichtung(bposrichtung);
			return;
		}
		
	}
	
	public void baum2(Graphics g, double seitec)	{
		bremse(100);
		for (int i = 0; i <= 3; i++)	{
			t.vor(seitec);
			t.drehe(-90);
		}
		double posx = t.aktuellesX();
		double posy = t.aktuellesY();
		double posrichtung = t.aktuelleRichtung();
		if (seitec <= 5) {
			System.out.println("fertig");
			t.drehe(90);
			t.vor(seitec);
			t.drehe(90);
			return;
		}
		else	{
			t.vor(seitec);
			t.drehe(40);
			baum2(g, seitec * 0.80);
			t.geheZu(posx, posy);
			t.setzeRichtung(posrichtung);
			t.vor(110);
			
		}
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
		double bposx = t.aktuellesX();				//Position und Richtung von b quadraten
		double bposy = t.aktuellesY();
		double bposrichtung = t.aktuelleRichtung();
		if (seitec <= 5) {
			System.out.println("fertig");
			t.stiftHoch();
			t.geheZu(aposx, aposy);
			t.setzeRichtung(aposrichtung);
			t.stiftRunter();
			//t.vor(110);
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
			//t.vor(110);
		}
	}
	

	public static void main(String[] args) {
			BaumPythagoras2 t = new BaumPythagoras2();
	}
}
	
