

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Komponentti.Komponentti;


public class PelaajaNopeusViesti implements Viesti {
    private int nopeus;
    
    public PelaajaNopeusViesti(int nopeus) {
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
