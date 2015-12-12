package ch.fhnw.AtcInt.KingOfTokyo.Client;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestMainClient extends Application {

	private ClientView view;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		view = new ClientView();

		ClientController c = new ClientController(view, stage, "127.0.0.1", 44444, "TestPlayer");

		

	}

}
