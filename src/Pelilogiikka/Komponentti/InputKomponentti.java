package Pelilogiikka.Komponentti;


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
public class InputKomponentti extends Komponentti implements KeyListener {

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
     * Toteuttaa KeyListener-rajapinnan keyTyped-metodin. Ei tee mitään
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
    /**
     * Käsittelee tapahtuman kun näppäin painetaan alas. Jos näppäinkoodi on aikaisemmin asetettu, lähettää LiikeViestin liikkeen alkamisesta
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int nappain = e.getKeyCode();
        if (nappaimet.containsKey(nappain)) {
            lisaaViesti(new LiikeViesti(nappaimet.get(nappain), true));
        }
    }

    /**
     * Käsittelee tapahtuman kun näppäin vapautetaan. Jos näppäinkoodi on aikaisemmin asetettu, lähettää LiikeViestin liikkeen loppumisesta.
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int nappain = e.getKeyCode();
        if (nappaimet.containsKey(nappain)) {
           lisaaViesti(new LiikeViesti(nappaimet.get(nappain), false));
        }
    }

}
