package u12package;

//Unterklasse Lastwagen
public class Lastwagen extends Auto {
 private int ladeflaeche;

 // Konstruktor mit drei Parametern
 public Lastwagen(int geschwindigkeit, int ps, int ladeflaeche) {
     super(geschwindigkeit, ps);
     this.setLadeflaeche(ladeflaeche);
 }

 // Getter & Setter Ladefläche
 public void setLadeflaeche(int ladeflaeche) {
     if (ladeflaeche >= 0)
         this.ladeflaeche = ladeflaeche;
     else
         this.ladeflaeche = 0;
 }

 public int getLadeflaeche() {
     return this.ladeflaeche;
 }

 // Überschreiben von setPs: max 150 PS
 @Override
 public void setPs(int ps) {
     if (ps > 150)
         this.ps = 150;
     else if (ps > 0)
         this.ps = ps;
     else
         this.ps = 0;
 }

 @Override
 public String toString() {
     return "Lastwagen: G:" + geschwindigkeit + " PS:" + ps + ", L:" + ladeflaeche + ", K:" + kilometer;
 }
}

