

package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Komponentti;


public class LiikeViesti implements Viesti {
    private boolean aloitaLiike;
    private Suunta suunta;
    
    public LiikeViesti(Suunta s, boolean aloitaLiike) {
        suunta = s;
        this.aloitaLiike = aloitaLiike;
    }
    
    public Suunta getSuunta() {
        return suunta;
    }
    
    public boolean aloitaLiike() {
        return aloitaLiike;
    }
    
    public boolean pysaytaLiike() {
        return !aloitaLiike;
    }
            
    @Override
    public void otaVastaanVierailija(Komponentti k) {
        k.vieraile(this);
    }

}
