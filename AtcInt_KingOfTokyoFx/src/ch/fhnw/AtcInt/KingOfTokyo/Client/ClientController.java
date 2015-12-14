package ch.fhnw.AtcInt.KingOfTokyo.Client;

import java.security.Key;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Chat;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.LobbyDaten;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * @author arcsuta (Lobby), joel(Verbindung), renato(Spiellogik), barbara(Spiel GUI Contoller)
 *
 */


public class ClientController {

	private ClientView clientSpielView;
	private ClientServerVerbindung clientServerVerbindung;

	private DatenAustausch datenAustausch;
	private LobbyDaten l;

	private LobbyView lobbyView;

	private int clientID;
	private Stage stage;

	private String server;
	private int port;
	private String name;

	private boolean isMonsterAusgeahlt;

	public ClientController(ClientView view, Stage stage, String server, int port, String name) {

		this.l = new LobbyDaten();
		this.isMonsterAusgeahlt = false;

		LobbyView lobbyView = new LobbyView();

		this.clientSpielView = view;
		this.stage = stage;
		this.server = server;
		this.port = port;
		this.name = name;

		this.lobbyView = lobbyView;

		lobbyView.show(stage);

		lobbyView.getLbTitel().setText("King of Tokyo - Warten bis 4 Spieler angemeldet sind");

		lobbyView.getBtnCyberBunny().setDisable(true);
		lobbyView.getBtnGigaZaur().setDisable(true);
		lobbyView.getBtnMekaDragon().setDisable(true);
		lobbyView.getBtnTheKing().setDisable(true);
		lobbyView.getBtnSpielstarten().setDisable(true);

		lobbyView.getBtnSpielstarten().setOnAction(new LobbySpielstartenButtonClicked());
		lobbyView.getBtnCyberBunny().setOnAction(new MonsterAuswahlEventHandler0());
		lobbyView.getBtnGigaZaur().setOnAction(new MonsterAuswahlEventHandler1());
		lobbyView.getBtnMekaDragon().setOnAction(new MonsterAuswahlEventHandler2());
		lobbyView.getBtnTheKing().setOnAction(new MonsterAuswahlEventHandler3());

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				clientServerVerbindung.disconnect();

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Spiel wird beendet");
				alert.setHeaderText("Spiel wird beendet");
				alert.setContentText("Spieler " + name + " wird inaktiv gesetzt");

				// Spieler inaktiv setzten

				alert.showAndWait();

			}
		});

		clientServerVerbindung = new ClientServerVerbindung(this, view, server, port, name);

		// Maus Events für Buttons
		view.getBtnWurfeln().setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				view.getBtnWurfeln().getStyleClass().remove("custom-button");
				view.getBtnWurfeln().getStyleClass().add("custom-button-enter");
			}
		});

		view.getBtnWurfeln().setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnWurfeln().getStyleClass().remove("custom-button-enter");
				view.getBtnWurfeln().getStyleClass().add("custom-button");
			}
		});

		view.getBtnWurfeln().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnWurfeln().getStyleClass().remove("custom-button-enter");
				view.getBtnWurfeln().getStyleClass().add("custom-button-pressed");
			}
		});

		view.getBtnWurfeln().setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnWurfeln().getStyleClass().remove("custom-button-pressed");
				view.getBtnWurfeln().getStyleClass().add("custom-button-enter");
			}
		});
		// Maus Events Button Tokyo verlassen

		view.getBtnTokyoVerlassen().setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnTokyoVerlassen().getStyleClass().remove("custom-button");
				view.getBtnTokyoVerlassen().getStyleClass().add("custom-button-enter");
			}
		});

		view.getBtnTokyoVerlassen().setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnTokyoVerlassen().getStyleClass().remove("custom-button-enter");
				view.getBtnTokyoVerlassen().getStyleClass().add("custom-button");
			}
		});

		view.getBtnTokyoVerlassen().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnTokyoVerlassen().getStyleClass().remove("custom-button-enter");
				view.getBtnTokyoVerlassen().getStyleClass().add("custom-button-pressed");
			}
		});

		view.getBtnTokyoVerlassen().setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnTokyoVerlassen().getStyleClass().remove("custom-button-pressed");
				view.getBtnTokyoVerlassen().getStyleClass().add("custom-button-enter");
			}
		});

		// Maus events button chat
		view.getBtnSenden().setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnSenden().getStyleClass().remove("custom-button");
				view.getBtnSenden().getStyleClass().add("custom-button-enter");
			}
		});

		view.getBtnSenden().setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnSenden().getStyleClass().remove("custom-button-enter");
				view.getBtnSenden().getStyleClass().add("custom-button");
			}
		});

		view.getBtnSenden().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnSenden().getStyleClass().remove("custom-button-enter");
				view.getBtnSenden().getStyleClass().add("custom-button-pressed");
			}
		});

		view.getBtnSenden().setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				view.getBtnSenden().getStyleClass().remove("custom-button-pressed");
				view.getBtnSenden().getStyleClass().add("custom-button-enter");
			}
		});

		view.getTf2Chat().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					nachrichtSenden();
				}

			}

		});

		view.getBtnWurfeln().setOnAction(new wurfelnEventHandler());
		view.getBtnWuerfel1().setOnAction(new wurfeln1AuswahlEventHandler());
		view.getBtnWuerfel2().setOnAction(new wurfeln2AuswahlEventHandler());
		view.getBtnWuerfel3().setOnAction(new wurfeln3AuswahlEventHandler());
		view.getBtnWuerfel4().setOnAction(new wurfeln4AuswahlEventHandler());
		view.getBtnWuerfel5().setOnAction(new wurfeln5AuswahlEventHandler());
		view.getBtnWuerfel6().setOnAction(new wurfeln6AuswahlEventHandler());

		view.getBtnSenden().setOnAction(new nachrichtSendenEventHandler());

		view.getBtnTokyoVerlassen().setOnAction(new tokyoVerlassenEventHandler());
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

		// Name Server mitteilen
		clientServerVerbindung.sendStringToServer(name);

	}

	public void objectFromServerSetDatenaustausch(DatenAustausch d) {

		this.datenAustausch = d;

	}

	public void lobbyMonsterWahl() {

		lobbyView.getLbTitel().setText("King of Tokyo - Monster auswählen");

		lobbyView.getBtnCyberBunny().setDisable(false);
		lobbyView.getBtnGigaZaur().setDisable(false);
		lobbyView.getBtnMekaDragon().setDisable(false);
		lobbyView.getBtnTheKing().setDisable(false);

		lobbyView.getLbSpielerName1().setText(getDatenAustausch().getSpielerByID(0).getSpielerName());
		lobbyView.getLbSpielerName2().setText(getDatenAustausch().getSpielerByID(1).getSpielerName());
		lobbyView.getLbSpielerName3().setText(getDatenAustausch().getSpielerByID(2).getSpielerName());
		lobbyView.getLbSpielerName4().setText(getDatenAustausch().getSpielerByID(3).getSpielerName());

	}

	public void updateLobbyGUI(LobbyDaten l) {

		if (l.isSpielStart()) {

			clientSpielView.show(stage);

		}

		setL(l);

		if (l.getMonsterWahlCounter() == 4 && getDatenAustausch().isSpielStart()) {

			lobbyView.getBtnSpielstarten().setDisable(false);

		}

		// System.out.println(clientID);
		// System.out.println(l.getSpielerID());

		if (clientID == l.getSpielerID() && !isMonsterAusgeahlt) {
			lobbyView.getBtnCyberBunny().setDisable(true);
			lobbyView.getBtnGigaZaur().setDisable(true);
			lobbyView.getBtnMekaDragon().setDisable(true);
			lobbyView.getBtnTheKing().setDisable(true);

			isMonsterAusgeahlt = true;
		}

		if (l.getMonsterID() == 0) {
			lobbyView.getBtnCyberBunny().setDisable(true);
		} else if (l.getMonsterID() == 1) {
			lobbyView.getBtnGigaZaur().setDisable(true);
		} else if (l.getMonsterID() == 2) {
			lobbyView.getBtnMekaDragon().setDisable(true);
		} else if (l.getMonsterID() == 3) {
			lobbyView.getBtnTheKing().setDisable(true);
		}

		lobbyView.getTaMonsterAuswahl().appendText("\n" + l.getLobbyModeration());

	}

	public void updateClientGUI(DatenAustausch d, int clientID) {

		// Wenn alle 4 Spieler angemldet sind, können die Monster ausgewählt
		// werden
		lobbyMonsterWahl();

		// DatenAustausch d = this.datenAustausch;

		// view.getLbModeration().setText("gewürfelt Client:" + clientID);
		// view.setModeration("gewürfelt Client:" + clientID);

		// Farben setzten inaktiv/tot, am Zug, warten auf Zug

		clientSpielView.getLbSpieler0().setText(ClientSpielLogik.spielerName(d, 0));
		clientSpielView.getLbLeben0().setText(ClientSpielLogik.lebenAnzeigen(d, 0));
		clientSpielView.getLbPunkte0().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 0));

		clientSpielView.setSpielerStandard(0);
		clientSpielView.setSpielerStandard(1);
		clientSpielView.setSpielerStandard(2);
		clientSpielView.setSpielerStandard(3);

		if (!(d.getSpielerByID(0).isSpielerAktiv())) {
			clientSpielView.setSpielerInaktiv(0);
		}

		else if (d.getSpielerByID(0).isAmZug()) {
			clientSpielView.setSpielerAmZug(0);
		}

		// Farben setzten inaktiv/tot, am Zug, warten auf Zug
		clientSpielView.getLbSpieler1().setText(ClientSpielLogik.spielerName(d, 1));
		clientSpielView.getLbLeben1().setText(ClientSpielLogik.lebenAnzeigen(d, 1));
		clientSpielView.getLbPunkte1().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 1));

		if (!(d.getSpielerByID(1).isSpielerAktiv())) {
			clientSpielView.setSpielerInaktiv(1);
		} else if (d.getSpielerByID(1).isAmZug()) {
			clientSpielView.setSpielerAmZug(1);
		}

		// Farben setzten inaktiv/tot, am Zug, warten auf Zug
		clientSpielView.getLbSpieler2().setText(ClientSpielLogik.spielerName(d, 2));
		clientSpielView.getLbLeben2().setText(ClientSpielLogik.lebenAnzeigen(d, 2));
		clientSpielView.getLbPunkte2().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 2));

		if (!(d.getSpielerByID(2).isSpielerAktiv())) {
			clientSpielView.setSpielerInaktiv(2);
		} else if (d.getSpielerByID(2).isAmZug()) {
			clientSpielView.setSpielerAmZug(2);
		}

		// Farben setzten inaktiv/tot, am Zug, warten auf Zug
		clientSpielView.getLbSpieler3().setText(ClientSpielLogik.spielerName(d, 3));
		clientSpielView.getLbLeben3().setText(ClientSpielLogik.lebenAnzeigen(d, 3));
		clientSpielView.getLbPunkte3().setText(ClientSpielLogik.ruhmpunkteAnzeigen(d, 3));

		if (!(d.getSpielerByID(3).isSpielerAktiv())) {
			clientSpielView.setSpielerInaktiv(3);
		} else if (d.getSpielerByID(3).isAmZug()) {
			clientSpielView.setSpielerAmZug(3);
		}

		// view.getLbModeration().setText(ClientSpielLogik.spielModerieren(d));
		clientSpielView.setModeration(ClientSpielLogik.spielModerieren(d));
		clientSpielView.getLbTokyo().setText(ClientSpielLogik.standortAnzeigen(d));

		clientSpielView.getBtnWurfeln().setDisable(true);
		clientSpielView.getBtnTokyoVerlassen().setDisable(true);

		clientSpielView.getBtnWuerfel1().setDisable(true);
		clientSpielView.getBtnWuerfel2().setDisable(true);
		clientSpielView.getBtnWuerfel3().setDisable(true);
		clientSpielView.getBtnWuerfel4().setDisable(true);
		clientSpielView.getBtnWuerfel5().setDisable(true);
		clientSpielView.getBtnWuerfel6().setDisable(true);

		clientSpielView.getBtnWuerfel1().setSelected(d.getWurfelIsAusgewahlt(0));
		clientSpielView.getBtnWuerfel2().setSelected(d.getWurfelIsAusgewahlt(1));
		clientSpielView.getBtnWuerfel3().setSelected(d.getWurfelIsAusgewahlt(2));
		clientSpielView.getBtnWuerfel4().setSelected(d.getWurfelIsAusgewahlt(3));
		clientSpielView.getBtnWuerfel5().setSelected(d.getWurfelIsAusgewahlt(4));
		clientSpielView.getBtnWuerfel6().setSelected(d.getWurfelIsAusgewahlt(5));

		clientSpielView.getBtnWuerfel1()
				.setGraphic(new ImageView(clientSpielView.getWurfelImage(d.getWurfel().getWert(0))));
		clientSpielView.getBtnWuerfel2()
				.setGraphic(new ImageView(clientSpielView.getWurfelImage(d.getWurfel().getWert(1))));
		clientSpielView.getBtnWuerfel3()
				.setGraphic(new ImageView(clientSpielView.getWurfelImage(d.getWurfel().getWert(2))));
		clientSpielView.getBtnWuerfel4()
				.setGraphic(new ImageView(clientSpielView.getWurfelImage(d.getWurfel().getWert(3))));
		clientSpielView.getBtnWuerfel5()
				.setGraphic(new ImageView(clientSpielView.getWurfelImage(d.getWurfel().getWert(4))));
		clientSpielView.getBtnWuerfel6()
				.setGraphic(new ImageView(clientSpielView.getWurfelImage(d.getWurfel().getWert(5))));

		// view.getBtnWuerfel2().setText(d.getWurfel().getWert(1) + "");
		// view.getBtnWuerfel3().setText(d.getWurfel().getWert(2) + "");
		// view.getBtnWuerfel4().setText(d.getWurfel().getWert(3) + "");
		// view.getBtnWuerfel5().setText(d.getWurfel().getWert(4) + "");
		// view.getBtnWuerfel6().setText(d.getWurfel().getWert(5) + "");

		if (getClientID() == d.getSpielerAmZug().getSpielerID()) {
			clientSpielView.getBtnWurfeln().setDisable(false);

			if (d.getWurfel().getwCounter() % 3 == 1 || d.getWurfel().getwCounter() % 3 == 2) {
				clientSpielView.getBtnWuerfel1().setDisable(false);
				clientSpielView.getBtnWuerfel2().setDisable(false);
				clientSpielView.getBtnWuerfel3().setDisable(false);
				clientSpielView.getBtnWuerfel4().setDisable(false);
				clientSpielView.getBtnWuerfel5().setDisable(false);
				clientSpielView.getBtnWuerfel6().setDisable(false);

			}

		}

		// Möglichkeiten wenn Tokyo verlassen werden kan:
		if (d.getSpielerAufTokyo() != null && getClientID() == d.getSpielerAufTokyo().getSpielerID()
				&& !d.isTokyoVerlassen() && d.wurdeIchAngegrifen() && d.getwCounter() % 3 == 0
				&& d.isSpielerAufTokyoAngegrifen() && !d.isSpielerAufTokyoGestroben()) {

			clientSpielView.getBtnTokyoVerlassen().setDisable(false);

		}

		else if (d.getSpielerAufTokyo() == null) {
			System.out.println("kein Spieler ist auf Tokyo");
			clientSpielView.getBtnTokyoVerlassen().setDisable(true);
		}

		// Wenn Spiel fertig ist, alle Buttons ausser Chat deaktivieren
		if (d.isSpielEnde()) {

			clientSpielView.getBtnTokyoVerlassen().setDisable(true);
			clientSpielView.getBtnWurfeln().setDisable(true);

			clientSpielView.getBtnWuerfel1().setDisable(true);
			clientSpielView.getBtnWuerfel2().setDisable(true);
			clientSpielView.getBtnWuerfel3().setDisable(true);
			clientSpielView.getBtnWuerfel4().setDisable(true);
			clientSpielView.getBtnWuerfel5().setDisable(true);
			clientSpielView.getBtnWuerfel6().setDisable(true);

			System.out.println("Spiel fertig");

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Spiel fertig");
			alert.setHeaderText(null);
			alert.setContentText("King of Tokyo wird beendet");

			alert.showAndWait();

			Stage stage = (Stage) clientSpielView.getScene().getWindow();

			stage.close();

			System.exit(0);

			// Ende

		}

	}

	public void updateChat(Chat c) {

		clientSpielView.getTaChat().appendText(c.getAbsender() + ": " + c.getChatNachricht() + "\n");

	}

	public void wurfelWurfeln() {

		DatenAustausch d = getDatenAustausch();

		d = ClientSpielLogik.wurfelWurfeln(d);

		if (d.getwCounter() % 3 == 0) {

			clientSpielView.getBtnWuerfel1().getStyleClass().add("wurfel");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel1().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel2().getStyleClass().add("wurfel");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel2().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel3().getStyleClass().add("wurfel");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel3().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel4().getStyleClass().add("wurfel");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel4().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel5().getStyleClass().add("wurfel");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel5().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel6().getStyleClass().add("wurfel");// setStyle("wurfel");
			clientSpielView.getBtnWuerfel6().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");

		}

		setDatenAustausch(d);

		d.setTokyoVerlassen(false);
		d.setSpielerAufTokyoAngegrifen(false);
		d.setSpielerAufTokyoGestroben(false);

		clientServerVerbindung.sendDatenAustauschToServer(d);

		// updateClientGUI(getDatenAustausch(), getClientID());

	}

	public void verbindenMitServer() {
		clientServerVerbindung.connect();

		clientSpielView.getBtnVerbinden().setDisable(true);

		// view.getLbModeration().setText("warten auf Spiel start");
		clientSpielView.setModeration("warten auf Spiel start");
		clientSpielView.getBtnSenden().setDisable(false);
	}

	public void wurfelAuswahl(int w) {

		getDatenAustausch().setWurfelIsAusgewahlt(w);

	}

	public void tokyoVerlasse() {

		getDatenAustausch().tokyoVerlassenById(getClientID());

		getDatenAustausch()
				.setModeration(
						getDatenAustausch().getSpielerByID(getClientID()).getSpielerName()
								+ " hat Tokyo verlassen und Spieler: " + getDatenAustausch()
										.getSpielerByID(getDatenAustausch().getSpielerAngriffID()).getSpielerName()
								+ " rückt nach!");

		// Spieler der Angreift rückt nach auf Tokyo
		getDatenAustausch().getSpielerByID(getDatenAustausch().getSpielerAngriffID()).setAufTokyo(true);

		System.out.println(getDatenAustausch().getSpielerByID(getClientID()).getSpielerName() + "Tokyo verlassen");

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

			if (clientSpielView.getBtnWuerfel1().isSelected()) {
				clientSpielView.getBtnWuerfel1().getStyleClass().remove("wurfel");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel1().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				clientSpielView.getBtnWuerfel1().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel1().getStyleClass().add("wurfel");// setStyle("wurfel");
			}

			System.out.println("würflenAuswahl1");

			int wID = 1;

			wurfelAuswahl(wID - 1);

		}
	}

	class wurfeln2AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (clientSpielView.getBtnWuerfel2().isSelected()) {
				clientSpielView.getBtnWuerfel2().getStyleClass().remove("wurfel");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel2().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				clientSpielView.getBtnWuerfel2().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel2().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl2");

			int wID = 2;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln3AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (clientSpielView.getBtnWuerfel3().isSelected()) {
				clientSpielView.getBtnWuerfel3().getStyleClass().remove("wurfel");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel3().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				clientSpielView.getBtnWuerfel3().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel3().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl3");

			int wID = 3;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln4AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (clientSpielView.getBtnWuerfel4().isSelected()) {
				clientSpielView.getBtnWuerfel4().getStyleClass().remove("wurfel");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel4().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				clientSpielView.getBtnWuerfel4().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel4().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl4");

			int wID = 4;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln5AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (clientSpielView.getBtnWuerfel5().isSelected()) {
				clientSpielView.getBtnWuerfel5().getStyleClass().remove("wurfel");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel5().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				clientSpielView.getBtnWuerfel5().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel5().getStyleClass().add("wurfel");// setStyle("wurfel");
			}
			System.out.println("würflenAuswahl5");

			int wID = 5;

			wurfelAuswahl(wID - 1);

		}

	}

	class wurfeln6AuswahlEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (clientSpielView.getBtnWuerfel6().isSelected()) {
				clientSpielView.getBtnWuerfel6().getStyleClass().remove("wurfel");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel6().getStyleClass().add("wurfelFocused");// setStyle("wurfel");
			} else {
				clientSpielView.getBtnWuerfel6().getStyleClass().remove("wurfelFocused");// setStyle("wurfel");
				clientSpielView.getBtnWuerfel6().getStyleClass().add("wurfel");// setStyle("wurfel");
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

	public void nachrichtSenden() {

		if (clientSpielView.getTf2Chat().getText() != "") {

			Chat c = new Chat("", "");

			c.setChatNachricht(clientSpielView.getTf2Chat().getText());
			c.setAbsender(name);

			clientServerVerbindung.sendChatToServer(c);

			clientSpielView.getTf2Chat().clear();
		}
	}

	// Chat
	class nachrichtSendenEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			nachrichtSenden();

		}

	}

	public DatenAustausch getDatenAustausch() {
		return datenAustausch;
	}

	public void setDatenAustausch(DatenAustausch datenAustausch) {
		this.datenAustausch = datenAustausch;
	}

	// MonsterAuswahl Methode 0
	class MonsterAuswahlEventHandler0 implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent ae) {

			System.out.println("Monster 0");

			getL().setMonsterWahlCounter(l.getMonsterWahlCounter() + 1);
			getL().setMonsterID(0);
			getL().setSpielerID(getClientID());

			getL().setLobbyModeration(name + " hat Cyber Bunny augewählt");

			clientServerVerbindung.sendLobbyDatenToServer(getL());

		}

	}

	// MonsterAuswahl Methode 1
	class MonsterAuswahlEventHandler1 implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent ae) {

			System.out.println("Monster 1");

			getL().setMonsterWahlCounter(l.getMonsterWahlCounter() + 1);
			getL().setMonsterID(1);
			getL().setSpielerID(getClientID());

			getL().setLobbyModeration(name + " hat Giga Zaur augewählt");

			clientServerVerbindung.sendLobbyDatenToServer(getL());

		}

	}

	// MonsterAuswahl Methode 2
	class MonsterAuswahlEventHandler2 implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent ae) {

			System.out.println("Monster 2");

			getL().setMonsterWahlCounter(l.getMonsterWahlCounter() + 1);
			getL().setMonsterID(2);
			getL().setSpielerID(getClientID());

			getL().setLobbyModeration(name + " hat Meka Dragon augewählt");

			clientServerVerbindung.sendLobbyDatenToServer(getL());

		}

	}

	// MonsterAuswahl Methode 3
	class MonsterAuswahlEventHandler3 implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent ae) {

			System.out.println("Monster 3 ");

			getL().setMonsterWahlCounter(l.getMonsterWahlCounter() + 1);
			getL().setMonsterID(3);
			getL().setSpielerID(getClientID());

			getL().setLobbyModeration(name + " hat The King augewählt");

			clientServerVerbindung.sendLobbyDatenToServer(getL());

		}

	}

	class LobbySpielstartenButtonClicked implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.out.println("Spiel wird gestartet...");

			getL().setSpielStart(true);

			clientServerVerbindung.sendLobbyDatenToServer(getL());

			// clientSpielView.show(stage);

		}
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public LobbyDaten getL() {
		return l;
	}

	public void setL(LobbyDaten l) {
		this.l = l;
	}

}
