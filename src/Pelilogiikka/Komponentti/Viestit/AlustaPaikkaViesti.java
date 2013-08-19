package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Enumit.Reuna;

/**
 * Viesti joka ilmoittaa että paikka olisi alustettava. Paikkakomponentti saa
 * päättää mitä alustus itse asiassa tarkoittaa.
 *
 */
public class AlustaPaikkaViesti implements Viesti {

    private final Reuna REUNA; // Reuna johonka osuttiin kun generoitiin tämä viesti

    public AlustaPaikkaViesti(Reuna reuna) {
        REUNA = reuna;
    }

    public Reuna getReuna() {
        return REUNA;
    }

    /**
     * Ottaa vastaan vierailijan ja vierailee siellä.
     *
     * @param k ViestiVierailija-rajapinnan toteuttava luokka
     */
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }
}
