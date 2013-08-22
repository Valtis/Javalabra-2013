package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.Viestit.AlustaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import Pelilogiikka.PisteKuuntelija;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalloPaikkaKomponenttiTest {

    private PalloPaikkaKomponentti komponentti;
    private ViestiJonoMockup viestiJono;
    private PisteKuuntelijaMockUp pisteKuuntelija;

    private class PisteKuuntelijaMockUp implements PisteKuuntelija {

        public Reuna osumaReuna;

        @Override
        public void pisteyta(Reuna reuna) {
            osumaReuna = reuna;
        }
    }

    public PalloPaikkaKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        komponentti = new PalloPaikkaKomponentti(10, 20);
        viestiJono = new ViestiJonoMockup();
        pisteKuuntelija = new PisteKuuntelijaMockUp();
        komponentti.lisaaViestijono(viestiJono);
        komponentti.asetaPisteKuuntelija(pisteKuuntelija);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void paikkaPalaaTakaisinAlkuunKunTormataanYlaReunaan() {
        int vanhaX = komponentti.getX();
        int vanhaY = komponentti.getY();
        MuutaPaikkaViesti muutos = new MuutaPaikkaViesti(10, -15);
        muutos.otaVastaanVierailija(komponentti);

        AlustaPaikkaViesti alustus = new AlustaPaikkaViesti(Reuna.YLA);
        alustus.otaVastaanVierailija(komponentti);

        assertEquals("X-akselin paikka ei palannut oikeaksi", vanhaX, komponentti.getX());
        assertEquals("Y-akselin paikka ei palannut oikeaksi", vanhaY, komponentti.getY());
    }

    @Test
    public void ilmoittaaKuuntelijalleOikeanReunanKunTormataanYlaReunaan() {
        AlustaPaikkaViesti alustus = new AlustaPaikkaViesti(Reuna.YLA);
        alustus.otaVastaanVierailija(komponentti);

        assertEquals("Pistekuuntelijan reuna on väärä", Reuna.YLA, pisteKuuntelija.osumaReuna);
    }

    @Test
    public void ilmoittaaKuuntelijalleOikeanReunanKunTormataanAlaReunaan() {
        AlustaPaikkaViesti alustus = new AlustaPaikkaViesti(Reuna.ALA);
        alustus.otaVastaanVierailija(komponentti);

        assertEquals("Pistekuuntelijan reuna on väärä", Reuna.ALA, pisteKuuntelija.osumaReuna);
    }

    @Test
    public void paikkaPalaaTakaisinAlkuunKunTormataanAlaReunaan() {
        int vanhaX = komponentti.getX();
        int vanhaY = komponentti.getY();
        MuutaPaikkaViesti muutos = new MuutaPaikkaViesti(10, -15);
        muutos.otaVastaanVierailija(komponentti);

        AlustaPaikkaViesti alustus = new AlustaPaikkaViesti(Reuna.ALA);
        alustus.otaVastaanVierailija(komponentti);

        assertEquals("X-akselin paikka ei palannut oikeaksi", vanhaX, komponentti.getX());
        assertEquals("Y-akselin paikka ei palannut oikeaksi", vanhaY, komponentti.getY());
    }

    @Test
    public void paikkaEiPalaaTakaisinAlkuunKunTormataanVasempaanReunaan() {
        int vanhaX = komponentti.getX();
        int vanhaY = komponentti.getY();
        MuutaPaikkaViesti muutos = new MuutaPaikkaViesti(10, -15);
        muutos.otaVastaanVierailija(komponentti);

        AlustaPaikkaViesti alustus = new AlustaPaikkaViesti(Reuna.VASEN);
        alustus.otaVastaanVierailija(komponentti);

        assertFalse("X-akselin paikka muuttui alkuun vaikka ei pitänyt", komponentti.getX() == vanhaX);
        assertFalse("Y-akselin paikka muuttui alkuun vaikka ei pitänyt ", komponentti.getY() == vanhaY);
    }

    @Test
    public void paikkaEiPalaaTakaisinAlkuunKunTormataanOikeaanReunaan() {
        int vanhaX = komponentti.getX();
        int vanhaY = komponentti.getY();
        MuutaPaikkaViesti muutos = new MuutaPaikkaViesti(10, -15);
        muutos.otaVastaanVierailija(komponentti);

        AlustaPaikkaViesti alustus = new AlustaPaikkaViesti(Reuna.OIKEA);
        alustus.otaVastaanVierailija(komponentti);

        assertFalse("X-akselin paikka muuttui vaikka ei pitänyt", komponentti.getX() == vanhaX);
        assertFalse("Y-akselin paikka muuttui vaikka ei pitänyt ", komponentti.getY() == vanhaY);
    }

    @Test
    public void testaaPaikkaViestiMuuttaaPaikkaaOikein() {
        int vanhaX = komponentti.getX();
        int vanhaY = komponentti.getY();

        MuutaPaikkaViesti v = new MuutaPaikkaViesti(1, -2);
        v.otaVastaanVierailija(komponentti);
        assertEquals("x-paikka muuttui väärin", vanhaX + 1, komponentti.getX());
        assertEquals("y-paikka muuttui väärin", vanhaY - 2, komponentti.getY());

    }

    @Test
    public void testaaPaikkaViestiEiGeneroiViestia() {
        MuutaPaikkaViesti v = new MuutaPaikkaViesti(1, 1);
        v.otaVastaanVierailija(komponentti);
        assertEquals("Generoi viestin kun ei olisi pitänyt", 0, viestiJono.jono.size());
    }
}
