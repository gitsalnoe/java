import java.awt.*;

/**
 * 
 * @author Horst Gierhardt, Version vom 31.10.2004
 * Quelle: http://www.oberstufeninformatik.de/info11/turtle/turtle.html
 * 
 */
class Turtle
{
  /*
   * Aktuelle Postion der Turtle
   */
	private double posX, posY;    
	/*
	 * Aktueller Blickwinkel
	 */
  private double winkel;
  /*
   * Standard-Zeichenfarbe
   */
  private Color farbe=Color.black;
  private Container c;
  /*
   * Home-Koordinaten
   */
  private double homeX, homeY;
  private boolean stiftUnten;
  private boolean kos = false;
  private int breiteX, hoeheY;
  private double xScale, yScale;
  private double xOffset, yOffset;
  private Graphics g;

  public Turtle(Container cont) {
  	this(cont, 0, 0, 0);
  }

  /**
   * In der linken oberen Ecke ist der Punkt (0|0).
   * Positive x-Koord. sind rechts davon, positive y-Koord. sind darunter.
   * x und y sind die Koordinaten des Startpunktes.
   * richtung ist der anfaengliche Blickwinkel (0: nach rechts)
   * @param cont
   * @param x
   * @param y
   * @param richtung
   */
  public Turtle(Container cont, double x, double y, double richtung) {
    posX=x;
    posY=y;
    winkel=richtung;
    homeX=x;
    homeY=y;
    c=cont;
    g=c.getGraphics();
    stiftUnten = true;
    breiteX = c.getBounds().width;
    hoeheY = c.getBounds().height;
  }

  private int sx(double x) {
  	return (int)Math.round(x * xScale + xOffset); 
  }
  
  private int sy(double y) { 
  	return (int)Math.round(y * yScale + yOffset); 
  }

  private double bogen(double winkel) {
    return winkel*Math.PI/180;
  }
  /**
   * Erlaubt die Definition eines neuen Koordinatensystems, das unabhängig von den 
   * Bildschirmkoordinaten ist. Der Startpunkt der Turtle ist nach der Definition des 
   * KOS in der Mitte des Bildschirms. Der aktuelle Winkel bleibt erhalten 
   * @param xMin
   * @param xMax
   * @param yMin
   * @param yMax
   */
  public void setzeKOS(double xMin, double xMax, double yMin, double yMax) {
    xScale = breiteX  / (xMax - xMin);
    yScale = -hoeheY / (yMax - yMin);
    xOffset = -xMin * xScale;
    yOffset = hoeheY - yMin * yScale;
    kos = true;
    posX = (xMax + xMin) / 2.0; // Standardmaessig in die Mitte setzen
    posY = (yMax + yMin) / 2.0;
    homeX=posX;
    homeY=posY;
  }

  /**
   * Bringt die Turtle zum zuerst angegebenen Punkt 
   */
  public void zumStartpunkt() {
    posX=homeX;
    posY=homeY;
  }

  /**
   * Die Turtle geht laenge Einheiten in aktueller Blickrichtung nach vorne
   * @param laenge
   */
  public void vor(double laenge) {
  	g.setColor(farbe);
    double neuX = posX + Math.cos(bogen(winkel))*laenge;
    double neuY;
    if (kos) {
    	neuY = posY + Math.sin(bogen(winkel))*laenge;
    	if (stiftUnten) 
    		g.drawLine(sx(posX), sy(posY), sx(neuX), sy(neuY));
    } else {
    	neuY = posY - Math.sin(bogen(winkel))*laenge;
    	if (stiftUnten) 
    		g.drawLine((int) posX, (int) posY, (int) neuX, (int) neuY);
    }
    posX = neuX;
    posY = neuY;
  }

  /**
   * Dreht im mathematisch positiven Sinn um den Winkel grad
   * @param grad
   */
  public void drehe(double grad) {
    winkel = winkel + grad;
  }

  /**
   * zumStartpunkt(), setzeRichtung(0) und Löschen des Fensters
   */
  public void loesche() {
    zumStartpunkt();
    winkel=0;
    int x=c.getBounds().width;
    int y=c.getBounds().height;
    g.clearRect(0, 0, x, y);
  }

  /**
   * Setzen eines absoluten Blickwinkels
   * @param grad
   */
  public void setzeRichtung(double grad) {
    winkel = grad;
  }

  /**
   * Bringt die Turtle auf die absolute Position (neuX|neuY)
   * @param neuX
   * @param neuY
   */
  public void geheZu(double neuX, double neuY) {
    g.setColor(farbe);
    if (kos) {
    	if (stiftUnten) 
    		g.drawLine(sx(posX), sy(posY), sx(neuX), sy(neuY));
    } else {
    	if (stiftUnten) 
    		g.drawLine((int) posX, (int) posY, (int) neuX, (int) neuY);
    }
    posX = neuX;
    posY = neuY;
  }

  /**
   * zeichnet eine Strecke zwischen zwei Punkten. Die Methode passt zwar nicht so 
   * richtig zum Turtle-Konzept, sie ist aber in der Praxis ganz nützlich
   * @param vonX
   * @param vonY
   * @param nachX
   * @param nachY
   */
  public void strecke(double vonX, double vonY, double nachX, double nachY) {
    g.setColor(farbe);
    if (kos) {
      g.drawLine(sx(vonX), sy(vonY), sx(nachX), sy(nachY));
    } else {
      g.drawLine((int) vonX, (int) vonY, (int) nachX, (int) nachY);
    }
  }
  
  /**
   * zeichnet einen 'dicken' Punkt ueber fillOval, wobei dicke die Größe in Pixeln 
   * angibt
   * @param pX
   * @param pY
   * @param dicke
   */
  public void dickPunkt(double pX, double pY, int dicke) {
    g.setColor(farbe);
    int xversatz = dicke/2;
    int yversatz = dicke/2;
    if (kos) {
      g.fillOval(sx(pX) - xversatz, sy(pY)- yversatz, dicke, dicke);
    } else {
      g.fillOval((int)pX - xversatz, (int)pY - yversatz, dicke, dicke);
    }
  } 

  /**
   * Vordefiniert sind Color.black, Color.blue, Color.cyan, Color.darkGray,
   * Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, 
   * Color.pink, Color.red, Color.white, Color.yellow<br>
   * Andere Farben kann man z.B. mit stiftfarbe (new Color(int rot, int gruen, 
   * int blau)); erzeugen. Die Werte fuer rot, gruen und blau müssen im Bereich 
   * zwischen 0 und 255 liegen
   * @param c
   */
  public void stiftfarbe(Color c) {
    farbe = c;
  }

  public void stiftHoch() {
    stiftUnten=false;
  }

  public void stiftRunter() {
    stiftUnten=true;
  }

  public double aktuelleRichtung() {
    return winkel;
  }

  public double aktuellesX() {
    return posX;
  }

  public double aktuellesY() {
    return posY;
  }

  public Color aktuelleFarbe() {
    return (Color)farbe;
  }

  public void schreibe(String str, double x, double y) {
    if (kos) {
    	if (stiftUnten) 
    		g.drawString(str, sx(x), sy(y));
    } else {
    	if (stiftUnten) 
    		g.drawString(str, (int) x, (int) y);
    }
  }

  /**
   * Zeichnet wieder im normalen Modus
   */
  public void zeichneNormal() {
    g.setPaintMode();
  }

  /**
   * Zeichnet im XOR-Modus. Zweimaliges Zeichnen führt wieder zum Ausgangszustand 
   */
  public void zeichneXOR() {
    g.setXORMode(Color.lightGray);
  }

}