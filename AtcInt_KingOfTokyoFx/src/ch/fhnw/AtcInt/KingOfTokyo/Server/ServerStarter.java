package ch.fhnw.AtcInt.KingOfTokyo.Server;

import java.io.IOException;

/**
 * @author joel
 *
 */

public class ServerStarter extends Thread {

	private int port;
	private AtcIntServer atcIntServer;
	
	public ServerStarter(int port) {

		this.port = port;

		this.start();

	}

	public void run() {
		try {
			atcIntServer = new AtcIntServer(port);
			atcIntServer.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
