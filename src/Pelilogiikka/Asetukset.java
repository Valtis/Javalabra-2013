package Pelilogiikka;

import Kayttoliittyma.NappulaKuuntelija;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Entiteetti.EntiteettiTehdas;
import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.NappulaTyyppi;
import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Asetusluokka. Alustaa entiteetit, kuuntelee käyttöliittymältä viestejä jos
 * halutaan lisätä uusia entiteettejä tai vaihtaa pelaajien kontrolli
 *
 */
public class Asetukset implements NappulaKuuntelija {

    private final int MAILAN_LEVEYS = 150;
    private final int MAILAN_KORKEUS = 20;
    private int kentanLeveys;
    private int kentanKorkeus;
    private PeliInterface peli;
    private Entiteetti pelaaja1;
    private Entiteetti pelaaja2;
    private Entiteetti pallo;
    private Komponentti pelaajan1PelaajaKontrolli;
    private Komponentti pelaajan2PelaajaKontrolli;
    private boolean pelaaja1OnAI;
    private boolean pelaaja2OnAI;

    /**
     * Lataa pelin asetukset
     *
     * @param peli Peli-olio
     * @see Peli
     */
    public void haeAsetukset(PeliInterface peli, int kentanLeveys, int kentanKorkeus) {
        this.kentanLeveys = kentanLeveys;
        this.kentanKorkeus = kentanKorkeus;
        this.peli = peli;
        alustaEntiteetit();

    }

    /**
     * Alustaa entiteetit. Luo pallon, ja kaksi pelaajamailaa
     */
    private void alustaEntiteetit() {
        EntiteettiTehdas.asetaMailanKoko(MAILAN_LEVEYS, MAILAN_KORKEUS);
        EntiteettiTehdas.asetaMailanTormaysKoko(MAILAN_LEVEYS, MAILAN_KORKEUS);
        pallo = luoPallo(kentanLeveys / 2, kentanKorkeus / 2);
        ((PalloPaikkaKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PAIKKA)).asetaPisteKuuntelija(peli);

        peli.lisaaEntiteetti(pallo, false);

        pelaaja1 = luoPelaaja(Suunta.VASEN, KeyEvent.VK_LEFT, Suunta.OIKEA, KeyEvent.VK_RIGHT, kentanLeveys / 2, kentanKorkeus - MAILAN_KORKEUS - MAILAN_KORKEUS/2);
        pelaaja2 = luoPelaaja(Suunta.VASEN, KeyEvent.VK_A, Suunta.OIKEA, KeyEvent.VK_D, kentanLeveys / 2, MAILAN_KORKEUS / 2);

        peli.lisaaEntiteetti(pelaaja1, true);
        peli.lisaaEntiteetti(pelaaja2, true);
        otaTalteenPelaajaTiedot();
    }

    /**
     * Luo pelaajamailan
     *
     * @param ensimmainenSuunta Suunta-arvo
     * @param ensimmaisenSuunnanNappain Suunta-arvoa vastaava näppäin
     * @param toinenSuunta Toinen suunta
     * @param toisenSuunnanNappain toista suuntaa vastaava näppäin
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return Alustettu pelaaja-entiteetti
     */
    private Entiteetti luoPelaaja(Suunta ensimmainenSuunta, int ensimmaisenSuunnanNappain, Suunta toinenSuunta, int toisenSuunnanNappain, int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti pelaaja = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, x, y);

        InputKomponentti input = (InputKomponentti) pelaaja.getKomponentti(KomponenttiTyyppi.INPUT);
        input.asetaNappain(ensimmaisenSuunnanNappain, ensimmainenSuunta);
        input.asetaNappain(toisenSuunnanNappain, toinenSuunta);

        return pelaaja;
    }

    /**
     * Luo pallon
     *
     * @param x pallon x-komponentti
     * @param y pallon y-komponentti
     * @return Pallo
     */
    private Entiteetti luoPallo(int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti e = tehdas.luoEntiteetti(EntiteettiTyyppi.PALLO, x, y);
        return e;
    }

    /**
     * Luo kimpoilevan esteen satunnaiseen paikkaan
     *
     * @return Kimpoilevan esteen satunnaiseen paikkaan
     */
    private Entiteetti luoKimpoilevaEste() {
        Random random = new Random();
        int x = arvoXPaikka(random);
        int y = arvoYPaikka(random);


        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti e = tehdas.luoEntiteetti(EntiteettiTyyppi.KIMPOILEVA_ESTE, x, y);

        return e;
    }

    /**
     * Luo staattisen esteen satunnaiseen paikkaan
     *
     * @return Staattinen este
     */
    private Entiteetti luoStaattinenEste() {
        Random random = new Random();
        int x = arvoXPaikka(random);
        int y = arvoYPaikka(random);
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti e = tehdas.luoEntiteetti(EntiteettiTyyppi.STAATTINEN_ESTE, x, y);

        return e;
    }

    /**
     * Kuuntelee asetus-ui:n nappuloita. Luo staattisia tai kimpoilevia
     * entiteetteijä tai vaihtelee pelaajia AI-kontrolliin ja takaisin
     *
     * @param nappula
     */
    @Override
    public void nappulaViesti(NappulaTyyppi nappula) {
        switch (nappula) {
            case STAATTINEN_ESTE:
                peli.lisaaEntiteetti(luoStaattinenEste(), false);
                break;
            case KIMPOILEVA_ESTE:
                peli.lisaaEntiteetti(luoKimpoilevaEste(), false);
                break;
            case PELAAJA_1_INPUT_VAIHTO:
                vaihdaPelaajanInput(pelaaja1OnAI, pelaaja1, pelaajan1PelaajaKontrolli);
                pelaaja1OnAI = !pelaaja1OnAI;
                break;

            case PELAAJA_2_INPUT_VAIHTO:
                vaihdaPelaajanInput(pelaaja2OnAI, pelaaja2, pelaajan2PelaajaKontrolli);
                pelaaja2OnAI = !pelaaja2OnAI;
                break;
        }
    }

    /**
     * Vaihtaa pelaajan inputin
     *
     * @param onAI onko pelaaja juuri nyt ai-kontrolloitu
     * @param pelaaja pelaaja-entiteetti
     * @param kontrolli pelaajan kontrolli-entiteetti
     */
    private void vaihdaPelaajanInput(boolean onAI, Entiteetti pelaaja, Komponentti kontrolli) {
        if (onAI) {
            pelaaja.lisaaKomponentti(KomponenttiTyyppi.INPUT, kontrolli);
        } else {
            vaihdaAI(pelaaja);
        }
    }

    /**
     * Vaihtaa pelaajan AI:ksi
     *
     * @param pelaaja pelaajan entiteetti
     */
    private void vaihdaAI(Entiteetti pelaaja) {
        TekoalyInputKomponentti k = new TekoalyInputKomponentti();
        k.asetaOmaPaikka((PaikkaKomponentti) pelaaja.getKomponentti(KomponenttiTyyppi.PAIKKA));
        k.asetaPallonPaikka((PaikkaKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PAIKKA));
        pelaaja.lisaaKomponentti(KomponenttiTyyppi.INPUT, k);
    }

    private void otaTalteenPelaajaTiedot() {
        // otetaan pelaajien kontrollit talteen sitä varten että voidaan vaihdella pelaajien ja AI-komponenttien välillä
        pelaajan1PelaajaKontrolli = pelaaja1.getKomponentti(KomponenttiTyyppi.INPUT);
        pelaajan2PelaajaKontrolli = pelaaja2.getKomponentti(KomponenttiTyyppi.INPUT);
        pelaaja1OnAI = false;
        pelaaja2OnAI = false;
    }

    private int arvoXPaikka(Random random) {
        return Math.abs(random.nextInt()) % (kentanLeveys - kentanLeveys/10);
    }

    private int arvoYPaikka(Random random) {
        return Math.abs(random.nextInt()) % (kentanKorkeus - kentanKorkeus/10);
    }
}
