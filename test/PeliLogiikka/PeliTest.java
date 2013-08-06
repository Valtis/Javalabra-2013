/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PeliLogiikka;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.LiikkuvaObjektiNopeusKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPiirtoKomponentti;
import Pelilogiikka.Peli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Omistaja
 */
public class PeliTest {

    Peli peli;

    public PeliTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        peli = new Peli();
    }

    @After
    public void tearDown() {
    }

    @Test(expected=NullPointerException.class)
    public void entiteetinLisaysHeittaaNullPointerExceptionJosLisataanNullEntiteetti() {
        peli.lisaaEntiteetti(null, false);
    }
    
    @Test(expected=NullPointerException.class)
    public void entiteetinLisaysHeittaaNullPointerExceptioninJosSyotekomponettiOnNullJaHalutaanSyote() {
        Entiteetti e = new Entiteetti();
        peli.lisaaEntiteetti(null, true);
    }
    
    @Test 
    public void pelaajan1PisteetKasvaaKunOsutaanYlalaitaan() {
        peli.pisteyta(Reuna.YLA);
        assertEquals("Pelaajan 1 pisteet väärin", 1, peli.getPelaajan1Pisteet());
    }
    
    @Test 
    public void pelaajan1PisteetEiMuutuKunOsutaanVasempaanLaitaan() {
        peli.pisteyta(Reuna.VASEN);
        assertEquals("Pelaajan 1 pisteet väärin", 0, peli.getPelaajan1Pisteet());
    }
    
    @Test 
    public void pelaajan1PisteetEiMuutuKunOsutaanOikeaanLaitaan() {
        peli.pisteyta(Reuna.OIKEA);
        assertEquals("Pelaajan 1 pisteet väärin", 0, peli.getPelaajan1Pisteet());
    }
    
    @Test 
    public void pelaajan1PisteetEiMuutuKunOsutaanAlaLaitaan() {
        peli.pisteyta(Reuna.ALA);
        assertEquals("Pelaajan 1 pisteet väärin", 0, peli.getPelaajan1Pisteet());
    }
    
    @Test 
    public void pelaajan2PisteetEiMuutuKunOsutaanYlalaitaan() {
        peli.pisteyta(Reuna.YLA);
        assertEquals("Pelaajan 2 pisteet väärin", 0, peli.getPelaajan2Pisteet());
    }
    
    @Test 
    public void pelaajan2PisteetEiMuutuKunOsutaanVasempaanLaitaan() {
        peli.pisteyta(Reuna.VASEN);
        assertEquals("Pelaajan 2 pisteet väärin", 0, peli.getPelaajan2Pisteet());
    }
    
    @Test 
    public void pelaajan2PisteetEiMuutuKunOsutaanOikeaanLaitaan() {
        peli.pisteyta(Reuna.OIKEA);
        assertEquals("Pelaajan 2 pisteet väärin", 0, peli.getPelaajan2Pisteet());
    }
    
    @Test 
    public void pelaajan2PisteetMuuttuuKunOsutaanAlaLaitaan() {
        peli.pisteyta(Reuna.ALA);
        assertEquals("Pelaajan 2 pisteet väärin", 1, peli.getPelaajan2Pisteet());
    }
}
