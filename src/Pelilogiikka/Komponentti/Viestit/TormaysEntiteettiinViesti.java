

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Komponentti.Komponentti;


public class TormaysEntiteettiinViesti implements Viesti {
    private Entiteetti tormatty;
    public TormaysEntiteettiinViesti(Entiteetti tormatty) {
        this.tormatty = tormatty; 
    }
    
    public Entiteetti getTormaaja() {
    
    }
    
    @Override
    public void otaVastaanVierailija(Komponentti k) {
        k.vieraile(this);
    }

}
