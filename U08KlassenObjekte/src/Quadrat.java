
public class Quadrat
{
	private double seiteA = 0; 

	public double getSeiteA()	{
		return seiteA;
	}
	
	public void setSeiteA(double seitea)	{
			if (seitea >= 0)	{
				this.seiteA = seitea;
			}
	}
	
	public double getSeiteB()	{
		return seiteA;
	}
	
	public void setSeiteB(double seiteb)	{
		if (seiteb >= 0)	{
			this.seiteA = seiteb;
		}
	}
	
	public double getUmfang()	{
		return seiteA*2;
	}
	
	public void setUmgang(double umfang)	{
		if (umfang >= 0)	{
			this.seiteA = umfang/4;
		}	
	}
	
	public double getFlaeche()	{
		return Math.pow(seiteA, 2);
	}
	
	public void setFlaeche(double flaeche)	{
		if (flaeche >= 0) {
			this.seiteA = Math.sqrt(flaeche);
		}
	}
	
	public Quadrat clone() {
		Quadrat q1 = new Quadrat();
		q1.setSeiteA(this.seiteA);
		return q1;
	}
	
	public boolean equals(Quadrat q1)	{
		if (seiteA == q1.getSeiteA())	{
			return true;
		}
		else	{
			return false;
		}
	}
	
	public int compareTo(Quadrat q1) {
		if (seiteA < q1.getSeiteA())	{
			return -1;
		}
		if (seiteA == q1.getSeiteA())	{
			return 1;
		}
		else	{
			return 0;
		}
	}
	
	public String toString()	{
		return "a = " + seiteA + ", b = " + seiteA + ", U = " + seiteA*2 + ", F = " + Math.pow(seiteA, 2);
	}
	
	public static void main(String[] args) {
			
	}

}
