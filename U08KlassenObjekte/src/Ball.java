import java.awt.Color; //Fuer die Farben

public class Ball
{
	private int radius = 0; 
	private int xposition = 60;
	private int yposition = 80;
	private int xrichtung = 0; 
	private int yrichtung = 0; 
	private Color farbe = Color.BLACK;
	

	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		if (radius > 0) {
			this.radius = radius;
		}
	}


	public int getXposition() {
		return xposition;
	}


	public void setXposition(int xposition) {
		if (xposition > 0) {
			this.xposition = xposition;
		}
	}


	public int getYposition() {
		return yposition;
	}


	public void setYposition(int yposition) {
		if (yposition > 0)	{
			this.yposition = yposition;
		}
	}
	
	public int getXrichtung() {
		return xrichtung;
	}


	public void setXrichtung(int xrichtung) {
		this.xrichtung = xrichtung;
	}


	public int getYrichtung() {
		return yrichtung;
	}


	public void setYrichtung(int yrichtung) {
		this.yrichtung = yrichtung;
	}


	public java.awt.Color getFarbe() {
		return farbe;
	}


	public void setFarbe(java.awt.Color farbe) {
		this.farbe = farbe;
	}

	public String toString()	{
		return "r = " + radius + ", xposition = " + xposition + ", yposition = " + yposition + ", xrichtung = " + xrichtung + ", yrichtung = " + yrichtung;
	}
	
	public void setZufaellig() {
		this.radius = (int)((Math.random() * (40 - 2)) + 2);							//Math.random() * (max - min) + min
		while (this.xrichtung == 0)				
			this.xrichtung = (int)((Math.random() * (10 - (-10))) + (-10));
		while (this.yrichtung == 0)
			this.yrichtung = (int)((Math.random() * (10 - (-10))) + (-10));
		this.farbe = new Color((int)(Math.random() * 255), (int)(Math.random() * 255) , (int)(Math.random() * 255));

	}
	
	public void bewege(java.awt.Graphics g, double breite, double hohe)	{
			int posx = getXposition();				//letzte Position
			int posy = getYposition();
			g.setColor(getFarbe());
			g.drawOval(posx, posy, radius, radius);
			g.fillOval(posx, posy, radius+1, radius+1);		//+1 damit es schöner aussieht 
			if (posx-radius <= 0 || posx+radius >= breite)	{		//Für das refklektieren
				setXrichtung(getXrichtung()*(-1));				
			}	
			else if (posy-radius <= 0 || posy+radius >= hohe)	{
				setYrichtung(getYrichtung()*(-1));
			}
			setXposition(posx+getXrichtung());			//Nächste Position 
			setYposition(posy+getYrichtung());
	}
}
