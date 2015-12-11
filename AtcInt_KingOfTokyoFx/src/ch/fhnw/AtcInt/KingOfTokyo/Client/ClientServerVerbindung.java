package ch.fhnw.AtcInt.KingOfTokyo.Client;

import java.io.*;
import java.net.Socket;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Chat;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;
import javafx.application.Platform;

public class ClientServerVerbindung extends Thread {

	private Socket clientSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private ClientController controller;
	private ClientSpielLogik clientSpielLogik;
	private ClientView cview;
	private Chat c;

	private DatenAustausch datenAustausch;

	private volatile boolean stopThread = false;

	private int clientID;

	public ClientServerVerbindung(ClientController controller, ClientView cview) {

		this.cview = cview;
		this.controller = controller;

		// this.datenAustausch = DatenAustausch.getInstanz();

	}

	public ClientServerVerbindung getClientServerVerbindung() {
		return this;
	}

	public void connect() {
		try {
			this.clientSocket = new Socket("localhost", 44444);
			this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
			this.ois = new ObjectInputStream(clientSocket.getInputStream());

			this.clientID = 9;
			
			

			this.start();
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (clientSocket != null) {
				clientSocket.close();
				stopThread = true;
				this.interrupt();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDatenAustauschToServer(DatenAustausch d) {

		try {

			d.setClientID(getClientID());
			oos.writeObject(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendChatToServer(Chat c) {

		try {

			oos.writeObject(c);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void run() {
		listen();

	}

	public void listen() {

		try {
			this.clientID = (int) ois.readObject();

			
			// UI updaten
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// entsprechende UI Komponente updaten
					cview.getLbTitel().setText("King of Tokyo - Spieler Nr: " + getClientID());
					// cview.getLbModeration().setText(
					// "client " + getClientID() + "verbunden");
					cview.setModeration("client " + getClientID() + "verbunden");
				}
			});

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("clientID: " + getClientID());
		// clientSpielLogik.updateClientGUIVerbindung(clientID);

		
		Chat c;
		Object x;

		try {
			while ((x = ois.readObject()) != null) { // waiting

				if (x instanceof DatenAustausch) {

					this.datenAustausch = (DatenAustausch) x;

					System.out.println("Client: " + getClientID() + this.datenAustausch);

					// setDatenAustausch(this.datenAustausch);

					int a = this.datenAustausch.getSpielerListe().size();
					System.out.println(a);

					// UI updaten
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// entsprechende UI Komponente updaten

							System.out.println("runlater" + getClientID());
							controller.updateClientGUI(getDatenAustausch(), getClientID());
						}
					});
					
					
					controller.objectFromServerSetDatenaustausch(this.datenAustausch);

				}

				else if (x instanceof Chat) {

					c = (Chat) x;

					System.out.println(c);

					setC(c);

					// UI updaten
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// entsprechende UI Komponente updaten

							System.out.println("runlater Chat");
							controller.updateChat(getC());
						}
					});

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void wuerfelWuerfeln(DatenAustausch d) {

		d.wurfeln();

		sendDatenAustauschToServer(d);

	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public DatenAustausch getDatenAustausch() {
		return datenAustausch;
	}

	public void setDatenAustausch(DatenAustausch datenAustausch) {
		this.datenAustausch = datenAustausch;
	}

	public Chat getC() {
		return c;
	}

	public void setC(Chat c) {
		this.c = c;
	}

}
