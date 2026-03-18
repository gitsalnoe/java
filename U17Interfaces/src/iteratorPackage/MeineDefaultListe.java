package iteratorPackage;

public class MeineDefaultListe implements MeineListe	{
	private ListenElement erstesElem = null;
	class ListenElement extends Object	{
		private Object element = null;
		private ListenElement naechstesElem = null;
		private ListenElement(Object element, ListenElement naechstesElem) {
			this.element = element;
			this.naechstesElem = naechstesElem;
		}
	}
	public boolean einfuegenErstesElement(Object element) {
		boolean ret = false;
		if (element != null) {
			ret = true;
			this.erstesElem = new ListenElement(element, erstesElem);
		}
		return ret;
	}
	
	public boolean loeschenErstesElement() {
	boolean ret = false;
	if (erstesElem != null) {
		ret = true;
		this.erstesElem = this.erstesElem.naechstesElem;
	}
	return ret;
	}
	public boolean istLeer() {
	return this.erstesElem == null;
	}
	public void leeren() {
	this.erstesElem = null;
	}
	@Override
	public MeinIterator elemente() {
		return new MeinIterator() {
			private ListenElement aktuellesElem = null;
			public boolean hatNaechstesElement() {
				boolean ret = false;
				if (this.aktuellesElem == null)
					ret = MeineDefaultListe.this.erstesElem != null;
				else
					ret = this.aktuellesElem.naechstesElem != null;
				return ret;
			}
			public Object naechstesElement() {
			Object ret = null;
			if (this.aktuellesElem == null) {
				if (MeineDefaultListe.this.erstesElem != null) {
					this.aktuellesElem = MeineDefaultListe.this.erstesElem;
					ret = this.aktuellesElem.element;
				}
			} 
			else
				if (this.aktuellesElem.naechstesElem == null)
					this.aktuellesElem = null;
				else {
					this.aktuellesElem = this.aktuellesElem.naechstesElem;
					ret = this.aktuellesElem.element;
				}
				return ret;
			}
			public boolean einfuegenElement(Object element) {
				boolean ret = false;
				if (element != null) {
					ret = true;
					if (this.aktuellesElem == null) {
						MeineDefaultListe.this.erstesElem = new ListenElement(element,
							MeineDefaultListe.this.erstesElem);
						this.aktuellesElem = MeineDefaultListe.this.erstesElem;
					} else {
						this.aktuellesElem.naechstesElem = 
							new ListenElement(element,this.aktuellesElem.naechstesElem);
						this.aktuellesElem = this.aktuellesElem.naechstesElem;
					}
				}
				return ret;
			}
			
			@Override
			public boolean setzenAktuellesElement(Object element) {
				boolean ret = false;
                if (element != null && this.aktuellesElem != null) {
                	this.aktuellesElem.element = element;
                	ret = true;
                }
                return ret;
			}
			@Override
			public boolean loeschenAktuellesElement() {	
				boolean ret = false;
				if (this.aktuellesElem != null) {
					ListenElement loeschelement = this.aktuellesElem;
					ListenElement element = MeineDefaultListe.this.erstesElem;
					if (loeschelement == element) {
						if (MeineDefaultListe.this.erstesElem.naechstesElem != null) {
							this.aktuellesElem = MeineDefaultListe.this.erstesElem;
							loeschenErstesElement();
						}
						else {
							leeren();
							this.aktuellesElem = null;
						}
					}
					else {
						while (element != null && element.naechstesElem != loeschelement) {
							element = element.naechstesElem;
						}
						element.naechstesElem = loeschelement.naechstesElem;

						this.aktuellesElem = element;
					}
					ret = true;
				}
				return ret;
			}
		};
	}
}
