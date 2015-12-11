package atcint_login;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * @code Opens the Login window (not necessary, because handled in Main)
 * @author Marianne
 *
 */

public class AtcInt_Login_View {
	
	public void start() {
						
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AtcInt_Login_Model.fxml"));
			Stage stage = new Stage();
			stage.setTitle("AtcInt Login");
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}