
package Kayttoliittyma;

import Pelilogiikka.Enumit.NappulaTyyppi;

/**
 * Rajapinta joka määrittää nappulaViesti-metodin eri nappuloiden painamisen kuuntelua varten
 */
public interface NappulaKuuntelija {
    public void nappulaViesti(NappulaTyyppi tyyppi);
}
