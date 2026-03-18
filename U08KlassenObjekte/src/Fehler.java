
public class Fehler
{

	public static void main(String[] args) {
		Messwert m1 = new Messwert();			
		m1.setMesswert(3.5);
		m1.innen = false;									//setter Methode gebraucht
		setMessdatum("01.08.09");					//Objekt zum setten gebraucht
		m2 = m1.clone();									// m2 wurde nie inizialisiert
		Messwert m3 = null;							
		m3.setMessdatum("02.08.09");			//m3 deklariert aber nie instanziiert		
		if (m1.equals(m2) == 0)						//equals Methode gibt einen Boolean
		System.out.println(m1.toString()); 
		Messwert[] m = new Messwert[5];			
		for (int i = 0; i <= 5; i = i + 1)	
		m[i] = new Messwert();							
		m[m.length - 1] = null;						
		for (int i = 0; i < m.length; i = i + 1)
			System.out.println(m[i].toString());
		}

}
