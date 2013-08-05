package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;

/**
 * Paikkakomponentti. Ylläpitää tietoa komponentin paikasta ja reagoi viesteihin
 * jotka pyytävät paikan muutosta
 * @see MailaPaikkaKomponentti
 * @see PalloPaikkaKomponentti
 */
public class PaikkaKomponentti extends Komponentti {

    private int x;
    private int y;

    /**
     * Konstruktori. Alustaa paikan koordinaatteihin (0, 0)
     */
    public PaikkaKomponentti() {
        asetaPaikka(0, 0);
    }

    /**
     * Konstruktori. Asettaa paikan haluttuihin koordinaatteihin
     *
     * @param x Haluttu x-koordinaatti
     * @param y Haluttu y-koordinaatti
     */
    public PaikkaKomponentti(int x, int y) {
        asetaPaikka(x, y);
    }

    /**
     * Asettaa paikan haluttuihin koordinaatteihin
     *
     * @param x Haluttu x-koordinaatti
     * @param y Haluttu y-koordinaatti
     */
    public final void asetaPaikka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Asettaa x-koordinaatin halutuksi
     *
     * @param x Haluttu x-koordinaatti
     */
    public final void asetaX(int x) {
        this.x = x;
    }

    /**
     * Asettaa y-koordinaatin halutuksi
     *
     * @param y Haluttu x-koordinaatti
     */
    public final void asetaY(int y) {
        this.y = y;
    }

    /**
     * Getteri. Palauttaa x-koordinaatin
     *
     * @return x-koordinaatti
     */
    public int getX() {
        return x;
    }

    /**
     * Getteri. Palauttaa y-koordinaatin
     *
     * @return y-koordinaatti
     */
    public int getY() {
        return y;
    }
    /**
     * Käsittelee MuutaPaikkaViestin. Muuttaa paikan viestin perusteella
     * @param viesti MuutaPaikkaViesti
     * @see MuutaPaikkaViesti
     */
    @Override
    public void vieraile(MuutaPaikkaViesti viesti) {
        asetaX(getX() + viesti.getXMuutos());
        asetaY(getY() + viesti.getYMuutos());
    }
}
