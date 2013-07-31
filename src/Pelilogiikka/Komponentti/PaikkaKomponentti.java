

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.NopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;


public abstract class PaikkaKomponentti extends Komponentti {
    private int x;
    private int y;
   
    public PaikkaKomponentti() {
        asetaPaikka(0, 0);
    
    }
    
    public PaikkaKomponentti(int x, int y) {
        asetaPaikka(x, y);
    }
    
    public final void asetaPaikka(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public final void asetaX(int x) {
        this.x = x;
    }
    
    public final void asetaY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
