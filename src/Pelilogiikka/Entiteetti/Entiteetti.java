package Pelilogiikka.Entiteetti;

import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Entiteetti {

    private Map<String, Komponentti> komponentit;
    private Queue<Viesti> viestit;

    public Entiteetti() {
        viestit = new LinkedBlockingQueue<Viesti>();
        komponentit = new HashMap<String, Komponentti>();
    }

    public void lisaaKomponentti(String tyyppi, Komponentti k) {
        k.lisaaViestijono(viestit);
        
        komponentit.put(tyyppi, k);
    }
    
    public Komponentti getKomponentti(String tyyppi) {
        if (!komponentit.containsKey(tyyppi)) {
            return null;
        }
        
        return komponentit.get(tyyppi);
    }

    public void paivita(long delta) {
        for (Komponentti k : komponentit.values()) {
            k.paivita(delta);
        }
    }
}
