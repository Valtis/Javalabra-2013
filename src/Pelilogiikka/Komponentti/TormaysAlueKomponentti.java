

package Pelilogiikka.Komponentti;

/**
 * Ylläpitää tietoa törmäysalueen koosta
 * 
 */

public class TormaysAlueKomponentti extends Komponentti {
    
    private int leveys;
    private int korkeus;
    /**
     * Luo törmäysalueen jonka leveys ja korkeus on nolla
     */
    public TormaysAlueKomponentti() {
        asetaUlottuvuudet(0, 0);
    }
    /**
     * Luo törmäysalueen jolla on haluttu leveys ja korkeus
     * @param leveys Haluttu leveys
     * @param korkeus Haluttu korkeus
     */
    public TormaysAlueKomponentti(int leveys, int korkeus) {
        asetaUlottuvuudet(leveys, korkeus);
    }
    
    /**
     * Asettaa törmäysalueen leveyden ja korkeuden halutuksi
     * @param leveys Haluttu leveys
     * @param korkeus Haluttu korkeu
     */
    public final void asetaUlottuvuudet(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    /**
     * Getteri. Palauttaa törmäysalueen leveyden
     * @return Törmäysalueen leveys
     */
    public int getLeveys() {
        return leveys;
    }
    /**
     * Getteri. Palauttaa törmäysalueen korkeuden.
     * @return Törmäysalueen korkeus
     */
    public int getKorkeus() {
        return korkeus;
    }
    
}
