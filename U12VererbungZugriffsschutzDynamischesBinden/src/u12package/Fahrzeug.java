package u12package;

//Oberklasse
public class Fahrzeug {
 protected int geschwindigkeit = 0;
 protected int kilometer = 0;  // nicht kleiner als 0

 // Konstruktor
 public Fahrzeug(int geschwindigkeit) {
     this.setGeschwindigkeit(geschwindigkeit);
 }

 // Getter und Setter für Geschwindigkeit
 public void setGeschwindigkeit(int geschwindigkeit) {
     if (geschwindigkeit > 0)
         this.geschwindigkeit = geschwindigkeit;
     else
         this.geschwindigkeit = 0;
 }

 public int getGeschwindigkeit() {
     return this.geschwindigkeit;
 }

 // Getter und Setter für Kilometer
 public void setKilometer(int kilometer) {
     if (kilometer >= 0)
         this.kilometer = kilometer;
     else
         this.kilometer = 0;
 }

 public int getKilometer() {
     return this.kilometer;
 }

 // toString für Übersicht
 @Override
 public String toString() {
     return "Fahrzeug: Geschwindigkeit = " + geschwindigkeit + " km/h, Kilometer = " + kilometer;
 }
}

