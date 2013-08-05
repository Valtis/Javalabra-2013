package Pelilogiikka.Komponentti;

import java.awt.Graphics;
/**
 * Piirtokomponentti joka piirtää suorakaiteen
 * 
 */
public final class SuoraKaidePiirtoKomponentti extends PiirtoKomponentti {
    private int leveys;
    private int korkeus;
    
    /**
     * Konstruktori
     * <p>
     * Alustaa suorakaiteen jonka leveys ja korkeus on nolla
     */
    public SuoraKaidePiirtoKomponentti() {
        asetaUlottuvuudet(0, 0);
    }
    /**
     * Konstruktori
     * <p>
     * Alustaa suorakaiteen jolla on haluttu leveys ja korkeus
     * @param leveys Haluttu leveys
     * @param korkeus  Haluttu korkeus
     */
    public SuoraKaidePiirtoKomponentti(int leveys, int korkeus) {
        asetaUlottuvuudet(leveys, korkeus);
    }
    
            
    /**
     * Asettaa suorakaiteen leveyden ja korkeuden
     * @param leveys Haluttu leveys
     * @param korkeus Haluttu korkeus
     */
    public final void asetaUlottuvuudet(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    /**
     * Piirtää suorakaiteen annettuihin koordinaatteihin
     * @param graphics Graphics-luokan toteuttava objekti
     * @param x Haluttu x-koordinaatti
     * @param y Haluttu y-koordinaatti
     * @see Graphics
     */
    @Override
    public void piirra(Graphics graphics, int x, int y) {
       graphics.fillRect(x, y, leveys, korkeus);  
    }
}
