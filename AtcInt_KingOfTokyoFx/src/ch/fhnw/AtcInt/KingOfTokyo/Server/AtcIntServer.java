package ch.fhnw.AtcInt.KingOfTokyo.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Chat;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.LobbyDaten;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Spieler;
import ch.fhnw.AtcInt.KingOfTokyo.Server.AtcIntServerClientThread;

/**
 * @author joel, renato
 *
 */

public class AtcIntServer {

	private int port;
	private ArrayList<AtcIntServerClientThread> clientlist;
	private int Threadcounter = 0;
	private String Threadname;
	private ServerSocket serverSocket;
	private boolean stopServer = false;
	private DatenAustausch datenAustausch;

	private int spielerCounter = 0;

	public AtcIntServer(int port) {
		this.port = port;
		this.clientlist = new ArrayList<AtcIntServerClientThread>();

		// Da DatenAustausch Singleton ist, kann von aussen nicht instanziert
		// werden, Instanz wird �ber spezifische Methode geholt
		this.datenAustausch = DatenAustausch.getInstanz();

		// Wenn der Server gestartet wird, wir das Datenaustausch Objekt
		// initiert

	}

	public void start() throws Exception {
		serverSocket = new ServerSocket(port);
		while (clientlist.size() <= 3) { // vier Threads sind m�glich
			Socket socket = serverSocket.accept();
			this.Threadname = "Spieler " + Threadcounter;
			AtcIntServerClientThread clientThread = new AtcIntServerClientThread(this, socket, Threadname);
			clientThread.start();
			clientlist.add(clientThread);

			this.datenAustausch.addSpieler(Threadcounter, Threadname); // Spieler
																		// wird
																		// erstellt

			this.firstContact(Threadcounter, clientThread);

			System.out.println("client added: " + Threadname + " - " + socket.getInetAddress().toString());
			this.Threadcounter++;

		}

	}

	public void stopListening() {
		if (!this.stopServer) {
			this.stopServer = true;
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void spielStarten(DatenAustausch w) {

		System.out.println("spielStarten");

		// Zeit des Spielstart in DB erfassen
		try {
			ServerDatenbank.SpielStartZeit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.broadcast(w);

		this.clientlist.get(0).sendObjekctToClient(w); // Wenn alle Clients
														// verbunden sind,
														// bekommen sie Infos
														// vom Server

	}

	public void objectFromClientSetDatenaustausch(DatenAustausch w) {

		this.datenAustausch = w; // Objekt welches vom Client gesendet wird,

		DatenAustausch.setInstanz(w); // wird auf dem Server gespeichert

		if (w.getwCounter() % 3 == 0 && !w.isTokyoVerlassen()) {
			ServerSpielLogik.werteListeEvaluieren(w.getSpielerByID(w.getClientID()));
		}
	}

	public void firstContact(int clientID, AtcIntServerClientThread clientThread) {
		// this.datenAustausch.setClientID(clientID);

		// this.datenAustausch = DatenAustausch.getInstanz();

		clientThread.sendIDToClient(clientID);
		// Wenn der Client verbunden
		// ist, bekommt er Infos vom Server

	}

	public void broadcast(DatenAustausch w) { // alle Objekte, welche vom Client
												// kommen, werden an alle
												// verbundenen Client verteilt

		for (AtcIntServerClientThread client : clientlist) {

			try {
				// int a = w.getSpielerListe().size();
				// System.out.println(a);

				client.sendObjekctToClient(w);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (w.isSpielEnde()) {

			// Daten in DB Speichern
			try {
				ServerDatenbank.SpielEndZeit();
				ServerDatenbank.TabelleErgebnis(w);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Server beenden beim SpielEnde
			System.exit(0);
		}

	}

	public void broadcastChat(Chat c) { // alle Chat Objekte, welche vom
										// Client
		// kommen, werden an alle
		// verbundenen Clients verteilt
		for (AtcIntServerClientThread client : clientlist) {

			try {

				client.sendChatObjekctToClient(c);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void broadcastLobbyDaten(LobbyDaten l) {

		for (AtcIntServerClientThread client : clientlist) {

			try {

				client.sendLobbyDatenObjekctToClient(l);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// Name des Spielers wird gesetzt
	public void nameClientSpieler(String s, int clientID) {

		Spieler p = this.datenAustausch.getSpielerByID(clientID);

		p.setSpielerName(s);

		this.datenAustausch.setSpielerByID(clientID, p);

		spielerCounter++;

		if (spielerCounter == 4) { // Wenn 4 Clients verbunden sind und mit
									// Namen registriert,
									// kann das Spiel gestartet werden

			this.datenAustausch.setSpielStart(true);
			spielStarten(this.datenAustausch);
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ArrayList<AtcIntServerClientThread> getClientlist() {
		return clientlist;
	}

	public void setClientlist(ArrayList<AtcIntServerClientThread> clientlist) {
		this.clientlist = clientlist;
	}

	public int getThreadcounter() {
		return Threadcounter;
	}

	public void setThreadcounter(int threadcounter) {
		Threadcounter = threadcounter;
	}

	public String getThreadname() {
		return Threadname;
	}

	public void setThreadname(String threadname) {
		Threadname = threadname;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public boolean isStopServer() {
		return stopServer;
	}

	public void setStopServer(boolean stopServer) {
		this.stopServer = stopServer;
	}

}
