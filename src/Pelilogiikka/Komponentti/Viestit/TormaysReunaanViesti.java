

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.Komponentti;


public class TormaysReunaanViesti implements Viesti {

    Reuna tormaysReuna;
    
    public TormaysReunaanViesti(Reuna reuna) {
        tormaysReuna = reuna;
    }    

    public Reuna getReuna() {
        return tormaysReuna;
    }
    
    @Override
    public void otaVastaanVierailija(Komponentti k) {
        k.vieraile(this);
    }

}
