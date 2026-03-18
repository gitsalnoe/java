package rssPackage;

public class Nachricht {
	    private String titel;
	    private String link;
	    private String datum;
	
	    public String getTitel() {
	        return titel;
	    }
	
	    public void setTitel(String titel) {
	        this.titel = titel;
	    }
	
	    public String getLink() {
	        return link;
	    }
	
	    public void setLink(String link) {
	        this.link = link;
	    }
	
	    public String getDatum() {
	        return datum;
	    }
	
	    public void setDatum(String datum) {
	        this.datum = datum;
	    }
	
	    @Override
	    public String toString() {
	        return titel;
	    }
}