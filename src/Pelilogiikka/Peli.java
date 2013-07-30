package Pelilogiikka;

import Kayttoliittyma.Kayttoliittyma;
import Pelilogiikka.Entiteetti.Entiteetti;
import java.util.ArrayList;
import java.util.List;

public class Peli {
    private Kayttoliittyma liittyma;
    private Thread liittymaThread;
    private List<Entiteetti> entiteetit;
    
    private final long TICK = 33*1000000; // 33 millisekuntia
    private long nykyinenAika;

    public Peli() {
        entiteetit = new ArrayList<Entiteetti>(); 
        nykyinenAika = System.nanoTime();
    }

    private void alustaUI() {
        liittyma = new Kayttoliittyma();
        liittymaThread = new Thread(liittyma);
        liittymaThread.start();
    }
    
    private void alustaEntiteetit() {
       
    }

    public void pelaa() {
        alustaUI();
        alustaEntiteetit();
        
        while (liittymaThread.isAlive()) {
            paivitaPeliLogiikka();
        }
    }
    
    private void paivitaPeliLogiikka() {
        long uusiAika = System.nanoTime();
        if (nykyinenAika + TICK < uusiAika) {
            long delta = uusiAika - nykyinenAika;
            nykyinenAika = uusiAika;
            
            paivitaEntiteetit(delta);
            
        }
    }
    
    private void paivitaEntiteetit(long delta) {
        for (Entiteetti e : entiteetit) {
            e.paivita(delta);
        }
    }
}
