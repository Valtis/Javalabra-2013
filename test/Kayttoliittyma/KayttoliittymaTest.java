/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Pelilogiikka.Asetukset;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPiirtoKomponentti;
import Pelilogiikka.Komponentti.PiirtoKomponentti;
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
public class KayttoliittymaTest {
    private KayttoLiittyma liittyma;  
    
    public KayttoliittymaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        liittyma = new KayttoLiittyma();
        
        liittyma.alusta(new Asetukset(), 800, 600);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected=NullPointerException.class)
    public void nullEntiteettiHeittaaNullPointerExceptionin() {
        
        liittyma.lisaaPiirrettava(null);
    }
    
    @Test
    public void entiteettiaEiLisataPiirrettavaksiJosEntiteetillaEiOleKomponetteja() {
        Entiteetti e = new Entiteetti();
        assertFalse("Lisättiin komponentti piirrettäväksi jolla ei ole komponentteja", liittyma.lisaaPiirrettava(e));
    }
    
    @Test
    public void entiteettiaEiLisataPiirrettavaksiJosEntiteetillaEiOlePiirtoKomponettia() {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PaikkaKomponentti());
        assertFalse("Lisättiin komponentti piirrettäväksi jolla ei ole komponentteja", liittyma.lisaaPiirrettava(e));
    }
    
    @Test
    public void entiteettiaEiLisataPiirrettavaksiJosEntiteetillaEiOlePaikkaKomponettia() {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(10));
        assertFalse("Lisättiin komponentti piirrettäväksi jolla ei ole komponentteja", liittyma.lisaaPiirrettava(e));
    }
    
    @Test
    public void entiteettiLisataanPiirrettavaksiJosOnKomponentit() {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(10));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PaikkaKomponentti());
        assertTrue("Entiteettiä ei lisätty piirettäväksi vaikka olisi pitänyt", liittyma.lisaaPiirrettava(e));
    }
    
    @Test(expected=ClassCastException.class)
    public void heittaaClassCastExceptioninJosVaaraPiirtoKomponentinTyyppi() {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PaikkaKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PaikkaKomponentti());
        assertFalse("Lisättiin komponentti piirrettäväksi jolla ei ole komponentteja", liittyma.lisaaPiirrettava(e));
    }
    
    @Test(expected=ClassCastException.class)
    public void heittaaClassCastExceptioninJosVaaraPaikkaKomponentinTyyppi() {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(10));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PalloPiirtoKomponentti(10));
        assertFalse("Lisättiin komponentti piirrettäväksi jolla ei ole komponentteja", liittyma.lisaaPiirrettava(e));
    }
    
    @Test(expected=NullPointerException.class)
    public void heittaaNullPointerExeptioninJosYritetaanLisataVaaraKuuntelija() {
        liittyma.lisaaNappainKuuntelija(null);
    }
    
    
  
}
