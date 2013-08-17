package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.PalloSeinaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
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

public class PalloSeinaTormaysKasittelijaKomponenttiTest {

    PalloSeinaTormaysKasittelijaKomponentti komponentti;
    ViestiJonoMockup viestiJono;
    private TormaysReunaanViesti ylaReuna;
    private TormaysReunaanViesti alaReuna;
    private TormaysReunaanViesti vasenReuna;
    private TormaysReunaanViesti oikeaReuna;

    public PalloSeinaTormaysKasittelijaKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        komponentti = new PalloSeinaTormaysKasittelijaKomponentti();
        viestiJono = new ViestiJonoMockup();
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
    public void tormaysYlaReunaanGeneroiKaksiViestia() {
        ylaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 2, viestiJono.valittomastiKasiteltavat.size());
    }

    @Test
    public void tormaysYlaReunaanGeneroiViestinJonkaTyyppiOikea() {
        ylaReuna.otaVastaanVierailija(komponentti);
        Viesti v = viestiJono.valittomastiKasiteltavat.poll();
        assertEquals("Viestin tyyppi on väärä", AlustaNopeusViesti.class, v.getClass());
    }

    @Test
    public void tormaysAlaReunaanGeneroiKaksiViestia() {
        alaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 2, viestiJono.valittomastiKasiteltavat.size());
    }

    @Test
    public void tormaysAlaReunaanGeneroiViestinJonkaTyyppiOikea() {
        alaReuna.otaVastaanVierailija(komponentti);
        Viesti v = viestiJono.valittomastiKasiteltavat.poll();
        assertEquals("Viestin tyyppi on väärä", AlustaNopeusViesti.class, v.getClass());
    }

    @Test
    public void tormaysVasempaanReunaanGeneroiViestin() {
        vasenReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void tormaysVasempaanReunaanGeneroiViestinJonkaSisaltoOikea() {
        vasenReuna.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertEquals("Viestillä on väärä arvo", -1, v.getXNopeudenMuutos(), 0.00001);
        assertEquals("Viestillä on väärä arvo", 1, v.getYNopeudenMuutos(), 0.00001);
    }

    @Test
    public void tormaysOikeaanReunaanGeneroiViestin() {
        oikeaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void tormaysOikeaanReunaanGeneroiViestinJonkaSisaltoOikea() {
        oikeaReuna.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertEquals("Viestillä on väärä arvo", -1, v.getXNopeudenMuutos(), 0.00001);
        assertEquals("Viestillä on väärä arvo", 1, v.getYNopeudenMuutos(), 0.00001);
    }

    @Test
    public void generoiNeljaViestiaJosOnTormataanSamaanReunaanSopivanAjanPaasta() {
        ylaReuna.otaVastaanVierailija(komponentti);
        for (int i = 0; i < komponentti.getTormaysHuomioimattaJattamisAika(); ++i) {
            komponentti.paivita(1.0);
        }

        ylaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 4, viestiJono.valittomastiKasiteltavat.size());
    }

    @Test
    public void eiGeneroiViestiaJosOnTormattyAskettainSamaan() {
        oikeaReuna.otaVastaanVierailija(komponentti);
        oikeaReuna.otaVastaanVierailija(komponentti);
        assertEquals("Generoi viestin kun ei olisi pitänyt", 1, viestiJono.jono.size());
    }
}
