package ch.fhnw.AtcInt.KingOfTokyo.Client;

import java.util.ArrayList;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;
import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.Spieler;


/**
 * @author renato
 *
 */


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

		return s.getSpielerName();
	}

	public static String spielModerieren(DatenAustausch d) {

		return d.getModeration();

	}

	public static String standortAnzeigen(DatenAustausch d) {

		Spieler s = d.getSpielerAufTokyo();

		if (s == null) {
			return "kein Spieler ist auf Tokyo";
		}

		return s.getSpielerName() + " ist auf Tokyo";

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

		if (s.isAufTokyo() && d.getwCounter() % 3 == 1) {
			s.setAnzahlRuhmpunkte(s.getAnzahlRuhmpunkte() + 2);
			d.setSpielerByID(s.getSpielerID(), s);
			d.setModeration(s.getSpielerName() + " hat eine Runde auf Tokyo überlebt  und " + d.getwCounter() % 3
					+ " Mal gewürfelt");
		} else {
			d.setModeration(s.getSpielerName() + " hat " + d.getwCounter() % 3 + " Mal gewürfelt");

		}

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
