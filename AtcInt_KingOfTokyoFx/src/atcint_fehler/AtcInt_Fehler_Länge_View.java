package atcint_fehler;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @code SHOULD open an error window
 * @author Marianne
 *
 */

public class AtcInt_Fehler_L�nge_View {

	public  void start(){
		try	{
			Parent root = FXMLLoader.load(getClass().getResource("AtcInt_Fehler_L�nge_Model.fxml"));
			Stage stage = new Stage();
			stage.setTitle("AtcInt Fehler L�nge");
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
