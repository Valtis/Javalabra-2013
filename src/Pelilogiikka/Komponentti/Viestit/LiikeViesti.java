

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Enumit.Suunta;

/**
 * Viesti joka ilmoittaa liikkeen aloittamisesta tai lopettamista johonkin suuntaan <br>
 * Nopeuskomponentti mahdollisesti prosessoi tämän viestin
 */

public class LiikeViesti implements Viesti {
    private final boolean ALOITA_LIIKE;
    private final Suunta SUUNTA;
    
    /**
     * Konstruktori. Ottaa vastaan suunnan ja aloitetaanko vaiko lopetetaanko liike
     * @param s Liikkeen suunta
     * @param aloitaLiike aloitetaanko vaiko lopetetaanko liike
     */
    public LiikeViesti(Suunta s, boolean aloitaLiike) {
        SUUNTA = s;
        this.ALOITA_LIIKE = aloitaLiike;
    }
    
    /**
     * Getteri. Palauttaa liikkeen suunnan
     * 
     * @return Liikeen suunta
     */
    public Suunta getSuunta() {
        return SUUNTA;
    }
    
    /**
     * Getteri. Palauttaa aloitetaanko liikettä
     * 
     * @return aloitetaanko liike
     */
    public boolean aloitaLiike() {
        return ALOITA_LIIKE;
    }
    
    /**
     * Getteri. Palauttaa pysäytetäänkö liike
     * 
     * @return Pysäytetäänkö liike
     */
    public boolean pysaytaLiike() {
        return !ALOITA_LIIKE;
    }
            
    @Override
    /**
     * Ottaa vastaan vierailijan ja vierailee siellä.
     * 
     * @param k ViestiVierailija-rajapinnan toteuttava luokka
     */
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
