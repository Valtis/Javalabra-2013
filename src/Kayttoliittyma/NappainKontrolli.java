

package Kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka joka kuuntelee KeyEventtejä ja muuttaa ne omiksi näppäinkoodeiksi
 * <p>
 * Luokka olemassa yksikkötestauksen helpottamiseksi; inputkontrolli ei riipu KeyEventeistä
 */
public class NappainKontrolli implements KeyListener {
    private List<NappainKuuntelija> kuuntelijat;
    /**
     * Konstruktori. Alustaa sisäiset tietorakenteet
     */
    public NappainKontrolli() {
        kuuntelijat = new ArrayList<NappainKuuntelija>();
    }
    /**
     * Lisää kuuntelijan kontrolliin
     * @param kuuntelija Lisättävä kuuntelijan
     */
    void lisaaKuuntelija(NappainKuuntelija kuuntelija) {
        kuuntelijat.add(kuuntelija);
    }
    /**
     * Käsittelee keyTyped-eventin. Ei käytössä
     * @param e KeyEvent
     * @see KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // ei reagoida
    }
    /**
     * Käsittelee keyPressed-eventin. Kutsuu kuuntelijoidensa nappainPainettu()-metodia keyCoden arvolla
     * @param e KeyEvent
     * @see KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        for (NappainKuuntelija kuuntelija : kuuntelijat) {
            kuuntelija.nappainPainettu(e.getKeyCode());
        }
    }
    
    /**
     * Käsittelee keyReleased-eventin. Kutsuu kuuntelijoidensa nappainPainettu()-metodia keyCoden arvolla
     * @param e KeyEvent
     * @see KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        for (NappainKuuntelija kuuntelija : kuuntelijat) {
            kuuntelija.nappainVapautettu(e.getKeyCode());
        }
    }
}
