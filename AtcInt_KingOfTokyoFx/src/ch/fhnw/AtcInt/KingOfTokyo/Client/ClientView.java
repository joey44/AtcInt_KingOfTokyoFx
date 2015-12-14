package ch.fhnw.AtcInt.KingOfTokyo.Client;

import java.lang.reflect.InvocationTargetException;


import java.lang.reflect.Method;

import javax.naming.LinkRef;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * @author barbara
 *
 */

public class ClientView {

	private Scene scene;

	private Label lbPunkte1;
	private Label lbPunkte2;
	private Label lbPunkte0;
	private Label lbPunkte3;

	private Label lbSpieler2;
	private Label lbSpieler3;
	private Label lbSpieler1;
	private Label lbSpieler0;

	private Label lbLeben2;
	private Label lbLeben1;
	private Label lbLeben0;
	private Label lbLeben3;

	private VBox vbSpieler0;
	private VBox vbSpieler1;
	private VBox vbSpieler2;
	private VBox vbSpieler3;
	// Chat
	private Button btnSenden;
	private TextArea taChat;
	private TextField tf2Chat;

	private Text txChat;

	private Button btnVerbinden;

	private Button btnTokyoVerlassen;
	private TextArea taModeration;
	private Label lbTitel;
	private Label lbTokyo;

	private Hyperlink linkRegeln;

	private Button btnWurfeln;

	private ToggleButton btnWuerfel1;
	private ToggleButton btnWuerfel2;
	private ToggleButton btnWuerfel3;
	private ToggleButton btnWuerfel4;
	private ToggleButton btnWuerfel6;
	private ToggleButton btnWuerfel5;

	private BorderPane root;

	private Image imgWurfel1;
	private Image imgWurfel2;
	private Image imgWurfel3;
	private Image imgWurfel4;
	private Image imgWurfel5;
	
	private ImageView imageSpieler0;
	private ImageView imageSpieler1;
	private ImageView imageSpieler2;
	private ImageView imageSpieler3;
	
	private ImageView[] imageSpieler = new ImageView[4];
	
	private HBox hb5;
	private HBox hb6;
	private HBox hb7;
	private HBox hb8;
	
	

	public Image getWurfelImage(int i) {
		if (i == 1)
			return imgWurfel1;
		else if (i == 2)
			return imgWurfel2;
		else if (i == 3)
			return imgWurfel3;
		else if (i == 4)
			return imgWurfel4;
		else
			return imgWurfel5;
	}

	public ClientView() {
		
	
		imgWurfel1 = new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/1.jpg");
		imgWurfel2 = new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/2.jpg");
		imgWurfel3 = new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/3.jpg");
		imgWurfel4 = new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/herz.jpg");
		imgWurfel5 = new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/tatze.jpg");

		// Generierung der Border Pane

		root = new BorderPane();

		Separator horizSeparatorMenu = new Separator();
		Separator horizSeparatorSpieler = new Separator();
		Separator vertSeparator = new Separator();
		vertSeparator.setOrientation(Orientation.VERTICAL);

		Separator vertSeparatorChat = new Separator();
		vertSeparatorChat.setOrientation(Orientation.VERTICAL);
		HBox menuLayout = new HBox();

		VBox menu = new VBox();
		linkRegeln = new Hyperlink("Regeln"); // Button Regeln wird erstellt

		// linkRegeln.set // --> HyperLink noch ergÃ¤nzen
		lbTitel = new Label("King of Tokyo");
		lbTitel.getStyleClass().add("Titel");

		linkRegeln.setMaxHeight(6);
		linkRegeln.setMaxWidth(70);
		linkRegeln.setBorder(Border.EMPTY);
		menuLayout.getChildren().addAll(lbTitel, linkRegeln);
		menuLayout.setAlignment(Pos.CENTER_LEFT);
		menuLayout.setSpacing(10);

		menu.getChildren().addAll(menuLayout, horizSeparatorMenu);

		menu.setPadding(new Insets(5, 10, 0, 10));
		menu.setSpacing(3);
		VBox spiel = new VBox();
		ImageView image = new ImageView(
				new Image(getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/KingOfTokyo1.jpg"), 510,
						510, true, true));

		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0);
		ds.setOffsetX(3.0);
		ds.setColor(Color.GRAY);

		image.setEffect(ds);

		taModeration = new TextArea();
		taModeration.setEditable(false);
		taModeration.setWrapText(true);

		btnVerbinden = new Button("Verbinden");
		btnVerbinden.getStyleClass().add("custom-button");
		btnVerbinden.setMinWidth(100);
		lbTokyo = new Label("Wer ist auf Tokyo?");

		btnTokyoVerlassen = new Button("Tokyo verlassen");
		btnTokyoVerlassen.getStyleClass().add("custom-button");
		btnTokyoVerlassen.setMinWidth(150);

		HBox hbTokyoOpt = new HBox();
		hbTokyoOpt.setAlignment(Pos.CENTER_LEFT);
		hbTokyoOpt.setSpacing(200);
		hbTokyoOpt.getChildren().addAll(btnTokyoVerlassen, lbTokyo);
		hbTokyoOpt.setPadding(new Insets(-10, 0, -10, 0));
		spiel.setMaxWidth(50);
		spiel.getChildren().addAll(image, hbTokyoOpt, taModeration);

		spiel.setPadding(new Insets(5, 10, 0, 10));
		spiel.setSpacing(0);
		// HBox hbox = new HBox(0);
		// spiel.getChildren().addAll(horizSeparator);

		// hbox.setPadding(Insets.EMPTY);
		// hbox.getChildren().addAll(btnTokyoVerlassen, btnVerbinden, lbTokyo);

		// vb.getChildren().add(taModeration);

		// FlowPane mit den 6 Würfel wird estellt und in der Border Pane
		// angeordnet
		FlowPane fpWurfel = new FlowPane();
		fpWurfel.setOrientation(Orientation.VERTICAL);
		fpWurfel.setVgap(4);
		fpWurfel.setHgap(4);
		fpWurfel.setPadding(new Insets(5, 30, 5, 5));
		btnWurfeln = new Button("Würfeln");
		btnWurfeln.setMinWidth(100);
		btnWurfeln.getStyleClass().add("custom-button");
		fpWurfel.getChildren().add(btnWurfeln);

		btnWuerfel1 = new ToggleButton();
		btnWuerfel1.setPadding(Insets.EMPTY);
		btnWuerfel1.getStyleClass().add("wurfel");
		fpWurfel.getChildren().add(btnWuerfel1);

		btnWuerfel2 = new ToggleButton();
		btnWuerfel2.setPadding(Insets.EMPTY);
		btnWuerfel2.getStyleClass().add("wurfel");
		fpWurfel.getChildren().add(btnWuerfel2);

		btnWuerfel3 = new ToggleButton();
		btnWuerfel3.setPadding(Insets.EMPTY);
		btnWuerfel3.getStyleClass().add("wurfel");
		fpWurfel.getChildren().add(btnWuerfel3);

		btnWuerfel4 = new ToggleButton();
		btnWuerfel4.setPadding(Insets.EMPTY);
		btnWuerfel4.getStyleClass().add("wurfel");
		fpWurfel.getChildren().add(btnWuerfel4);

		btnWuerfel5 = new ToggleButton();
		btnWuerfel5.setPadding(Insets.EMPTY);
		btnWuerfel5.getStyleClass().add("wurfel");
		fpWurfel.getChildren().add(btnWuerfel5);

		btnWuerfel6 = new ToggleButton();
		btnWuerfel6.setPadding(Insets.EMPTY);
		btnWuerfel6.getStyleClass().add("wurfel");
		fpWurfel.getChildren().add(btnWuerfel6);

		fpWurfel.setColumnHalignment(HPos.CENTER);
		// fpWurfel.borderProperty(

		VBox vbAlleSpielerLayout = new VBox();

		HBox hbAlleSpieler = new HBox();
		hbAlleSpieler.setPadding(new Insets(5, 5, 5, 5));
		hbAlleSpieler.setSpacing(40);

		// Spieler generieren

		// Spieler 1

		vbSpieler0 = new VBox();
		vbSpieler0.setMinWidth(150);
		imageSpieler0 = new ImageView(
				new Image(getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/CyberBunny.jpg"), 120, 120,
						true, true));

		imageSpieler0.setEffect(ds);
		hb5 = new HBox();
		hb5.setPadding(new Insets(0, 0, 5, 0));
		hb5.setSpacing(8);
		ImageView image9 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Herz.png"), 25, 25, true, true));
		lbLeben0 = new Label();
		ImageView image10 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Stern.png"), 18, 18, true, true));

		lbPunkte0 = new Label();
		hb5.setMinWidth(130);
		hb5.setMaxWidth(130);
		hb5.getChildren().addAll(image9, lbLeben0, image10, lbPunkte0);
		hb5.setAlignment(Pos.CENTER);
		lbSpieler0 = new Label("SpielerName");
		
		imageSpieler[0]= imageSpieler0;
		
	//	vbSpieler0.getChildren().addAll(lbSpieler0, imageSpieler[0], hb5);
		


		// Spieler 2

		vbSpieler1 = new VBox();
		vbSpieler1.setMinWidth(150);
		imageSpieler1 = new ImageView(
				new Image(getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/GigaZaur.jpg"), 120, 120,
						true, true));
		imageSpieler1.setEffect(ds);
		hb6 = new HBox();
		hb6.setPadding(new Insets(0, 0, 5, 0));
		hb6.setSpacing(8);
		ImageView image12 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Herz.png"), 25, 25, true, true));
		lbLeben1 = new Label();
		ImageView image13 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Stern.png"), 18, 18, true, true));
		lbPunkte1 = new Label();
		hb6.setMinWidth(130);
		hb6.setMaxWidth(130);
		hb6.getChildren().addAll(image12, lbLeben1, image13, lbPunkte1);
		hb6.setAlignment(Pos.CENTER);
		lbSpieler1 = new Label("SpielerName");
		
		
		imageSpieler[1]= imageSpieler1;
		
		
	//	vbSpieler1.getChildren().addAll(lbSpieler1, imageSpieler[1], hb6);

		// Spieler 3

		vbSpieler2 = new VBox();
		vbSpieler2.setMinWidth(150);
		imageSpieler2 = new ImageView(
				new Image(getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/MekaDragon.jpg"), 120, 120,
						true, true));
		imageSpieler2.setEffect(ds);
		hb7 = new HBox();
		hb7.setPadding(new Insets(0, 0, 5, 0));
		hb7.setSpacing(8);
		ImageView image15 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Herz.png"), 25, 25, true, true));
		lbLeben2 = new Label();
		ImageView image16 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Stern.png"), 18, 18, true, true));
		lbPunkte2 = new Label();
		hb7.getChildren().addAll(image15, lbLeben2, image16, lbPunkte2);
		hb7.setAlignment(Pos.CENTER);
		hb7.setMinWidth(130);
		hb7.setMaxWidth(130);
		lbSpieler2 = new Label("SpielerName");
		
	
		imageSpieler[2]= imageSpieler2;
		
	//	vbSpieler2.getChildren().addAll(lbSpieler2, imageSpieler[2], hb7);

		// Spieler 4

		vbSpieler3 = new VBox();
		vbSpieler3.setMinWidth(150);
		imageSpieler3 = new ImageView(
				new Image(getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/TheKing.jpg"), 120, 120,
						true, true));
		imageSpieler3.setEffect(ds);
		hb8 = new HBox();
		hb8.setPadding(new Insets(0, 0, 5, 0));
		hb8.setSpacing(8);
		ImageView image18 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Herz.png"), 25, 25, true, true));
		lbLeben3 = new Label();
		ImageView image19 = new ImageView(new Image(
				getClass().getResourceAsStream("/ch/fhnw/AtcInt/KingOfTokyo/Images/Stern.png"), 18, 18, true, true));
		lbPunkte3 = new Label();
		hb8.getChildren().addAll(image18, lbLeben3, image19, lbPunkte3);
		hb8.setAlignment(Pos.CENTER);
		hb8.setMinWidth(130);
		hb8.setMaxWidth(130);
		lbSpieler3 = new Label("SpielerName");
		
		
		imageSpieler[3]= imageSpieler3;

		
	//	vbSpieler3.getChildren().addAll(lbSpieler3, imageSpieler[3], hb8);

		hbAlleSpieler.getChildren().addAll(vbSpieler0, vbSpieler1, vbSpieler2, vbSpieler3);
		hbAlleSpieler.setPadding(new Insets(0, 0, 0, 0));
		vbAlleSpielerLayout.getChildren().addAll(horizSeparatorSpieler, hbAlleSpieler);
		// vbAlleSpielerLayout.getChildren().add(horizSeparatorSpieler);
		vbAlleSpielerLayout.setPadding(new Insets(-1, 10, 0, 10));
		vbAlleSpielerLayout.setSpacing(10);
		HBox hbChatLayout = new HBox();
		// Chat --> Rechts ausgerichtet
		VBox vbChat = new VBox();
		// txChat = new Text("Chat");
		Label lbChat = new Label("Chat");
		// txChat.setFont(Font.font("Arial", FontWeight.NORMAL,
		// FontPosture.REGULAR, 14));
		vbChat.setPadding(new Insets(10, 10, 0, 10));
		spiel.setSpacing(20);
		taChat = new TextArea();
		taChat.setScrollLeft(0);
		taChat.setEditable(false);
		taChat.setWrapText(true);
		taChat.setFocusTraversable(false);

		taChat.setPrefWidth(200);
		taChat.setPrefHeight(300);
		tf2Chat = new TextField();
		tf2Chat.setFocusTraversable(true);
		btnSenden = new Button();
		btnSenden.setText("Senden");
		btnSenden.getStyleClass().add("custom-button");
		vbChat.getChildren().addAll(lbChat, taChat, tf2Chat, btnSenden);

		hbChatLayout.getChildren().addAll(vertSeparatorChat, vbChat);
		scene = new Scene(root, 950, 650, Color.WHITE);

		scene.getStylesheets().add("/ch/fhnw/AtcInt/KingOfTokyo/Styles/stylesheet.css");

		root.setTop(menu);
		root.setLeft(spiel);
		root.setCenter(fpWurfel);
		root.setBottom(vbAlleSpielerLayout);
		root.setRight(hbChatLayout);
		
		
		

	}

	public VBox getVbSpieler0() {
		return vbSpieler0;
	}

	public VBox getVbSpieler1() {
		return vbSpieler1;
	}

	public VBox getVbSpieler2() {
		return vbSpieler2;
	}

	public VBox getVbSpieler3() {
		return vbSpieler3;
	}

	public void setModeration(String modText) {

		this.taModeration.appendText("\n" + modText);
	}

	public Label getLbTokyo() {
		return lbTokyo;
	}

	public void setLbTokyo(Label lbTokyo) {
		this.lbTokyo = lbTokyo;
	}

	public void show(Stage stage) {
		stage.setTitle("King of Tokyo");
		stage.setScene(scene);
		stage.show();
		stage.getIcons().add(new Image("/ch/fhnw/AtcInt/KingOfTokyo/Images/logo.png"));
		// primaryStage.setTitle("King of Tokyo");
		// primaryStage.setScene(scene);
		stage.setResizable(false);

		// primaryStage.show();

	}

	public void setSpielerStandard(int clientID) {

		switch (clientID) {
		case 0:

			getLbSpieler0().setTextFill(Color.BLACK);
			break;
		case 1:

			getLbSpieler1().setTextFill(Color.BLACK);
			break;
		case 2:

			getLbSpieler2().setTextFill(Color.BLACK);
			break;
		case 3:

			getLbSpieler3().setTextFill(Color.BLACK);
			break;
		}
	}

	public void setSpielerAmZug(int clientID) {

		switch (clientID) {
		case 0:
			getLbSpieler0().setTextFill(Color.BLUE);
			break;
		case 1:
			getLbSpieler1().setTextFill(Color.BLUE);
			break;
		case 2:
			getLbSpieler2().setTextFill(Color.BLUE);
			break;

		case 3:
			getLbSpieler3().setTextFill(Color.BLUE);
			break;
		}

	}

	public void setSpielerInaktiv(int clientID) {

		switch (clientID) {
		case 0:

			getLbSpieler0().setTextFill(Color.RED);
			// getVbSpieler0().setDisable(true);
			break;
		case 1:

			getLbSpieler1().setTextFill(Color.RED);
			// getVbSpieler1().setDisable(true);
			break;
		case 2:

			getLbSpieler2().setTextFill(Color.RED);
			// getVbSpieler2().setDisable(true);
			break;
		case 3:

			getLbSpieler3().setTextFill(Color.RED);
			// getVbSpieler3().setDisable(true);
			break;
		}

	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Label getLbPunkte0() {
		return lbPunkte0;
	}

	public void setLbPunkte0(Label lbPunkte0) {
		this.lbPunkte0 = lbPunkte0;
	}

	public Label getLbPunkte1() {
		return lbPunkte1;
	}

	public void setLbPunkte1(Label lbPunkte1) {
		this.lbPunkte1 = lbPunkte1;
	}

	public Label getLbPunkte2() {
		return lbPunkte2;
	}

	public void setLbPunkte2(Label lbPunkte2) {
		this.lbPunkte2 = lbPunkte2;
	}

	public Label getLbPunkte3() {
		return lbPunkte3;
	}

	public void setLbPunkte3(Label lbPunkte3) {
		this.lbPunkte3 = lbPunkte3;
	}

	public Label getLbSpieler0() {
		return lbSpieler0;
	}

	public void setLbSpieler0(Label lbSpieler0) {
		this.lbSpieler0 = lbSpieler0;
	}

	public Label getLbSpieler1() {
		return lbSpieler1;
	}

	public void setLbSpieler1(Label lbSpieler1) {
		this.lbSpieler1 = lbSpieler1;
	}

	public Label getLbSpieler2() {
		return lbSpieler2;
	}

	public void setLbSpieler2(Label lbSpieler2) {
		this.lbSpieler2 = lbSpieler2;
	}

	public Label getLbSpieler3() {
		return lbSpieler3;
	}

	public void setLbSpieler3(Label lbSpieler3) {
		this.lbSpieler3 = lbSpieler3;
	}

	public Label getLbLeben0() {
		return lbLeben0;
	}

	public void setLbLeben0(Label lbLeben0) {
		this.lbLeben0 = lbLeben0;
	}

	public Label getLbLeben1() {
		return lbLeben1;
	}

	public void setLbLeben1(Label lbLeben1) {
		this.lbLeben1 = lbLeben1;
	}

	public Label getLbLeben2() {
		return lbLeben2;
	}

	public void setLbLeben2(Label lbLeben2) {
		this.lbLeben2 = lbLeben2;
	}

	public Label getLbLeben3() {
		return lbLeben3;
	}

	public void setLbLeben3(Label lbLeben3) {
		this.lbLeben3 = lbLeben3;
	}

	public Label getLbTitel() {
		return lbTitel;
	}

	public void setLbTitel(Label lbTitel) {
		this.lbTitel = lbTitel;
	}

	public Button getBtnWurfeln() {
		return btnWurfeln;
	}

	public void setBtnWurfeln(Button btnWurfeln) {
		this.btnWurfeln = btnWurfeln;
	}

	public Button getBtnTokyoVerlassen() {
		return btnTokyoVerlassen;
	}

	public void setBtnTokyoVerlassen(Button btnTokyoVerlassen) {
		this.btnTokyoVerlassen = btnTokyoVerlassen;
	}

	public Button getBtnVerbinden() {
		return btnVerbinden;
	}

	public void setBtnVerbinden(Button btnVerbinden) {
		this.btnVerbinden = btnVerbinden;
	}

	public Button getBtnSenden() {
		return btnSenden;
	}

	public void setBtnSenden(Button btnSenden) {
		this.btnSenden = btnSenden;
	}

	public Text getTxChat() {
		return txChat;
	}

	public void setTxChat(Text txChat) {
		this.txChat = txChat;
	}

	// public Button getBtnRegeln() {
	// return btnRegeln;
	// }
	//
	// public void setBtnRegeln(Button btnRegeln) {
	// this.btnRegeln = btnRegeln;
	// }

	public ToggleButton getBtnWuerfel1() {
		return btnWuerfel1;
	}

	public void setBtnWuerfel1(ToggleButton btnWuerfel1) {
		this.btnWuerfel1 = btnWuerfel1;
	}

	public ToggleButton getBtnWuerfel2() {
		return btnWuerfel2;
	}

	public void setBtnWuerfel2(ToggleButton btnWuerfel2) {
		this.btnWuerfel2 = btnWuerfel2;
	}

	public ToggleButton getBtnWuerfel3() {
		return btnWuerfel3;
	}

	public void setBtnWuerfel3(ToggleButton btnWuerfel3) {
		this.btnWuerfel3 = btnWuerfel3;
	}

	public ToggleButton getBtnWuerfel4() {
		return btnWuerfel4;
	}

	public void setBtnWuerfel4(ToggleButton btnWuerfel4) {
		this.btnWuerfel4 = btnWuerfel4;
	}

	public ToggleButton getBtnWuerfel6() {
		return btnWuerfel6;
	}

	public void setBtnWuerfel6(ToggleButton btnWuerfel6) {
		this.btnWuerfel6 = btnWuerfel6;
	}

	public ToggleButton getBtnWuerfel5() {
		return btnWuerfel5;
	}

	public void setBtnWuerfel5(ToggleButton btnWuerfel5) {
		this.btnWuerfel5 = btnWuerfel5;
	}

	public TextArea getTaChat() {
		return taChat;
	}

	public void setTaChat(TextArea taChat) {
		this.taChat = taChat;
	}

	public TextField getTf2Chat() {
		return tf2Chat;
	}

	public void setTf2Chat(TextField tf2Chat) {
		this.tf2Chat = tf2Chat;
	}

	public TextArea getTaModeration() {
		return taModeration;
	}

	public void setTaModeration(TextArea taModeration) {
		this.taModeration = taModeration;
	}

	public ImageView getImageSpieler(int clientID) {
		return imageSpieler[clientID];
	}

	public void setImageSpieler(int clientID, int monsterID) {
		
		if (monsterID == 0){
			this.imageSpieler[clientID] = imageSpieler0;
		}
		else if (monsterID == 1){
			this.imageSpieler[clientID] = imageSpieler1;
		}
		else if (monsterID == 2){
			this.imageSpieler[clientID] = imageSpieler2;
		}
		else{
			this.imageSpieler[clientID] = imageSpieler3;
		}
		
	}

	public HBox getHb5() {
		return hb5;
	}

	public void setHb5(HBox hb5) {
		this.hb5 = hb5;
	}

	public HBox getHb6() {
		return hb6;
	}

	public void setHb6(HBox hb6) {
		this.hb6 = hb6;
	}

	public HBox getHb7() {
		return hb7;
	}

	public void setHb7(HBox hb7) {
		this.hb7 = hb7;
	}

	public HBox getHb8() {
		return hb8;
	}

	public void setHb8(HBox hb8) {
		this.hb8 = hb8;
	}
	
	

}
