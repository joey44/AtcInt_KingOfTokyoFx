package ch.fhnw.AtcInt.KingOfTokyo.Client;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Chat;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.LobbyDaten;
import ch.fhnw.AtcInt.KingOfTokyo.Login.DBZugriff;
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
 * @author arcsuta (Lobby), joel(Chat, Verbindung, wurfel, GUI Steuerung,
 *         Spieler Bilder zuweisen), renato(Spiellogik, GUI Styling),
 *         barbara(Spiel GUI Actions)
 * 
 * 
 *         Diese Klasse steuert die beiden GUIs Lobby und Client(Spielbrett)
 *
 */

public class ClientController {

	// Variablen deklaration

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

	// Konstruktor
	public ClientController(ClientView clientSpielView, Stage stage, String server, int port, String name) {

		this.l = new LobbyDaten();
		this.isMonsterAusgeahlt = false;

		LobbyView lobbyView = new LobbyView();

		this.clientSpielView = clientSpielView;
		this.stage = stage;
		this.server = server;
		this.port = port;
		this.name = name;

		this.lobbyView = lobbyView;

		clientServerVerbindung = new ClientServerVerbindung(this, clientSpielView, server, port, name, stage);

				
		lobbyView.show(stage);

		lobbyView.getLbTitel().setText("King of Tokyo - Warten bis 4 Spieler angemeldet sind - Hallo " + name + " !");

		try {
			lobbyView.getLbHighScoreWerte().setText(DBZugriff.ListeHighScore());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Lobby GUI default alles inaktiv setzen
		lobbyView.getBtnCyberBunny().setDisable(true);
		lobbyView.getBtnGigaZaur().setDisable(true);
		lobbyView.getBtnMekaDragon().setDisable(true);
		lobbyView.getBtnTheKing().setDisable(true);
		lobbyView.getBtnSpielstarten().setDisable(true);

		// Lobby GUI ActionsHandler zuweisen
		lobbyView.getBtnSpielstarten().setOnAction(new LobbySpielstartenButtonClicked());
		lobbyView.getBtnCyberBunny().setOnAction(new MonsterAuswahlEventHandler0());
		lobbyView.getBtnGigaZaur().setOnAction(new MonsterAuswahlEventHandler1());
		lobbyView.getBtnMekaDragon().setOnAction(new MonsterAuswahlEventHandler2());
		lobbyView.getBtnTheKing().setOnAction(new MonsterAuswahlEventHandler3());
		
		lobbyView.getBtnRegeln().setOnAction(new RegelnEventHandler());
		

		// Actions wenn GUI geschlossen wird
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {

				if (getDatenAustausch() != null) {

					getDatenAustausch().setSpielEnde(true);
					getDatenAustausch()
							.setModeration("Spieler " + name + " hat das Spiel verlassen" + "\n Spiel wird beendet");

					// Spiel wird beendet, funktioniert nur mit 4 Spieler

					clientServerVerbindung.sendDatenAustauschToServer(getDatenAustausch());

					

				} else {
					stage.close();
					System.exit(0);
				}

				// clientServerVerbindung.disconnect();


			}
		});

		
	
		// Maus Events f�r Regel Button
				clientSpielView.getBtnRegeln().setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent t) {
						clientSpielView.getBtnRegeln().getStyleClass().remove("custom-button");
						clientSpielView.getBtnRegeln().getStyleClass().add("custom-button-enter");
					}
				});

				clientSpielView.getBtnRegeln().setOnMouseExited(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						clientSpielView.getBtnRegeln().getStyleClass().remove("custom-button-enter");
						clientSpielView.getBtnRegeln().getStyleClass().add("custom-button");
					}
				});

				clientSpielView.getBtnRegeln().setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						clientSpielView.getBtnRegeln().getStyleClass().remove("custom-button-enter");
						clientSpielView.getBtnRegeln().getStyleClass().add("custom-button-pressed");
					}
				});

				clientSpielView.getBtnRegeln().setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						clientSpielView.getBtnRegeln().getStyleClass().remove("custom-button-pressed");
						clientSpielView.getBtnRegeln().getStyleClass().add("custom-button-enter");
					}
				});
		
		
		
		// Maus Events f�r Buttons
		clientSpielView.getBtnWurfeln().setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnWurfeln().getStyleClass().remove("custom-button");
				clientSpielView.getBtnWurfeln().getStyleClass().add("custom-button-enter");
			}
		});

		clientSpielView.getBtnWurfeln().setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnWurfeln().getStyleClass().remove("custom-button-enter");
				clientSpielView.getBtnWurfeln().getStyleClass().add("custom-button");
			}
		});

		clientSpielView.getBtnWurfeln().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnWurfeln().getStyleClass().remove("custom-button-enter");
				clientSpielView.getBtnWurfeln().getStyleClass().add("custom-button-pressed");
			}
		});

		clientSpielView.getBtnWurfeln().setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnWurfeln().getStyleClass().remove("custom-button-pressed");
				clientSpielView.getBtnWurfeln().getStyleClass().add("custom-button-enter");
			}
		});
		// Maus Events Button Tokyo verlassen

		clientSpielView.getBtnTokyoVerlassen().setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().remove("custom-button");
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().add("custom-button-enter");
			}
		});

		clientSpielView.getBtnTokyoVerlassen().setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().remove("custom-button-enter");
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().add("custom-button");
			}
		});

		clientSpielView.getBtnTokyoVerlassen().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().remove("custom-button-enter");
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().add("custom-button-pressed");
			}
		});

		clientSpielView.getBtnTokyoVerlassen().setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().remove("custom-button-pressed");
				clientSpielView.getBtnTokyoVerlassen().getStyleClass().add("custom-button-enter");
			}
		});

		// Maus events button chat
		clientSpielView.getBtnSenden().setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnSenden().getStyleClass().remove("custom-button");
				clientSpielView.getBtnSenden().getStyleClass().add("custom-button-enter");
			}
		});

		clientSpielView.getBtnSenden().setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnSenden().getStyleClass().remove("custom-button-enter");
				clientSpielView.getBtnSenden().getStyleClass().add("custom-button");
			}
		});

		clientSpielView.getBtnSenden().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnSenden().getStyleClass().remove("custom-button-enter");
				clientSpielView.getBtnSenden().getStyleClass().add("custom-button-pressed");
			}
		});

		clientSpielView.getBtnSenden().setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				clientSpielView.getBtnSenden().getStyleClass().remove("custom-button-pressed");
				clientSpielView.getBtnSenden().getStyleClass().add("custom-button-enter");
			}
		});

		clientSpielView.getTf2Chat().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					nachrichtSenden();
				}

			}

		});

		// Client (Spielbrett) GUI ActionsHandler zuweisen
		clientSpielView.getBtnWurfeln().setOnAction(new wurfelnEventHandler());
		clientSpielView.getBtnWuerfel1().setOnAction(new wurfeln1AuswahlEventHandler());
		clientSpielView.getBtnWuerfel2().setOnAction(new wurfeln2AuswahlEventHandler());
		clientSpielView.getBtnWuerfel3().setOnAction(new wurfeln3AuswahlEventHandler());
		clientSpielView.getBtnWuerfel4().setOnAction(new wurfeln4AuswahlEventHandler());
		clientSpielView.getBtnWuerfel5().setOnAction(new wurfeln5AuswahlEventHandler());
		clientSpielView.getBtnWuerfel6().setOnAction(new wurfeln6AuswahlEventHandler());

		clientSpielView.getBtnSenden().setOnAction(new nachrichtSendenEventHandler());

		clientSpielView.getBtnRegeln().setOnAction(new RegelnEventHandler());
		
		
		clientSpielView.getBtnTokyoVerlassen().setOnAction(new tokyoVerlassenEventHandler());
		clientSpielView.getBtnVerbinden().setOnAction(new verbindenEventHandler());

		
		// Default alle Buttons inaktiv setzen
		clientSpielView.getBtnWurfeln().setDisable(true);

		clientSpielView.getBtnWuerfel1().setDisable(true);
		clientSpielView.getBtnWuerfel2().setDisable(true);
		clientSpielView.getBtnWuerfel3().setDisable(true);
		clientSpielView.getBtnWuerfel4().setDisable(true);
		clientSpielView.getBtnWuerfel5().setDisable(true);
		clientSpielView.getBtnWuerfel6().setDisable(true);

		clientSpielView.getBtnTokyoVerlassen().setDisable(true);

		clientSpielView.getBtnVerbinden().setDefaultButton(true);

		clientSpielView.getBtnSenden().setDisable(true);

		verbindenMitServer();

		// Name Server mitteilen
		clientServerVerbindung.sendStringToServer(name);
				

	}

	
	public void objectFromServerSetDatenaustausch(DatenAustausch d) {

		this.datenAustausch = d;

	}

	public void lobbyMonsterWahl() {

		lobbyView.getLbTitel().setText("King of Tokyo - Monster ausw�hlen - Hallo " + this.name + " !");

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

			// richtiges Monster wird richtigem Spieler zugewiesen

			for (int i = 0; i < 4; i++) {
				clientSpielView.setImageSpieler(i, getL().getMonsterList(i));
			}

			clientSpielView.getVbSpieler0().getChildren().addAll(clientSpielView.getLbSpieler0(),
					clientSpielView.getImageSpieler(0), clientSpielView.getHb5());
			clientSpielView.getVbSpieler1().getChildren().addAll(clientSpielView.getLbSpieler1(),
					clientSpielView.getImageSpieler(1), clientSpielView.getHb6());
			clientSpielView.getVbSpieler2().getChildren().addAll(clientSpielView.getLbSpieler2(),
					clientSpielView.getImageSpieler(2), clientSpielView.getHb7());
			clientSpielView.getVbSpieler3().getChildren().addAll(clientSpielView.getLbSpieler3(),
					clientSpielView.getImageSpieler(3), clientSpielView.getHb8());

			clientSpielView.show(stage);

		}

		//Lobby Objekt setzen
		setL(l);

		//Wenn alle Monster gew�hlt sind, kann ein Spieler das Spiel starten
		if (l.getMonsterWahlCounter() == 4 && getDatenAustausch().isSpielStart()) {

			lobbyView.getLbTitel().setText("King of Tokyo - Spiel starten - Hallo " + this.name + " !");

			lobbyView.getBtnSpielstarten().setDisable(false);

		}

	
		//alle Monster sind deaktiviert, wenn du bereits ein Monster gew�hlt hast
		if (clientID == l.getSpielerID() && !isMonsterAusgeahlt) {
			lobbyView.getBtnCyberBunny().setDisable(true);
			lobbyView.getBtnGigaZaur().setDisable(true);
			lobbyView.getBtnMekaDragon().setDisable(true);
			lobbyView.getBtnTheKing().setDisable(true);

			isMonsterAusgeahlt = true;
		}

		//MOnster wird inaktiv gesetzt, welches die anderen Spieler gew�hlt haben
		if (l.getMonsterID() == 0) {
			lobbyView.getBtnCyberBunny().setDisable(true);
		} else if (l.getMonsterID() == 1) {
			lobbyView.getBtnGigaZaur().setDisable(true);
		} else if (l.getMonsterID() == 2) {
			lobbyView.getBtnMekaDragon().setDisable(true);
		} else if (l.getMonsterID() == 3) {
			lobbyView.getBtnTheKing().setDisable(true);
		}

		//Lobby Moderation setzten
		lobbyView.getLbMonsterAuswahl().setText(l.getLobbyModeration());

	}

	public void updateClientGUI(DatenAustausch d, int clientID) {

		// Wenn alle 4 Spieler angemldet sind, k�nnen die Monster ausgew�hlt
		// werden
		lobbyMonsterWahl();

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

		//Variablen aus DatenAustausch auf GUI anzeigen
		clientSpielView.setModeration(ClientSpielLogik.spielModerieren(d));
		clientSpielView.getLbTokyo().setText(ClientSpielLogik.standortAnzeigen(d));

		clientSpielView.getBtnWurfeln().setDisable(true);
		clientSpielView.getBtnTokyoVerlassen().setDisable(true);

		//W�rfel inaktiv setzen
		clientSpielView.getBtnWuerfel1().setDisable(true);
		clientSpielView.getBtnWuerfel2().setDisable(true);
		clientSpielView.getBtnWuerfel3().setDisable(true);
		clientSpielView.getBtnWuerfel4().setDisable(true);
		clientSpielView.getBtnWuerfel5().setDisable(true);
		clientSpielView.getBtnWuerfel6().setDisable(true);

		//anzeigen, ob wurfel ausgew�hlt ist
		clientSpielView.getBtnWuerfel1().setSelected(d.getWurfelIsAusgewahlt(0));
		clientSpielView.getBtnWuerfel2().setSelected(d.getWurfelIsAusgewahlt(1));
		clientSpielView.getBtnWuerfel3().setSelected(d.getWurfelIsAusgewahlt(2));
		clientSpielView.getBtnWuerfel4().setSelected(d.getWurfelIsAusgewahlt(3));
		clientSpielView.getBtnWuerfel5().setSelected(d.getWurfelIsAusgewahlt(4));
		clientSpielView.getBtnWuerfel6().setSelected(d.getWurfelIsAusgewahlt(5));

		
		//gew�rfelte Werte anzeigen
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


		//wurfeln zulassen, bei dem Spieler der am Zug ist
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

		// M�glichkeiten wenn Tokyo verlassen werden kan:
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
			alert.setContentText("King of Tokyo wird beendet \n" + getDatenAustausch().getModeration());

			alert.showAndWait();

			// Stage stage = (Stage) clientSpielView.getScene().getWindow();

			stage.close();

			System.exit(0);

			// Ende

		}

	}

	//Chat Nachricht auf GUI anzeigen
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

		

	}

	public void verbindenMitServer() {
		clientServerVerbindung.connect();

		clientSpielView.getBtnVerbinden().setDisable(true);

		
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
								+ " r�ckt nach!");

		// Spieler der Angreift r�ckt nach auf Tokyo
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

			System.out.println("w�rflen");

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

			System.out.println("w�rflenAuswahl1");

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
			System.out.println("w�rflenAuswahl2");

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
			System.out.println("w�rflenAuswahl3");

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
			System.out.println("w�rflenAuswahl4");

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
			System.out.println("w�rflenAuswahl5");

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

			System.out.println("w�rflenAuswahl6");

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
	public void nachrichtSenden() {

		if (!clientSpielView.getTf2Chat().getText().isEmpty()) {

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

			getL().setMonsterList(getClientID(), 0);

			getL().setLobbyModeration(name + " hat Cyber Bunny augew�hlt");

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

			getL().setLobbyModeration(name + " hat Giga Zaur augew�hlt");
			getL().setMonsterList(getClientID(), 1);

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

			getL().setLobbyModeration(name + " hat Meka Dragon augew�hlt");

			getL().setMonsterList(getClientID(), 2);

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

			getL().setLobbyModeration(name + " hat The King augew�hlt");

			getL().setMonsterList(getClientID(), 3);

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
	
	
	class RegelnEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.out.println("Regeln");

			

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
