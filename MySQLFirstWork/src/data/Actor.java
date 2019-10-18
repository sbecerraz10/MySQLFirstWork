package data;

import java.util.ArrayList;

public class Actor {
	
	private int id;
	private String name;
	private String nationality;
	
	private ArrayList<Movie> mymovies;
	
	
	public Actor() {
		super();
		mymovies = new ArrayList<Movie>();
		// TODO Auto-generated constructor stub
	}

	public Actor(int id, String name, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		mymovies = new ArrayList<Movie>();
	}
	
	public Actor(String name, String nationality) {
		super();
		this.name = name;
		this.nationality = nationality;
		mymovies = new ArrayList<Movie>();
	}
	
	public Actor(int id, String name) {
		super();
		this.name = name;
		this.id = id;
		mymovies = new ArrayList<Movie>();
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	
	
	
	

}
