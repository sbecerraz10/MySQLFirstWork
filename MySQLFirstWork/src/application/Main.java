package application;
	
import data.MySQLConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static MySQLConnection connection;
	
	

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Index.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		connection = MySQLConnection.getInstance();
		launch(args);
	}
	
	public static MySQLConnection getConnection() {
		return connection;
	}
	
	public static void setConnection(MySQLConnection connection) {
		Main.connection = connection;
	}
}
