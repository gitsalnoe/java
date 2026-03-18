package net.gobbz.net;

public class Argument extends Konstante {
	
	public Argument(double ergebnis)	{
		super(ergebnis);
	}
	
	public void getErgebnis(double ergebnis) {
		double erg = Math.abs(Math.floor(ergebnis));
		super.setErgebnis(erg);
	}

}
