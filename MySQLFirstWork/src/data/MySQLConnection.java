package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnection {
	
	private static MySQLConnection instance;

	private Connection connection;
	
	private Statement statement;
	
	public static String TABLE_ACTORS = "actors";
	public static String TABLE_MOVIES = "movies";
	public static String TABLE_GENRES = "genres";
	public static String TABLE_ACTORS_MOVIES = "actors_movies";
	public static String ACTORS_ID = "id";
	public static String ACTOR_ID = "actorID";
	public static String MOVIE_ID = "movieID";
	public static String MOVIES_ID = "id";
	public static String GENRES_ID = "id";
	public static String GENRES_GENRE = "genre";
	public static String ACTORS_NAME = "name";
	public static String MOVIES_NAME = "name";
	public static String MOVIES_YEAR = "year";
	public static String MOVIES_GENREID = "genreID";
	
	public static String ACTORS_NATIONALITY = "nationality";
	

	private MySQLConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blockbuster");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/msqlfirstwork","root","");
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized static MySQLConnection getInstance() {
		if(instance == null) instance = new MySQLConnection();
		return instance;
	}
	
	
	public void registerActor(Actor actor) {
		String sql="INSERT INTO $TABLE($NAME,$NATIONALITY) VALUES('$VNAME','$VNATIONALITY')";
		sql=sql
				.replace("$TABLE", TABLE_ACTORS)
				.replace("$NAME", ACTORS_NAME)
				.replace("$NATIONALITY", ACTORS_NATIONALITY)
				.replace("$VNAME", actor.getName())
				.replace("$VNATIONALITY", actor.getNationality());
		
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registerMovie(Movie movie) {
		String sql="INSERT INTO $TABLE($NAME,$YEAR, $GENREID) VALUES('$VNAME','$VYEAR',$VGENREID)";
		sql=sql
				.replace("$TABLE", TABLE_MOVIES)
				.replace("$NAME", MOVIES_NAME)
				.replace("$YEAR", MOVIES_YEAR)
				.replace("$GENREID", MOVIES_GENREID)
				.replace("$VNAME", movie.getName())
				.replace("$VYEAR", movie.getYear() + "")
				.replace("$VGENREID", movie.getGenreID() + "");
		
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void registerGenre(Genre genre) {
		String sql="INSERT INTO $TABLE($GENRE) VALUES('$VGENRE')";
		sql=sql
				.replace("$TABLE",TABLE_GENRES)
				.replace("$GENRE", GENRES_GENRE )
				.replace("$VGENRE", genre.getGenre());
				
		
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Actor> getAllActors(){
		ArrayList<Actor> output = new ArrayList<Actor>();
		String sql = "SELECT * FROM $TABLE";
		sql = sql.replace("$TABLE", TABLE_ACTORS);
		
		try {
			ResultSet resultados = statement.executeQuery(sql);
			
			while(resultados.next()) {
				int id = resultados.getInt(1);
				String nombre = resultados.getString(2);
				String nacionalidad = resultados.getString(3);
				
				Actor actor = new Actor(id, nombre, nacionalidad);
				output.add(actor);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	public List<Genre> getAllGenres(){
		ArrayList<Genre> genres = new ArrayList<Genre>();
		String sql = "SELECT * FROM $TABLE";
		sql = sql.replace("$TABLE", TABLE_GENRES);
		
		try {
			ResultSet resultados = statement.executeQuery(sql);
			
			while(resultados.next()) {
				int id = resultados.getInt(1);
				String genre= resultados.getString(2);
				
				genres.add(new Genre(id,genre));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genres;
	}
	
	
	public List<Movie> getAllMovies(){
		ArrayList<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM $TABLE";
		sql = sql.replace("$TABLE", TABLE_MOVIES);
		
		try {
			ResultSet resultados = statement.executeQuery(sql);
			
			while(resultados.next()) {
				int id = resultados.getInt(1);
				String name= resultados.getString(2);
				int year = resultados.getInt(3);
				int genreID = resultados.getInt(4);
				movies.add(new Movie(id, name, year, genreID));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	
	
	
	public String searchMovies(String data) {
		String result = "MOVIES \n";
		
		String sql = "SELECT name FROM $TABLE WHERE movies.name LIKE '$name%'";
		sql = sql.replace("$TABLE", TABLE_MOVIES);
		sql = sql.replace("$name", data);
		
		try {
			ResultSet resultados = statement.executeQuery(sql);
			
			while(resultados.next()) {
				result += resultados.getString(1) + "\n";
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String searchActors(String data) {
		String result = "ACTORS \n";
		
		String sql = "SELECT name FROM $TABLE WHERE actors.name LIKE '$name%'";
		sql = sql.replace("$TABLE", TABLE_ACTORS);
		sql = sql.replace("$name", data);
		
		try {
			ResultSet resultados = statement.executeQuery(sql);
			
			while(resultados.next()) {
				result += resultados.getString(1) + "\n";
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void connectMoviesActors(int movieid, int actorid) {
		System.out.println("Movie id " +movieid); 
		System.out.println("Actor id" + actorid);
		String sql="INSERT INTO $TABLE($ACTORID,$MOVIEID) VALUES($VACTORID,$VMOVIEID)";
		sql=sql
				.replace("$TABLE",TABLE_ACTORS_MOVIES)
				.replace("$ACTORID", ACTOR_ID)
				.replace("$MOVIEID", MOVIE_ID)
				.replace("$VACTORID",actorid + "" )
				.replace("$VMOVIEID",movieid + "");
				
		
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
