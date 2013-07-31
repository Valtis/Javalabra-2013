

package Pelilogiikka.Komponentti;


import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.NopeusViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.Queue;


public abstract class Komponentti {
    
    protected Queue<Viesti> viestit;
    
    public void lisaaViestijono(Queue<Viesti> viestit) {
        this.viestit = viestit;
    }
    
    public void paivita(double ticks) {
    
    }
    
    public void vieraile(LiikeViesti viesti) {
    
    }
    
    public void vieraile(NopeusViesti viesti) {
    
    }
    

}
