package atcint_login;

import ch.fhnw.AtcInt.KingOfTokyo.Client.ClientController;
import ch.fhnw.AtcInt.KingOfTokyo.Client.ClientView;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.fxml.FXML;

/**
 * @code Controls the elements of the login model
 * @author Marianne
 *
 */

public class AtcInt_Login_Controller {

	@FXML
	private Button closeButton;
	@FXML
	private Button goOn;
	@FXML
	private TextField Server, Port, Spieler, Passwort;
	@FXML
	private Hyperlink forgetPassword;
	@FXML
	private Hyperlink newUser;
	
	@FXML
	private void closeButton(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void goOn(ActionEvent event){
		System.out.println("Server: " + Server.getText());
		System.out.println("Port: " + Port.getText());
		System.out.println("Spieler: " + Spieler.getText());
		System.out.println("Passwort: " + Passwort.getText());
		
		atcint_db.Benutzer benutzer = atcint_db.Benutzer.Find(Spieler.getText(), Passwort.getText());
		if(benutzer!=null){
			System.out.println("Benutzer ist in der Datenbank, Hallo " + benutzer.SpielerName());	
		
		
			String server = Server.getText();
			int port = Integer.parseInt(Port.getText());
			String name = benutzer.SpielerName();
			
			// get a connection to the stage
		    Stage stage = (Stage) goOn.getScene().getWindow();
		    
		    // close the program
		    stage.close();
		    
		    ClientView view = new ClientView();

			ClientController c = new ClientController(view, stage,server, port, name);
			
			view.show(stage);
		
		}
		
		else {
			atcint_fehler.AtcInt_Fehler_Passwort_View view = new atcint_fehler.AtcInt_Fehler_Passwort_View();
			view.start();
			System.out.println("Benutzer ist nicht in der Datenbank oder Passwort falsch");
		}	
	}		
	
	@FXML
	private void newUser(ActionEvent event){
		atcint_benutzer.Atcint_Benutzer_View view = new atcint_benutzer.Atcint_Benutzer_View();
		view.start();		
	}
	
	@FXML
	private void forgetPasswort(ActionEvent event){
		System.out.println("Wir arbeiten zur Zeit an einer Lösung für dieses Problem. Vielen Dank für Dein Verständnis...");
	}
}
