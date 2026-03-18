package iteratorPackage;

public interface MeinIterator	{
	// Kontrolliert ob ein nächstes Element in der Liste vorhanden ist
	boolean hatNaechstesElement();
	// Liefert das nächste Element in der Liste zurück
	Object naechstesElement();
	// Fügt nach dem Element auf dem Iterator zeigt das übergebene
	// Element ein
	boolean einfuegenElement(Object element);
	
	boolean setzenAktuellesElement(Object element);
	
	boolean loeschenAktuellesElement();
}
