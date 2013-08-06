
package PeliLogiikka.Entiteetti;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.EntiteettiTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.LiikkuvaObjektiNopeusKomponentti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class EntiteettiTest {
    private Entiteetti entiteetti;
    public EntiteettiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        entiteetti = new Entiteetti();
        entiteetti.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new LiikkuvaObjektiNopeusKomponentti(10));
        entiteetti.lisaaKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA, new EntiteettiTormaysKasittelijaKomponentti());
    }
    
    @After
    public void tearDown() {
    
    }
    
    @Test
    public void entiteetillaNopeusKomponentti() {
        assertNotNull("Entiteetillä ei ole nopeuskomponenttia", entiteetti.getKomponentti(KomponenttiTyyppi.NOPEUS)); 
    }
    
    @Test
    public void entiteetillaTormaysKomponentti() {
        assertNotNull("Entiteetillä ei ole nopeuskomponenttia", entiteetti.getKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA)); 
    }
    
    @Test
    public void entiteetillaEiInputKomponenttia() {
        assertNull("Entiteetillä virheellisesti inputkomponentti", entiteetti.getKomponentti(KomponenttiTyyppi.INPUT)); 
    }
    
    @Test
    public void entiteetiJonoonIlmestyyViestiKunKasitellaanValittomastiTormaysViesti() {
        entiteetti.kasitteleValittomastiViesti(new TormaysEntiteettiinViesti(entiteetti, Reuna.YLA));
        assertEquals("Viestijonon koko väärä", 1, entiteetti.getViestiJono().size());
    }
    
    @Test
    public void entiteetiJonoonKaikkiViestitKasiteltyKunPaivitetaan() {
        entiteetti.paivita(1.0);
        
        assertEquals("Viestijonon koko väärä", 0, entiteetti.getViestiJono().size());
    }
    
}
