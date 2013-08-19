/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.MailaSeinaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Omistaja
 */
public class MailaSeinaTormaysKasittelijaKomponenttiTest {

    private MailaSeinaTormaysKasittelijaKomponentti komponentti;
    private ViestiJonoMockup viestit;

    public MailaSeinaTormaysKasittelijaKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        komponentti = new MailaSeinaTormaysKasittelijaKomponentti();
        viestit = new ViestiJonoMockup();
        komponentti.lisaaViestijono(viestit);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tormaysReunaanViestiGeneroiViestin() {
        TormaysReunaanViesti viesti = new TormaysReunaanViesti(Reuna.VASEN);
        viesti.otaVastaanVierailija(komponentti);

        assertEquals("Viestiä ei generoitu", 1, viestit.valittomastiKasiteltavat.size());
    }

    @Test
    public void tormaysEntiteettiinViestiEiGeneroiViestia() {
        TormaysEntiteettiinViesti viesti = new TormaysEntiteettiinViesti(null, Reuna.YLA);
        viesti.otaVastaanVierailija(komponentti);

        assertEquals("Generoitiin virheellisesti viesti", 0, viestit.jono.size());
        assertEquals("Generoitiin virheellisesti viesti", 0, viestit.valittomastiKasiteltavat.size());
    }
}
