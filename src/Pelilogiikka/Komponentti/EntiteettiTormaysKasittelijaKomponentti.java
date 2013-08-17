package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;

/**
 * Luokka joka käsittelee entiteettien väliset törmäykset
 *
 */
public class EntiteettiTormaysKasittelijaKomponentti extends Komponentti {

    private final int TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA = 6;
    private final int TORMAYS_JATA_EDELLINEN_ENTITEETTI_HUOMIOIMATTA = 24;
    private int tormaysLaskuri = 0;
    private int edellinenEntiteettiTormaysLaskuri = 0;
    private Object entiteettiJohonTormattyViimeksi = null;

    /**
     * Getteri. Palauttaa kuinka monta peliaskelta komponentti ei huomioi törmäyksiä kun on törmätty johonkin.
     * @return 
     */
    public int getTormaysHuomioimattaJattamisAika() {
        return TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }
    /**
     * Getteri. Palauttaa kuinka monta peliaskelta komponentti ei huomioi törmäyksiä samaan entiteettiin kun on törmätty.
     * @return 
     */
    public int getTormaysJataEdellinenEntiteettiHuomioimattaAika() {
        return TORMAYS_JATA_EDELLINEN_ENTITEETTI_HUOMIOIMATTA;
    }
    
    /**
     * Päivittää törmäyslaskurin
     *
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä.
     */
    @Override
    public void paivita(double ticks) {
        if (tormaysLaskuri > 0) {
            tormaysLaskuri -= (int) ticks;
        }

        if (edellinenEntiteettiTormaysLaskuri > 0) {
            --edellinenEntiteettiTormaysLaskuri;
        }
    }

    /**
     * Käsittelee viestin joka ilmoittaa että on törmätty reunaan. <p> Jos on
     * törmätty seinän kanssa, jätetään entiteettitörmäykset hetkiksi
     * huomioimatta; muuten voi jäädä reunaan jumiin
     *
     * @param viesti TörmäysReunaanViesti
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA / 2;
    }

    /**
     * Käsittelee viestin joka ilmoittaa että on törmätty entiteettiin <p> Jos
     * on törmätty entiteettin ja edellisestä törmäyksestä samaan entiteettiin
     * on kulunut tarpeeksi aikaa, lähettää MuutaNopeusViestin joka ilmoittaa
     * tarvittavasta nopeuden muutoksesta
     *
     * @param viesti TormaysEntiteettiinViesti
     * @see TormaysEntiteettiinViesti
     */
    @Override
    public void vieraile(TormaysEntiteettiinViesti viesti) {
        // annetaan törmäyksen jälkeen objektille hieman aikaa irtaantua jos on useampi objekti erittäin lähellä toisiaan
        if (tormaysLaskuri > 0) {
            return;
        }

        // annetaan enemmän aikaa että entiteetti irtoaa siitä mihin viimeksi törmättiin (jos on syvällä toisen entiteetin sisällä ettei jää jumiin
        if (edellinenEntiteettiTormaysLaskuri >  0 && viesti.getTormaaja() == entiteettiJohonTormattyViimeksi) {
            return;
        }
        entiteettiJohonTormattyViimeksi = viesti.getTormaaja();
        double xSuunta = 1;
        double ySuunta = 1;

        switch (viesti.getTormaysReuna()) {
            case YLA:
            case ALA:
                ySuunta = -1;
                break;
            case VASEN:
            case OIKEA:
                xSuunta = -1;
                break;
        }

        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
        edellinenEntiteettiTormaysLaskuri = TORMAYS_JATA_EDELLINEN_ENTITEETTI_HUOMIOIMATTA;
        viestit.lisaaViesti(new MuutaNopeusViesti(xSuunta, ySuunta));
    }
}
