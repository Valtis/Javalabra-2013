package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
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
    public void vieraile(MuutaPaikkaViesti viesti) {
        vanhaX = getX();
        super.vieraile(viesti);
    }

    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        asetaX(vanhaX);
    }
}
