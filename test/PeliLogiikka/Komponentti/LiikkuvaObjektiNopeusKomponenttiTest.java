
package PeliLogiikka.Komponentti;

import Pelilogiikka.Komponentti.LiikkuvaObjektiNopeusKomponentti;
import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
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


public class LiikkuvaObjektiNopeusKomponenttiTest {

    private LiikkuvaObjektiNopeusKomponentti komponentti;
    private ViestiJonoMockup viestiJono;
    private int maksimiNopeus;

    public LiikkuvaObjektiNopeusKomponenttiTest() {
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
        maksimiNopeus = Math.abs(random.nextInt()) % 10000;
        komponentti = new LiikkuvaObjektiNopeusKomponentti(maksimiNopeus);
        viestiJono = new ViestiJonoMockup();
        komponentti.lisaaViestijono(viestiJono);
    }

    @After
    public void tearDown() {
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
    // nopeudet voivat olla samat alustuksen jälkeen

    /* @Test
     public void nopeusAlustusViestiMuuttaaNopeutta() {
     int vanhaX = komponentti.getXNopeus();
     int vanhaY = komponentti.getYNopeus();

     AlustaNopeusViesti v = new AlustaNopeusViesti();
     v.otaVastaanVierailija(komponentti);
     

     assertFalse("X-Nopeus väärä muutoksen jälkeen", vanhaX == komponentti.getXNopeus());
     assertEquals("Y-Nopeus väärä muutoksen jälkeen", vanhaY == komponentti.getYNopeus());
     }*/
    @Test
    public void lahettaaViestinKunNopeusEiNolla() {
        komponentti.paivita(1.0);
        assertEquals("Viestiä ei generoitu", 1, viestiJono.jono.size());
    }

    @Test
    public void lahettaaViestinJonkaArvotOikeatKunNopeusEiNolla() {
        komponentti.paivita(1.0);
        MuutaPaikkaViesti v = (MuutaPaikkaViesti) viestiJono.jono.poll();

        assertEquals("X-akselin muutos väärä", v.getXMuutos(), komponentti.getXNopeus());
        assertEquals("Y-akselin muutos väärä", v.getYMuutos(), komponentti.getYNopeus());
    }
    
    @Test
    public void lahettaaViestinJonkaArvotOikeatKunNopeusEiNollaJaPeliAskelVenahtanyt() {
        komponentti.paivita(3.5);
        MuutaPaikkaViesti v = (MuutaPaikkaViesti) viestiJono.jono.poll();

        assertEquals("X-akselin muutos väärä", (int)(3.5*komponentti.getXNopeus()), v.getXMuutos());
        assertEquals("Y-akselin muutos väärä", (int)(3.5*komponentti.getYNopeus()), v.getYMuutos());
    }
    
    @Test
    public void lahettaaViestinJonkaArvotOikeatKunNopeusEiNollaJaEnsiksiMuutettuNopeutta() {
        
        MuutaNopeusViesti nv = new MuutaNopeusViesti(1, -0.5);
        nv.otaVastaanVierailija(komponentti);
        
        komponentti.paivita(1.0);
        MuutaPaikkaViesti v = (MuutaPaikkaViesti) viestiJono.jono.poll();

        assertEquals("X-akselin muutos väärä", komponentti.getXNopeus(), v.getXMuutos());
        assertEquals("Y-akselin muutos väärä", komponentti.getYNopeus(), v.getYMuutos());
    }
    
    

    @Test
    public void eiLahetaViestiaKunNopeusNolla() {
        komponentti.asetaXNopeus(0);
        komponentti.asetaYNopeus(0);
        komponentti.paivita(1.0);
        assertEquals("Viestiä ei generoitu", 0, viestiJono.jono.size());
    }
    
}
