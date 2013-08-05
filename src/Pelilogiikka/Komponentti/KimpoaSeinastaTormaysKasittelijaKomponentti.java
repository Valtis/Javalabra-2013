package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;

public class KimpoaSeinastaTormaysKasittelijaKomponentti extends Komponentti {

    private final int TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA = 20;
    private Reuna viimeisinTormattyReuna;
    private int tormaysLaskuri = 0;

    @Override
    public void paivita(double ticks) {
        if (tormaysLaskuri > 0) {
            --tormaysLaskuri;
        }
    }

    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        if (tormaysLaskuri > 0 && viesti.getReuna() == viimeisinTormattyReuna) {
            return;
        }
        
        viimeisinTormattyReuna = viesti.getReuna();
        
        switch (viimeisinTormattyReuna) {
            case YLA:
            case ALA:
                viestit.add(new MuutaNopeusViesti(1, -1.0));
                break;
            case VASEN:
            case OIKEA:
                viestit.add(new MuutaNopeusViesti(-1.0, 1));
        }
        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }
}
