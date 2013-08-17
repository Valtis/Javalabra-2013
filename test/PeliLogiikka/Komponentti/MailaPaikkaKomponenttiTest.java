
package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MailaPaikkaKomponenttiTest {

    private MailaPaikkaKomponentti komponentti;
    private ViestiJonoMockup viestiJono;

    public MailaPaikkaKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        komponentti = new MailaPaikkaKomponentti();
        viestiJono = new ViestiJonoMockup();
        komponentti.lisaaViestijono(viestiJono);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void vanhaPaikkaPalautuuOikein() {
        int vanhaX = komponentti.getX();
        
        MuutaPaikkaViesti paikkaViesti = new MuutaPaikkaViesti(1, 0);    
        paikkaViesti.otaVastaanVierailija(komponentti);
        
        TormaysReunaanViesti tormaysViesti = new TormaysReunaanViesti(Reuna.YLA);
        tormaysViesti.otaVastaanVierailija(komponentti);
        assertEquals("X-koordinaatin arvo on väärä", vanhaX, komponentti.getX());
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
