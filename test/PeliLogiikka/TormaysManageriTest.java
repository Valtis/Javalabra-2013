
package PeliLogiikka;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.EntiteettiTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.KimpoaSeinastaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.TormaysAlueKomponentti;
import Pelilogiikka.TormaysManageri;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TormaysManageriTest {
    private TormaysManageri manageri;
    
    private Entiteetti ensimmainen;
    private PaikkaKomponentti ensimmaisenPaikka;
    
    private Entiteetti toinen;
    private PaikkaKomponentti toisenPaikka;
    public TormaysManageriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        manageri = new TormaysManageri();
        manageri.asetaAlueenKoko(400, 400);
        
        ensimmainen = new Entiteetti();
        toinen = new Entiteetti();
        
        ensimmaisenPaikka = new PaikkaKomponentti(200, 200);
        toisenPaikka = new PaikkaKomponentti(200, 200);
        
        ensimmainen.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, ensimmaisenPaikka);
        ensimmainen.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(100, 100));
        ensimmainen.lisaaKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA, new KimpoaSeinastaTormaysKasittelijaKomponentti());
        ensimmainen.lisaaKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA, new EntiteettiTormaysKasittelijaKomponentti());
        
        toinen.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, toisenPaikka);
        toinen.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(100, 100));
        toinen.lisaaKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA, new KimpoaSeinastaTormaysKasittelijaKomponentti());
        toinen.lisaaKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA, new EntiteettiTormaysKasittelijaKomponentti());
        
        manageri.lisaaTormaaja(ensimmainen);
        manageri.lisaaTormaaja(toinen);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test 
    public void manageriEiLisaaEntiteettiaJollaEiOleTormaajaa() {
       assertFalse("Manageri lisäsi virheellisesti entiteetin jolla ei ole törmäysaluekomponenttia", manageri.lisaaTormaaja(new Entiteetti()));
    }
    
    @Test 
    public void manageriGeneroiViestinKunEntiteettiTormaaYlaReunaan() {
       ensimmaisenPaikka.asetaPaikka(10, -10);
       manageri.tarkistaTormaykset();
      
       
       assertEquals("Manageri ei generoinut törmäysviestiä", 1, ensimmainen.getViestiJono().size());
    }
    
    @Test 
    public void manageriGeneroiViestinKunEntiteettiTormaaVasempaanReunaan() {
       ensimmaisenPaikka.asetaPaikka(-10, 10);
       manageri.tarkistaTormaykset();
     
       assertEquals("Manageri ei generoinut törmäysviestiä", 1, ensimmainen.getViestiJono().size());
    }
    
    @Test 
    public void manageriGeneroiViestinKunEntiteettiTormaaAlaReunaan() {
       ensimmaisenPaikka.asetaPaikka(10, 350);
       manageri.tarkistaTormaykset();
     
       assertEquals("Manageri ei generoinut törmäysviestiä", 1, ensimmainen.getViestiJono().size());
    }
    
    @Test 
    public void manageriGeneroiViestinKunEntiteettiTormaaOikeaanReunaan() {
       ensimmaisenPaikka.asetaPaikka(350, 10);
       manageri.tarkistaTormaykset();
     
       assertEquals("Manageri ei generoinut törmäysviestiä", 1, ensimmainen.getViestiJono().size());
    }
    
    @Test 
    public void manageriGeneroiViestinKunEntiteetitTormaavat() {
       ensimmaisenPaikka.asetaPaikka(350, 10);
       toisenPaikka.asetaPaikka(300, 10);
       manageri.tarkistaTormaykset();
     
       assertEquals("Manageri ei generoinut törmäysviestiä ensimmäiselle", 1, ensimmainen.getViestiJono().size());
       assertEquals("Manageri ei generoinut törmäysviestiä toiselle", 1, toinen.getViestiJono().size());
       
    }
    
    @Test 
    public void manageriEiGeneroiViestinKunEntiteetitEivatTormaa() {
       ensimmaisenPaikka.asetaPaikka(10, 10);
       toisenPaikka.asetaPaikka(290, 290);
       manageri.tarkistaTormaykset();
     
       assertEquals("Manageri generoi virheellisesti viestin ensimmäiselle", 0, ensimmainen.getViestiJono().size());
       assertEquals("Manageri ei generoinut viestin virheellisesti toiselle", 0, toinen.getViestiJono().size());
       
    }
    
}
