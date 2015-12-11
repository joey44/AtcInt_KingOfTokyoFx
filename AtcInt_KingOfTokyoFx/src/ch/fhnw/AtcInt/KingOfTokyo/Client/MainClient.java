package ch.fhnw.AtcInt.KingOfTokyo.Client;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainClient extends Application {

	private ClientView view;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		view = new ClientView();

		ClientController c = new ClientController(view, stage);

		view.show(stage);

	}

}
