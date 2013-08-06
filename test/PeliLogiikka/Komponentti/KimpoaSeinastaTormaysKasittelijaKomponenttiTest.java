package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.KimpoaSeinastaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
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

public class KimpoaSeinastaTormaysKasittelijaKomponenttiTest {

    private KimpoaSeinastaTormaysKasittelijaKomponentti komponentti;
    private Queue<Viesti> viestiJono;
    private TormaysReunaanViesti ylaReuna;
    private TormaysReunaanViesti alaReuna;
    private TormaysReunaanViesti vasenReuna;
    private TormaysReunaanViesti oikeaReuna;

    public KimpoaSeinastaTormaysKasittelijaKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        komponentti = new KimpoaSeinastaTormaysKasittelijaKomponentti();
        viestiJono = new LinkedList<Viesti>();
        komponentti.lisaaViestijono(viestiJono);

        ylaReuna = new TormaysReunaanViesti(Reuna.YLA);
        alaReuna = new TormaysReunaanViesti(Reuna.ALA);
        vasenReuna = new TormaysReunaanViesti(Reuna.VASEN);
        oikeaReuna = new TormaysReunaanViesti(Reuna.OIKEA);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void tormaysYlaReunaanGeneroiViestin() {
        ylaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.size());
    }

    @Test
    public void tormaysYlaReunaanGeneroiViestinJonkaSisaltoOikea() {
        ylaReuna.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.poll();
        assertEquals("Viestillä on väärä arvo", 1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", -1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void tormaysAlaReunaanGeneroiViestin() {
        alaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.size());
    }

    @Test
    public void tormaysAlaReunaanGeneroiViestinJonkaSisaltoOikea() {
        alaReuna.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.poll();
        assertEquals("Viestillä on väärä arvo", 1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", -1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void tormaysVasempaanReunaanGeneroiViestin() {
        vasenReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.size());
    }

    @Test
    public void tormaysVasempaanReunaanGeneroiViestinJonkaSisaltoOikea() {
        vasenReuna.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.poll();
        assertEquals("Viestillä on väärä arvo", -1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", 1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void tormaysOikeaanReunaanGeneroiViestin() {
        oikeaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.size());
    }

    @Test
    public void tormaysOikeaanReunaanGeneroiViestinJonkaSisaltoOikea() {
        oikeaReuna.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.poll();
        assertEquals("Viestillä on väärä arvo", -1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", 1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void generoiViestiaJosOnTormattyAskettainEriReunaan() {
        oikeaReuna.otaVastaanVierailija(komponentti);
        vasenReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 2, viestiJono.size());
    }

    @Test
    public void generoiViestiaJosOnTormataanSamaanReunaanSopivanAjanPaasta() {
        ylaReuna.otaVastaanVierailija(komponentti);
        for (int i = 0; i < komponentti.getTormaysHuomioimattaJattamisAika(); ++i) {
            komponentti.paivita(1.0);
        }

        ylaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 2, viestiJono.size());
    }

    @Test
    public void eiGeneroiViestiaJosOnTormattyAskettainSamaan() {
        oikeaReuna.otaVastaanVierailija(komponentti);
        oikeaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Generoi viestin kun ei olisi pitänyt", 1, viestiJono.size());
    }
}
