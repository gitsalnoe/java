
public class Messwert
{
	private int messwert = 0; // Wert muss zwischen -60 und 50 liegen
	private String messdatum = null; // Datum muss exakt achtstellig sein
	private boolean innen = true; // legt fest ob außen oder innen gemessen
	
	public void setMesswert(int messwert)	{
			if (messwert >= -60 && messwert <= 50)	{
					this.messwert = messwert;
			}
	}
	
	public int getMesswert()	{
		return messwert;
	}
	
	public void setMessdatum(String messdatum)	{
		if (messdatum.length() == 8)	{
			this.messdatum = messdatum;
		}
	}
	
	public String getMessdatum()	{
		return messdatum;
	}
	
	public void setInnen(boolean innen)	{
		this.innen = innen;
	}
	
	public boolean getInnen()	{
		return innen;
	}
	
	public static void main(String[] args) {
		Messwert messwert = new Messwert();
		
		messwert.setMesswert(5);
		messwert.setMessdatum("10.12.08");
		messwert.setInnen(false);
		
		Messwert[] messwerte = new Messwert[1024];

	}
	
	public double messwertDurchschnitt(Messwert[] messwertarray)	{
		double durchschnitt = 0;
		for (int i = 0; i <= messwertarray.length; i++)	{
			if (messwertarray[i] != null)	{
				durchschnitt = durchschnitt + messwertarray[i].getMesswert();
			}
		}
		return durchschnitt/messwertarray.length;
	}
	
	
}
