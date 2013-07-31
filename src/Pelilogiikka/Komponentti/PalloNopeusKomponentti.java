

package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.PalloNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import java.util.Random;

/*
 Mahdollisesti hienostuneempi mekanismi nopeudelle? maxnopeus vektoriksi -> xnopeus^2 + yNopeus^2 = maxNopeus^2 ?
 * Törmäystarkistus muokkaisi nopeuden kulmaa riippuen mihin kohtaan osutaan mailaa
 */

public class PalloNopeusKomponentti extends Komponentti {
    private int xNopeus;
    private int yNopeus;
    
    private final int MAX_NOPEUS;
    
    public PalloNopeusKomponentti(int maxNopeus) {
        MAX_NOPEUS = maxNopeus;
        Random random = new Random();
        // asetetaan satunnainen suunta
        xNopeus = MAX_NOPEUS * (int)Math.pow(-1, random.nextInt() % 2);
        yNopeus = MAX_NOPEUS * (int)Math.pow(-1, random.nextInt() % 2);
    }
    
    @Override 
    public void paivita(double ticks) {
        viestit.add(new PalloNopeusViesti(xNopeus, yNopeus));
    }
    
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        switch (viesti.getReuna()) {
            case YLA:
            case ALA:
                yNopeus = -yNopeus;
                break;
            case VASEN:
            case OIKEA:
                xNopeus = -xNopeus;

        }
    }
    
    @Override
    public void vieraile(TormaysEntiteettiinViesti viesti) {
       yNopeus = -yNopeus;
    }
    
    
}
