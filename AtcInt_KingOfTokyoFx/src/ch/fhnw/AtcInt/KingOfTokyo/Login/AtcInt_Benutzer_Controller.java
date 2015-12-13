package ch.fhnw.AtcInt.KingOfTokyo.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @code Controls the elements of the "benutzer" model
 * @author Marianne
 *
 */

public class AtcInt_Benutzer_Controller {
	
	@FXML
	private TextField Spieler, Passwort1, Passwort2;
	@FXML
	private Button goBack;
	
	@FXML
	private void goOn(ActionEvent event){

		System.out.println("Spielername: " + Spieler.getText());
		System.out.println("Passwort: " + Passwort1.getText());
		System.out.println("Passwort wiederholen: " + Passwort2.getText());
		
		try {
			if(DBZugriff.SpielernameAvailable(Spieler.getText())) {
				try {
					DBZugriff benutzer = DBZugriff.AddBenutzer(Spieler.getText(), Passwort1.getText());
					if(benutzer!=null) {
						System.out.println("Spieler '"+benutzer.SpielerName()+"' wurde erstellt");
						}
					else {
						System.out.println("Irgend ä andere fehler");	
						}
					
				}catch(Exception addEx){
					System.out.println("Spieler konnte nicht erstellt werden. Grund: " + addEx.getMessage());
				//atcint_fehler.AtcInt_Fehler_Länge_View view = new atcint_fehler.AtcInt_Fehler_Länge_View();
				//view.start();
			}
		}
		else	
		{
			System.out.println("Spielername: " + Spieler.getText() + " ist bereits vergeben.");
		}
		}
		catch(Exception ex)
		{
			System.out.println("DB Verbindung fehlgeschlagen");
		}
	}
	
	@FXML
	private void goBack(ActionEvent event){
		
		// get a connection to the stage
	    Stage stage = (Stage) goBack.getScene().getWindow();
	    // close the program
	    stage.close();
	}	
}