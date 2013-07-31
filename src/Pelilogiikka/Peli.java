package Pelilogiikka;

import Kayttoliittyma.Peliruutu;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.InputKomponentti;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Peli {
    private Peliruutu liittyma;
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
        liittyma = new Peliruutu();
        liittyma.alusta();
    }
   
    private void asetaKuuntelija(Entiteetti e) throws NullPointerException, ClassCastException {
        InputKomponentti input = (InputKomponentti)e.getKomponentti(KomponenttiTyyppi.INPUT);
        liittyma.lisaaNappainKuuntelija(input);
    }

    
    public void lisaaEntiteetti(Entiteetti e, boolean tarvitseeNappaimistoSyotteen) {
        
        liittyma.lisaaPiirrettava(e);
        tormaysManageri.lisaaTormaaja(e);
        
        if (tarvitseeNappaimistoSyotteen) {
            asetaKuuntelija(e);
        }
        
        entiteetit.add(e);
    }
    
    
     
    public void pelaa() {
        // kutsuttava ennen asetuset asettelua jotta voidaan lisätä keylistenerit ym.
        
        Asetukset asetus = new Asetukset();
        alustaUI();
        
        asetus.haeAsetukset(this);
                
        SwingUtilities.invokeLater(liittyma);
        
        
        tormaysManageri.asetaAlueenKoko(liittyma.peliAlueenLeveys(), liittyma.peliAlueenKorkeus());
                
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
            tormaysManageri.tarkistaTormaykset();
        }
    }
    
    private void paivitaEntiteetit(double ticks) {
        for (Entiteetti e : entiteetit) {
            e.paivita(ticks);
        }
    }



 
}
