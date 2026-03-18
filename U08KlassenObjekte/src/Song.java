
public class Song
{
	private String titel = "";
	private String interpret = "";
	private String album = "";
	private int erscheinungsjahr;
	
	public String getTitel() {
		return titel;
	}
	
	public void setTitel(String titel)	{
		this.titel = titel;
	}
	
	public String getInterpret()	{
		return interpret;
	}
	
	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}
	
	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
	
	public int getErscheinungsjahr()	{
		return erscheinungsjahr;
	}
	
	public void setErscheinungsjahr(int erscheinungsjahr) {
		if (erscheinungsjahr >= 0)	{
			this.erscheinungsjahr = erscheinungsjahr;
		}
	}
	
	public boolean equals(Song song) {
		if (titel.equals(song.titel) && interpret.equals(song.interpret) && album.equals(song.album) && erscheinungsjahr == song.erscheinungsjahr)	{
			return true;
		}
		else	{
			return false;
		}
	}
	
	public int compareTo(Song song)	{
		String songstring2 = song.toString();
		Song song1 = new Song();
		song1.titel = titel;
		song1.album = album;
		song1.interpret = interpret;
		song1.erscheinungsjahr = erscheinungsjahr;
		String songstring1 = song1.toString();
		return songstring1.compareTo(songstring2);
	}
	
	public Song clone(Song song) {
		Song copy = new Song();
		copy.setTitel(song.getTitel());
		copy.setAlbum(song.getAlbum());
		copy.setInterpret(song.getInterpret());
		copy.setErscheinungsjahr(song.getErscheinungsjahr());
		return copy;
		}
	
	public String toString() {
		return titel + ";" + album + ";" + interpret + ";" + erscheinungsjahr;
	}
	
	public void setString(String song) {
		int type = 0;												//sagt wie welche Membervariable gesetet werden soll
		for (int i = 0; i < song.length(); i++)	{
			if (song.charAt(i) == ';') {
					if (type == 0) {
							titel = song.substring(0, i);
							song = song.substring(i);
							type++;
					}
					if (type == 1) {
							album = song.substring(0, i);
							song = song.substring(i);
							type++;
					}
					if (type == 2) {
							interpret = song.substring(0, i);
							song = song.substring(i);
							type++;
					}
					if (type == 3) {
							erscheinungsjahr = Integer.valueOf(song.substring(0,1));
							song = song.substring(i);
							type++;
					}
			}
		}
	}
	
	
	public static void main(String[] args) {
		/*
			Song s1 = new Song();
			s1.setAlbum("hallo");
			s1.setErscheinungsjahr(2000);
			s1.setInterpret("hallo");
			s1.setTitel("hallo");
			System.out.println(s1.toString());
		*/
	}

}
	