/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PeliLogiikka;

import Pelilogiikka.Asetukset;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.NappulaTyyppi;
import Pelilogiikka.Enumit.Reuna;
import java.util.ArrayList;
import java.util.List;
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
public class AsetuksetTest {
    private Asetukset asetukset;
    PeliMockup peli;
    private class PeliMockup implements Pelilogiikka.PeliInterface {
        
        public List<Entiteetti> entiteetit = new ArrayList<Entiteetti>();
        
        @Override
        public void lisaaEntiteetti(Entiteetti e, boolean tarvitseeNappaimistoSyotteen) {
            entiteetit.add(e);
        }

        @Override
        public void pisteyta(Reuna reuna) {
            
        }
    
    }
    public AsetuksetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        asetukset = new Asetukset();
        peli = new PeliMockup();
        asetukset.haeAsetukset(peli);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void generoiOikeanMaaranEntiteettejaAlussa() {
        
        assertEquals("Asetusten lataus generoi väärän määrän entiteettejä", 3, peli.entiteetit.size());
    }
    
    @Test
    public void generoiEntiteetinKunHalutaanStaattinenEste() {
        asetukset.nappulaViesti(NappulaTyyppi.STAATTINEN_ESTE);
        assertEquals("Ei generoinut entiteettiä kun haluttiin", 4, peli.entiteetit.size());
    }
    
    @Test
    public void generoiEntiteetinKunHalutaanKimpoilevaEste() {
        asetukset.nappulaViesti(NappulaTyyppi.STAATTINEN_ESTE);
        assertEquals("Ei generoinut entiteettiä kun haluttiin", 4, peli.entiteetit.size());
    }
    
   
}
