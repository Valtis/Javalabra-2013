
package Pelilogiikka;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.Reuna;
public interface PeliInterface extends PisteKuuntelija {

    /**
     * Lisää entiteetin peliin.
     *
     * @param e Lisättävä entiteetti
     * @param tarvitseeNappaimistoSyotteen Tarvitseeko entiteetti
     * näppäimistösyötettä
     */
    void lisaaEntiteetti(Entiteetti e, boolean tarvitseeNappaimistoSyotteen);

    /**
     * Antaa pelaajille pisteitä riippuen mihin reunaan osuttiin
     * @param reuna Reuna johonka pallo osui
     */
    void pisteyta(Reuna reuna);
    
}
