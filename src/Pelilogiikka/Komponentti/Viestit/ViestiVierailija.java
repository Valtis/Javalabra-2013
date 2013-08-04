
package Pelilogiikka.Komponentti.Viestit;

public interface ViestiVierailija {

    void vieraile(LiikeViesti viesti);

    void vieraile(NopeusViesti viesti);

    void vieraile(TormaysReunaanViesti viesti);

    void vieraile(TormaysEntiteettiinViesti viesti);
    
    void vieraile(AlustaNopeusViesti viesti);
    
}
