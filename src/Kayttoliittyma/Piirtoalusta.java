

package Kayttoliittyma;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PiirtoKomponentti;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class Piirtoalusta extends JPanel {
    
    private List<Entiteetti> piirrettavat;
    public Piirtoalusta() {
        super.setBackground(Color.WHITE);
        piirrettavat = new ArrayList<Entiteetti>();
    
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
        for (Entiteetti e : piirrettavat) {
            piirraEntiteetti(graphics, e);
        }
    }
    
    private void piirraEntiteetti(Graphics graphics, Entiteetti e) {
        PaikkaKomponentti paikka = (PaikkaKomponentti)e.getKomponentti(KomponenttiTyyppi.PAIKKA);
        PiirtoKomponentti piirto = (PiirtoKomponentti)e.getKomponentti(KomponenttiTyyppi.PIIRTO);
        
        piirto.piirra(graphics, paikka.getX(), paikka.getY());
    }

    
    
    void lisaaPiirrettava(Entiteetti piirettavaEntiteetti) throws ClassCastException, NullPointerException {
        if ((PiirtoKomponentti)piirettavaEntiteetti.getKomponentti(KomponenttiTyyppi.PIIRTO) == null) {
            throw new NullPointerException("Entiteetillä ei ole piirtokomponenttia metodissa Piirtoalusta.lisaaPiirrettava()!");
        }
        
        if ((PaikkaKomponentti)piirettavaEntiteetti.getKomponentti(KomponenttiTyyppi.PAIKKA) == null) {
            throw new NullPointerException("Entiteetillä ei ole paikkakomponenttia metodissa Piirtoalusta.lisaaPiirrettava()!");
        }
        
        piirrettavat.add(piirettavaEntiteetti);
    }
}

