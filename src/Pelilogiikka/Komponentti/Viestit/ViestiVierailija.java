/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelilogiikka.Komponentti.Viestit;

public interface ViestiVierailija {

    void vieraile(LiikeViesti viesti);

    void vieraile(NopeusViesti viesti);

    void vieraile(PalloNopeusViesti viesti);

    void vieraile(TormaysReunaanViesti viesti);

    void vieraile(TormaysEntiteettiinViesti viesti);
    
}
