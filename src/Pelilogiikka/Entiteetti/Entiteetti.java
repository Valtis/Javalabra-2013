package Pelilogiikka.Entiteetti;

import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.EnumMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Entiteettiluokka. Varastoi komponentit ja pitää yllä viestijonoa jonka avulla komponentit viestittävät toisilleen
 * 
 */
public class Entiteetti {

    private Map<KomponenttiTyyppi, Komponentti> komponentit;
    private Queue<Viesti> viestit;
    /**
     * Konstruktori. Alustaa sisäiset rakenteet.
     */
    public Entiteetti() {
        viestit = new LinkedBlockingQueue<Viesti>();
        komponentit = new EnumMap<KomponenttiTyyppi, Komponentti>(KomponenttiTyyppi.class);
    }
    /**
     * Lisää tyypin määrittämän komponentin entiteettin. Mahdollinen vanha komponentti jolla on sama tyyppi korvataan.
     * @param tyyppi Komponentin tyyppi
     * @param k Komponentti joka lisätään
     */
    public void lisaaKomponentti(KomponenttiTyyppi tyyppi, Komponentti k) {
        k.lisaaViestijono(viestit);
        komponentit.put(tyyppi, k);
    }
    /**
     * Palauttaa tyypin määrittämän komponentin tai null jos tyypille ei ole määritetty komponenttia
     * @param tyyppi Komponentin tyyppi
     * @return Tyypin määrittämä komponentti tai null jos tyypille ei ole komponenttia
     */
    public Komponentti getKomponentti(KomponenttiTyyppi tyyppi) {
        if (!komponentit.containsKey(tyyppi)) {
            return null;
        }

        return komponentit.get(tyyppi);
    }
    /**
     * Lisää viestin viestijonoon. Viesti käsitellään seuraavalla framella.
     * @param v Viesti joha lisätään jonoon.
     */
    public void lisaaViesti(Viesti v) {
        viestit.add(v);
    }
    /**
     * Käsittelee välittömästi viestin eikä odota seuraavaa framea.
     * @param v 
     */
    public void kasitteleValittomastiViesti(Viesti v) {
        kasitteleViesti(v);
    }
    
    /**
     * Päivittää komponenttien tilan ja käsittelee viestijonon. Parametri kertoo montako peliaskelta on kulunut viime päivityksestä
     * @param ticks Montako askelta on kulunut viime päivityksestä
     */
     
    public void paivita(double ticks) {

        paivitaKomponentit(ticks);
        kasitteleViestit();
    }
    /**
     * Päivittää komponentit
     * @param ticks Montako askelta on kulunut viime päivityksestä
     */
    private void paivitaKomponentit(double ticks) {
        for (Komponentti k : komponentit.values()) {
            k.paivita(ticks);
        }
    }
    /**
     * Käsittelee viestit
     */
    
    private void kasitteleViestit() {
        while (!viestit.isEmpty()) {
            Viesti v = viestit.poll();
            kasitteleViesti(v);             
        }
    }
    
    /**
     * Käsittelee yksittäisen viestin
     * @param v Käsiteltävä viesti
     */ 
    private void kasitteleViesti(Viesti v) {
        if (v == null) {
            return;
        }
        
        for (Komponentti k : komponentit.values()) {
            v.otaVastaanVierailija(k);
        }
    }
}
