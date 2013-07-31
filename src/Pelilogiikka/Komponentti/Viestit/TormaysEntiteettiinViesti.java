

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Komponentti.Komponentti;


public class TormaysEntiteettiinViesti implements Viesti {
    private Entiteetti tormatty;
    public TormaysEntiteettiinViesti(Entiteetti tormatty) {
        this.tormatty = tormatty; 
    }
    
    public Entiteetti getTormaaja() {
        return tormatty;
    }
    
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
