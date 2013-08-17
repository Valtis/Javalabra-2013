

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.AlustaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;


public class MailaSeinaTormaysKasittelijaKomponentti extends Komponentti {

    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        viestit.kasitteleValittomastiViesti(new AlustaPaikkaViesti(viesti.getReuna()));
    }
}
