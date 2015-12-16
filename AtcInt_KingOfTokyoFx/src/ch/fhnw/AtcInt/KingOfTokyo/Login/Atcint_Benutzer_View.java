package ch.fhnw.AtcInt.KingOfTokyo.Login;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @code opens an entry window
 * @author Marianne
 *
 */

public class Atcint_Benutzer_View {
	
	public void start() {
						
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AtcInt_Benutzer_Model.fxml"));
			Stage stage = new Stage();
			stage.setTitle("AtcInt Benutzer erstellen");
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}