package Viestit;

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
        assertEquals("Väärä parametrin arvo", viesti.aloitaLiike(), true);
        assertEquals("Väärä parametrin arvo", viesti.pysaytaLiike(), false);
    }

    @Test
    public void testaaMuutaNopeusViesti() {
        MuutaNopeusViesti viesti = new MuutaNopeusViesti(0.5, 0.7);

        assertEquals("Väärä parametrin arvo", viesti.getXNopeudenMuutos(), 0.5, 0.001);
        assertEquals("Väärä parametrin arvo", viesti.getYNopeudenMuutos(), 0.7, 0.001);

    }

    @Test
    public void testaaNopeusViesti() {
        MuutaPaikkaViesti viesti = new MuutaPaikkaViesti(5, 2);

        assertEquals("Väärä parametrin arvo", viesti.getXMuutos(), 5);
        assertEquals("Väärä parametrin arvo", viesti.getYMuutos(), 2);
    }

    @Test
    public void testaaTormaysEntiteettiinViesti() {
        Entiteetti e = new Entiteetti();
        TormaysEntiteettiinViesti viesti = new TormaysEntiteettiinViesti(e, 0.3);

        assertEquals("Väärä parametrin arvo", viesti.getTormaaja(), e);
        assertEquals("Väärä parametrin arvo", viesti.getTormaysReuna(), 0.3, 0.001);
    }

    @Test
    public void testaaTormaysReunaanViesti() {

        TormaysReunaanViesti viesti = new TormaysReunaanViesti(Reuna.ALA);
        assertEquals("Väärä parametrin arvo", viesti.getReuna(), Reuna.ALA);
    }
}
