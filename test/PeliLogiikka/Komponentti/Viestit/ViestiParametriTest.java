package PeliLogiikka.Komponentti.Viestit;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa viestit. Viestit ovat luokkina niin yksinkertaisia että tämä tiedosto
 * testaa ne kaikki
 */
public class ViestiParametriTest {

    public ViestiParametriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaLiikeViesti() {
        LiikeViesti viesti = new LiikeViesti(Suunta.VASEN, true);

        assertEquals("Väärä parametrin arvo", viesti.getSuunta(), Suunta.VASEN);
        assertEquals("Väärä parametrin arvo", true, viesti.aloitaLiike());
        assertEquals("Väärä parametrin arvo", false, viesti.pysaytaLiike());
    }

    @Test
    public void testaaMuutaNopeusViesti() {
        MuutaNopeusViesti viesti = new MuutaNopeusViesti(0.5, 0.7);

        assertEquals("Väärä parametrin arvo", 0.5, viesti.getXNopeudenMuutos(), 0.00001);
        assertEquals("Väärä parametrin arvo", 0.7, viesti.getYNopeudenMuutos(), 0.00001);

    }

    @Test
    public void testaaNopeusViesti() {
        MuutaPaikkaViesti viesti = new MuutaPaikkaViesti(5, 2);

        assertEquals("Väärä parametrin arvo", 5, viesti.getXMuutos());
        assertEquals("Väärä parametrin arvo", 2, viesti.getYMuutos());
    }

    @Test
    public void testaaTormaysEntiteettiinViesti() {
        Entiteetti e = new Entiteetti();
        TormaysEntiteettiinViesti viesti = new TormaysEntiteettiinViesti(e, Reuna.OIKEA);

        assertEquals("Väärä parametrin arvo", e, viesti.getTormaaja());
        assertEquals("Väärä parametrin arvo", Reuna.OIKEA, viesti.getTormaysReuna());
    }

    @Test
    public void testaaTormaysReunaanViesti() {

        TormaysReunaanViesti viesti = new TormaysReunaanViesti(Reuna.ALA);
        assertEquals("Väärä parametrin arvo", Reuna.ALA, viesti.getReuna());
    }
}
