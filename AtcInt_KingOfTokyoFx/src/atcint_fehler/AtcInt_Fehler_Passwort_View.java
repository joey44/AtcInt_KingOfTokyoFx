package atcint_fehler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
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
			stage.show();
			
		}
		catch(Exception ex)
		{		
			
		}
	}
}