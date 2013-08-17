package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.AlustaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
/**
 * Komponentti joka vastaa mailan paikasta.
 * @see PaikkaKomponentti
 * @see PalloPaikkaKomponentti
 */
public class MailaPaikkaKomponentti extends PaikkaKomponentti {

    private int vanhaX;

    /**
     * Konstruktori. Alustaa komponentin
     */
    public MailaPaikkaKomponentti() {
        super();
    }
    /**
     * Alustaa komponentin. Asettaa paikain parametrien osoittamaan koordinaattiin
     * @param x Haluttu x-koordinaatti
     * @param y Haluttu y-koordinaatti
     */
    public MailaPaikkaKomponentti(int x, int y) {
        super(x, y);
    }
    /**
     * Käsittelee MuutaPaikkaViestin. 
     * <p>
     * Ottaa talteen vanhan X:n paikan reunaan törmäyksen käsittelyä varten, muuten antaa yläluokan käsitellä viestin
     * @param viesti MuutaPaikkaViesti
     * @see MuutaPaikkaViesti
     */
    @Override
    public void vieraile(MuutaPaikkaViesti viesti) {
        vanhaX = getX();
        super.vieraile(viesti);
    }
    /**
     * Jos törmättiin reunaan, palauttaa vanhan paikan
     * @param viesti TormaysReunaanViesti
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(AlustaPaikkaViesti viesti) {
        asetaX(vanhaX);
    }
}
