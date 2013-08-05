package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import java.util.EnumMap;
import java.util.Map;

/**
 * Nopeuskomponentti mailalle. Reagoi InputKomponentin viesteihin ja muuttaa
 * nopeutta niiden perusteella <p> Lähettää mahdollisesti MuutaPaikkaViestin
 *
 * @see InputKomponentti
 * @see LiikkuvaObjektiNopeusKomponentti
 */
public class MailaNopeusKomponentti extends NopeusKomponentti {

    private Map<Suunta, Integer> nopeusMuutos;

    /**
     * Konstruktori. Ottaa vastaan mailan maksiminopeuden
     *
     * @param mailanNopeus haluttu mailan nopeus
     */
    public MailaNopeusKomponentti(int mailanNopeus) {
        nopeusMuutos = new EnumMap<Suunta, Integer>(Suunta.class);

        nopeusMuutos.put(Suunta.VASEN, -mailanNopeus);
        nopeusMuutos.put(Suunta.OIKEA, mailanNopeus);
    }
    /**
     * Asettaa suuntaan liityvän nopeuden.
     * @param suunta Haluttu suunta
     * @param nopeus Suuntaan liityvä nopeus
     */
    public void asetaNopeus(Suunta suunta, Integer nopeus) {
        nopeusMuutos.put(suunta, nopeus);
    }
    
    /**
     * Käsittelee LiikeViestin. 
     * <p>
     * Jos liike pitää aloittaa, asettaa nopeuden suuntaan liittyväksi nopeudeksi, muutoin asettaa nopeuden nollaksi
     * @param viesti LiikeViesti
     * @see LiikeViesti
     */
    @Override
    public void vieraile(LiikeViesti viesti) {
        if (viesti.aloitaLiike()) {
            asetaXNopeus(nopeusMuutos.get(viesti.getSuunta()));
        } else {
            asetaXNopeus(0);
        }
    }
}
