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

    private final Object LUKKO;
    private List<Entiteetti> piirrettavat;

    private int pelaajan1Pisteet;
    private int pelaajan2Pisteet;
    
    public Piirtoalusta() {
        super.setBackground(Color.WHITE);
        LUKKO = new Object();
        piirrettavat = new ArrayList<Entiteetti>();
    }

    @Override
    protected void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        graphics.drawString("Pelaajan 1 pisteet: " + pelaajan1Pisteet, 10, 50);
        graphics.drawString("Pelaajan 2 pisteet: " + pelaajan2Pisteet, 650, 50);
        
        synchronized (LUKKO) {
            for (Entiteetti e : piirrettavat) {
                piirraEntiteetti(graphics, e);
            }
        }
        
    }
    public void asetaPisteet(int piste1, int piste2) {
        pelaajan1Pisteet = piste1;
        pelaajan2Pisteet = piste2;
    }
    
    private void piirraEntiteetti(Graphics graphics, Entiteetti e) {
        PaikkaKomponentti paikka = (PaikkaKomponentti) e.getKomponentti(KomponenttiTyyppi.PAIKKA);
        PiirtoKomponentti piirto = (PiirtoKomponentti) e.getKomponentti(KomponenttiTyyppi.PIIRTO);

        piirto.piirra(graphics, paikka.getX(), paikka.getY());
    }

    void lisaaPiirrettava(Entiteetti piirettavaEntiteetti) throws ClassCastException, NullPointerException {
        if ((PiirtoKomponentti) piirettavaEntiteetti.getKomponentti(KomponenttiTyyppi.PIIRTO) == null) {
            throw new NullPointerException("Entiteetillä ei ole piirtokomponenttia metodissa Piirtoalusta.lisaaPiirrettava()!");
        }

        if ((PaikkaKomponentti) piirettavaEntiteetti.getKomponentti(KomponenttiTyyppi.PAIKKA) == null) {
            throw new NullPointerException("Entiteetillä ei ole paikkakomponenttia metodissa Piirtoalusta.lisaaPiirrettava()!");
        }

        synchronized (LUKKO) {
            piirrettavat.add(piirettavaEntiteetti);
        }
    }
}
