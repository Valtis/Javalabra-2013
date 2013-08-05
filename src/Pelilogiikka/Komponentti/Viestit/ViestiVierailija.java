
package Pelilogiikka.Komponentti.Viestit;
/**
 * ViestiVierailija-rajapinta. Määrittää metodit eri viesteille jotka voivat vierailla.
 * 
 */
public interface ViestiVierailija {

    /**
     * LiikeViestin vierailija. 
     * @param viesti LiikeViesti
     */
    void vieraile(LiikeViesti viesti);
    /**
     * NopeusViestin vierailija. 
     * @param viesti NopeusViesti
     */
    void vieraile(MuutaPaikkaViesti viesti);
    
    /**
     * TormaysReunaanViestin vierailija. 
     * @param viesti TormaysReunaanViesti
     */
    void vieraile(TormaysReunaanViesti viesti);
    /**
     * TormaysEntiteettiinViestin vierailija. 
     * @param viesti TormaysEntiteettiinViesti
     */
    void vieraile(TormaysEntiteettiinViesti viesti);
    /**
     * MuutaNopeusViestin vierailija. 
     * @param viesti MuutaNopeusViesti
     */
    void vieraile(MuutaNopeusViesti viesti);
    /**
     * AlustaNopeusViestin vierailija. 
     * @param viesti AlustaNopeusViesti
     */
    void vieraile(AlustaNopeusViesti viesti);
    
}
