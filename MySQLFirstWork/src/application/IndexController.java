package application;


import data.Actor;
import data.Genre;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndexController {
	
	
	@FXML private Button btRegisterActor;
	@FXML private Button btRegisterMovie;
	@FXML private Button btRegisterGenre;
	@FXML private Button btSearch;
	@FXML private TextField tfSearch;
	@FXML private TextArea taResults;
	
	public void initialize() {
		
	}
	
	@FXML
	public void registerActor() {
		System.out.println("Registering Actor");
		Dialog<String> dialog = new Dialog();
		dialog.setTitle("Register Actor");
        dialog.setHeaderText("Please type..");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField tfname = new TextField();
        TextField tfnationality = new TextField();
        dialog.show();
        dialogPane.setContent(new HBox(14,tfname, tfnationality));
        Platform.runLater(tfname::requestFocus);
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
		
	}
	
	@FXML
	public void registerGenre() {
		System.out.println("Registering Genre");
		Dialog<String> dialog = new Dialog();
		dialog.setTitle("Register Genre");
        dialog.setHeaderText("Please type..");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField tfgenre = new TextField();
        dialog.show();
        dialogPane.setContent(new HBox(14,tfgenre));
        Platform.runLater(tfgenre::requestFocus);
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
	
	
}
