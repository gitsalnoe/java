package u12package;

public class Fahrrad extends Fahrzeug {
private boolean beleuchtung;

 public Fahrrad(int geschwindigkeit) {
     super(Math.max(0, Math.min(60, geschwindigkeit)));
 }

 public void setBeleuchtung(boolean beleuchtung) {
	 beleuchtung = beleuchtung;
 }

 public boolean getBeleuchtung() {
     return this.beleuchtung;
 }
 
 public void setGeschwindigkeit(int geschwindigkeit)	{
	 super.setGeschwindigkeit(Math.max(0, Math.min(60, geschwindigkeit)));
 }

 @Override
 public String toString() {
     return "Fahrrad: Geschwindigkeit = " + geschwindigkeit + " km/h, Beleuchtung = " + beleuchtung  + ", Kilometer = " + kilometer;
 }
}

