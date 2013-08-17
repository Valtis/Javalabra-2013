package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputKomponenttiTest {

    private InputKomponentti komponentti;
    private ViestiJonoMockup viestiJono;

    public InputKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        viestiJono = new ViestiJonoMockup();
        komponentti = new InputKomponentti();
        komponentti.asetaNappain(KeyEvent.VK_LEFT, Suunta.VASEN);
        komponentti.asetaNappain(KeyEvent.VK_RIGHT, Suunta.OIKEA);
        komponentti.lisaaViestijono(viestiJono);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nappaimenJotaEiAsetettuPainallusEiGeneroiViestia() {
        komponentti.nappainPainettu(KeyEvent.VK_A);
        assertEquals("Viesti generoitiin kun ei olisi pitänyt", 0, viestiJono.jono.size());
    }

    @Test
    public void nappaimenJotaEiAsetettuVapautusEiGeneroiViestia() {
        komponentti.nappainVapautettu(KeyEvent.VK_A);
        assertEquals("Viesti generoitiin kun ei olisi pitänyt", 0, viestiJono.jono.size());
    }

    @Test
    public void vasemmanNappaimenPainallusGeneroiViestin() {
        komponentti.nappainPainettu(KeyEvent.VK_LEFT);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void oikeanNappaimenPainallusGeneroiViestin() {
        komponentti.nappainPainettu(KeyEvent.VK_RIGHT);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void vasemmanNappaimenVapautusGeneroiViestin() {
        komponentti.nappainVapautettu(KeyEvent.VK_LEFT);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void oikeanNappaimenVapautusGeneroiViestin() {
        komponentti.nappainVapautettu(KeyEvent.VK_RIGHT);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void vasemmanNappaimenPainallusGeneroiViestinJossaLiikeAloitettu() {
        komponentti.nappainPainettu(KeyEvent.VK_LEFT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertTrue("Liikettä ei aloitettu oikein", viesti.aloitaLiike());
    }

    @Test
    public void vasemmanNappaimenVapautusGeneroiViestinJossaLiikePysaytetty() {
        komponentti.nappainVapautettu(KeyEvent.VK_LEFT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertTrue("Liikettä ei aloitettu oikein", viesti.pysaytaLiike());
    }

    @Test
    public void vasemmanNappaimenPainallusGeneroiViestinJossaLiikeEiPysaytetty() {
        komponentti.nappainPainettu(KeyEvent.VK_LEFT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertFalse("Liikettä ei aloitettu oikein", viesti.pysaytaLiike());
    }

    @Test
    public void vasemmanNappaimenVapautusGeneroiViestinJossaLiikeEiAloitettu() {
        komponentti.nappainVapautettu(KeyEvent.VK_LEFT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertFalse("Liikettä ei aloitettu oikein", viesti.aloitaLiike());
    }

    @Test
    public void vasemmanNappaimenPainallusGeneroiViestinJonkaSuuntaOikea() {
        komponentti.nappainVapautettu(KeyEvent.VK_LEFT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertEquals("Liikkeen suunta väärä", Suunta.VASEN, viesti.getSuunta());
    }

    @Test
    public void oikeanNappaimenPainallusGeneroiViestinJossaLiikeAloitettu() {
        komponentti.nappainPainettu(KeyEvent.VK_RIGHT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertTrue("Liikettä ei aloitettu oikein", viesti.aloitaLiike());
    }

    @Test
    public void oikeanNappaimenVapautusGeneroiViestinJossaLiikePysaytetty() {
        komponentti.nappainVapautettu(KeyEvent.VK_RIGHT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertTrue("Liikettä ei aloitettu oikein", viesti.pysaytaLiike());
    }

    @Test
    public void oikeanNappaimenPainallusGeneroiViestinJossaLiikeEiPysaytetty() {
        komponentti.nappainPainettu(KeyEvent.VK_RIGHT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertFalse("Liikettä ei aloitettu oikein", viesti.pysaytaLiike());
    }

    @Test
    public void oikeanNappaimenVapautusGeneroiViestinJossaLiikeEiAloitettu() {
        komponentti.nappainVapautettu(KeyEvent.VK_RIGHT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertFalse("Liikettä ei aloitettu oikein", viesti.aloitaLiike());
    }

    @Test
    public void oikeanNappaimenPainallusGeneroiViestinJonkaSuuntaOikea() {
        komponentti.nappainPainettu(KeyEvent.VK_RIGHT);
        LiikeViesti viesti = (LiikeViesti) viestiJono.jono.poll();
        assertEquals("Liikkeen suunta väärä", Suunta.OIKEA, viesti.getSuunta());
    }
}
