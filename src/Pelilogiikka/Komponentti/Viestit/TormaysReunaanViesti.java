

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Enumit.Reuna;

/**
 * Viesti joka viestittää törmäyskäsittelijälle että on törmätty reunaan.
 */
public class TormaysReunaanViesti implements Viesti {

    private final Reuna TORMAYS_REUNA;
    /**
     * Konstruktori. Ottaa vastaan parametrin joka kertoo mihin reunaan törmättiin
     * @param reuna Mihin reunaan törmättiin
     */
    public TormaysReunaanViesti(Reuna reuna) {
        TORMAYS_REUNA = reuna;
    }       
    /**
     * Getteri. Palauttaa reunan johon törmättiin
     * @return Reuna johon törmättiin
     */
    public Reuna getReuna() {
        return TORMAYS_REUNA;
    }
   /**
     * Ottaa vastaan vierailijan ja vierailee siellä.
     * 
     * @param k ViestiVierailija-rajapinnan toteuttava luokka
     */    
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
