

package Pelilogiikka.Komponentti;

import java.awt.Graphics;


public class PalloPiirtoKomponentti extends PiirtoKomponentti {
    private int sade;
    
    public PalloPiirtoKomponentti() {
        asetaSade(0);
    }
    
    public PalloPiirtoKomponentti(int sade) {
        asetaSade(sade);
    }

    public final void asetaSade(int sade) {
        this.sade = sade;
    }
    
    public int getSade() {
        return sade;
    }
    
    @Override
    public void piirra(Graphics graphics, int x, int y) {
       graphics.fillOval(x, y, 2*sade, 2*sade);  
    }
}
