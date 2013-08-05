

package Pelilogiikka.Komponentti;


import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import Pelilogiikka.Komponentti.Viestit.ViestiVierailija;
import java.util.Queue;

/**
 * Abstrakti kantaluokka komponenteille. Implementoi oletuskäyttäytymoisen ViestiVierailija-rajapinnalle (ei reagoi viesteihin)
 */
public abstract class Komponentti implements ViestiVierailija {
    
    private Queue<Viesti> viestit;
    /**
     * Ottaa vastaan viittauksen komponentin viestijonoon jolla komponentti pystyy välittämään viestejä eteenpäin
     * @param viestit Omistavan komponentin viestijono
     */
    public void lisaaViestijono(Queue<Viesti> viestit) {
        this.viestit = viestit;
    }
    
    /**
     * Lisää viestin jonoon. Tämän luokan perivät luokat käyttävät tätä metodia kommunikointiin. 
     */
    protected void lisaaViesti(Viesti viesti) {
        viestit.add(viesti);
    }
    /**
     * Päivittää komponentin tilan jos tarvetta.
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä.
     */
    
    public void paivita(double ticks) {
    
    }
    /**
     * Oletusimplementaatio LiikeViestin käsittelylle. Jättää viestin huomioimatta
     * @param viesti Liikeviesti
     * @see LiikeViesti
     */
    @Override
    public void vieraile(LiikeViesti viesti) {
    
    }
    
    /**
     * Oletusimplementaatio MuutaPaikkaViestin käsittelylle. Jättää viestin huomioimatta
     * @param viesti MuutaPaikkaViesti
     * @see MuutaPaikkaViesti
     */
    @Override
    public void vieraile(MuutaPaikkaViesti viesti) {
    
    }
    
    /**
     * Oletusimplementaatio TormaysReunaanViestin käsittelylle. Jättää viestin huomioimatta
     * @param viesti TormaysReunaanViesti
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
    
    }
    
    /**
     * Oletusimplementaatio TormaysEntiteettiinViestin käsittelylle. Jättää viestin huomioimatta
     * @param viesti TormaysEntiteettiinViesti
     * @see TormaysEntiteettiinViesti
     */
    @Override
    public void vieraile(TormaysEntiteettiinViesti viesti) {
    
    }
    
   /**
     * Oletusimplementaatio MuutaNopeusViestin käsittelylle. Jättää viestin huomioimatta
     * @param viesti MuutaNopeusViesti
     * @see MuutaNopeusViesti
     */
    @Override
    public void vieraile(MuutaNopeusViesti viesti) {
    
    }
    
    /**
     * Oletusimplementaatio AlustaNopeusViestin käsittelylle. Jättää viestin huomioimatta
     * @param viesti AlustaNopeusViesti
     * @see AlustaNopeusViesti
     */
    @Override
    public void vieraile(AlustaNopeusViesti viesti) {
    
    }


}
