
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
    public void vieraile(LiikeViesti viesti);
    /**
     * NopeusViestin vierailija. 
     * @param viesti NopeusViesti
     */
    public void vieraile(MuutaPaikkaViesti viesti);
    
    /**
     * TormaysReunaanViestin vierailija. 
     * @param viesti TormaysReunaanViesti
     */
    public void vieraile(TormaysReunaanViesti viesti);
    /**
     * TormaysEntiteettiinViestin vierailija. 
     * @param viesti TormaysEntiteettiinViesti
     */
    public void vieraile(TormaysEntiteettiinViesti viesti);
    /**
     * MuutaNopeusViestin vierailija. 
     * @param viesti MuutaNopeusViesti
     */
    public void vieraile(MuutaNopeusViesti viesti);
    /**
     * AlustaNopeusViestin vierailija. 
     * @param viesti AlustaNopeusViesti
     */
    public void vieraile(AlustaNopeusViesti viesti);
    
    /**
     * AlustaPaikkaViestin vierailija. 
     * @param viesti AlustaNopeusViesti
     */
    public void vieraile(AlustaPaikkaViesti viesti);
    
}
