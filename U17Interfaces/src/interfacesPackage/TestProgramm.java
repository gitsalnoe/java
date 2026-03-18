package interfacesPackage;

public class TestProgramm {
	
	public void Test1()	{
		Groesse[] g = new Groesse[8];
		for (int i = 0; i < 5; i++)	{
			g[i] = new Papierblatt(i);
		}
		g[5] = new Auto();
		g[6] = new Auto();
		g[7] = new Fussballfeld();
		for (int i = 0; i < g.length; i++) {
			double grundflaeche = g[i].getLaenge() * g[i].getBreite();
			System.out.println("Klasse: " + g[i].getClass() + " " + grundflaeche +  "m");
		}
	}
	
	public void Test2() {
		Groesse[] g = new Groesse[10];
		g[0] = new Auto(4, 4);
		g[1] = new Fussballfeld(200, 200);
		g[2] = new Papierblatt(10, 10);
		g[3] = new Auto(1, 1);
		g[4] = new Fussballfeld(100, 100);
		g[5] = new Papierblatt(40, 40);
		g[6] = new Auto(20, 20);
		g[7] = new Papierblatt(70, 70);
		g[8] = new Papierblatt(10, 15);
		g[9] = new Papierblatt(40, 10);
		
		System.out.println("Ausgabe unsortiert");
		for (int i = 0; i < g.length; i++)	{
			System.out.println(i + ": " + g[i].toString());
		}
		for (int i = 0; i < g.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < g.length; j++) {
					if ((g[minIndex].getLaenge() * g[minIndex].getBreite()) > (g[j].getLaenge() * g[j].getBreite())) {
						minIndex = j;
					}
			}
			Groesse temp = g[i];
			g[i] = g[minIndex];
			g[minIndex] = temp;
		}
		System.out.println("Ausgabe sortiert");
		for (int i = 0; i < g.length; i++)	{
			System.out.println(i + ": " + g[i].toString());
		}
	}

	public static void main(String[] args) {
		TestProgramm t = new TestProgramm();
		t.Test2();
	}
}
