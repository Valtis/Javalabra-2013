

package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;

/**
 * Tekoälyn input-komponetti
 * <p> pyrkii pitämään mailan pallon kohdalla
 * 
 */
public class TekoalyInputKomponentti extends Komponentti {
    private PaikkaKomponentti pallonPaikka;
    private PaikkaKomponentti omaPaikka;
     
    /**
     * Asettaa pallonPaikkaKomponentin. 
     * <p>
     * Pallon paikkatiedot tarvitaan jotta maila osaa liikkua oikeaan suuntaan
     * @param pallonPaikkaKomponentti pallon paikkakomponentti
     */
    public void asetaPallonPaikka(PaikkaKomponentti pallonPaikkaKomponentti) {
        this.pallonPaikka = pallonPaikkaKomponentti;
    }
    /**
     * Asettaa oman PaikkaKomponenttinsa. 
     * <p>
     * Oma paikkatieto tarvitaan jotta voidaan liikkua oikeaan suuntaan
     * @param omaPaikkaKomponentti Oman mailan paikkakomponentti
     */
    public void asetaOmaPaikka(PaikkaKomponentti omaPaikkaKomponentti) {
        omaPaikka = omaPaikkaKomponentti;
    }
    /**
     * Lähettää LiikeViestin riippuen onko pallo vasemmalla vai oikealla mailasta
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä
     * @see LiikeViesti
     */
    @Override
    public void paivita(double ticks) {
       if (omaPaikka.getX() < pallonPaikka.getX() ) {
           viestit.lisaaViesti(new LiikeViesti(Suunta.OIKEA, true));
       }
       else {
           viestit.lisaaViesti(new LiikeViesti(Suunta.VASEN, true));
       }
    }

}
