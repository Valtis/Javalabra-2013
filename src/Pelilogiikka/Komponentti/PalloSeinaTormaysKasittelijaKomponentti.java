

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;


public class PalloSeinaTormaysKasittelijaKomponentti extends Komponentti {
  private final int TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA = 4;
    private int tormaysLaskuri = 0;

    @Override
    public void paivita(double ticks) {
        if (tormaysLaskuri > 0) {
            --tormaysLaskuri;
        }
    }

    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        if (tormaysLaskuri > 0) {
            return;
        }
        switch (viesti.getReuna()) {
            case YLA:
            case ALA:
                viestit.add(new AlustaNopeusViesti());
                break;
            case VASEN:
            case OIKEA:
                viestit.add(new MuutaNopeusViesti(-1.0, 1));
        }
        
        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }
}
