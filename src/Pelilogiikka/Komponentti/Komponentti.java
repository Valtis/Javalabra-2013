

package Pelilogiikka.Komponentti;


import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.NopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import Pelilogiikka.Komponentti.Viestit.ViestiVierailija;
import java.util.Queue;


public abstract class Komponentti implements ViestiVierailija {
    
    protected Queue<Viesti> viestit;
    
    public void lisaaViestijono(Queue<Viesti> viestit) {
        this.viestit = viestit;
    }
    
    public void paivita(double ticks) {
    
    }
    
    @Override
    public void vieraile(LiikeViesti viesti) {
    
    }
    
    @Override
    public void vieraile(NopeusViesti viesti) {
    
    }
    
    
    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
    
    }
    
    @Override
    public void vieraile(TormaysEntiteettiinViesti viesti) {
    
    }
    
   
    @Override
    public void vieraile(AlustaNopeusViesti viesti) {
    
    }


}
