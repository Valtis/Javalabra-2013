

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Komponentti;


public class LiikeViesti implements Viesti {
    private final boolean ALOITA_LIIKE;
    private final Suunta SUUNTA;
    
    public LiikeViesti(Suunta s, boolean aloitaLiike) {
        SUUNTA = s;
        this.ALOITA_LIIKE = aloitaLiike;
    }
    
    public Suunta getSuunta() {
        return SUUNTA;
    }
    
    public boolean aloitaLiike() {
        return ALOITA_LIIKE;
    }
    
    public boolean pysaytaLiike() {
        return !ALOITA_LIIKE;
    }
            
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
