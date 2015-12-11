package atcint_fehler;

import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.fxml.FXML;

/**
 * @code Controls the elements of the "fehler passwort" model
 * @author Marianne
 *
 */

public class AtcInt_Fehler_Passwort_Controller {

	@FXML
	private Button closeButton;
	@FXML
	private Hyperlink Passwort;
	@FXML
	private Hyperlink newUser;
	
	@FXML
	private void closeButton(ActionEvent event) {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void Passwort(ActionEvent event){
		System.out.println("Wir arbeiten zur Zeit an einer L�sung f�r dieses Problem. Vielen Dank f�r Dein Verst�ndnis...");
	}
	
	@FXML
	private void newUser(ActionEvent event){
		atcint_benutzer.Atcint_Benutzer_View view = new atcint_benutzer.Atcint_Benutzer_View();
		view.start();		
	}
	
}