package Pelilogiikka.Komponentti;


import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.NopeusViesti;
import java.util.EnumMap;
import java.util.Map;


public class NopeusKomponentti extends Komponentti {

    private int nopeus;
    private Map<Suunta, Integer> nopeusMuutos;
    
    public NopeusKomponentti() {
        nopeus = 0;
        nopeusMuutos = new EnumMap<Suunta, Integer>(Suunta.class);
        
        nopeusMuutos.put(Suunta.VASEN, -10);
        nopeusMuutos.put(Suunta.OIKEA, 10);
    }
    
    public void asetaNopeus(Suunta suunta, Integer nopeus) {
        nopeusMuutos.put(suunta, nopeus);
    }

    @Override
    public void vieraile(LiikeViesti viesti) {
        if (viesti.aloitaLiike()) {
            nopeus = nopeusMuutos.get(viesti.getSuunta());
        }
        else {
            nopeus = 0;
        }
    }
    
    @Override
    public void paivita(double ticks) {
        if (nopeus != 0) {
            viestit.add(new NopeusViesti((int)((double)nopeus*ticks)));
        }
    }

}
