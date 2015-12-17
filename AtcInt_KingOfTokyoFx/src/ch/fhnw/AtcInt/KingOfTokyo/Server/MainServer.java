package ch.fhnw.AtcInt.KingOfTokyo.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * @author arcsuta
 *
 */


public class MainServer extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ServerGUI.fxml"));
		primaryStage.setTitle("ServerGUI");
		primaryStage.getIcons().add(new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));
		primaryStage.setScene(new Scene(root, 340, 400));
		primaryStage.show();

	}

}
