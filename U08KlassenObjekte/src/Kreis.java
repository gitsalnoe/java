
public class Kreis extends java.lang.Object
{
	
	private double radius = 0;
	private double umfang = 0;
	private double flaeche = 0;

	public void setRadius(double radius)	{
		if (radius > 0)	{
			this.radius = radius;
			this.umfang = 2*Math.PI*radius;
			this.flaeche = Math.PI*Math.pow(radius, 2);
		}
	}
	
	public double getRadius()	{
		return radius;
	}
	
	public void setUmfang(double umfang)	{
		if (umfang > 0)	{
			this.umfang = umfang;
			this.radius = umfang/(2*Math.PI);
			this.flaeche = Math.PI*Math.pow(radius, 2);
		}
	}
	
	public double getUmfang()	{
		return umfang;
	}
	
	public void setFlaeche(double flaeche)	{
		if (flaeche > 0)	{
			this.flaeche = flaeche;
			this.radius = Math.sqrt(flaeche/(Math.PI));
			this.umfang = 2*Math.PI*radius;
		}	
	}
	
	public double getFlaeche()	{
		return flaeche;
	}
	
	public Kreis clone()	{
		Kreis k1 = new Kreis();
		k1.setRadius(this.radius);
		k1.setUmfang(this.umfang);
		k1.setFlaeche(this.flaeche);
		return k1;
	}

	public int compareTo(Kreis k) {
		if (radius < k.getRadius())	{
			return -1;
		}
		if (radius == k.getRadius())	{
			return 0;
		}
		else 	{
			return 1;
		}
	}
	
	public boolean equals(Kreis k)	{
		if (radius == k.getRadius()) {
			return true;
		}
		else	{
			return false;
		}
	}
	
	public String toString()	{
		String kreis = "r = " + radius + ", U = " + umfang + ", F = " + flaeche;
		return kreis;
	}
	
	public static void main(String[] args)	{
		Kreis k1 = new Kreis();
		double f = 3;
		k1.setFlaeche(f);
		Kreis k2 = new Kreis();
		k2 = k1.clone();
		System.out.println(k1.toString());
		System.out.println(k2.toString());
		System.out.println("k1.equals(k2) ergibt " + k1.equals(k2));
		k2.setUmfang(-1);
		k2.setRadius(1);
		System.out.println("k1.compareTo(k2) ergibt " + k1.compareTo(k2));
		System.out.println(k1.toString());
		System.out.println(k2.toString());
	}
	
	
}
