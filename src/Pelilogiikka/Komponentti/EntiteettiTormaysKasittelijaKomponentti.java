package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;

public class EntiteettiTormaysKasittelijaKomponentti extends Komponentti {

    private final int TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA = 10;
    private int tormaysLaskuri = 0;

    @Override
    public void paivita(double ticks) {
        if (tormaysLaskuri > 0) {
            --tormaysLaskuri;
        }
    }

    /**
     * Käsittelee viestin joka ilmoittaa että on törmätty reunaan.
     * <br>
     * Jos on törmätty seinän kanssa, jätetään entiteettitörmäykset hetkiksi huomioimatta; muuten voi jäädä reunaan jumiin
     * @param viesti TörmäysReunaanViesti 
     * @see TormaysReunaanViesti
     */
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
    }

    @Override
    public void vieraile(TormaysEntiteettiinViesti viesti) {
        // annetaan törmäyksen jälkeen objektille hieman aikaa irtaantua
        if (tormaysLaskuri > 0) {
            return;
        }
        
        double xSuunta = 1;
        double ySuunta = 1;
        
        switch (viesti.getTormaysReuna()) {
            case YLA:
            case ALA:
                ySuunta = -1;
                break;
            case VASEN:
            case OIKEA:
                xSuunta = -1;
                break;
        }
        
        
        
        
        tormaysLaskuri = TORMAYS_HUOMIOIMATTA_JATTAMIS_AIKA;
        viestit.add(new MuutaNopeusViesti(xSuunta, ySuunta));
    }
}
