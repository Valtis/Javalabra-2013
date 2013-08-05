package Pelilogiikka.Entiteetti;

import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.EnumMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Entiteettiluokka. Varastoi komponentit ja pitää yllä viestijonoa jonka avulla komponentit viestittävät toisilleen
 * 
 */
public class Entiteetti {

    private Map<KomponenttiTyyppi, Komponentti> komponentit;
    private Queue<Viesti> viestit;

    public Entiteetti() {
        viestit = new LinkedBlockingQueue<Viesti>();
        komponentit = new EnumMap<KomponenttiTyyppi, Komponentti>(KomponenttiTyyppi.class);
    }

    public void lisaaKomponentti(KomponenttiTyyppi tyyppi, Komponentti k) {
        k.lisaaViestijono(viestit);
        komponentit.put(tyyppi, k);
    }

    public Komponentti getKomponentti(KomponenttiTyyppi tyyppi) {
        if (!komponentit.containsKey(tyyppi)) {
            return null;
        }

        return komponentit.get(tyyppi);
    }
    
    public void lisaaViesti(Viesti v) {
        viestit.add(v);
    }
    
    public void kasitteleValittomastiViesti(Viesti v) {
        kasitteleViesti(v);
    }

    public void paivita(double ticks) {

        paivitaKomponentit(ticks);
        kasitteleViestit();
    }

    private void paivitaKomponentit(double ticks) {
        for (Komponentti k : komponentit.values()) {
            k.paivita(ticks);
        }
    }

    private void kasitteleViestit() {
        while (!viestit.isEmpty()) {
            Viesti v = viestit.poll();
            kasitteleViesti(v);             
        }
    }

    private void kasitteleViesti(Viesti v) {
        if (v == null) {
            return;
        }
        
        for (Komponentti k : komponentit.values()) {
            v.otaVastaanVierailija(k);
        }
    }
}
