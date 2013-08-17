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
import java.util.Arrays;

/**
 * Tehdasluokka. Valmistaa halutun entiteetin parametrien perusteella
 *
 */
public class EntiteettiTehdas {

    private final static int NOPEUS_INDEKSI = 0;
    private final static int LEVEYS_INDEKSI = 1;
    private final static int KORKEUS_INDEKSI = 2;
    private final static int TORMAYS_LEVEYS_INDEKSI = 3;
    private final static int TORMAYS_KORKEUS_INDEKSI = 4;
    private final static int[] PALLO_OLETUS_ARVOT = new int[]{6, 20, 20, 17, 17};
    private final static int[] ESTE_OLETUS_ARVOT = new int[]{6, 20, 20, 20, 20};
    private final static int[] MAILA_OLETUS_ARVOT = new int[]{4, 150, 20, 150, 20};
    private static int[] pallonArvot = Arrays.copyOf(PALLO_OLETUS_ARVOT, PALLO_OLETUS_ARVOT.length);
    private static int[] esteenArvot = Arrays.copyOf(ESTE_OLETUS_ARVOT, ESTE_OLETUS_ARVOT.length);
    private static int[] mailanArvot = Arrays.copyOf(MAILA_OLETUS_ARVOT, MAILA_OLETUS_ARVOT.length);

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

        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new LiikkuvaObjektiNopeusKomponentti(pallonArvot[NOPEUS_INDEKSI]));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PalloPaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(pallonArvot[LEVEYS_INDEKSI]));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(pallonArvot[TORMAYS_LEVEYS_INDEKSI], pallonArvot[TORMAYS_KORKEUS_INDEKSI]));
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
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(esteenArvot[LEVEYS_INDEKSI], esteenArvot[KORKEUS_INDEKSI]));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(esteenArvot[TORMAYS_LEVEYS_INDEKSI], esteenArvot[TORMAYS_KORKEUS_INDEKSI]));

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
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(esteenArvot[LEVEYS_INDEKSI], esteenArvot[KORKEUS_INDEKSI]));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(esteenArvot[TORMAYS_LEVEYS_INDEKSI], esteenArvot[TORMAYS_KORKEUS_INDEKSI]));
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new LiikkuvaObjektiNopeusKomponentti(esteenArvot[NOPEUS_INDEKSI]));
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
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new MailaNopeusKomponentti(mailanArvot[NOPEUS_INDEKSI]));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new MailaPaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(mailanArvot[LEVEYS_INDEKSI], mailanArvot[KORKEUS_INDEKSI]));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(mailanArvot[TORMAYS_LEVEYS_INDEKSI], mailanArvot[TORMAYS_KORKEUS_INDEKSI]));
    }

    /**
     * Palauttaa luotaville entiteeteille niiden oletusarvot.
     */
    public static void palautaOletusArvot() {
        pallonArvot = Arrays.copyOf(PALLO_OLETUS_ARVOT, PALLO_OLETUS_ARVOT.length);
        esteenArvot = Arrays.copyOf(ESTE_OLETUS_ARVOT, ESTE_OLETUS_ARVOT.length);
        mailanArvot = Arrays.copyOf(MAILA_OLETUS_ARVOT, MAILA_OLETUS_ARVOT.length);
    }

    /**
     * Asettaa luotavien pallojen nopeuden.
     *
     * @param nopeus Uusi nopeus
     */
    public static void asetaPallonNopeus(int nopeus) {
        pallonArvot[NOPEUS_INDEKSI] = nopeus;
    }

    /**
     * Asettaa luotavien pallojen halkaisijan
     *
     * @param halkaisija Uusi halkaisija
     */
    public static void asetaPallonHalkaisija(int halkaisija) {
        pallonArvot[LEVEYS_INDEKSI] = halkaisija;
        pallonArvot[KORKEUS_INDEKSI] = halkaisija;
    }

    /**
     * Asettaa pallojen törmäysalueen leveyden ja korkeuden (alue on neliö)
     *
     * @param koko Uusi törmäysalueen koko
     */
    public static void asetaPallonTormaysKoko(int koko) {
        pallonArvot[TORMAYS_LEVEYS_INDEKSI] = koko;
        pallonArvot[TORMAYS_KORKEUS_INDEKSI] = koko;
    }

    /**
     * Asettaa luotavien mailojen nopeuden.
     *
     * @param nopeus Uusi nopeus
     */
    public static void asetaMailanNopeus(int nopeus) {
        mailanArvot[NOPEUS_INDEKSI] = nopeus;
    }

    /**
     * Asettaa luotavien mailojen leveyden ja korkeuden
     *
     * @param leveys Uusi leveys
     * @param korkeus Uusi korkeus
     */
    public static void asetaMailanKoko(int leveys, int korkeus) {
        mailanArvot[LEVEYS_INDEKSI] = leveys;
        mailanArvot[KORKEUS_INDEKSI] = korkeus;
    }

    /**
     * Asettaa luotavien mailojen törmäysalueen leveyden ja korkeuden
     *
     * @param leveys Uusi leveys
     * @param korkeus Uusi korkeus
     */
    public static void asetaMailanTormaysKoko(int leveys, int korkeus) {
        mailanArvot[TORMAYS_LEVEYS_INDEKSI] = leveys;
        mailanArvot[TORMAYS_KORKEUS_INDEKSI] = korkeus;
    }

    /**
     * Asettaa luotavien esteiden nopeuden.
     *
     * @param nopeus Uusi nopeus
     */
    public static void asetaEsteenNopeus(int nopeus) {
        esteenArvot[NOPEUS_INDEKSI] = nopeus;
    }

    /**
     * Asettaa luotavien esteiden leveyden ja korkeuden
     *
     * @param leveys Uusi leveys
     * @param korkeus Uusi korkeus
     */
    public static void asetaEsteenKoko(int leveys, int korkeus) {
        esteenArvot[LEVEYS_INDEKSI] = leveys;
        esteenArvot[KORKEUS_INDEKSI] = korkeus;
    }

    /**
     * Asettaa luotavien esteiden törmäysalueen leveyden ja korkeuden
     *
     * @param leveys Uusi leveys
     * @param korkeus Uusi korkeus
     */
    public static void asetaEsteenTormaysKoko(int leveys, int korkeus) {
        esteenArvot[TORMAYS_LEVEYS_INDEKSI] = leveys;
        esteenArvot[TORMAYS_KORKEUS_INDEKSI] = korkeus;
    }
}
