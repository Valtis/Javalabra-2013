

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Komponentti.Komponentti;


public class PalloNopeusViesti implements Viesti {
    private final int X_NOPEUS;
    private final int Y_NOPEUS;
    
    public PalloNopeusViesti(int x, int y) {
        this.X_NOPEUS = x;
        this.Y_NOPEUS = y;
    }
    
    public int getXNopeus() {
        return X_NOPEUS;
    }
    
    public int getYNopeus() {
        return Y_NOPEUS;
    }
    
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
