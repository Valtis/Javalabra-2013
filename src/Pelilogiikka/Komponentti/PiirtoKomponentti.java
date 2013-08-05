

package Pelilogiikka.Komponentti;

import java.awt.Graphics;

/**
 * Abstrakti kantaluokka piirtokomponenteille.
 * @author Omistaja
 */
public abstract class PiirtoKomponentti extends Komponentti {
    /**
     * Abstrakti metodi piirtämiselle. Perivä komponentti toteuttaa tämän
     * @param graphics Graphics-luokan toteuttava objekti
     * @param x x-koordinaatti johonka halutaan piirtää
     * @param y y-koordinaatti johonka halutaan piirtää.
     * @see Graphics
     */
    public abstract void piirra(Graphics graphics, int x, int y);
}
