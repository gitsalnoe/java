package net.gobbz.kontoverwaltung;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Gehaltskonto extends Konto {
	
	protected static double startueberziehung;
    protected double ueberziehung;

    public Gehaltskonto() {
        super();
        this.ueberziehung = startueberziehung;
    }

    public void buchen(double betrag) throws KontoException{
        if ((kontostand + betrag) >= -ueberziehung) {
        	kontostand = kontostand + betrag;
        }
        else	{
        	throw new KontoException("buchung überschreitet Überziehungsramen");
        }
    }

    @Override
    public double getSpesen() {
    	double ret = 0;
    	if (kontostand < 0)	{
    		ret = 50;
    	}
        return ret;
    }

    public static double getStartueberziehung() {
        return startueberziehung;
    }

    public double getUeberziehung() {
        return ueberziehung;
    }

    @Override
    public double getZinsen() {
    	double ret = 0;
        if (kontostand > 0) {
            LocalDate heute = LocalDate.now();
            LocalDate jahresende = LocalDate.of(heute.getYear(), 12, 31);
            long tage = ChronoUnit.DAYS.between(heute, jahresende);	//https://stackoverflow.com/questions/27005861/calculate-days-between-two-dates-in-java-8
            ret = (kontostand * zinssatz / 100 / 365) * tage;   
        }
        return ret;
    }

    public static void setStartueberziehung(double startueberziehung) throws KontoException {
    	if (startueberziehung > 0)	{
    		throw new KontoException("startüberziehung muss kleiner als 0 sein");
    	}
    	else	{
    		Gehaltskonto.startueberziehung = startueberziehung;
    	}
    }

    public void setUeberziehung(double ueberziehung) throws KontoException	{
    	if (ueberziehung > 0) {
    		throw new KontoException("überziehung muss kleiner als 0 sein");
    	}
    	else	{
    		 this.ueberziehung = ueberziehung;
    	}
    }

    @Override
    public String toString() {
        return super.toString() + ", Überziehung: " + this.ueberziehung;
    }
}