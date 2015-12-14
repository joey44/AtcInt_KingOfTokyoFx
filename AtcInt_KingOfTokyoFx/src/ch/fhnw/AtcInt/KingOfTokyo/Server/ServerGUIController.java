package ch.fhnw.AtcInt.KingOfTokyo.Server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author arcsuta
 *
 */

public class ServerGUIController {

	ServerStarter serverStarter;

	// Deklarationen der einzelnen Elementen im GUI
	@FXML
	private TextField PortNr;

	@FXML
	private Button Start;

	@FXML
	private Label Status;

	@FXML
	private Stage stage;

	// Methode, damit im Hintergrund alles geschlossen wird
	public void CloseWindow(WindowEvent event) {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {

				// Server beenden
				

			}

		});

	}

	@FXML
	void ServerStarten(ActionEvent event) {
		System.out.println("Server wird gestartet...");

		serverStarter = new ServerStarter(Integer.parseInt(PortNr.getText()));

		Status.setText("Server activ on Port: " + PortNr.getText());
		Start.setDisable(true);
		PortNr.setEditable(false);

	}
}
