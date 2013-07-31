

package Pelilogiikka.Komponentti;

import java.awt.Graphics;


public class PalloPiirtoKomponentti extends PiirtoKomponentti {
    private int halkaisija;
    
    public PalloPiirtoKomponentti() {
        asetaHalkaisija(0);
    }
    
    public PalloPiirtoKomponentti(int sade) {
        asetaHalkaisija(sade);
    }

    public final void asetaHalkaisija(int halkaisija) {
        this.halkaisija = halkaisija;
    }
    
    public int getSade() {
        return halkaisija;
    }
    
    @Override
    public void piirra(Graphics graphics, int x, int y) {
       graphics.fillOval(x, y, halkaisija, halkaisija);  
    }
}
