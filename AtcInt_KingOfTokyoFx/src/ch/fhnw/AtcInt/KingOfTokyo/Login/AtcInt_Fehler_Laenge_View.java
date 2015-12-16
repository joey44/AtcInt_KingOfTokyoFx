package ch.fhnw.AtcInt.KingOfTokyo.Login;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @code SHOULD open an error window
 * @author Marianne
 *
 */

public class AtcInt_Fehler_Laenge_View {

	public  void start(){
		try	{
			Parent root = FXMLLoader.load(getClass().getResource("AtcInt_Fehler_Länge_Model.fxml"));
			Stage stage = new Stage();
			stage.setTitle("AtcInt Fehler Länge");
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
