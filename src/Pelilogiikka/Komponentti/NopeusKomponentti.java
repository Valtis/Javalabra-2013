package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
/**
 * Abstrakti kantaluokka nopeuskomponenteille
 * @see MailaNopeusKomponentti
 * @see LiikkuvaObjektiNopeusKomponentti
 */
public abstract class NopeusKomponentti extends Komponentti {

    private int xNopeus;
    private int yNopeus;

    
    /**
     * Konstruktori. Alustaa arvot
     */
    public NopeusKomponentti() {
        xNopeus = 0;
        yNopeus = 0;
    }
    
    /**
     * Lähettää MuutaPaikkaViestin jos nopeus on einolla
     *
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä
     */
    @Override
    public void paivita(double ticks) {
        if (xNopeus != 0 || yNopeus != 0) {
            lisaaViesti(new MuutaPaikkaViesti((int) (xNopeus * ticks), (int) (yNopeus * ticks)));
        }
    }

    /**
     * Getteri. Palauttaa x-akselin suuntaisen nopeuden
     */
    public int getXNopeus() {
        return xNopeus;
    }

    /**
     * Getteri. Palauttaa y-akselin suuntaisen nopeuden
     */
    public int getYNopeus() {
        return yNopeus;
    }
    /**
     * Asettaa x-akselin suuntaisen nopeuden
     * @param nopeus Uusi nopeus
     */
    public void asetaXNopeus(int nopeus) {
        xNopeus = nopeus;
    }
    
    /**
     * Asettaa y-akselin suuntaisen nopeuden
     * @param nopeus Uusi nopeus
     */
    public void asetaYNopeus(int nopeus) {
        yNopeus = nopeus;
    }
}
