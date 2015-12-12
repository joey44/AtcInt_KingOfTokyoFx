package ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch;

import java.io.Serializable;

public class LobbyDaten implements Serializable {

	private boolean isSpielStart;
	private int spielerID;
	private int monsterID;
	private int monsterWahlCounter;
	private String lobbyModeration;

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

	@Override
	public String toString() {
		return "LobbyDaten [isSpielStart=" + isSpielStart + ", spielerID="
				+ spielerID + ", monsterID=" + monsterID
				+ ", monsterWahlCounter=" + monsterWahlCounter
				+ ", lobbyModeration=" + lobbyModeration + "]";
	}

}