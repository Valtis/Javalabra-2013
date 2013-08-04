

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Entiteetti.Entiteetti;


public class TormaysEntiteettiinViesti implements Viesti {
    private final Entiteetti TORMATTY;
    private final double TORMAYS_PISTE;
    public TormaysEntiteettiinViesti(Entiteetti tormatty, double tormaysPiste) {
        TORMATTY = tormatty; 
        TORMAYS_PISTE = tormaysPiste;
    }
    
    public Entiteetti getTormaaja() {
        return TORMATTY;
    }
    
    public double getTormaysPiste() {
        return TORMAYS_PISTE;
    }
    
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
