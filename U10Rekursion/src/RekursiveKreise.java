
import java.awt.*;
import java.applet.*;

public class RekursiveKreise extends Applet
{
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
				
		}
	}
	
	public void paint(Graphics g) {
		int[][] pos = null;
		int layers = 4;							//wie viele Layers von Kreise vorkommen sollen
		kreis4(g, 200, 100, 100, layers, layers);
	}
	/*
	 * Diese Methode gibt Rekursiv ein Muster von Kreisen aus
	 * @param g idk
	 * @param radius radius der kreise
	 * @param x position des Kreises auf der x achse
	 * @param y position des Kreises auf der y achse
	 * @param layers anzahl der Layer von Kreisen
	 * @param anfangslayers layers die am anfang eingegeben wurden (andert sich nie)
	 */
	public void kreis4(Graphics g, int radius, int x, int y, int layers, int anfangslayers) {		
		bremse(100);
		int[][] pos = {																			//positionen der einzelnen Kreise relativ zum groesseren Kreis
				{x + radius/4, y - radius/4},										//oben
				{x - radius/4, y + radius/4},										//links
				{x + radius/4, y + radius - radius/4},					//unten
				{x + radius - radius/4, y + radius/4}};					//rechts
		
		if (layers == anfangslayers) {
			g.drawOval(x, y, radius, radius);									//der erste Kreis der gemacht wird
			kreis4(g, radius/2, pos[0][0], pos[0][1], layers - 1, anfangslayers);			//die weiteren Layers 
			kreis4(g, radius/2, pos[1][0], pos[1][1], layers - 1, anfangslayers);
			kreis4(g, radius/2, pos[2][0], pos[2][1], layers - 1, anfangslayers);
			kreis4(g, radius/2, pos[3][0], pos[3][1], layers - 1, anfangslayers);
		}
		if (layers >= 1 && layers <= anfangslayers - 1) {	 //alle anderen Kreise die gemacht werden auser das erste 
			g.drawOval(x, y, radius, radius);
			
			kreis4(g, radius/2, pos[0][0], pos[0][1], layers - 1, anfangslayers);	//oben
			
			kreis4(g, radius/2, pos[1][0], pos[1][1], layers - 1, anfangslayers);	//links
			
			kreis4(g, radius/2, pos[2][0], pos[2][1], layers - 1, anfangslayers);	//unten
			
			kreis4(g, radius/2, pos[3][0], pos[3][1], layers - 1, anfangslayers);	//rechts
		}
		if (layers == 0)	{		//abbruch
			return;
		}
	}
}
	