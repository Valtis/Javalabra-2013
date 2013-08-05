package Kayttoliittyma;

import Pelilogiikka.Asetukset;
import Pelilogiikka.Entiteetti.Entiteetti;
import java.awt.event.KeyListener;

/**
 * Käyttöliittymäluokka. <p> Luo asetus- ja peliruutuikkunat
 */
public class KayttoLiittyma implements Runnable {

    private AsetusRuutu asetusRuutu;
    private PeliRuutu peliRuutu;
    /**
     * Toteuttaa Runnable-rajapinnan run-metodin
     */
    @Override
    public void run() {
    }
    /**
     * Piirtää pelialueen
     * @param pelaajan1Pisteet pelaajan 1 pisteet
     * @param pelaajan2Pisteet pelaajan 2 pisteet
     */
    public void piirra(int pelaajan1Pisteet, int pelaajan2Pisteet) {
        peliRuutu.asetaPisteet(pelaajan1Pisteet, pelaajan2Pisteet);
        peliRuutu.repaint();
    }
    /**
     * Alustaa käyttöliittymän
     * @param asetukset Asetusluokan ilmentymä; tämä kuuntelee asetusikkunaa
     */
    public void alusta(Asetukset asetukset) {
        asetusRuutu = new AsetusRuutu("Pong - asetukset", asetukset);
        asetusRuutu.setLocation(800, 20);

        peliRuutu = new PeliRuutu("Pong");

    }
    /**
     * Palauttaa peliruudun leveyden
     * @return Peliruudun leveys
     */
    public int peliAlueenLeveys() {
        return peliRuutu.getPeliAlueenLeveys();
    }
    /**
     * Palauttaa peliruudn korkeus
     * @return Peliruudun korkeus
     */
    public int peliAlueenKorkeus() {
        return peliRuutu.getPeliAlueenKorkeus();
    }
    /**
     * Lisää komponentin joka kuuntelee näppäimistöä
     * @param komponentti Kuunteleva komponentti
     * @throws ClassCastException Jos komponentti ei toteuta KeyListener-rajapintaa
     * @throws NullPointerException Jos komponentti on null
     */
    public void lisaaNappainKuuntelija(KeyListener komponentti) throws ClassCastException, NullPointerException {
        KeyListener kuuntelija = (KeyListener) komponentti;
        peliRuutu.addKeyListener(kuuntelija);
    }
    /**
     * Lisää piirrettävän entiteetin
     * @param entiteetti Piirrettävä entiteetti
     * @throws ClassCastException Jos entiteetin PiirtoKomponentti ja PaikkaKomponentti eivät toteuta oikeita luokkia
     * @throws NullPointerException Jos Entiteetti, Piirtokomponentti tai paikkakomponetti on null
     */
    public void lisaaPiirrettava(Entiteetti entiteetti) throws ClassCastException, NullPointerException {
        peliRuutu.lisaaPiirrettava(entiteetti);
    }
}
