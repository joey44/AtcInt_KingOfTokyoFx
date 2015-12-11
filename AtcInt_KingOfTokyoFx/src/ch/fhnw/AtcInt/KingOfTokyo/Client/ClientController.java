package ch.fhnw.AtcInt.KingOfTokyo.Client;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Chat;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientController {

	private ClientView view;
	private ClientServerVerbindung clientServerVerbindung;

	private DatenAustausch datenAustausch;

	private int clientID;
	private Stage stage;

	private String server;
	private int port;
	private String name;


	public ClientController(ClientView view, Stage stage, String server, int port, String name) {

		this.view = view;
		this.stage = stage;
		this.server = server;
		this.port = port;
		this.name = name;

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				clientServerVerbindung.disconnect();
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Spiel wird beendet");
				alert.setHeaderText("Spiel wird beendet");
				alert.setContentText("Spieler " + name + " wird inaktiv gesetzt");
				
				//getDatenAustausch().getSpielerByID(clientID).setSpielerAktiv(false);
				

				alert.showAndWait();

			}
		});

		clientServerVerbindung = new ClientServerVerbindung(this, view, server, port, name);

	
		
		view.getBtnWurfeln().setOnAction(new wurfelnEventHandler());

		view.getBtnWuerfel1().setOnAction(new wurfeln1AuswahlEventHandler());
		view.getBtnWuerfel2().setOnAction(new wurfeln2AuswahlEventHandler());
		view.getBtnWuerfel3().setOnAction(new wurfeln3AuswahlEventHandler());
		view.getBtnWuerfel4().setOnAction(new wurfeln4AuswahlEventHandler());
		view.getBtnWuerfel5().setOnAction(new wurfeln5AuswahlEventHandler());
		view.getBtnWuerfel6().setOnAction(new wurfeln6AuswahlEventHandler());

		view.getBtnSenden().setOnAction(new nachrichtSendenEventHandler());

		view.getBtnTokyoVerlassen().setOnAction(
				new tokyoVerlassenEventHandler());
		view.getBtnVerbinden().setOnAction(new verbindenEventHandler());

		view.getBtnWurfeln().setDisable(true);

		view.getBtnWuerfel1().setDisable(true);
		view.getBtnWuerfel2().setDisable(true);
		view.getBtnWuerfel3().setDisable(true);
		view.getBtnWuerfel4().setDisable(true);
		view.getBtnWuerfel5().setDisable(true);
		view.getBtnWuerfel6().setDisable(true);

		view.getBtnTokyoVerlassen().setDisable(true);

		view.getBtnVerbinden().setDefaultButton(true);

		view.getBtnSenden().setDisable(true);
		
		verbindenMitServer();
		
		//Name Server mitteilen
		clientServerVerbindung.sendStringToServer(name);

	}

	public void objectFromServerSetDatenaustausch(DatenAustausch d) {

		this.datenAustausch = d;

		// DatenAustausch.setInstanz(d);

		// view.getLbModeration().setText(d.getWurfel().toString());
		// updateClientGUI();

	}

	public void updateClientGUI(DatenAustausch d, int clientID) {

		// DatenAustausch d = this.datenAustausch;

		// view.getLbModeration().setText("gewürfelt Client:" + clientID);
		// view.setModeration("gewürfelt Client:" + clientID);
		view.getLbSpieler0().setText(ClientSpielLogik.spielerName(d, 0));
		view.getLbLeben0().setText(ClientSpielLogik.lebenAnzeigen(d, 0));
		view.getLbPunkte0().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 0));

		view.getLbSpieler1().setText(ClientSpielLogik.spielerName(d, 1));
		view.getLbLeben1().setText(ClientSpielLogik.lebenAnzeigen(d, 1));
		view.getLbPunkte1().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 1));

		view.getLbSpieler2().setText(ClientSpielLogik.spielerName(d, 2));
		view.getLbLeben2().setText(ClientSpielLogik.lebenAnzeigen(d, 2));
		view.getLbPunkte2().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 2));

		view.getLbSpieler3().setText(ClientSpielLogik.spielerName(d, 3));
		view.getLbLeben3().setText(ClientSpielLogik.lebenAnzeigen(d, 3));
		view.getLbPunkte3().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 3));

		// view.getLbModeration().setText(ClientSpielLogik.spielModerieren(d));
		view.setModeration(ClientSpielLogik.spielModerieren(d));
		view.getLbTokyo().setText(ClientSpielLogik.standortAnzeigen(d));

		view.getBtnWurfeln().setDisable(true);
		view.getBtnTokyoVerlassen().setDisable(true);

		view.getBtnWuerfel1().setDisable(true);
		view.getBtnWuerfel2().setDisable(true);
		view.getBtnWuerfel3().setDisable(true);
		view.getBtnWuerfel4().setDisable(true);
		view.getBtnWuerfel5().setDisable(true);
		view.getBtnWuerfel6().setDisable(true);

		view.getBtnWuerfel1().setSelected(d.getWurfelIsAusgewahlt(0));
		view.getBtnWuerfel2().setSelected(d.getWurfelIsAusgewahlt(1));
		view.getBtnWuerfel3().setSelected(d.getWurfelIsAusgewahlt(2));
		view.getBtnWuerfel4().setSelected(d.getWurfelIsAusgewahlt(3));
		view.getBtnWuerfel5().setSelected(d.getWurfelIsAusgewahlt(4));
		view.getBtnWuerfel6().setSelected(d.getWurfelIsAusgewahlt(5));

		view.getBtnWuerfel1().setGraphic(
				new ImageView(view.getWurfelImage(d.getWurfel().getWert(0))));
		view.getBtnWuerfel2().setGraphic(
				new ImageView(view.getWurfelImage(d.getWurfel().getWert(1))));
		view.getBtnWuerfel3().setGraphic(
				new ImageView(view.getWurfelImage(d.getWurfel().getWert(2))));
		view.getBtnWuerfel4().setGraphic(
				new ImageView(view.getWurfelImage(d.getWurfel().getWert(3))));
		view.getBtnWuerfel5().setGraphic(
				new ImageView(view.getWurfelImage(d.getWurfel().getWert(4))));
		view.getBtnWuerfel6().setGraphic(
				new ImageView(view.getWurfelImage(d.getWurfel().getWert(5))));

		// view.getBtnWuerfel2().setText(d.getWurfel().getWert(1) + "");
		// view.getBtnWuerfel3().setText(d.getWurfel().getWert(2) + "");
		// view.getBtnWuerfel4().setText(d.getWurfel().getWert(3) + "");
		// view.getBtnWuerfel5().setText(d.getWurfel().getWert(4) + "");
		// view.getBtnWuerfel6().setText(d.getWurfel().getWert(5) + "");

		if (getClientID() == d.getSpielerAmZug().getSpielerID()) {
			view.getBtnWurfeln().setDisable(false);

			if (d.getWurfel().getwCounter() % 3 == 1
					|| d.getWurfel().getwCounter() % 3 == 2) {
				view.getBtnWuerfel1().setDisable(false);
				view.getBtnWuerfel2().setDisable(false);
				view.getBtnWuerfel3().setDisable(false);
				view.getBtnWuerfel4().setDisable(false);
				view.getBtnWuerfel5().setDisable(false);
				view.getBtnWuerfel6().setDisable(false);

			}

		}

		// Möglichkeiten wenn Tokyo verlassen werden kan:
		if (getClientID() == d.getSpielerAufTokyo().getSpielerID()
				&& !d.isTokyoVerlassen()
				&& d.wurdeIchAngegrifen()
				&& d.getwCounter() % 3 == 0 && d.isSpielerAufTokyoAngegrifen()
				&& getClientID() != d.getSpielerAmZug().getSpielerID()) {
			view.getBtnTokyoVerlassen().setDisable(false);

		}

		else if (d.getSpielerAufTokyo() == null) {
			System.out.println("kein Spieler ist auf Tokyo");
			view.getBtnTokyoVerlassen().setDisable(true);
		}

		// Wenn Spiel fertig ist, alle Buttons ausser Chat deaktivieren
		if (d.isSpielEnde()) {

			view.getBtnTokyoVerlassen().setDisable(true);
			view.getBtnWurfeln().setDisable(true);

			view.getBtnWuerfel1().setDisable(true);
			view.getBtnWuerfel2().setDisable(true);
			view.getBtnWuerfel3().setDisable(true);
			view.getBtnWuerfel4().setDisable(true);
			view.getBtnWuerfel5().setDisable(true);
			view.getBtnWuerfel6().setDisable(true);
			
			
			System.out.println("Spiel fertig");

			// zurück in die Lobby
			// Ende

		}

	}

	public void updateChat(Chat c) {

		view.getTaChat().appendText(
				c.getAbsender() + ": " + c.getChatNachricht() + "\n");

	}

	public void wurfelWurfeln() {

		DatenAustausch d = getDatenAustausch();

		d = ClientSpielLogik.wurfelWurfeln(d);

		if (d.getwCounter() % 3 == 0) {

			view.getBtnWuerfel1().getStyleClass().add("wurfel");// setStyle("wurfel");
			view.getBtnWuerfel1().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			view.getBtnWuerfel2().getStyleClass().add("wurfel");// setStyle("wurfel");
			view.getBtnWuerfel2().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			view.getBtnWuerfel3().getStyleClass().add("wurfel");// setStyle("wurfel");
			view.getBtnWuerfel3().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			view.getBtnWuerfel4().getStyleClass().add("wurfel");// setStyle("wurfel");
			view.getBtnWuerfel4().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			view.getBtnWuerfel5().getStyleClass().add("wurfel");// setStyle("wurfel");
			view.getBtnWuerfel5().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			view.getBtnWuerfel6().getStyleClass().add("wurfel");// setStyle("wurfel");
			view.getBtnWuerfel6().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");

		}

		setDatenAustausch(d);

		d.setTokyoVerlassen(false);
		d.setSpielerAufTokyoAngegrifen(false);

		clientServerVerbindung.sendDatenAustauschToServer(d);

		// updateClientGUI(getDatenAustausch(), getClientID());

	}
	
	public void verbindenMitServer(){
		clientServerVerbindung.connect();

		view.getBtnVerbinden().setDisable(true);

		// view.getLbModeration().setText("warten auf Spiel start");
		view.setModeration("warten auf Spiel start");
		view.getBtnSenden().setDisable(false);
	}

	public void wurfelAuswahl(int w) {

		getDatenAustausch().setWurfelIsAusgewahlt(w);

	}

	public void tokyoVerlasse() {

		getDatenAustausch().tokyoVerlassenById(getClientID());

		getDatenAustausch().setModeration(
				"Spieler " + getClientID()
						+ " hat Tokyo verlassen und Spieler: "
						+ getDatenAustausch().getSpielerAngriffID()
						+ " rückt nach!");

		// Spieler der Angreift rückt nach auf Tokyo
		getDatenAustausch().getSpielerByID(
				getDatenAustausch().getSpielerAngriffID()).setAufTokyo(true);

		System.out.println("Spieler " + getClientID() + "Tokyo verlassen");

		getDatenAustausch().setTokyoVerlassen(true);

		clientServerVerbindung.sendDatenAustauschToServer(getDatenAustausch());

	}

	public int getClientID() {
		return clientServerVerbindung.getClientID();
	}

	class wurfelnEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			System.out.println("würflen");

			wurfelWurfeln();

		}

	}

	class wurfeln1AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (view.getBtnWuerfel1().isSelected()) {
				view.getBtnWuerfel1().getStyleClass().remove("wurfel");// setStyle("wurfel");
				view.getBtnWuerfel1().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				view.getBtnWuerfel1().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				view.getBtnWuerfel1().getStyleClass().add("wurfel");// setStyle("wurfel");
			}

			System.out.println("würflenAuswahl1");

			int wID = 1;

			wurfelAuswahl(wID - 1);

		}
	}

	class wurfeln2AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (view.getBtnWuerfel2().isSelected()) {
				view.getBtnWuerfel2().getStyleClass().remove("wurfel");// setStyle("wurfel");
				view.getBtnWuerfel2().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				view.getBtnWuerfel2().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				view.getBtnWuerfel2().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl2");

			int wID = 2;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln3AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (view.getBtnWuerfel3().isSelected()) {
				view.getBtnWuerfel3().getStyleClass().remove("wurfel");// setStyle("wurfel");
				view.getBtnWuerfel3().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				view.getBtnWuerfel3().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				view.getBtnWuerfel3().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl3");

			int wID = 3;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln4AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (view.getBtnWuerfel4().isSelected()) {
				view.getBtnWuerfel4().getStyleClass().remove("wurfel");// setStyle("wurfel");
				view.getBtnWuerfel4().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				view.getBtnWuerfel4().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				view.getBtnWuerfel4().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl4");

			int wID = 4;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln5AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (view.getBtnWuerfel5().isSelected()) {
				view.getBtnWuerfel5().getStyleClass().remove("wurfel");// setStyle("wurfel");
				view.getBtnWuerfel5().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				view.getBtnWuerfel5().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				view.getBtnWuerfel5().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl5");

			int wID = 5;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln6AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (view.getBtnWuerfel6().isSelected()) {
				view.getBtnWuerfel6().getStyleClass().remove("wurfel");// setStyle("wurfel");
				view.getBtnWuerfel6().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				view.getBtnWuerfel6().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				view.getBtnWuerfel6().getStyleClass().add("wurfel");// setStyle("wurfel");
			}

			System.out.println("würflenAuswahl6");

			int wID = 6;

			wurfelAuswahl(wID - 1);

		}

	}

	class tokyoVerlassenEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			System.out.println("tokyoVerlassen");

			tokyoVerlasse();

		}

	}

	class verbindenEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			System.out.println("verbindenEventHandler");

			verbindenMitServer();

		}

	}

	// Chat
	class nachrichtSendenEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			Chat c = new Chat("", "");

			c.setChatNachricht(view.getTf2Chat().getText());
			c.setAbsender("S " + getClientID());

			clientServerVerbindung.sendChatToServer(c);

			view.getTf2Chat().clear();

		}

	}

	public DatenAustausch getDatenAustausch() {
		return datenAustausch;
	}

	public void setDatenAustausch(DatenAustausch datenAustausch) {
		this.datenAustausch = datenAustausch;
	}

}
