package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.AlustaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;

/**
 * Komponentti joka toteuttaa pallon törmäyksen käsittelyn seinään.
 *
 * @see KimpoaSeinastaTormaysKasittelijaKomponentti
 * @see MailaSeinaTormaysKasittelijaKomponentti
 */
public class PalloSeinaTormaysKasittelijaKomponentti extends Komponentti {

    private final int TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA = 4;
    private int tormaysLaskuri = 0;

    
    /**
     * Getteri. Palauttaa kuinka monta peliaskelta komponentti ei huomioi törmäyksiä kun on törmätty johonkin.
     * @return 
     */
    public int getTormaysHuomioimattaJattamisAika() {
        return TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }
    
    /**
     * Päivittää törmäyslaskurin
     *
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä.
     */
    @Override
    public void paivita(double ticks) {
        if (tormaysLaskuri > 0) {
            --tormaysLaskuri;
        }
    }

    /**
     * Käsittelee TormaysReunaanViestin. <p> Jos törmätään vasempaan tai oikeaan
     * seinään, kimmotaan. Jos törmätään Ylä-tai alareunaan, alustetaan nopeus ja paikka.
     *
     * @param viesti TormaysReunaanViesti
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        if (tormaysLaskuri > 0) {
            return;
        }
        switch (viesti.getReuna()) {
            case YLA:
            case ALA:
                viestit.kasitteleValittomastiViesti(new AlustaNopeusViesti());
                viestit.kasitteleValittomastiViesti(new AlustaPaikkaViesti(viesti.getReuna()));
                break;
            case VASEN:
            case OIKEA:
                viestit.lisaaViesti(new MuutaNopeusViesti(-1.0, 1));
        }

        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }
}
