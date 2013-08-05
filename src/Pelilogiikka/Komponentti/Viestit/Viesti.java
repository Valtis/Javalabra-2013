
package Pelilogiikka.Komponentti.Viestit;
/**
 * Rajapinta viesteille. Määrittää vain vierailijan vastaanottamisen.
 */
public interface  Viesti {
    /**
     * Vierailijan vastaanotto. Toteuttava luokka välittää itsensa this-parametrilla
     * vierailjalle
     * @param k ViestiVierailija-rajapinnan toteuttava luokka 
     */
    public void otaVastaanVierailija(ViestiVierailija k);
}
