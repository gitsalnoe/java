package net.gobbz.kontoverwaltung;

public class Sparkonto extends Konto {
	protected double sparrate;

    public Sparkonto(double ersteZahlung, double sparrate) throws KontoException{
        super();
        this.sparrate = sparrate;
        if (ersteZahlung > 0)	{
        	 buchen(ersteZahlung);
        }
        else	{
        	naechsteKontonummer--;
        	throw new KontoException("erste Zahlung muss grösser ale 0 sein");
        }
        if (sparrate > 0)	{
        	this.sparrate = sparrate;
        }
        else	{
        	naechsteKontonummer--;
        	throw new KontoException("sparrate muss grösser als 0 sein");
        }
    }

    @Override
    public void buchen(double betrag) throws KontoException {
    	if ((kontostand + betrag) > 0 && kontostand > 0) {
    		throw new KontoException("Kontostand unter 0");
    	}
    	if (betrag < -3000) {
			throw new KontoException("nur max -3000 kann abgebucht werden");
		}
		if (betrag < 0 && betrag != -3000 && kontostand + betrag != 0) {
			throw new KontoException("betrag zum abbuchen muss gleich gross wie konstostand sein");
		}
		kontostand = kontostand + betrag;
    }
    
    public void buchenSparrate() {
        try {
			buchen(sparrate);
		} catch (KontoException e) {
			e.printStackTrace();
		}
    }

    public double getSparrate() {
        return this.sparrate;
    }

    @Override
    public double getSpesen() {
        return kontostand * 0.001;
    }

    @Override
    public double getZinsen() {
        return kontostand *zinssatz/100;
    }

    public void setSparrate(double sparrate) {
        this.sparrate = sparrate;
    }

    @Override
    public String toString() {
        return super.toString() + ", sparrate:" + sparrate;
    }
}
