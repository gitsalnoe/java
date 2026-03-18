
public class Rekursiv
{
	public static void main(String[] args) {
		whilenichtRekursiv(4);
		whileRekursiv(4);
		
		System.out.println(machWas(4));
		System.out.println(machWasRekursiv(4, 0));
	}
	
	public static void whilenichtRekursiv(int i) {
		while (i > 3) {
			System.out.println(i * 3);
			i = i - 1;
		}
	}
	
	public static void whileRekursiv(int i)	{
		if (i > 3) {
			System.out.println(i * 3);
			whileRekursiv(i - 1);
		}
	}
	
	public static int machWas(int x) {
		int ret = 0;
		while (x / 2 > 0) {
			ret = ret + 1;
			x = x / 2;
		}
		return ret;
	}
	
	public static int machWasRekursiv(int x, int ret) {
		if (x / 2 > 0) {
			ret = machWasRekursiv(x / 2, ret + 1);
		}
		return ret;
	}
	
}
