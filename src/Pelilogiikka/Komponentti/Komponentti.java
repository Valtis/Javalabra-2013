

package Pelilogiikka.Komponentti;


import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.PalloNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.PelaajaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
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
    
    public void vieraile(PelaajaNopeusViesti viesti) {
    
    }
    
    public void vieraile(PalloNopeusViesti viesti) {
    
    }
    
    
    public void vieraile(TormaysReunaanViesti viesti) {
    
    }
    
    

}
