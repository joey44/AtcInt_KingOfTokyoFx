package ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Wurfel implements Serializable {

	private static Wurfel InstanzWurfel;
	private int[] werte = new int[6];
	private boolean[] isAusgewahlt = new boolean[6];
	private int wCounter = 0;

	public String toString() {
		return "Wurfel [werte=" + Arrays.toString(werte) + ", isAusgewahlt="
				+ Arrays.toString(isAusgewahlt) + ", wCounter=" + wCounter
				+ "]";
	}

	public static Wurfel getInstanz() {
		// Singletonprinzip
		if (Wurfel.InstanzWurfel == null) {
			Wurfel.InstanzWurfel = new Wurfel();
		}
		return Wurfel.InstanzWurfel;
	}

	public int[] wuerfeln() {

		if (this.wCounter == 3) {

			// nächster Spieler kommt an den Zug
			//this.wCounter = 0;
		}

		if (this.wCounter % 3 == 0) {
			for (int i = 0; i < 6; i++) {
				this.werte[i] = (int) (1 + Math.random() * 5);
				this.isAusgewahlt[i] = false;
			}

		}

		else if (this.wCounter % 3 == 1) {
			for (int i = 0; i < 6; i++) {
				if (this.isAusgewahlt[i] == false) {
					this.werte[i] = (int) (1 + Math.random() * 5);
				}

			}

		}

		else if (this.wCounter % 3 == 2) {
			for (int i = 0; i < 6; i++) {
				if (this.isAusgewahlt[i] == false) {
					this.werte[i] = (int) (1 + Math.random() * 5);
				}

			}

	
			
			

		}

		this.wCounter++;

		return werte;

	}

	public void setIsAusgewahlt(int i, boolean wert) {
		this.isAusgewahlt[i] = wert;

	}

	public int getAnzWurfe() {

		return this.wCounter;

	}

	public boolean getIsAusgewahlt(int i) {
		return isAusgewahlt[i];
	}

	public void setWert(int i, int wert) {
		this.werte[i] = wert;
	}

	public int getWert(int i) {
		return werte[i];
	}

	public int getwCounter() {
		return wCounter;
	}

	public void setwCounter(int wCounter) {
		this.wCounter = wCounter;
	}

	public int[] getWerte() {
		return werte;
	}

	public void setWerte(int[] werte) {
		this.werte = werte;
	}

}
