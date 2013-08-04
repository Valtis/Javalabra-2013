

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Komponentti.Komponentti;


public class MailaNopeusViesti implements Viesti {
    private final int NOPEUS;
    
    public MailaNopeusViesti(int nopeus) {
        this.NOPEUS = nopeus;
    }
    
    public int getNopeus() {
        return NOPEUS;
    }
    
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }
}
