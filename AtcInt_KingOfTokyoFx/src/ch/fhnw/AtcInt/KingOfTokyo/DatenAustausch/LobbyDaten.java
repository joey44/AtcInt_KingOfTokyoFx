package ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch;

import java.io.Serializable;
import java.util.Arrays;


/**
 * @author arcsuta, joel
 *
 */

public class LobbyDaten implements Serializable {

	private boolean isSpielStart;
	private int spielerID;
	private int monsterID;
	private int monsterWahlCounter;
	private String lobbyModeration;
	private int[] monsterList = new int[4];

	public LobbyDaten() {

		this.isSpielStart = false;
		this.monsterWahlCounter = 0;

	}

	public boolean isSpielStart() {
		return isSpielStart;
	}

	public void setSpielStart(boolean isSpielStart) {
		this.isSpielStart = isSpielStart;
	}

	public int getSpielerID() {
		return spielerID;
	}

	public void setSpielerID(int spielerID) {
		this.spielerID = spielerID;
	}

	public int getMonsterID() {
		return monsterID;
	}

	public void setMonsterID(int monsterID) {
		this.monsterID = monsterID;
	}

	public String getLobbyModeration() {
		return lobbyModeration;
	}

	public void setLobbyModeration(String lobbyModeration) {
		this.lobbyModeration = lobbyModeration;
	}

	public int getMonsterWahlCounter() {
		return monsterWahlCounter;
	}

	public void setMonsterWahlCounter(int monsterWahlCounter) {
		this.monsterWahlCounter = monsterWahlCounter;
	}

	public int getMonsterList(int clientID) {
		return monsterList[clientID];
	}

	public void setMonsterList(int spielerID, int monsterID) {
		this.monsterList[spielerID] = monsterID;
	}

	@Override
	public String toString() {
		return "LobbyDaten [isSpielStart=" + isSpielStart + ", spielerID=" + spielerID + ", monsterID=" + monsterID
				+ ", monsterWahlCounter=" + monsterWahlCounter + ", lobbyModeration=" + lobbyModeration
				+ ", monsterList=" + Arrays.toString(monsterList) + "]";
	}
	
	

	

}