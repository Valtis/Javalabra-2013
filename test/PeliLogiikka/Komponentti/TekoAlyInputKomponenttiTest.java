package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TekoAlyInputKomponenttiTest {

    private TekoalyInputKomponentti komponentti;
    private MailaPaikkaKomponentti paikkaKomponentti;
    private PaikkaKomponentti pallonPaikkaKomponentti;
    Queue<Viesti> viestiJono;

    public TekoAlyInputKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        viestiJono = new LinkedList<Viesti>();

        paikkaKomponentti = new MailaPaikkaKomponentti(10, 20);
        pallonPaikkaKomponentti = new PaikkaKomponentti();
        komponentti = new TekoalyInputKomponentti();

        komponentti.lisaaViestijono(viestiJono);
        komponentti.asetaOmaPaikka(paikkaKomponentti);
        komponentti.asetaPallonPaikka(pallonPaikkaKomponentti);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void generoiViestinKunPaivitetaan() {
        komponentti.paivita(1.0);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.size());
    }

    @Test
    public void generoiViestinJonkaSuuntaOikeatKunPalloVasemmalla() {
        pallonPaikkaKomponentti.asetaPaikka(5, 20);
        komponentti.paivita(1.0);

        LiikeViesti v = (LiikeViesti) viestiJono.poll();
        assertEquals("Liikeen suunta väärä", Suunta.VASEN, v.getSuunta());
    }

    @Test
    public void generoiViestinJonkaLiikkeenAloitusOikeatKunPalloVasemmalla() {
        pallonPaikkaKomponentti.asetaPaikka(5, 20);
        komponentti.paivita(1.0);

        LiikeViesti v = (LiikeViesti) viestiJono.poll();
        assertTrue("Liikeen aloitus väärä", v.aloitaLiike());
    }

    @Test
    public void generoiViestinJonkaLiikkeenLopetusOikeatKunPalloVasemmalla() {
        pallonPaikkaKomponentti.asetaPaikka(5, 20);
        komponentti.paivita(1.0);

        LiikeViesti v = (LiikeViesti) viestiJono.poll();
        assertFalse("Liikeen lopetus väärä", v.pysaytaLiike());
    }

    @Test
    public void generoiViestinJonkaSuuntaOikeatKunPalloOikealla() {
        pallonPaikkaKomponentti.asetaPaikka(40, 20);
        komponentti.paivita(1.0);

        LiikeViesti v = (LiikeViesti) viestiJono.poll();
        assertEquals("Liikeen suunta väärä", Suunta.OIKEA, v.getSuunta());
    }

    @Test
    public void generoiViestinJonkaLiikkeenAloitusOikeatKunPalloOikealla() {
        pallonPaikkaKomponentti.asetaPaikka(40, 20);
        komponentti.paivita(1.0);

        LiikeViesti v = (LiikeViesti) viestiJono.poll();
        assertTrue("Liikkeen aloitus väärä", v.aloitaLiike());
    }

    @Test
    public void generoiViestinJonkaLiikkeenLopetusOikeatKunPalloOikealla() {
        pallonPaikkaKomponentti.asetaPaikka(40, 20);
        komponentti.paivita(1.0);

        LiikeViesti v = (LiikeViesti) viestiJono.poll();
        assertFalse("Liikeen lopetus väärä", v.pysaytaLiike());
    }
}
