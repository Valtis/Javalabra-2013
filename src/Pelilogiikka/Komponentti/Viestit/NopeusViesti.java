

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Komponentti.Komponentti;


public class NopeusViesti implements Viesti {
    private int nopeus;
    
    public NopeusViesti(int nopeus) {
        this.nopeus = nopeus;
    }
    
    public int getNopeus() {
        return nopeus;
    }
    
    @Override
    public void otaVastaanVierailija(Komponentti k) {
        k.vieraile(this);
    }
}
