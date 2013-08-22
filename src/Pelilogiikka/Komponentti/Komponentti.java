package Pelilogiikka.Komponentti;

import Pelilogiikka.Entiteetti.ViestiJonoInterface;
import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.AlustaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import Pelilogiikka.Komponentti.Viestit.ViestiVierailija;

/**
 * Abstrakti kantaluokka komponenteille. Implementoi oletuskäyttäytymoisen
 * ViestiVierailija-rajapinnalle (ei reagoi viesteihin)
 */
public abstract class Komponentti implements ViestiVierailija {

    protected ViestiJonoInterface viestit;

    /**
     * Ottaa vastaan viittauksen komponentin viestijonoon jolla komponentti
     * pystyy välittämään viestejä eteenpäin
     *
     * @param viestit Omistavan komponentin viestijono
     */
    public void lisaaViestijono(ViestiJonoInterface viestit) {
        this.viestit = viestit;
    }

    /**
     * Lisää viestin jonoon. Tämän luokan perivät luokat käyttävät tätä metodia
     * kommunikointiin.
     */

    /**
     * Päivittää komponentin tilan jos tarvetta.
     *
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä.
     */
    public void paivita(double ticks) {
    }

    /**
     * Oletusimplementaatio LiikeViestin käsittelylle. Jättää viestin
     * huomioimatta
     *
     * @param viesti Liikeviesti
     * @see LiikeViesti
     */
    @Override
    public void vieraile(LiikeViesti viesti) {
    }

    /**
     * Oletusimplementaatio MuutaPaikkaViestin käsittelylle. Jättää viestin
     * huomioimatta
     *
     * @param viesti MuutaPaikkaViesti
     * @see MuutaPaikkaViesti
     */
    @Override
    public void vieraile(MuutaPaikkaViesti viesti) {
    }

    /**
     * Oletusimplementaatio TormaysReunaanViestin käsittelylle. Jättää viestin
     * huomioimatta
     *
     * @param viesti TormaysReunaanViesti
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
    }

    /**
     * Oletusimplementaatio TormaysEntiteettiinViestin käsittelylle. Jättää
     * viestin huomioimatta
     *
     * @param viesti TormaysEntiteettiinViesti
     * @see TormaysEntiteettiinViesti
     */
    @Override
    public void vieraile(TormaysEntiteettiinViesti viesti) {
    }

    /**
     * Oletusimplementaatio MuutaNopeusViestin käsittelylle. Jättää viestin
     * huomioimatta
     *
     * @param viesti MuutaNopeusViesti
     * @see MuutaNopeusViesti
     */
    @Override
    public void vieraile(MuutaNopeusViesti viesti) {
    }

    /**
     * Oletusimplementaatio AlustaNopeusViestin käsittelylle. Jättää viestin
     * huomioimatta
     *
     * @param viesti AlustaNopeusViesti
     * @see AlustaNopeusViesti
     */
    @Override
    public void vieraile(AlustaNopeusViesti viesti) {
    }
    /**
     * Oletusimplementaatio AlustaPaikkaViestin käsittelylle. Jättää viestin
     * huomioimatta
     *
     * @param viesti AlustaNopeusViesti
     * @see AlustaNopeusViesti
     */
    @Override 
    public void vieraile(AlustaPaikkaViesti viesti) {
    
    }
}
