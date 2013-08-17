package PeliLogiikka.Komponentti;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.EntiteettiTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntiteettiTormaysKasittelijaKomponenttiTest {

    private EntiteettiTormaysKasittelijaKomponentti komponentti;
    private ViestiJonoMockup viestiJono;
    private TormaysEntiteettiinViesti entiteettiTormaysYlos;
    private TormaysEntiteettiinViesti entiteettiTormaysVasemmalle;
    private TormaysEntiteettiinViesti entiteettiTormaysOikealle;
    private TormaysEntiteettiinViesti entiteettiTormaysAlas;
    private Entiteetti entiteetti;

    public EntiteettiTormaysKasittelijaKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        komponentti = new EntiteettiTormaysKasittelijaKomponentti();
        viestiJono = new ViestiJonoMockup();
        entiteetti = new Entiteetti();
        entiteettiTormaysYlos = new TormaysEntiteettiinViesti(entiteetti, Reuna.YLA);
        entiteettiTormaysVasemmalle = new TormaysEntiteettiinViesti(entiteetti, Reuna.VASEN);
        entiteettiTormaysOikealle = new TormaysEntiteettiinViesti(entiteetti, Reuna.OIKEA);
        entiteettiTormaysAlas = new TormaysEntiteettiinViesti(entiteetti, Reuna.ALA);

        komponentti.lisaaViestijono(viestiJono);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = NullPointerException.class)
    public void heittaaPoikkeuksenJosViestiJonoaEiAsetettu() {
        Komponentti k = new EntiteettiTormaysKasittelijaKomponentti();

        entiteettiTormaysYlos.otaVastaanVierailija(k);
    }

    @Test
    public void generoiViestinKunSaaTormaysViestin() {
        entiteettiTormaysYlos.otaVastaanVierailija(komponentti);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void generoiOikeanViestinKunSaaTormaysViestinYlos() {
        entiteettiTormaysYlos.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertNotNull("Viesti on null!", v);
    }

    @Test
    public void generoiOikeanViestinKunSaaTormaysViestinAlas() {
        entiteettiTormaysAlas.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertNotNull("Viesti on null!", v);
    }

    @Test
    public void generoiOikeanViestinKunSaaTormaysViestinVasemmalle() {
        entiteettiTormaysVasemmalle.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertNotNull("Viesti on null!", v);
    }

    @Test
    public void generoiOikeanViestinKunSaaTormaysViestinOikealle() {
        entiteettiTormaysOikealle.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertNotNull("Viesti on null!", v);
    }

    @Test
    public void generoidullaViestillaOikeaParametriKunTormataanYlos() {
        entiteettiTormaysYlos.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertEquals("Viestillä on väärä arvo", 1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", -1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void generoidullaViestillaOikeaParametriKunTormataanAlas() {
        entiteettiTormaysAlas.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertEquals("Viestillä on väärä arvo", 1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", -1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void generoidullaViestillaOikeaParametriKunTormataanVasemmalle() {
        entiteettiTormaysVasemmalle.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertEquals("Viestillä on väärä arvo", -1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", 1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void generoidullaViestillaOikeaParametriKunTormataanOikealle() {
        entiteettiTormaysOikealle.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        assertEquals("Viestillä on väärä arvo", -1, v.getXNopeudenMuutos(), 0.001);
        assertEquals("Viestillä on väärä arvo", 1, v.getYNopeudenMuutos(), 0.001);
    }

    @Test
    public void eiGeneroiViestiaJosOnTormannytHetkiSitten() {
        entiteettiTormaysOikealle.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();
        entiteettiTormaysOikealle.otaVastaanVierailija(komponentti);

        assertEquals("Generoi viestin kun ei olisi pitänyt!", 0, viestiJono.jono.size());
    }

    @Test
    public void generoiViestinJosOnTormannytJaTormataanUudelleenSopivanAjanPaasta() {
        entiteettiTormaysOikealle.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();

        for (int i = 0; i < komponentti.getTormaysHuomioimattaJattamisAika(); ++i) {
            komponentti.paivita(1.0);
        }
        TormaysEntiteettiinViesti uusiTormays = new TormaysEntiteettiinViesti(new Entiteetti(), Reuna.VASEN);
        uusiTormays.otaVastaanVierailija(komponentti);

        assertEquals("Ei generoinut viestiä kun olisi pitänyt", 1, viestiJono.jono.size());
    }

    @Test
    public void generoiViestinJosOnTormannytJaTormataanUudelleenSamaanObjektiinSopivanAjanPaasta() {
        entiteettiTormaysOikealle.otaVastaanVierailija(komponentti);
        MuutaNopeusViesti v = (MuutaNopeusViesti) viestiJono.jono.poll();

        for (int i = 0; i < komponentti.getTormaysJataEdellinenEntiteettiHuomioimattaAika(); ++i) {
            komponentti.paivita(1.0);
        }

        entiteettiTormaysOikealle.otaVastaanVierailija(komponentti);

        assertEquals("Ei generoinut viestiä kun olisi pitänyt", 1, viestiJono.jono.size());
    }
}
