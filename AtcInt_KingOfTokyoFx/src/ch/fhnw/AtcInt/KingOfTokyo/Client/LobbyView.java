package ch.fhnw.AtcInt.KingOfTokyo.Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

	private TextArea taMonsterAuswahl;
	private Button btnCyberBunny;
	private Button btnGigaZaur;
	private Button btnMekaDragon;
	private Button btnTheKing;

	private Label HighScoreSpieler1;
	private Label HighScoreSpieler2;
	private Label HighScoreSpieler3;
	private Label HighScoreSpieler4;
	private Label HighScoreSpieler5;
	private Label HighScoreSpieler6;
	private Label HighScoreSpieler7;
	private Label HighScoreSpieler8;
	private Label HighScoreSpieler9;
	private Label HighScoreSpieler10;

	public LobbyView() {

		root = new BorderPane();
		root.setPadding(new Insets (5));
		
		//Menü-Leiste
		// linkRegeln.set // --> HyperLink noch zu ergänzen
		liRegeln = new Hyperlink("Regeln");
		lbTitel = new Label("King of Tokyo");
		HBox menu = new HBox(lbTitel, liRegeln);
		menu.setSpacing(10);
		menu.setPadding(new Insets (10,0,50,0));
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
		links2.setPadding(new Insets(80));

		// Spielerliste & Startknopf zusammen in einem VBox positionieren
		VBox SpielerListe = new VBox(links1, links2);
	//	VBox.setMargin(links1, new Insets(80));
		SpielerListe.setAlignment(Pos.CENTER_LEFT);
		SpielerListe.setMaxSize(20, 20);
		SpielerListe.setPadding(new Insets (5,0,0,0));
		SpielerListe.getStyleClass().add("VBox");
		HBox links = new HBox (SpielerListe);
		links.setPadding(new Insets (20));

		taMonsterAuswahl = new TextArea();
		
		taMonsterAuswahl.setEditable(false);
		taMonsterAuswahl.setWrapText(true);

		// Monster-Buttons inkl. Fotos
		btnCyberBunny = new Button();
		Image monster1 = new Image(getClass().getResourceAsStream(
				"CyberBunny.jpg"), 150, 150, true, true);
		btnCyberBunny.setGraphic(new ImageView(monster1));

		btnGigaZaur = new Button();
		Image monster2 = new Image(getClass().getResourceAsStream(
				"GigaZaur.jpg"), 150, 150, true, true);
		btnGigaZaur.setGraphic(new ImageView(monster2));

		btnMekaDragon = new Button();
		Image monster3 = new Image(getClass().getResourceAsStream(
				"MekaDragon.jpg"), 150, 150, true, true);
		btnMekaDragon.setGraphic(new ImageView(monster3));

		btnTheKing = new Button();
		Image monster4 = new Image(getClass()
				.getResourceAsStream("TheKing.jpg"), 150, 150, true, true);
		btnTheKing.setGraphic(new ImageView(monster4));

		VBox monster12 = new VBox(btnCyberBunny, btnGigaZaur);
		monster12.setPadding(new Insets(5));
		monster12.setSpacing(10);
		
		VBox monster34 = new VBox(btnMekaDragon, btnTheKing);
		monster34.setPadding(new Insets(5));
		monster34.setSpacing(10);

		HBox monster1234 = new HBox(monster12, monster34);
		monster1234.setPadding(new Insets(20));
		monster1234.setAlignment(Pos.CENTER);
		VBox monster = new VBox(monster1234, taMonsterAuswahl);
		monster.setAlignment(Pos.CENTER);
		monster.setMaxSize(200, 100);
		monster.getStyleClass().add("VBox");
		HBox mitte = new HBox (monster);
		mitte.setPadding(new Insets (50));
		mitte.setAlignment(Pos.TOP_CENTER);

		// Logo King of Tokyo

		ImageView Logo = new ImageView(new Image(getClass()
				.getResourceAsStream("logo.png"), 200, 200, true, true));

		// HighScoreListe erstellen
		HighScoreSpieler1 = new Label("HighScoreS1 Punkte");
		HighScoreSpieler2 = new Label("HighScoreS2 Punkte");
		HighScoreSpieler3 = new Label("HighScoreS3 Punkte");
		HighScoreSpieler4 = new Label("HighScoreS4 Punkte");
		HighScoreSpieler5 = new Label("HighScoreS5 Punkte");
		HighScoreSpieler6 = new Label("HighScoreS6 Punkte");
		HighScoreSpieler7 = new Label("HighScoreS7 Punkte");
		HighScoreSpieler8 = new Label("HighScoreS8 Punkte");
		HighScoreSpieler9 = new Label("HighScoreS9 Punkte");
		HighScoreSpieler10 = new Label("HighScoreS10 Punkte");


		VBox HighScoreListe = new VBox(HighScoreSpieler1, HighScoreSpieler2, HighScoreSpieler3,
				HighScoreSpieler4, HighScoreSpieler5, HighScoreSpieler6, HighScoreSpieler7,
				HighScoreSpieler8, HighScoreSpieler9, HighScoreSpieler10);
		VBox.setMargin(HighScoreListe, new Insets (50));
		HighScoreListe.setPadding(new Insets(50));
		HighScoreListe.setAlignment(Pos.CENTER);
		HighScoreListe.setSpacing(5);
		HighScoreListe.getStyleClass().add("VBox");
		
		HBox Bild = new HBox(Logo);
		Bild.setAlignment(Pos.CENTER);
		VBox rechts = new VBox(HighScoreListe, Bild);
		rechts.setPadding(new Insets (10));
		

		root.setTop(menu);
		root.setLeft(links);
		root.setCenter(mitte);
		root.setRight(rechts);
		scene = new Scene(root, 1200, 670);
		
	//	scene.getStylesheets().add("style/stylesheet.css");

	}

	public void show(Stage stage) {
		stage.setTitle("King of Tokyo");
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

	

	public TextArea getTaMonsterAuswahl() {
		return taMonsterAuswahl;
	}

	public void setTaMonsterAuswahl(TextArea taMonsterAuswahl) {
		this.taMonsterAuswahl = taMonsterAuswahl;
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

	
}