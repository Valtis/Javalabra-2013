

package Pelilogiikka.Komponentti;

// törmäyskomponentti on suorakaide
// pallolle luodaan suorakaide joka on pienempi kuin itse pallo jotta pallo ei jäisi kulmista kiinni; hieman epätarkka mutta helppo toteuttaa

public class TormaysAlueKomponentti extends Komponentti {
    
    private int leveys;
    private int korkeus;
    
    public TormaysAlueKomponentti() {
        asetaUlottuvuudet(0, 0);
    }
    
    public TormaysAlueKomponentti(int leveys, int korkeus) {
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
