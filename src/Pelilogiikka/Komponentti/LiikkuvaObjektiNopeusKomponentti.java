package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import java.util.Random;

/**
 * Nopeuskomponentti liikkuville objekteille jotka eivät ole mailoja (pallo,
 * kimpoilevat esteet)
 *
 * @See MailaNopeusKomponentti
 */
public class LiikkuvaObjektiNopeusKomponentti extends NopeusKomponentti {

     private final int MAX_NOPEUS;

    /**
     * Konstruktori. Ottaa vastaan entiteetin maksiminopeuden.
     *
     * @param maxNopeus Maksiminopeus eniteetille
     */
    public LiikkuvaObjektiNopeusKomponentti(int maxNopeus) {
        MAX_NOPEUS = maxNopeus;
        asetaSatunnainenNopeus();
    }


    /**
     *  Käsittelee MuutaNopeusViestin. Muuttaa nopeuksia viestin arvojen mukaan
     * @param viesti MuutaNopeusViesti
     * @see MuutaNopeusViesti
     */
    @Override
    public void vieraile(MuutaNopeusViesti viesti) {
        asetaYNopeus((int) (getYNopeus() * viesti.getYNopeudenMuutos()));
        asetaXNopeus((int) (getXNopeus() * viesti.getXNopeudenMuutos()));
    }
    
    /**
     * Käsittelee AlustaNopeusViesti. Asettaa nopeuden satunnaiseksi
     * @param viesti AlustaNopeusViesti
     * @see AlustaNopeusViesti
     */
    @Override
    public void vieraile(AlustaNopeusViesti viesti) {
        asetaSatunnainenNopeus();
    }

    /**
     * Asettaa nopeuden satunnaiseksi. Välttelee nopeuksia joissa nopeus on lähinnä vaakatasossa
     */
    private void asetaSatunnainenNopeus() {

        Random random = new Random();

        int xEtumerkki = (int) Math.pow(-1, random.nextInt() % 2);
        int yEtumerkki = (int) Math.pow(-1, random.nextInt() % 2);

        asetaXNopeus(xEtumerkki * MAX_NOPEUS / 3 + Math.abs(random.nextInt()) % MAX_NOPEUS / 3);
        asetaYNopeus(yEtumerkki * (int) Math.sqrt(MAX_NOPEUS * MAX_NOPEUS - getXNopeus() * getXNopeus()));
        
    }
}
