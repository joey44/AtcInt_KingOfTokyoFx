package atcint_fehler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @code WOULD control the elements of the "fehler l�nge" model
 * @author Marianne
 *
 */

public class Atcint_Fehler_L�nge_Controller {

	@FXML
	private Button closeButton;
	
	@FXML
	private void closeButton(ActionEvent event) {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}
