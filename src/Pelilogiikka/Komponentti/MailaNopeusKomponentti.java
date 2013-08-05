package Pelilogiikka.Komponentti;


import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import java.util.EnumMap;
import java.util.Map;


public class MailaNopeusKomponentti extends Komponentti {

    private int nykyinenNopeus;
    private Map<Suunta, Integer> nopeusMuutos;
    
    public MailaNopeusKomponentti(int mailanNopeus) {
        this.nykyinenNopeus = 0;
        nopeusMuutos = new EnumMap<Suunta, Integer>(Suunta.class);
        
        nopeusMuutos.put(Suunta.VASEN, -mailanNopeus);
        nopeusMuutos.put(Suunta.OIKEA, mailanNopeus);
    }
    
    public void asetaNopeus(Suunta suunta, Integer nopeus) {
        nopeusMuutos.put(suunta, nopeus);
    }

    @Override
    public void vieraile(LiikeViesti viesti) {
        if (viesti.aloitaLiike()) {
            nykyinenNopeus = nopeusMuutos.get(viesti.getSuunta());
        }
        
        else {
            nykyinenNopeus = 0;
        }
    }
    
    @Override
    public void paivita(double ticks) {
        if (nykyinenNopeus != 0) {
            viestit.add(new MuutaPaikkaViesti((int)((double)nykyinenNopeus*ticks), 0));
        }
    }

}
