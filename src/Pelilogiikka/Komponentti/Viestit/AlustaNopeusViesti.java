

package Pelilogiikka.Komponentti.Viestit;

/**
 * Viesti joka ilmoittaa että nopeus olisi alustettava<br>
 * Nopeuskomponentti saa päättää mitä alustus tarkoittaa
 */
public class AlustaNopeusViesti implements Viesti {

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
