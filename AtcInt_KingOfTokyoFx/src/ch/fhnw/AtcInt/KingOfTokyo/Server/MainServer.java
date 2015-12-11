package ch.fhnw.AtcInt.KingOfTokyo.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainServer extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("ServerGUI.fxml"));
		primaryStage.setTitle("ServerGUI");
		primaryStage.setScene(new Scene(root, 555, 450));
		primaryStage.show();
		

	}

 
}
