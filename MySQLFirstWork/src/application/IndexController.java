package application;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.Actor;
import data.Genre;
import data.Movie;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndexController {
	
	
	@FXML private Button btRegisterActor;
	@FXML private Button btRegisterMovie;
	@FXML private Button btRegisterGenre;
	@FXML private Button btConnectMoviesActor;
	@FXML private Button btSearch;
	@FXML private TextField tfSearch;
	@FXML private TextArea taResults;
	
	public void initialize() {
		Platform.runLater( new Thread(()->{
			searchme();
		}) );
	}
	
	private void searchme() {
		tfSearch.setOnKeyReleased((event)->{
			String data = tfSearch.getText();
			String actors = Main.getConnection().searchActors(data);
			String movies = Main.getConnection().searchMovies(data);
			System.out.println(actors);
			System.out.println(movies);
			taResults.setText(actors + "\n" + movies );
		});
	}

	@FXML
	public void registerActor() {
		System.out.println("Registering Actor");
		Dialog<String> dialog = new Dialog();
		dialog.setTitle("Register Actor");
        dialog.setHeaderText("Please type..");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField tfname = new TextField(); tfname.setPromptText("Actor's name");
        TextField tfnationality = new TextField(); tfnationality.setPromptText("Actor's nationality");
        dialog.show();
        dialogPane.setContent(new HBox(14,tfname, tfnationality));
        //Platform.runLater(tfname::requestFocus);
        dialog.setResultConverter((button) -> {
            if (button == ButtonType.OK) {
                String name = tfname.getText();
                String nationality = tfnationality.getText();
                Main.getConnection().registerActor(new Actor(name,nationality));
            }
            return null;
        });
       
	}
	
	@FXML
	public void registerMovie() {
		System.out.println("Registering Movie");
		Dialog<String> dialog = new Dialog();
		dialog.setTitle("Register Movie");
        dialog.setHeaderText("Please type..");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField tfname = new TextField(); tfname.setPromptText("Movie's name");
        TextField tfyear = new TextField(); tfyear.setPromptText("Movie's year");
        dialog.show();
        List<String> genres = new ArrayList<String>();
        HashMap<String,Integer> hashGenres = new HashMap<String,Integer>();
        for (int i = 0; i < Main.getConnection().getAllGenres().size(); i++) {
        	String genre =Main.getConnection().getAllGenres().get(i).getGenre();
        	genres.add(genre);
        	hashGenres.put(genre,i);
		}
        ChoiceBox<String> chbGenre = new ChoiceBox(FXCollections.observableArrayList(genres));
        dialogPane.setContent(new HBox(20,tfname, tfyear,chbGenre));
        //Platform.runLater(tfname::requestFocus);
        dialog.setResultConverter((button) -> {
            if (button == ButtonType.OK) {
                String name = tfname.getText();
                int year = Integer.parseInt(tfyear.getText());
                String genre =  chbGenre.getSelectionModel().getSelectedItem();
                Integer genreid = hashGenres.get(genre);
                Main.getConnection().registerMovie(new Movie(name,year,genreid + 1));
            }
            return null;
        });
	}
	
	@FXML
	public void registerGenre() {
		System.out.println("Registering Genre");
		Dialog<String> dialog = new Dialog();
		dialog.setTitle("Register Genre");
        dialog.setHeaderText("Please type..");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField tfgenre = new TextField(); tfgenre.setPromptText("Genre");
        dialog.show();
        dialogPane.setContent(new HBox(14,tfgenre));
        //Platform.runLater(tfgenre::requestFocus);
        dialog.setResultConverter((button) -> {
            if (button == ButtonType.OK) {
                String genre = tfgenre.getText();
                Main.getConnection().registerGenre(new Genre(genre));
            }
            return null;
        });
	}
	
	@FXML
	public void search() {
		
	}
	
	@FXML 
	public void connect() {
		System.out.println("Connectiong Movie and Actor");
		Dialog<String> dialog = new Dialog();
		dialog.setTitle("Movies-Actors");
        dialog.setHeaderText("Please select..");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.show();
        List<String> actors = new ArrayList<String>();
        List<String> movies = new ArrayList<String>();
        HashMap<String,Integer> hashActors = new HashMap<String,Integer>();
        HashMap<String,Integer> hashMovies = new HashMap<String,Integer>();
        for (int i = 0; i < Main.getConnection().getAllActors().size(); i++) {
        	String actorname =Main.getConnection().getAllActors().get(i).getName();
        	actors.add(actorname);
        	hashActors.put(actorname,i);
		}
        for (int i = 0; i < Main.getConnection().getAllMovies().size(); i++) {
        	String moviename =Main.getConnection().getAllMovies().get(i).getName();
        	movies.add(moviename);
        	hashMovies.put(moviename,i);
        }
                
        ChoiceBox<String> chbActors = new ChoiceBox(FXCollections.observableArrayList(actors));
        ChoiceBox<String> chbMovies = new ChoiceBox(FXCollections.observableArrayList(movies));
        dialogPane.setContent(new HBox(20,chbActors,chbMovies));
        //Platform.runLater(tfname::requestFocus);
        dialog.setResultConverter((button) -> {
            if (button == ButtonType.OK) {
                String actorname =  chbActors.getSelectionModel().getSelectedItem();
                String moviename =  chbMovies.getSelectionModel().getSelectedItem();
                Integer actorid = hashActors.get(actorname);
                Integer movieid = hashMovies.get(moviename);
                Main.getConnection().connectMoviesActors(movieid + 1, actorid + 1);
                
            }
            return null;
        });
	}
	
	
	
	
}
