package Pelilogiikka.Komponentti;


import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputKomponentti extends Komponentti implements KeyListener {

    private Map<Integer, Suunta> nappaimet;

    public InputKomponentti() {
        nappaimet = new HashMap<Integer, Suunta>();
    }

    public void asetaNappain(int nappain, Suunta suunta) {
        nappaimet.put(nappain, suunta);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int nappain = e.getKeyCode();
        if (nappaimet.containsKey(nappain)) {
            viestit.add(new LiikeViesti(nappaimet.get(nappain), true));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int nappain = e.getKeyCode();
        if (nappaimet.containsKey(nappain)) {
            viestit.add(new LiikeViesti(nappaimet.get(nappain), false));
        }
    }

}
