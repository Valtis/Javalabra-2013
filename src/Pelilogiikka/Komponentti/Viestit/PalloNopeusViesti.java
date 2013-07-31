

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Komponentti.Komponentti;


public class PalloNopeusViesti implements Viesti {
    private int xNopeus;
    private int yNopeus;
    
    public PalloNopeusViesti(int x, int y) {
        this.xNopeus = x;
        this.yNopeus = y;
    }
    
    public int getXNopeus() {
        return xNopeus;
    }
    
    public int getYNopeus() {
        return yNopeus;
    }
    
    @Override
    public void otaVastaanVierailija(Komponentti k) {
        k.vieraile(this);
    }

}
