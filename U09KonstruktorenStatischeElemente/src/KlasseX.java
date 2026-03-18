
public class KlasseX
{
	private int a = 0;
	private int b = 0;
	private int c = 0;
	private KlasseX x = null;
	public KlasseX() {
	this.a = 1; this.b = 2; this.c = 3;
	}
	public KlasseX(int a) {
	this(); this.a = a;
	}
	public KlasseX(int a, int b) {
	this(a); this.b = b;
	}
	public KlasseX(int a, int b, int c) {
	this(a, b); this.c = c;
	}
	public KlasseX(int a, int b, int c, int d) {
	this(a, b, c);
	x = new KlasseX(d);
	}

	public static void main(String[] args) {
		KlasseX x1 = new KlasseX();
		System.out.println("x1 = " + x1.a + " " + x1.b + " " + x1.c);
		KlasseX x2 = new KlasseX(10);
		System.out.println("x2 = " + x1.a + " " + x2.b + " " + x2.c);
		KlasseX x3 = new KlasseX(10,11);
		System.out.println("x3 = " + x3.a + " " + x3.b + " " + x3.c);
		KlasseX x4 = new KlasseX(10,11,12);
		System.out.println("x4 = " + x4.a + " " + x4.b + " " + x4.c);
		KlasseX x5 = new KlasseX(10,11,12,13);	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("x5 = " + x5.a + " " + x5.b + " " + x5.c);
	}

}
