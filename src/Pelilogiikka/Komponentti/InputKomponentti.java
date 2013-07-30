package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputKomponentti extends Komponentti implements KeyListener {

    private Map<KeyEvent, Suunta> nappaimet;

    public InputKomponentti() {
        nappaimet = new HashMap<KeyEvent, Suunta>();
    }

    public void asetaNappain(KeyEvent nappain, Suunta suunta) {
        nappaimet.put(nappain, suunta);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (nappaimet.containsKey(e)) {
            viestit.add(new LiikeViesti(nappaimet.get(e), true));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (nappaimet.containsKey(e)) {
            viestit.add(new LiikeViesti(nappaimet.get(e), false));
        }
    }
}
