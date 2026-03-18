package brueche;

public class Bruch{
	
		private int zaehler;
		private int nenner;
		
		public Bruch(int zaehler, int nenner) throws BruchException{
			this.zaehler = zaehler;
			if (nenner == 0)	{
				throw new BruchException("Nenner darf nicht 0 sein");
			}
			else	{
				this.nenner = nenner;
			}
		}

		public int getZaehler() {
			return zaehler;
		}

		public void setZaehler(int zaehler) {
			this.zaehler = zaehler;
		}

		public int getNenner() {
			return nenner;
		}

		public void setNenner(int nenner) throws BruchException{
			if (nenner == 0)	{
				throw new BruchException("Nenner darf nicht 0 sein");
			}
			else	{
				this.nenner = nenner;
			}
		}
		
		public void kuerze()	{
			int zaehler = this.zaehler;
			int nenner = this.nenner;
			int rest = 0;
			while (nenner != 0)	{			//Euklidischer Algoritmus zum berechnen des groessten gemeinsamen Teilers
				rest = zaehler % nenner;
				zaehler = nenner;
				nenner = rest;
			}
			int ggT = zaehler;
			this.zaehler = this.zaehler/ggT;
			this.nenner = this.nenner/ggT;
			
		}
		
		@Override
		public String toString()	{
			return this.zaehler + "/" + this.nenner;
		}
		
		public boolean equals(Object o)	{
			if (!(o instanceof Object))	{
				throw new NullPointerException("Kein Object");
			}
			if (!(o instanceof Bruch))	{
				throw new ClassCastException("Kein Bruch oder von Bruch abgeleitet");
			}
			if (o instanceof Bruch && this.zaehler == ((Bruch) o).zaehler && this.nenner == ((Bruch)o).nenner)	{
				return true;
			}
			return false;
		}
		
		public Bruch clone()	{
			try {
				Bruch kopie = new Bruch(this.zaehler, this.nenner);
				return kopie;
			} catch (BruchException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public void addiere(Object summand) throws BruchException	{
			if (!(summand instanceof Bruch)) {
				throw new NullPointerException("Zweiter Bruch ist kein Bruch");
			}
			else	{
				if (summand == null) {
					throw new NullPointerException("Zweiter Bruch ist null");
				}
				this.setZaehler(this.getZaehler() * ((Bruch)summand).nenner + ((Bruch)summand).zaehler * this.getNenner());
				this.setNenner(this.getNenner() * ((Bruch)summand).nenner);
				this.kuerze();
			}
		}
		
		public void subtrahiere(Object summand) throws BruchException	{
			if (!(summand instanceof Bruch)) {
				throw new NullPointerException("Zweiter Bruch ist kein Bruch");
			}
			else	{
				if (summand == null) {
					throw new NullPointerException("Zweiter Bruch ist null");
				}
				this.setZaehler(this.getZaehler() * ((Bruch)summand).nenner - ((Bruch)summand).zaehler * this.getNenner());
				this.setNenner(this.getNenner() * ((Bruch)summand).nenner);
				this.kuerze();
			}
		}
		
		public void multipliziere(Object faktor) throws BruchException	{
			if (!(faktor instanceof Bruch))	{
				throw new NullPointerException("Zweiter Bruch ist kein Bruch");
			}
			else	{
				if (faktor == null)	{
					throw new NullPointerException("Zweiter Bruch ist null");
				}
				this.setZaehler(this.getZaehler() * ((Bruch)faktor).zaehler);
				this.setNenner(this.getNenner() * ((Bruch)faktor).nenner);
				this.kuerze();
			}
			
		}
		
		public void dividiere(Object faktor) throws BruchException	{
			if (!(faktor instanceof Bruch))	{
				throw new NullPointerException("Zweiter Bruch ist kein Bruch");
			}
			else	{
				if (faktor == null)	{
					throw new NullPointerException("Zweiter Bruch ist null");
				}
			}
			this.setZaehler(this.getZaehler() / ((Bruch)faktor).zaehler);
			this.setNenner(this.getNenner() / ((Bruch)faktor).nenner);
			this.kuerze();
		}
}
	