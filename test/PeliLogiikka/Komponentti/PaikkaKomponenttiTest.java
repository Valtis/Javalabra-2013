
package PeliLogiikka.Komponentti;

import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PaikkaKomponenttiTest {
    private PaikkaKomponentti komponentti;
    private ViestiJonoMockup viestiJono;
    
    public PaikkaKomponenttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        komponentti = new PaikkaKomponentti(4, 10);
        viestiJono = new ViestiJonoMockup();
        komponentti.lisaaViestijono(viestiJono);
    }
    
    @After
    public void tearDown() {
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
