

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.Komponentti;


public class TormaysReunaanViesti implements Viesti {

    private final Reuna TORMAYS_REUNA;
    
    public TormaysReunaanViesti(Reuna reuna) {
        TORMAYS_REUNA = reuna;
    }    

    public Reuna getReuna() {
        return TORMAYS_REUNA;
    }
    
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
