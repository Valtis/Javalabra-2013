

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.PalloNopeusViesti;


public class PalloPaikkaKomponentti extends PaikkaKomponentti {
    
    public PalloPaikkaKomponentti() {
        super();
    }
    
    public PalloPaikkaKomponentti(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void vieraile(PalloNopeusViesti viesti) {
        asetaX(getX() + viesti.getXNopeus());
        asetaY(getY() + viesti.getYNopeus());
    }
}
