package Pelilogiikka;

import Kayttoliittyma.Kayttoliittyma;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Entiteetti.EntiteettiTehdas;
import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.InputKomponentti;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Peli {
    private Kayttoliittyma liittyma;
    private TormaysManageri tormaysManageri;
    private List<Entiteetti> entiteetit;
    
    private final long TICK = 33*1000000; // 33 millisekuntia
    private long nykyinenAika;

    public Peli() {
        entiteetit = new ArrayList<Entiteetti>(); 
        tormaysManageri = new TormaysManageri();
        nykyinenAika = System.nanoTime();    
    }

    private void alustaUI() {
        liittyma = new Kayttoliittyma();
        liittyma.alusta();
     
    }
    
    private void kaynnistaUI() {
        liittyma.run();
    }
    
    private void alustaEntiteetit() {
       entiteetit.add(luoPelaaja(Suunta.VASEN, KeyEvent.VK_A, Suunta.OIKEA, KeyEvent.VK_D, 250, 10));
       entiteetit.add(luoPelaaja(Suunta.VASEN, KeyEvent.VK_LEFT, Suunta.OIKEA, KeyEvent.VK_RIGHT, 250, 530));
       entiteetit.add(luoPallo(800/2 - 10, 600/2 - 10));
       
      
    }
    
    private Entiteetti luoPelaaja(Suunta ensimmainenSuunta, int ensimmaisenSuunnanNappain, Suunta toinenSuunta, int toisenSuunnanNappain, int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti pelaaja = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, x, y);
    
        alustaPelaajanInput(pelaaja, ensimmaisenSuunnanNappain, ensimmainenSuunta, toisenSuunnanNappain, toinenSuunta);
        liittyma.lisaaPiirrettava(pelaaja);
        tormaysManageri.lisaaTormaaja(pelaaja);
        return pelaaja;
    }
    
    private Entiteetti luoPallo(int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti pallo = tehdas.luoEntiteetti(EntiteettiTyyppi.PALLO, x, y);
    
        liittyma.lisaaPiirrettava(pallo);
        tormaysManageri.lisaaTormaaja(pallo);
        return pallo;
    }
    
    private void alustaPelaajanInput(Entiteetti pelaaja, int ensimmaisenSuunnanNappain, Suunta ensimmainenSuunta, int toisenSuunnanNappain, Suunta toinenSuunta) throws NullPointerException, ClassCastException {
        InputKomponentti input = (InputKomponentti)pelaaja.getKomponentti(KomponenttiTyyppi.INPUT);
        input.asetaNappain(ensimmaisenSuunnanNappain, ensimmainenSuunta);
        input.asetaNappain(toisenSuunnanNappain, toinenSuunta);
        liittyma.lisaaNappainKuuntelija(input);
    }

    public void pelaa() {
        alustaUI();
        alustaEntiteetit();
        
        kaynnistaUI();
        
        
        while (liittyma.onNakyvilla()) {
            // ikkunan koko mahdollisesti muuttunut
            tormaysManageri.asetaAlueenKoko(liittyma.peliAlueenLeveys(), liittyma.peliAlueenKorkeus());
            paivitaPeliLogiikka();
            liittyma.piirra();
        }
    }
    
    private void paivitaPeliLogiikka() {
        long uusiAika = System.nanoTime();
        if (nykyinenAika + TICK < uusiAika) {
            long delta = uusiAika - nykyinenAika;
            nykyinenAika = uusiAika;
            
            paivitaEntiteetit((double)delta/(double)TICK);
            tormaysManageri.tarkistaTormaykset();
        }
    }
    
    private void paivitaEntiteetit(double ticks) {
        for (Entiteetti e : entiteetit) {
            e.paivita(ticks);
        }
    }



 
}
