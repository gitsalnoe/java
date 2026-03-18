package brueche;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestProgramm {

	@Test(expected = BruchException.class)
	public void BruchMitNennerNull() throws BruchException {
	    new Bruch(1, 0);
	}

	@Test(expected = BruchException.class)
	public void SetNennerNull() throws BruchException {
	    Bruch b = new Bruch(2, 3);
	    b.setNenner(0);
	}

	@Test(expected = NullPointerException.class)
	public void AddiereMitNull() throws BruchException {
	    Bruch b1 = new Bruch(1, 2);
	    b1.addiere(null);
	}

	@Test(expected = NullPointerException.class)
	public void AddiereMitKeinemBruch() throws BruchException {
	    Bruch b1 = new Bruch(1, 2);
	    b1.addiere("Kein Bruch");
	}

	@Test(expected = NullPointerException.class)
	public void SubtrahiereMitNull() throws BruchException {
	    Bruch b1 = new Bruch(3, 4);
	    b1.subtrahiere(null);
	}

	@Test(expected = NullPointerException.class)
	public void MultipliziereMitNull() throws BruchException {
	    Bruch b1 = new Bruch(1, 3);
	    b1.multipliziere(null);
	}

	@Test(expected = NullPointerException.class)
	public void DividiereMitNull() throws BruchException {
	    Bruch b1 = new Bruch(2, 5);
	    b1.dividiere(null);
	}

	@Test(expected = NullPointerException.class)
	public void EqualsMitKeinObject() throws BruchException{
	    Bruch b = new Bruch(1, 2);
	    b.equals(null); 
	}

	@Test
	public void Kuerzen() throws BruchException {
	    Bruch b = new Bruch(4, 8);
	    b.kuerze();
	    assertEquals(1, b.getZaehler());
	    assertEquals(2, b.getNenner());
	}

	@Test
	public void Clone() throws BruchException {
	    Bruch b1 = new Bruch(3, 5);
	    Bruch b2 = b1.clone();
	    assertNotSame(b1, b2);
	    assertEquals(b1.getZaehler(), b2.getZaehler());
	    assertEquals(b1.getNenner(), b2.getNenner());
	}

}

