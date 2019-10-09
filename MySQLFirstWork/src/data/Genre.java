package data;

public class Genre {
	
	public static final String COMEDY = "comedy";
	public static final String ACTION = "action";
	private int id;
	private String genre;
	
	
	public Genre(int id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}
	
	public Genre(String genre) {
		super();
		this.genre = genre;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}
		
	
	

}
