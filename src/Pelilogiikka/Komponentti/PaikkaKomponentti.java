

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.NopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;


public class PaikkaKomponentti extends Komponentti {
    private int x;
    private int y;
    private int vanhaX;
    
    public PaikkaKomponentti() {
        asetaPaikka(0, 0);
    
    }
    
    public PaikkaKomponentti(int x, int y) {
        asetaPaikka(x, y);
        ;
    }
    
    public final void asetaPaikka(int x, int y) {
        this.x = x;
        this.y = y;
        vanhaX = x;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    @Override
    public void vieraile(NopeusViesti viesti) {
        vanhaX = x;
        x += viesti.getNopeus();
    }
    
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        x = vanhaX;
    }
    

}
