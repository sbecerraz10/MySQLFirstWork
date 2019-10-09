package data;

public class Actor {
	
	private int id;
	private String name;
	private String nationality;
	
	
	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Actor(int id, String name, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
	}
	
	public Actor(String name, String nationality) {
		super();
		this.name = name;
		this.nationality = nationality;
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
