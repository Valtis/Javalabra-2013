package PeliLogiikka.Entiteetti;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Entiteetti.EntiteettiTehdas;
import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.EntiteettiTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.KimpoaSeinastaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.LiikkuvaObjektiNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.MailaSeinaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.NopeusKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPiirtoKomponentti;
import Pelilogiikka.Komponentti.PalloSeinaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.SuoraKaidePiirtoKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import Pelilogiikka.Komponentti.TormaysAlueKomponentti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntiteettiTehdasTest {

    private final int X_PAIKKA = 20;
    private final int Y_PAIKKA = 40;
    private EntiteettiTehdas tehdas;

    public EntiteettiTehdasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tehdas = new EntiteettiTehdas();
        EntiteettiTehdas.palautaOletusArvot();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luoStaattisenEsteenJollaOikeatKomponentit() {
        Entiteetti este = tehdas.luoEntiteetti(EntiteettiTyyppi.STAATTINEN_ESTE, X_PAIKKA, Y_PAIKKA);
        assertNull("Staattisella esteellä on virheellisesti input-komponentti", este.getKomponentti(KomponenttiTyyppi.INPUT));

        assertNotNull("Staattiselta esteeltä puuttuu paikkakomponentti", este.getKomponentti(KomponenttiTyyppi.PAIKKA));
        assertEquals("Staattisen esteen paikkakomponentilla on väärä tyyppi", PaikkaKomponentti.class, este.getKomponentti(KomponenttiTyyppi.PAIKKA).getClass());

        assertNull("Staattisella esteellä on virheellisesti nopeuskomponentti", este.getKomponentti(KomponenttiTyyppi.NOPEUS));

        assertNotNull("Staattisella esteeltä puuttuu piirtokomponentti", este.getKomponentti(KomponenttiTyyppi.PIIRTO));
        assertEquals("Staattisen esteen piirtokomponentilla on väärä tyyppi", SuoraKaidePiirtoKomponentti.class, este.getKomponentti(KomponenttiTyyppi.PIIRTO).getClass());

        assertNotNull("Staattisella esteeltä puuttuu törmäysaluekomponentti", este.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE));
        assertEquals("Staattisen esteen törmäysaluekomponentilla on väärä tyyppi", TormaysAlueKomponentti.class, este.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE).getClass());

        assertNull("Staattisella esteellä on virheellisesti seinätörmäyskäsittelijäkomponentti", este.getKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA));

        assertNull("Staattisella esteellä on virheellisesti entiteettitörmäyskäsittelijäkomponentti", este.getKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA));

    }

    @Test
    public void luoKimpoilevanEsteenJollaOikeatKomponentit() {
        Entiteetti este = tehdas.luoEntiteetti(EntiteettiTyyppi.KIMPOILEVA_ESTE, X_PAIKKA, Y_PAIKKA);
        assertNull("Kimpoilevalla esteellä on virheellisesti input-komponentti", este.getKomponentti(KomponenttiTyyppi.INPUT));

        assertNotNull("Kimpoilevalla esteeltä puuttuu paikkakomponentti", este.getKomponentti(KomponenttiTyyppi.PAIKKA));
        assertEquals("Kimpoilevalla esteen paikkakomponentilla on väärä tyyppi", PaikkaKomponentti.class, este.getKomponentti(KomponenttiTyyppi.PAIKKA).getClass());

        assertNotNull("Kimpoilevalla esteeltä puuttuu nopeuskomponentti", este.getKomponentti(KomponenttiTyyppi.NOPEUS));
        assertEquals("Kimpoilevan esteen nopeuskomponentilla on väärä tyyppi", LiikkuvaObjektiNopeusKomponentti.class, este.getKomponentti(KomponenttiTyyppi.NOPEUS).getClass());

        assertNotNull("Kimpoilevalla esteeltä  puuttuu piirtokomponentti", este.getKomponentti(KomponenttiTyyppi.PIIRTO));
        assertEquals("Kimpoilevan esteen piirtokomponentilla on väärä tyyppi", SuoraKaidePiirtoKomponentti.class, este.getKomponentti(KomponenttiTyyppi.PIIRTO).getClass());

        assertNotNull("Kimpoilevalla esteeltä puuttuu törmäysaluekomponentti", este.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE));
        assertEquals("Kimpoilevan esteen törmäysaluekomponentilla on väärä tyyppi", TormaysAlueKomponentti.class, este.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE).getClass());

        assertNotNull("Kimpoilevalla esteeltä puuttuu seinätörmäyskäsittelijäkomponentti", este.getKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA));
        assertEquals("Kimpoilevan esteen seinätörmäyskäsittelijäkomponentilla on väärä tyyppi", KimpoaSeinastaTormaysKasittelijaKomponentti.class, este.getKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA).getClass());

        assertNotNull("Kimpoilevalla esteeltä  puuttuu entiteettitörmäyskäsittelijäkomponentti", este.getKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA));
        assertEquals("Kimpoilevan esteen entiteettitörmäyskäsittelijäkomponentilla on väärä tyyppi", EntiteettiTormaysKasittelijaKomponentti.class, este.getKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA).getClass());

    }

    @Test
    public void luoPallonJollaOikeatKomponentit() {
        Entiteetti pallo = tehdas.luoEntiteetti(EntiteettiTyyppi.PALLO, X_PAIKKA, Y_PAIKKA);

        assertNull("Pallolla virheellisesti input-komponentti", pallo.getKomponentti(KomponenttiTyyppi.INPUT));

        assertNotNull("Pallolta puuttuu paikkakomponentti", pallo.getKomponentti(KomponenttiTyyppi.PAIKKA));
        assertEquals("Pallon paikkakomponentilla on väärä tyyppi", PalloPaikkaKomponentti.class, pallo.getKomponentti(KomponenttiTyyppi.PAIKKA).getClass());

        assertNotNull("Pallolta puuttuu nopeuskomponentti", pallo.getKomponentti(KomponenttiTyyppi.NOPEUS));
        assertEquals("Pallon nopeuskomponentilla on väärä tyyppi", LiikkuvaObjektiNopeusKomponentti.class, pallo.getKomponentti(KomponenttiTyyppi.NOPEUS).getClass());

        assertNotNull("Pallolta puuttuu piirtokomponentti", pallo.getKomponentti(KomponenttiTyyppi.PIIRTO));
        assertEquals("Pallon piirtokomponentilla on väärä tyyppi", PalloPiirtoKomponentti.class, pallo.getKomponentti(KomponenttiTyyppi.PIIRTO).getClass());

        assertNotNull("Pallolta puuttuu törmäysaluekomponentti", pallo.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE));
        assertEquals("Pallon törmäysaluekomponentilla on väärä tyyppi", TormaysAlueKomponentti.class, pallo.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE).getClass());

        assertNotNull("Pallolta puuttuu seinätörmäyskäsittelijäkomponentti", pallo.getKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA));
        assertEquals("Pallon seinätörmäyskäsittelijäkomponentilla on väärä tyyppi", PalloSeinaTormaysKasittelijaKomponentti.class, pallo.getKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA).getClass());

        assertNotNull("Pallolta puuttuu entiteettitörmäyskäsittelijäkomponentti", pallo.getKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA));
        assertEquals("Pallon entiteettitörmäyskäsittelijäkomponentilla on väärä tyyppi", EntiteettiTormaysKasittelijaKomponentti.class, pallo.getKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA).getClass());
    }

    @Test
    public void luoPelaajaMailanJollaOikeatKomponentit() {
        Entiteetti maila = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, X_PAIKKA, Y_PAIKKA);

        assertNotNull("Pelaajamailalta puuttuu input-komponentti", maila.getKomponentti(KomponenttiTyyppi.INPUT));
        assertEquals("Pelaajamailan input-komponentilla on väärä tyyppi", InputKomponentti.class, maila.getKomponentti(KomponenttiTyyppi.INPUT).getClass());

        tarkistaMailanYhteisetKomponentit(maila);


    }

    @Test
    public void luoAIMailanJollaOikeatKomponentit() {
        Entiteetti maila = tehdas.luoEntiteetti(EntiteettiTyyppi.TEKOALY_MAILA, X_PAIKKA, Y_PAIKKA);

        assertNotNull("AI-mailalta puuttuu input-komponentti", maila.getKomponentti(KomponenttiTyyppi.INPUT));
        assertEquals("AI-mailan input-komponentilla on väärä tyyppi", TekoalyInputKomponentti.class, maila.getKomponentti(KomponenttiTyyppi.INPUT).getClass());

        tarkistaMailanYhteisetKomponentit(maila);


    }

    private void tarkistaMailanYhteisetKomponentit(Entiteetti maila) {

        assertNotNull("Mailalta puuttuu paikkakomponentti", maila.getKomponentti(KomponenttiTyyppi.PAIKKA));
        assertEquals("Mailan paikkakomponentilla on väärä tyyppi", MailaPaikkaKomponentti.class, maila.getKomponentti(KomponenttiTyyppi.PAIKKA).getClass());

        assertNotNull("Mailalta puuttuu nopeuskomponentti", maila.getKomponentti(KomponenttiTyyppi.NOPEUS));
        assertEquals("Mailan nopeuskomponentilla on väärä tyyppi", MailaNopeusKomponentti.class, maila.getKomponentti(KomponenttiTyyppi.NOPEUS).getClass());

        assertNotNull("Mailalta puuttuu piirtokomponentti", maila.getKomponentti(KomponenttiTyyppi.PIIRTO));
        assertEquals("Mailan piirtokomponentilla on väärä tyyppi", SuoraKaidePiirtoKomponentti.class, maila.getKomponentti(KomponenttiTyyppi.PIIRTO).getClass());

        assertNotNull("Mailalta puuttuu törmäysaluekomponentti", maila.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE));
        assertEquals("Mailan törmäysaluekomponentilla on väärä tyyppi", TormaysAlueKomponentti.class, maila.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE).getClass());

        assertNotNull("Mailalta puuttuu seinätörmäyskäsittelijäkomponentti", maila.getKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA));
        assertEquals("Mailan seinätörmäyskäsittelijäkomponentilla on väärä tyyppi", MailaSeinaTormaysKasittelijaKomponentti.class, maila.getKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA).getClass());

        assertNull("Mailalta on virheellisesti entiteettiörmäyskäsittelijä", maila.getKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA));
    }

    @Test
    public void pallollaOikeatOletusArvot() {
        Entiteetti pallo = tehdas.luoEntiteetti(EntiteettiTyyppi.PALLO, X_PAIKKA, Y_PAIKKA);
        tarkistaPaikkaKomponentinArvot((PaikkaKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PAIKKA), X_PAIKKA, Y_PAIKKA);
        tarkistaNopeusKomponentinArvot((NopeusKomponentti) pallo.getKomponentti(KomponenttiTyyppi.NOPEUS), 6);
        assertEquals("Pallon piirtäjän halkaisija väärä", 20, ((PalloPiirtoKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PIIRTO)).getHalkaisija());
        tarkistaTormaysKomponentinArvot((TormaysAlueKomponentti) pallo.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE), 17, 17);
    }

    @Test
    public void pallollaOikeatArvotKunMuutellaan() {
        EntiteettiTehdas.asetaPallonHalkaisija(25);
        EntiteettiTehdas.asetaPallonNopeus(25);
        EntiteettiTehdas.asetaPallonTormaysKoko(303);

        Entiteetti pallo = tehdas.luoEntiteetti(EntiteettiTyyppi.PALLO, 345, 11);
        tarkistaPaikkaKomponentinArvot((PaikkaKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PAIKKA), 345, 11);
        tarkistaNopeusKomponentinArvot((NopeusKomponentti) pallo.getKomponentti(KomponenttiTyyppi.NOPEUS), 25);
        assertEquals("Pallon piirtäjän halkaisija väärä", 25, ((PalloPiirtoKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PIIRTO)).getHalkaisija());
        tarkistaTormaysKomponentinArvot((TormaysAlueKomponentti) pallo.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE), 303, 303);
    }

    @Test
    public void mailallaOikeatOletusArvot() {
        Entiteetti maila = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, X_PAIKKA, Y_PAIKKA);
        tarkistaPaikkaKomponentinArvot((PaikkaKomponentti) maila.getKomponentti(KomponenttiTyyppi.PAIKKA), X_PAIKKA, Y_PAIKKA);
        tarkistaNopeusKomponentinArvot((NopeusKomponentti) maila.getKomponentti(KomponenttiTyyppi.NOPEUS), 4);
        tarkistaPiirtoKomponentinArvot((SuoraKaidePiirtoKomponentti) maila.getKomponentti(KomponenttiTyyppi.PIIRTO), 150, 20);
        tarkistaTormaysKomponentinArvot((TormaysAlueKomponentti) maila.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE), 150, 20);
    }

    @Test
    public void mailallaOikeatArvotKunMuutellaan() {

        EntiteettiTehdas.asetaMailanKoko(300, 400);
        EntiteettiTehdas.asetaMailanNopeus(25);
        EntiteettiTehdas.asetaMailanTormaysKoko(100, 40);

        Entiteetti maila = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, X_PAIKKA, Y_PAIKKA);
        tarkistaPaikkaKomponentinArvot((PaikkaKomponentti) maila.getKomponentti(KomponenttiTyyppi.PAIKKA), X_PAIKKA, Y_PAIKKA);
        tarkistaNopeusKomponentinArvot((NopeusKomponentti) maila.getKomponentti(KomponenttiTyyppi.NOPEUS), 25);
        tarkistaPiirtoKomponentinArvot((SuoraKaidePiirtoKomponentti) maila.getKomponentti(KomponenttiTyyppi.PIIRTO), 300, 400);
        tarkistaTormaysKomponentinArvot((TormaysAlueKomponentti) maila.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE), 100, 40);
    }

    @Test
    public void esteellaOikeatOletusArvot() {
        Entiteetti este = tehdas.luoEntiteetti(EntiteettiTyyppi.KIMPOILEVA_ESTE, X_PAIKKA, Y_PAIKKA);
        tarkistaPaikkaKomponentinArvot((PaikkaKomponentti) este.getKomponentti(KomponenttiTyyppi.PAIKKA), X_PAIKKA, Y_PAIKKA);
        tarkistaNopeusKomponentinArvot((NopeusKomponentti) este.getKomponentti(KomponenttiTyyppi.NOPEUS), 6);
        tarkistaPiirtoKomponentinArvot((SuoraKaidePiirtoKomponentti) este.getKomponentti(KomponenttiTyyppi.PIIRTO), 20, 20);
        tarkistaTormaysKomponentinArvot((TormaysAlueKomponentti) este.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE), 20, 20);
    }

    @Test
    public void esteellaOikeatArvotKunMuutellaan() {

        EntiteettiTehdas.asetaEsteenKoko(23, 111);
        EntiteettiTehdas.asetaEsteenNopeus(215);
        EntiteettiTehdas.asetaEsteenTormaysKoko(13400, 123);

        Entiteetti este = tehdas.luoEntiteetti(EntiteettiTyyppi.KIMPOILEVA_ESTE, X_PAIKKA, Y_PAIKKA);
        tarkistaPaikkaKomponentinArvot((PaikkaKomponentti) este.getKomponentti(KomponenttiTyyppi.PAIKKA), X_PAIKKA, Y_PAIKKA);
        tarkistaNopeusKomponentinArvot((NopeusKomponentti) este.getKomponentti(KomponenttiTyyppi.NOPEUS), 215);
        tarkistaPiirtoKomponentinArvot((SuoraKaidePiirtoKomponentti) este.getKomponentti(KomponenttiTyyppi.PIIRTO), 23, 111);
        tarkistaTormaysKomponentinArvot((TormaysAlueKomponentti) este.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE), 13400, 123);
    }

    private void tarkistaPaikkaKomponentinArvot(PaikkaKomponentti komponentti, int x, int y) {
        assertEquals("X-koordinaatin arvo on väärä", komponentti.getX(), x);
        assertEquals("Y-koordinaatin arvo on väärä", komponentti.getY(), y);
    }

    private void tarkistaNopeusKomponentinArvot(NopeusKomponentti komponentti, int nopeus) {
        int xNopeus = Math.abs(komponentti.getXNopeus());
        int yNopeus = Math.abs(komponentti.getYNopeus());
        // nopeus tallennetaan integerinä, voi olla oikeasti pienempi kuin annettu nopeus
        assertTrue("Nopeuden arvo väärä:", nopeus * nopeus >= (xNopeus * xNopeus + yNopeus * yNopeus));
    }

    
    private void tarkistaTormaysKomponentinArvot(TormaysAlueKomponentti komponentti, int leveys, int korkeus) {
        assertEquals("Leveys on väärä", komponentti.getLeveys(), leveys);
        assertEquals("Korkeus on väärä", komponentti.getKorkeus(), korkeus);
    }

    private void tarkistaPiirtoKomponentinArvot(SuoraKaidePiirtoKomponentti komponentti, int leveys, int korkeus) {
        assertEquals("Leveys on väärä", komponentti.getLeveys(), leveys);
        assertEquals("Korkeus on väärä", komponentti.getKorkeus(), korkeus);
    }
}
