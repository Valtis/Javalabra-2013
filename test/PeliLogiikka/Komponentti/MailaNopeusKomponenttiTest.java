package PeliLogiikka.Komponentti;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.MailaNopeusKomponentti;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MailaNopeusKomponenttiTest {

    private MailaNopeusKomponentti komponentti;
    private int mailaNopeus;
    private Queue<Viesti> viestiJono;

    public MailaNopeusKomponenttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Random random = new Random();
        mailaNopeus = Math.abs(random.nextInt()) % 10000;
        komponentti = new MailaNopeusKomponentti(mailaNopeus);
        viestiJono = new LinkedList<Viesti>();
        komponentti.lisaaViestijono(viestiJono);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nopeusOikeaKunLahetetaanLiikuVasemmalleViesti() {
        LiikeViesti v = new LiikeViesti(Suunta.VASEN, true);
        v.otaVastaanVierailija(komponentti);
        assertEquals("Nopeus väärä", -mailaNopeus, komponentti.getXNopeus());
        assertEquals("Y-nopeus einolla", 0, komponentti.getYNopeus());
    }

    @Test
    public void nopeusOikeaKunLahetetaanLiikuOikealleViesti() {
        LiikeViesti v = new LiikeViesti(Suunta.OIKEA, true);
        v.otaVastaanVierailija(komponentti);
        assertEquals("Nopeus väärä", mailaNopeus, komponentti.getXNopeus());
        assertEquals("Y-nopeus einolla", 0, komponentti.getYNopeus());
    }

    @Test
    public void nopeusMuutosViestiMuuttaaNopeutta() {
        int vanhaX = komponentti.getXNopeus();
        int vanhaY = komponentti.getYNopeus();

        MuutaNopeusViesti v = new MuutaNopeusViesti(-0.5, 1.0);
        v.otaVastaanVierailija(komponentti);


        assertEquals("X-Nopeus väärä muutoksen jälkeen", (int) (-0.5 * vanhaX), komponentti.getXNopeus());
        assertEquals("Y-Nopeus väärä muutoksen jälkeen", (int) (1.0 * vanhaY), komponentti.getYNopeus());
    }

    @Test
    public void lahettaaViestinKunNopeusEiNolla() {
        LiikeViesti v = new LiikeViesti(Suunta.VASEN, true);
        v.otaVastaanVierailija(komponentti);

        komponentti.paivita(1.0);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.size());
    }

    @Test
    public void lahettaaViestinJonkaArvotOikeatKunNopeusEiNolla() {
        LiikeViesti v = new LiikeViesti(Suunta.VASEN, true);
        v.otaVastaanVierailija(komponentti);

        komponentti.paivita(1.0);
        MuutaPaikkaViesti muutaViesti = (MuutaPaikkaViesti) viestiJono.poll();

        assertEquals("X-akselin muutos väärä", komponentti.getXNopeus(), muutaViesti.getXMuutos());
        assertEquals("Y-akselin muutos väärä", komponentti.getYNopeus(), muutaViesti.getYMuutos());
    }

    @Test
    public void lahettaaViestinJonkaArvotOikeatKunNopeusEiNollaJaPeliAskelVenahtanyt() {
        LiikeViesti v = new LiikeViesti(Suunta.VASEN, true);
        v.otaVastaanVierailija(komponentti);

        komponentti.paivita(5.2);
        MuutaPaikkaViesti muutaViesti = (MuutaPaikkaViesti) viestiJono.poll();

        assertEquals("X-akselin muutos väärä", (int)(5.2*komponentti.getXNopeus()), muutaViesti.getXMuutos());
        assertEquals("Y-akselin muutos väärä", (int)(5.2*komponentti.getYNopeus()), muutaViesti.getYMuutos());
    }

    @Test
    public void lahettaaViestinJonkaArvotOikeatKunNopeusEiNollaJaEnsiksiMuutettuNopeutta() {
        LiikeViesti v = new LiikeViesti(Suunta.VASEN, true);
        v.otaVastaanVierailija(komponentti);

        MuutaNopeusViesti nv = new MuutaNopeusViesti(1, -0.5);
        nv.otaVastaanVierailija(komponentti);

        komponentti.paivita(1.0);
        MuutaPaikkaViesti muutaPaikkaViesti = (MuutaPaikkaViesti) viestiJono.poll();

        assertEquals("X-akselin muutos väärä", komponentti.getXNopeus(), muutaPaikkaViesti.getXMuutos());
        assertEquals("Y-akselin muutos väärä", komponentti.getYNopeus(), muutaPaikkaViesti.getYMuutos());
    }

    @Test
    public void eiLahetaViestiaKunNopeusNolla() {
        komponentti.asetaXNopeus(0);
        komponentti.asetaYNopeus(0);
        komponentti.paivita(1.0);
        assertEquals("Viestiä ei generoitu", 0, viestiJono.size());
    }
}
