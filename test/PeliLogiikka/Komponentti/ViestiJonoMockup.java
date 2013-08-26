

package PeliLogiikka.Komponentti;

import Pelilogiikka.Entiteetti.ViestiJonoInterface;
import Pelilogiikka.Komponentti.Viestit.Viesti;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Mockup-luokka testejä varten
 * 
 */
public class ViestiJonoMockup implements ViestiJonoInterface {
    public Queue<Viesti> jono = new LinkedList<Viesti>();
    public Queue<Viesti> valittomastiKasiteltavat = new LinkedList<Viesti>();
    
    
    @Override
    public void lisaaViesti(Viesti v) {
        jono.add(v);
    }

    @Override
    public void kasitteleValittomastiViesti(Viesti v) {
        valittomastiKasiteltavat.add(v); 
    }

}
