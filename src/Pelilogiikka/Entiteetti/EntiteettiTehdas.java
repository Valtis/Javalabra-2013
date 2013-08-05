package Pelilogiikka.Entiteetti;

import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.EntiteettiTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.KimpoaSeinastaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.LiikkuvaObjektiNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPiirtoKomponentti;
import Pelilogiikka.Komponentti.PalloSeinaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.SuoraKaidePiirtoKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import Pelilogiikka.Komponentti.TormaysAlueKomponentti;

/**
 * Tehdasluokka. Valmistaa halutun entiteetin parametrien perusteella
 *
 */
public class EntiteettiTehdas {

    /**
     * Tehdasmetodi. Valmistaa halutun entiteetin parametrien perusteella
     *
     * @param tyyppi Minkä tyyppinen entiteetti halutaan valmistaa
     * @param x Entiteetin x-koordinaatti
     * @param y Entiteetin y-koordinaatti
     * @return Palauttaa valmistetun entiteetin tai null jos tyyppiparametria ei
     * tunnistettu
     */
    public Entiteetti luoEntiteetti(EntiteettiTyyppi tyyppi, int x, int y) {
        switch (tyyppi) {
            case PALLO:
                return luoPallo(x, y);
            case PELAAJA_MAILA:
                return luoPelaajanMaila(x, y);
            case TEKOALY_MAILA:
                return luoTekoalyMaila(x, y);
            case STAATTINEN_ESTE:
                return luoStaattinenEste(x, y);
            case KIMPOILEVA_ESTE:
                return luoKimpoilevaEste(x, y);
        }
        return null;
    }

    /**
     * Valmistaa pallon
     *
     * @param x Pallon x-koordinaatti
     * @param y Pallon y-koordinaatti
     * @return Pallo-entiteetti
     */
    private Entiteetti luoPallo(int x, int y) {
        Entiteetti e = new Entiteetti();

        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new LiikkuvaObjektiNopeusKomponentti(6));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PalloPaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(17, 17));
        e.lisaaKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA, new PalloSeinaTormaysKasittelijaKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA, new EntiteettiTormaysKasittelijaKomponentti());

        return e;
    }

    /**
     * Valmistaa staattisen esteen
     *
     * @param x Esteen x-koordinaatti
     * @param y Esteen y-koordinaatti
     * @return Este-entiteetti
     */
    private Entiteetti luoStaattinenEste(int x, int y) {
        Entiteetti e = new Entiteetti();
        
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(20, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(20, 20));
        
        return e;
    }

    /**
     * Valmistaa kimpoilevan esteen
     *
     * @param x Esteen x-koordinaatti
     * @param y Esteen y-koordinaatti
     * @return Este-entiteetti
     */
    private Entiteetti luoKimpoilevaEste(int x, int y) {
        Entiteetti e = new Entiteetti();
        
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(20, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(20, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new LiikkuvaObjektiNopeusKomponentti(6));
        e.lisaaKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA, new KimpoaSeinastaTormaysKasittelijaKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA, new EntiteettiTormaysKasittelijaKomponentti());
        
        return e;
    }

    /**
     * Valmistaa pelaajamailan
     *
     * @param x Mailan x-koordinaatti
     * @param y Mailan y-koordinaatti
     * @return Maila-entiteetti
     */
    private Entiteetti luoPelaajanMaila(int x, int y) {
        Entiteetti e = new Entiteetti();
        
        luoYhteisetMailaKomponentit(e, x, y);
        e.lisaaKomponentti(KomponenttiTyyppi.INPUT, new InputKomponentti());
        
        return e;
    }

    /**
     * Valmistaa AI-mailan
     *
     * @param x Mailan x-koordinaatti
     * @param y Mailan y-koordinaatti
     * @return Maila-entiteetti
     */
    private Entiteetti luoTekoalyMaila(int x, int y) {
        Entiteetti e = new Entiteetti();
        
        luoYhteisetMailaKomponentit(e, x, y);
        TekoalyInputKomponentti k = new TekoalyInputKomponentti();
        k.asetaOmaPaikka((PaikkaKomponentti) e.getKomponentti(KomponenttiTyyppi.PAIKKA));
        e.lisaaKomponentti(KomponenttiTyyppi.INPUT, k);
        
        return e;

    }

    /**
     * Apumetodi mailan luomiseen. Asettaa entiteettiin komponentit jotka ovat
     * samat ai- ja pelaajamailoille
     *
     * @param e Mailaentiteetti
     * @param x Mailan x-koordinaatti
     * @param y Mailan y-koordinaatti
     */
    private void luoYhteisetMailaKomponentit(Entiteetti e, int x, int y) {
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new MailaNopeusKomponentti(4));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new MailaPaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(150, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(150, 20));
    }
}
