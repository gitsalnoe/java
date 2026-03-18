package interfacesPackage;

public class Auto implements Groesse, Comparable{
	double laenge;
	double breite;
	double hoehe;
	
	public Auto()	{
		
	}
	
	public Auto(int laenge, int breite) {
		this.laenge = laenge;
		this.breite = breite;
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
