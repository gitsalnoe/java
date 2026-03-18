package iteratorPackage;

public interface MeineListe	{
	// Fügt am Beginn der Liste ein neues Objekt ein
	boolean einfuegenErstesElement(Object element);
	// Löscht das erste Element der Liste
	boolean loeschenErstesElement();
	// Kontrolliert ob Liste leer ist
	boolean istLeer();
	// Löscht die gesamte Liste
	void leeren();
	// Liefert einen Iterator zurück, welcher es erlaubt die Elemente
	// der Liste nacheinander zu bearbeiten
	MeinIterator elemente();
}
