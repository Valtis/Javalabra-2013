

package Pelilogiikka.Komponentti;

// törmäyskomponentti on suorakaide
// pallolle luodaan suorakaide joka on pienempi kuin itse pallo jotta pallo ei jäisi kulmista kiinni; hieman epätarkka mutta helppo toteuttaa

public class TormaysKomponentti extends Komponentti {
    
    private int leveys;
    private int korkeus;
    
    public TormaysKomponentti() {
        asetaUlottuvuudet(0, 0);
    }
    
    public TormaysKomponentti(int leveys, int korkeus) {
        asetaUlottuvuudet(leveys, korkeus);
    }
    
    public final void asetaUlottuvuudet(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    public int getLeveys() {
        return leveys;
    }
    
    public int getKorkeus() {
        return korkeus;
    }
    
}
