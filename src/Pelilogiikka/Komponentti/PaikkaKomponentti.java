

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.NopeusViesti;


public class PaikkaKomponentti extends Komponentti {
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
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    @Override
    public void vieraile(NopeusViesti viesti) {
        x += viesti.getNopeus();
    }
    

}
