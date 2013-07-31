

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.PelaajaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;


public class MailaPaikkaKomponentti extends PaikkaKomponentti {

    private int vanhaX;
    
    public MailaPaikkaKomponentti() {
        super();
    }
    
    public MailaPaikkaKomponentti(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void vieraile(PelaajaNopeusViesti viesti) {
        vanhaX = getX();
        asetaX(viesti.getNopeus() + getX());
    }
    
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        asetaX(vanhaX);
    }
}
