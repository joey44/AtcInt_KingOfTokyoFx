package ch.fhnw.AtcInt.KingOfTokyo.Login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @code Opens an error window
 * @author Marianne
 *
 */

public class AtcInt_Fehler_Passwort_View {
	
	public  void start(){
		try	{
			Parent root = FXMLLoader.load(getClass().getResource("AtcInt_Fehler_Passwort_Model.fxml"));
			Stage stage = new Stage();
			stage.setTitle("AtcInt Fehler");
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));

			stage.show();
			
		}
		catch(Exception ex)
		{		
			
		}
	}
}