package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.AlustaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;

/**
 * Komponentti joka toteuttaa mailan törmäyksen käsittelyn seinään.
 *
 * @see KimpoaSeinastaTormaysKasittelijaKomponentti
 * @see PalloSeinaTormaysKasittelijaKomponentti
 */
public class MailaSeinaTormaysKasittelijaKomponentti extends Komponentti {
    
    // jos törmätty seinään, ilmoitetaan mailalle että sen on palattava vanhaan paikkaan. 
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        viestit.kasitteleValittomastiViesti(new AlustaPaikkaViesti(viesti.getReuna()));
    }
}
