package interfacesPackage;

public class Papierblatt implements Groesse, Comparable{

	double laenge = 0;
	double breite = 0;
	double hoehe = 0;
	
	public Papierblatt() {
		
	}
	
	public Papierblatt(int laenge, int breite) {
		this.laenge = laenge;
		this.breite = breite;
	}
	
	public Papierblatt(int mass)	{
		switch (mass)	{
			case 0:	{
				laenge = 1.189;
				breite = 0.841;
				break;
			}
			case 1:	{
				laenge = 0.841;
				breite = 0.594;
				break;
			}
			case 2:	{
				laenge = 0.594;
				breite = 0.420;
				break;
			}
			case 3:	{
				laenge = 0.420;
				breite = 0.297;
				break;
			}
			case 4: {
				laenge = 0.297;
				breite = 0.210;
				break;
			}
		}
	}

	@Override
	public double getLaenge() {
		return laenge;
	}

	@Override
	public double getBreite() {
		return breite;
	}

	@Override
	public double getHoehe() {
		return hoehe;
	}
	
	public void setLaenge(double laenge) {
		this.laenge = laenge;
	}

	public void setBreite(double breite) {
		this.breite = breite;
	}

	public void setHoehe(double hoehe) {
		this.hoehe = hoehe;
	}

	@Override
	public int compareTo(Object o) {
		int ret = 0;
		if (o instanceof Groesse) {
			double grundflaeche = ((Groesse) o).getBreite() * ((Groesse) o).getLaenge();
			if ((this.breite * this.laenge) < grundflaeche)	{
				ret = -1;
			}
			if ((this.breite * this.laenge) == grundflaeche)	{
				ret = 0;
			}
			if ((this.breite * this.laenge) > grundflaeche) {
				ret = +1;
			}
		}
		return ret;
	}
	
	public String toString()	{
		return this.getClass().getSimpleName() + ": L = " + laenge + " B = " + breite + " H = " + hoehe + " G = " + laenge * breite;
		
	}
}
