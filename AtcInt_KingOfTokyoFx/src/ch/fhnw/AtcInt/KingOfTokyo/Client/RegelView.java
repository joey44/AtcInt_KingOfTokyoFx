package ch.fhnw.AtcInt.KingOfTokyo.Client;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Barbara
 */

// Main Klasse Ausführen aller Würfelfunktionalitäten
public class RegelView {
	
	
	private Scene regelScene;
	private StackPane root;

	public RegelView() {

		// ScrollPane mit den entsprechenden Regeln des Spiels "King of Tokyo"
		// wird erzeugt. Zudem wird eine VBox erzeugt
		// und die Seiten der Regelanleitung hinzugefügt

		
		root = new StackPane();
		
		
		VBox vb = new VBox();

		ImageView RegelnS1 = new ImageView(new Image(getClass()
				.getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/RegelnSeite1.jpg")));
		ImageView RegelnS2 = new ImageView(new Image(getClass()
				.getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/RegelnSeite2.jpg")));
		ImageView RegelnS3 = new ImageView(new Image(getClass()
				.getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/RegelnSeite3.jpg")));
		vb.getChildren().addAll(RegelnS1, RegelnS2, RegelnS3);
		ScrollPane sp = new ScrollPane(vb);

		// Das zweite Fenster wird erzeugt in dem dann die Regeln ausgegeben
		// werden
		
		
		root.getChildren().add(sp);
		
		regelScene = new Scene(root, 950, 650);
			

	}

	public void show(Stage stage) {
		stage.setTitle("King of Tokyo - Regeln");
		stage.getIcons().add(
				new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));
		stage.setScene(regelScene);
		stage.show();

	}

}
