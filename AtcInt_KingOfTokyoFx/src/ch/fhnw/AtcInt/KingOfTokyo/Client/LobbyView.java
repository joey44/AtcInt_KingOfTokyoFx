package ch.fhnw.AtcInt.KingOfTokyo.Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author arcsuta
 *
 */

public class LobbyView {

	private Scene scene;
	private BorderPane root;

	private Hyperlink liRegeln;
	private Label lbTitel;

	private Label lbSpielerName1;
	private Label lbSpielerName2;
	private Label lbSpielerName3;
	private Label lbSpielerName4;
	private Button btnSpielstarten;

	private Label lbMonsterAuswahl;
	private Button btnCyberBunny;
	private Button btnGigaZaur;
	private Button btnMekaDragon;
	private Button btnTheKing;
	
	private Label lbHighScoreWerte;

	public LobbyView() {

		root = new BorderPane();
		root.setPadding(new Insets(5));

		// Menü-Leiste
		// linkRegeln.set // --> HyperLink noch zu ergänzen
		liRegeln = new Hyperlink("Regeln");
		lbTitel = new Label("King of Tokyo");
		HBox menu = new HBox(lbTitel, liRegeln);
		menu.setSpacing(10);
		menu.setPadding(new Insets(10, 0, 50, 0));
		lbTitel.getStyleClass().add("Titel");

		lbSpielerName1 = new Label("Spieler 1");
		lbSpielerName2 = new Label("Spieler 2");
		lbSpielerName3 = new Label("Spieler 3");
		lbSpielerName4 = new Label("Spieler 4");
		// Positionierung innerhalb Spielerliste
		VBox links1 = new VBox(lbSpielerName1, lbSpielerName2, lbSpielerName3,
				lbSpielerName4);
		links1.setAlignment(Pos.CENTER);
		links1.setPadding(new Insets(20));
		links1.setSpacing(20);

		// Positionierung Spielstartknopf
		btnSpielstarten = new Button("Spiel starten");
		btnSpielstarten.setMinSize(100, 40);
		btnSpielstarten.getStyleClass().add("custom-button");
		VBox links2 = new VBox(btnSpielstarten);
		links2.setAlignment(Pos.CENTER_LEFT);
		links2.setPadding(new Insets(20));

		// Spielerliste & Startknopf zusammen in einem VBox positionieren
		VBox SpielerListe = new VBox(links1, links2);
		// VBox.setMargin(links1, new Insets(80));
		SpielerListe.setAlignment(Pos.CENTER_LEFT);
		SpielerListe.setMaxSize(20, 20);
		SpielerListe.setPadding(new Insets(5, 0, 0, 0));
		SpielerListe.getStyleClass().add("VBox");
		Label Spieler = new Label ("Spieler");
		

		VBox BeschriftungLinks = new VBox (Spieler);
		BeschriftungLinks.setAlignment(Pos.TOP_CENTER);
		VBox links = new VBox(BeschriftungLinks,SpielerListe);
		links.setPadding(new Insets(50));

		lbMonsterAuswahl = new Label();
		lbMonsterAuswahl.setMinSize(5, 40);
		lbMonsterAuswahl.setWrapText(true);

		// Monster-Buttons inkl. Fotos
		btnCyberBunny = new Button();
		Image monster1 = new Image(getClass().getResourceAsStream(
				"/ch/fhnw/AtcInt/KingOfTokyo/Images/CyberBunny.jpg"), 150, 150,
				true, true);
		btnCyberBunny.setGraphic(new ImageView(monster1));
		btnCyberBunny.setPadding(Insets.EMPTY);

		btnGigaZaur = new Button();
		Image monster2 = new Image(getClass().getResourceAsStream(
				"/ch/fhnw/AtcInt/KingOfTokyo/Images/GigaZaur.jpg"), 150, 150,
				true, true);
		btnGigaZaur.setGraphic(new ImageView(monster2));
		btnGigaZaur.setPadding(Insets.EMPTY);

		btnMekaDragon = new Button();
		Image monster3 = new Image(getClass().getResourceAsStream(
				"/ch/fhnw/AtcInt/KingOfTokyo/Images/MekaDragon.jpg"), 150, 150,
				true, true);
		btnMekaDragon.setGraphic(new ImageView(monster3));
		btnMekaDragon.setPadding(Insets.EMPTY);

		btnTheKing = new Button();
		Image monster4 = new Image(getClass().getResourceAsStream(
				"/ch/fhnw/AtcInt/KingOfTokyo/Images/TheKing.jpg"), 150, 150,
				true, true);
		btnTheKing.setGraphic(new ImageView(monster4));
		btnTheKing.setPadding(Insets.EMPTY);

		VBox monster12 = new VBox(btnCyberBunny, btnGigaZaur);
		monster12.setPadding(new Insets(5));
		monster12.setSpacing(10);

		VBox monster34 = new VBox(btnMekaDragon, btnTheKing);
		monster34.setPadding(new Insets(5));
		monster34.setSpacing(10);

		HBox monster1234 = new HBox(monster12, monster34);
		monster1234.setPadding(new Insets(20));
		monster1234.setAlignment(Pos.CENTER);
		VBox monster = new VBox(monster1234, lbMonsterAuswahl);
		monster.setPadding(new Insets(20));
		monster.setAlignment(Pos.CENTER);
		monster.setMaxSize(200, 100);
		monster.getStyleClass().add("VBox");

		// Logo King of Tokyo
		ImageView Logo = new ImageView(new Image(getClass()
				.getResourceAsStream(
						"/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"), 200,
				200, true, true));

		HBox Bild = new HBox(Logo);
		Bild.setAlignment(Pos.CENTER);
		Label Monster = new Label ("Monster");
		

		VBox BeschriftungMitte = new VBox (Monster);
		BeschriftungMitte.setAlignment(Pos.TOP_CENTER);
		VBox mitte = new VBox(BeschriftungMitte,monster, Logo);
		mitte.setPadding(new Insets(50));
		mitte.setAlignment(Pos.TOP_CENTER);

		// HighScoreListe erstellen
		lbHighScoreWerte = new Label();
		lbHighScoreWerte.setMinSize(100, 200);;
		lbHighScoreWerte.setWrapText(true);
		
		VBox HighScoreListe = new VBox (lbHighScoreWerte);		
		HighScoreListe.setPadding(new Insets(50));
		HighScoreListe.setAlignment(Pos.CENTER);
		HighScoreListe.getStyleClass().add("VBox");
		Label HighScore = new Label ("HighScore-Liste");
		

		VBox BeschriftungRechts = new VBox (HighScore);
		BeschriftungRechts.setAlignment(Pos.TOP_CENTER);
		VBox rechts = new VBox(BeschriftungRechts,HighScoreListe);
		rechts.setPadding(new Insets (50));

		root.setTop(menu);
		root.setLeft(links);
		root.setCenter(mitte);
		root.setRight(rechts);
		scene = new Scene(root, 1100, 670);

		scene.getStylesheets().add(
				"/ch/fhnw/AtcInt/KingOfTokyo/Styles/stylesheet.css");

	}

	public void show(Stage stage) {
		stage.setTitle("King of Tokyo");
		stage.getIcons().add(new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));
		stage.setScene(scene);
		stage.show();

	}

	public Label getLbTitel() {
		return lbTitel;
	}

	public void setLbTitel(Label lbTitel) {
		this.lbTitel = lbTitel;
	}

	public Label getLbSpielerName1() {
		return lbSpielerName1;
	}

	public void setLbSpielerName1(Label lbSpielerName1) {
		this.lbSpielerName1 = lbSpielerName1;
	}

	public Label getLbSpielerName2() {
		return lbSpielerName2;
	}

	public void setLbSpielerName2(Label lbSpielerName2) {
		this.lbSpielerName2 = lbSpielerName2;
	}

	public Label getLbSpielerName3() {
		return lbSpielerName3;
	}

	public void setLbSpielerName3(Label lbSpielerName3) {
		this.lbSpielerName3 = lbSpielerName3;
	}

	public Label getLbSpielerName4() {
		return lbSpielerName4;
	}

	public void setLbSpielerName4(Label lbSpielerName4) {
		this.lbSpielerName4 = lbSpielerName4;
	}

	public Button getBtnSpielstarten() {
		return btnSpielstarten;
	}

	public void setBtnSpielstarten(Button btnSpielstarten) {
		this.btnSpielstarten = btnSpielstarten;
	}

	public Label getLbMonsterAuswahl() {
		return lbMonsterAuswahl;
	}

	public void setLbMonsterAuswahl(Label lbMonsterAuswahl) {
		this.lbMonsterAuswahl = lbMonsterAuswahl;
	}
	
	public Button getBtnCyberBunny() {
		return btnCyberBunny;
	}

	public void setBtnCyberBunny(Button btnCyberBunny) {
		this.btnCyberBunny = btnCyberBunny;
	}

	public Button getBtnGigaZaur() {
		return btnGigaZaur;
	}

	public void setBtnGigaZaur(Button btnGigaZaur) {
		this.btnGigaZaur = btnGigaZaur;
	}

	public Button getBtnMekaDragon() {
		return btnMekaDragon;
	}

	public void setBtnMekaDragon(Button btnMekaDragon) {
		this.btnMekaDragon = btnMekaDragon;
	}

	public Button getBtnTheKing() {
		return btnTheKing;
	}

	public void setBtnTheKing(Button btnTheKing) {
		this.btnTheKing = btnTheKing;
	}
	
	public Label getLbHighScoreWerte() {
		return lbHighScoreWerte;
	}

	public void setLbHighScoreWerte(Label lbHighScoreWerte) {
		this.lbHighScoreWerte = lbHighScoreWerte;
	}

}