package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;


/**
 * Törmäyskäsittelijä joka käsittelee seinään törmäämisen. Ilmoittaa että nopeuden on vaihduttava kun törmää seinään. 
 * @see PalloSeinaTormaysKasittelijaKomponentti
 */
public class KimpoaSeinastaTormaysKasittelijaKomponentti extends Komponentti {
    
    // törmäyslaskurilla estetään tilanne että entiteetti on liian syvällä reunassa kun törmäystarkistus tehdään
    // tällöin entiteetti ei välttämättä ehdi poistua reunalta ennen uutta törmäystarkistusta ja jää näin jumiin
    private final int TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA = 20;
    private Reuna viimeisinTormattyReuna;
    private int tormaysLaskuri = 0;
    
    
    public int getTormaysHuomioimattaJattamisAika() {
        return TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }
    
    /**
     * Päivittää törmäyslaskurin
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä.
     */
    @Override
    public void paivita(double ticks) {
        if (tormaysLaskuri > 0) {
            --tormaysLaskuri;
        }
    }
    /**
     *  Käsittelee törmäyksen raunaan. 
     * <p>
     * Lähettää MuutaNopeusViestin joka kertoo törmätystä reunasta riippuen joko vaihtamaan x- tai y-akselin suuntaisen nopeuden suunnan
     * @param viesti TormaysReunaanViesti
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        // toisin kuin pallo jonka paikka palaa keskelle jos törmää ylä\alareunaan, tämä törmääjä voi törmätä 
        // esimerkiksi aivan vasemman reunan yläosan tuntumassa vasempaan reunaan ja välittömästi törmätä yläreunaan
        // jos suunta ei huomioida, entiteetti lentää ruudulta ulos tai jää jumiin.
        if (tormaysLaskuri > 0 && viesti.getReuna() == viimeisinTormattyReuna) {
            return;
        }
        
        viimeisinTormattyReuna = viesti.getReuna();
        
        switch (viimeisinTormattyReuna) {
            case YLA:
            case ALA:
                lisaaViesti(new MuutaNopeusViesti(1, -1.0));
                break;
            case VASEN:
            case OIKEA:
                lisaaViesti(new MuutaNopeusViesti(-1.0, 1));
        }
        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }
}
