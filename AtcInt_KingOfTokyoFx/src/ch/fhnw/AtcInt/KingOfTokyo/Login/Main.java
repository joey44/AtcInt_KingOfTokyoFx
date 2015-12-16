package ch.fhnw.AtcInt.KingOfTokyo.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * @code Main method in which the program runs
 * @author Marianne
 *
 */

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {	
			Parent root = FXMLLoader.load(getClass().getResource("AtcInt_Login_Model.fxml"));
			primaryStage.setTitle("AtcInt Login");
			primaryStage.setScene(new Scene(root));
			primaryStage.getIcons().add(new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
