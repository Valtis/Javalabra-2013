package Pelilogiikka.Komponentti;


import Kayttoliittyma.NappainKuuntelija;
import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
/**
 * Komponentti joka vastaa näppäimistösyötteen käsittelystä. Lähettää LiikeViesti-viestin kun käsittelee syötettä
 * @see LiikeViesti
 */
public class InputKomponentti extends Komponentti implements NappainKuuntelija {

    private Map<Integer, Suunta> nappaimet;
    /**
     * Konstruktori. Alustaa sisäiset rakenteet.
     */
    public InputKomponentti() {
        nappaimet = new HashMap<Integer, Suunta>();
    }

    /**
     * Asettaa näppäimen vastaamaan tiettyä liikesuuntaan
     * @param nappain Näppäinkoodi
     * @param suunta Suunta johon liikutaan kun painetaan näppäinkoodin määrittämää näppäintä
     */
    public void asetaNappain(int nappain, Suunta suunta) {
        nappaimet.put(nappain, suunta);
    }
    
    /**
     * Ottaa vastaan painetun näppäimen koodin
     * @param nappain Painettu näppäin, KeyEventin getCode()-arvo
     */
    @Override
    public void nappainPainettu(int nappain) {
        if (nappaimet.containsKey(nappain)) {
            lisaaViesti(new LiikeViesti(nappaimet.get(nappain), true));
        }
    }

    /**
     * Ottaa vastaan painetun näppäimen koodin
     * @param nappain Vapautettu näppäin, KeyEventin getCode()-arvo
     */
    @Override
    public void nappainVapautettu(int nappain) {
  
        if (nappaimet.containsKey(nappain)) {
           lisaaViesti(new LiikeViesti(nappaimet.get(nappain), false));
        }
    }

}
