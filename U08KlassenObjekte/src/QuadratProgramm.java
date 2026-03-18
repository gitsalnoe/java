
public class QuadratProgramm
{

	public static void main(String[] args) {
			Quadrat[] q1 = new Quadrat[50];
			int groesste = 0; 
			for (int i = 0; i < q1.length; i++)	{
				q1[i] = new Quadrat();
				q1[i].setSeiteA((Math.random() * (10 - 0)) + 0);
				if (q1[i].getSeiteA() > groesste)	{
					groesste = i;
				}
			}
			System.out.println(q1[groesste].toString());
	}
}
