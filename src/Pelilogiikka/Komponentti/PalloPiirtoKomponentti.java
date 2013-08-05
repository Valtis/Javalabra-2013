

package Pelilogiikka.Komponentti;

import java.awt.Graphics;

/**
 * Piirtokomponentti joka piirtää pallon
 * 
 */
public final class PalloPiirtoKomponentti extends PiirtoKomponentti {
    private int halkaisija;
    
    /**
     * Alustaa piirtokomponentin jonka halkaisija on nolla
     */
    public PalloPiirtoKomponentti() {
        asetaHalkaisija(0);
    }
    /**
     * Alustaa pallon jolla on haluttu halkaisija
     * @param halkaisija Pallon halkaisija
     */
    public PalloPiirtoKomponentti(int halkaisija) {
        asetaHalkaisija(halkaisija);
    }
    /**
     * Asettaa halkaisijan halutuksi
     * @param halkaisija Pallon halkaisija
     */
    public final void asetaHalkaisija(int halkaisija) {
        this.halkaisija = halkaisija;
    }
    /**
     * Palauttaa pallon halkaisijan
     * @return Pallon halkaisija
     */
    public int getHalkaisija() {
        return halkaisija;
    }
    /**
     * Piirtää pallon haluttuihin koordinaatteihin
     * @param graphics Graphics-luokan toteuttava objekti
     * @param x haluttu x-koordinaatti
     * @param y haluttu y-koordinaatti
     * @see Graphics
     */
    @Override
    public void piirra(Graphics graphics, int x, int y) {
       graphics.fillOval(x, y, halkaisija, halkaisija);  
    }
}
