package data;

public class Movie {
	
	private int id;
	private String name;
	private int year;
	private int genreID;
	
	
	public Movie(int id, String name, int year, int genreID) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.genreID = genreID;
	}
	
	public Movie(String name, int year, int genreID) {
		super();
		this.name = name;
		this.year = year;
		this.genreID = genreID;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getGenreID() {
		return genreID;
	}


	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}
	
	
	
	

}
