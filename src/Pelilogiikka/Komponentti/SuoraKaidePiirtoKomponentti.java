package Pelilogiikka.Komponentti;

import java.awt.Graphics;

public class SuoraKaidePiirtoKomponentti extends PiirtoKomponentti {
    private int leveys;
    private int korkeus;
    
    public SuoraKaidePiirtoKomponentti() {
        asetaUlottuvuudet(0, 0);
    }
    
    public SuoraKaidePiirtoKomponentti(int leveys, int korkeus) {
        asetaUlottuvuudet(leveys, korkeus);
    }
    
            
    
    public final void asetaUlottuvuudet(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    @Override
    public void piirra(Graphics graphics, int x, int y) {
       graphics.fillRect(x, y, leveys, korkeus); 
    }
}
