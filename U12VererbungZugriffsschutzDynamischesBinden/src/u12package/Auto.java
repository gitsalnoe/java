package u12package;

//Unterklasse Auto
public class Auto extends Fahrzeug {
 protected int ps = 0;

 // Konstruktor
 public Auto(int geschwindigkeit, int ps) {
     super(geschwindigkeit);
     this.setPs(ps);
 }

 // Getter & Setter für PS
 public void setPs(int ps) {
     if (ps > 0)
         this.ps = ps;
     else
         this.ps = 0;
 }

 public int getPs() {
     return this.ps;
 }

 public void setGeschwindigkeit(double geschwindigkeit) {
     this.setGeschwindigkeit((int) geschwindigkeit);
 }

 @Override
 public String toString() {
     return "Auto: Geschwindigkeit = " + geschwindigkeit + " km/h, PS = " + ps + ", Kilometer = " + kilometer;
 }
}
