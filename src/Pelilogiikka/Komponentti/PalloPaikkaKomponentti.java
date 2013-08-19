package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.Viestit.AlustaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import Pelilogiikka.PisteKuuntelija;

/**
 * Vastaa pallon paikasta. <p> Ilmoittaa Pistekuuntelijalle jos pallo osuu ylä-
 * tai alareunaan.
 *
 */
public class PalloPaikkaKomponentti extends PaikkaKomponentti {

    private int aloitusX;
    private int aloitusY;
    private PisteKuuntelija kuuntelija;

    /**
     * Konstruktori. Kutsuu vain PaikkaKomponentin konstruktoria.
     */
    public PalloPaikkaKomponentti() {
        super();
    }

    /**
     * Konstruktori. <p> Kutsuu PaikkaKomponentin konstruktoria ja alustaa
     * aloituspaikan jota tarvitaan kun pallo osuu ylä- tai alareunaan
     *
     * @param x Aloituksen x-koordinaatti
     * @param y Aloituksen y-koordinaatti
     */
    public PalloPaikkaKomponentti(int x, int y) {
        super(x, y);
        aloitusX = x;
        aloitusY = y;
    }

    /**
     * Asettaa pistekuuntelijan komponentille
     *
     * @param kuuntelija Pistekuuntelija-rajapinnan toteuttava luokka
     */
    public void asetaPisteKuuntelija(PisteKuuntelija kuuntelija) {
        this.kuuntelija = kuuntelija;
    }

    /**
     * Käsittelee AlustaPaikkaViestin. Jos törmätään ylä- tai alareunaan,
     * palautetaan alkupaikka.
     *
     * @param viesti AlustaPaikkaViesti
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(AlustaPaikkaViesti viesti) {
        
        if (viesti.getReuna() == Reuna.VASEN || viesti.getReuna() == Reuna.OIKEA) {
            return;
        }
        
        asetaPaikka(aloitusX, aloitusY);
        kuuntelija.pisteyta(viesti.getReuna());

    }
}
