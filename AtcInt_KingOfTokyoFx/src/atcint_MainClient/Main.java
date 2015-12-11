package atcint_MainClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
			Parent root = FXMLLoader.load(atcint_login.AtcInt_Login_View.class.getResource("AtcInt_Login_Model.fxml"));
			primaryStage.setTitle("AtcInt Login");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
