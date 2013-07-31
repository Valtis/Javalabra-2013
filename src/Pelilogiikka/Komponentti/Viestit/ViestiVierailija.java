/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.NopeusViesti;
import Pelilogiikka.Komponentti.Viestit.PalloNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;


public interface ViestiVierailija {

    void vieraile(LiikeViesti viesti);

    void vieraile(NopeusViesti viesti);

    void vieraile(PalloNopeusViesti viesti);

    void vieraile(TormaysReunaanViesti viesti);

    void vieraile(TormaysEntiteettiinViesti viesti);
    
}
