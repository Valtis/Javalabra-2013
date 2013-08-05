package Kayttoliittyma;

import Pelilogiikka.Entiteetti.Entiteetti;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * Peliruudun toteuttava ikkuna
 * 
 */
public class PeliRuutu extends JFrame {

    private int RUUDUN_LEVEYS = 800;
    private int RUUDUN_KORKEUS = 600;
    private Piirtoalusta piirtoalusta;
    /**
     * Luo peliruudun annetulla nimellä ja 800x600-resoluutiolla
     * @param title Ikkunan nimi
     */
    public PeliRuutu(String title) {
        super(title);

        setPreferredSize(new Dimension(RUUDUN_LEVEYS, RUUDUN_KORKEUS));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoPiistoAlusta(getContentPane());
        pack();
        setVisible(true);
    }
    /**
     * Asettaa pelaajien pisteet piirtämistä varten
     * @param pelaajan1Pisteet pelaajan 1 pisteet
     * @param pelaajan2Pisteet pelaajan 2 pisteet
     */
    public void asetaPisteet(int pelaajan1Pisteet, int pelaajan2Pisteet) {
        piirtoalusta.asetaPisteet(pelaajan1Pisteet, pelaajan2Pisteet);
    }
    /**
     * luo piirtoalustan
     * @param container Container johonka piirtoalusta tallennetaan
     */
    private void luoPiistoAlusta(Container container) {
        piirtoalusta = new Piirtoalusta();
        container.add(piirtoalusta);
    }
    /**
     * Getteri. Palauttaa pelialueen leveyden
     * @return pelialueen leveys
     */
    public int getPeliAlueenLeveys() {
        return piirtoalusta.getWidth();
    }
    /**
     * Getteri. Palauttaa pelialueen korkeuden
     * @return Pelialueen korkeus
     */    
    public int getPeliAlueenKorkeus() {
        return piirtoalusta.getHeight();
    }
    /**
     * Lisää piirrettävän piirtoalustaan
     * @param entiteetti Entiteetti joka halutaan piirtää
     * @throws ClassCastException Jos entiteetin piirto- tai paikkakomponettien luokat ovat väärät
     * @throws NullPointerException Jos entiteetillä ei ole piirto- tai paikkakomponenttia
     */
    public void lisaaPiirrettava(Entiteetti entiteetti) throws ClassCastException, NullPointerException {
        if (entiteetti == null) {
            throw new NullPointerException("Entiteetin arvo null metodissa KayttoLiittyma.lisaaPiirrettava()");
        }

        piirtoalusta.lisaaPiirrettava(entiteetti);
    }
}
