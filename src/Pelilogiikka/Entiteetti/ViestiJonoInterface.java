/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelilogiikka.Entiteetti;

import Pelilogiikka.Komponentti.Viestit.Viesti;

/**
 *
 * @author Omistaja
 */
public interface ViestiJonoInterface {
    
    public void lisaaViesti(Viesti v);  
    public void kasitteleValittomastiViesti(Viesti v);
}
