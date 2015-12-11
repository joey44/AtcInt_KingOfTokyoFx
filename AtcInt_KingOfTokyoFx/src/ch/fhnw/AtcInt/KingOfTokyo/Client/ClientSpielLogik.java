package ch.fhnw.AtcInt.KingOfTokyo.Client;

import java.util.ArrayList;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Spieler;

public class ClientSpielLogik {



	// ClientController controller;

	public static String lebenAnzeigen(DatenAustausch d2, int clientID) {

		// DatenAustausch d = DatenAustausch.getInstanz();

		Spieler s = d2.getSpielerByID(clientID);

		return ": " + s.getAnzahlLeben();

	}

	public static String ruhmpunkteAnzeigen(DatenAustausch d2, int clientID) {

		// DatenAustausch d = DatenAustausch.getInstanz();

		Spieler s = d2.getSpielerByID(clientID);

		return ": " + s.getAnzahlRuhmpunkte();
	}

	public static String spielerName(DatenAustausch d2, int clientID) {

		// DatenAustausch d = DatenAustausch.getInstanz();

		Spieler s = d2.getSpielerByID(clientID);

		return  s.getSpielerName();
	}

	public static String spielModerieren(DatenAustausch d) {

		return d.getModeration();

	}

	public static String standortAnzeigen(DatenAustausch d) {

		Spieler s = d.getSpielerAufTokyo();

		if (s == null) {
			return "kein Spieler ist auf Tokyo";
		}

		return " Spieler " + s.getSpielerID() + " ist auf Tokyo";

	}

	public static DatenAustausch wurfelWurfeln(DatenAustausch d) {
		Spieler s = d.getSpielerAmZug();
		int a = s.getSpielerID();
		
		
		int IDcounter = 1;
		if (d.getwCounter() % 3 == 0) {
			for (int x = 0; x < 6; x++) {

				d.getWurfel().setIsAusgewahlt(x, false);
			}
		}

		d.wurfeln();

		d.setModeration(s.getSpielerName() + " hat " + d.getwCounter() % 3 + " Mal gew�rfelt");

		if (d.getwCounter() % 3 == 0) {

			

			d.getSpielerAmZug().setAmZug(false);

			while (!(d.getSpielerByID((a + IDcounter) % 4).isSpielerAktiv())) {
				IDcounter++;
				if (IDcounter == 3) {
					break;
				}
			}
			d.getSpielerByID((a + IDcounter) % 4).setAmZug(true);

			d.setModeration(s.getSpielerName() + " hat den Zug beendet");
			
			

		}

		return d;

	}

}
