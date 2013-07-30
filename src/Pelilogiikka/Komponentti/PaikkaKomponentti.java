

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.NopeusViesti;


public class PaikkaKomponentti extends Komponentti {
    private int x;
    private int y;
    
    public PaikkaKomponentti() {
        x = 0;
        y = 0;
    }
    
    public void asetaPaikka(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void vieraile(NopeusViesti viesti) {
        x += viesti.getNopeus();
    }
    
}
