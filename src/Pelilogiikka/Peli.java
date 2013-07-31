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
    
    private List<Entiteetti> entiteetit;
    
    private final long TICK = 33*1000000; // 33 millisekuntia
    private long nykyinenAika;

    public Peli() {
        entiteetit = new ArrayList<Entiteetti>(); 
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
       
      
    }
    
    // todo - pilko pienemmäksi
    private Entiteetti luoPelaaja(Suunta ensimmainenSuunta, int ensimmaisenSuunnanNappain, Suunta toinenSuunta, int toisenSuunnanNappain, int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti pelaaja = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, x, y);
        
        InputKomponentti input = (InputKomponentti)pelaaja.getKomponentti(KomponenttiTyyppi.INPUT);
        input.asetaNappain(ensimmaisenSuunnanNappain, ensimmainenSuunta);
        input.asetaNappain(toisenSuunnanNappain, toinenSuunta);
        
        liittyma.lisaaNappainKuuntelija(input);

        liittyma.lisaaPiirrettava(pelaaja);
        return pelaaja;
    }

    public void pelaa() {
        alustaUI();
        alustaEntiteetit();
        
        kaynnistaUI();
        while (liittyma.onNakyvilla()) {
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
            
        }
    }
    
    private void paivitaEntiteetit(double ticks) {
        for (Entiteetti e : entiteetit) {
            e.paivita(ticks);
        }
    }

 
}
