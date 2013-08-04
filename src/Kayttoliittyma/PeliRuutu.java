package Kayttoliittyma;

import Pelilogiikka.Asetukset;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Komponentti.Komponentti;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PeliRuutu implements Runnable {

    private int RUUDUN_LEVEYS = 800;
    private int RUUDUN_KORKEUS = 600;
    private JFrame peliFrame;
   
    
    private JFrame asetusRuutu;
    private Piirtoalusta piirtoalusta;

    public boolean onNakyvilla() {
        return peliFrame.isVisible();
    }

    @Override
    public void run() {
    }

    public void piirra(int pelaajan1Pisteet, int pelaajan2Pisteet) {
        piirtoalusta.asetaPisteet(pelaajan1Pisteet, pelaajan2Pisteet);
        if (peliFrame.isVisible()) {
            peliFrame.repaint();
        }
    }

    public void alusta(Asetukset asetukset) {
        asetusRuutu = new AsetusRuutu("Pong - asetukset", asetukset);
        peliFrame = new JFrame("Pong");
       
        
        peliFrame.setPreferredSize(new Dimension(RUUDUN_LEVEYS, RUUDUN_KORKEUS));
        peliFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoPeliKomponentit(peliFrame.getContentPane());
        peliFrame.pack();
        peliFrame.setVisible(true);
    }

    private void luoPeliKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta();
        container.add(piirtoalusta);
    }

    public int peliAlueenLeveys() {
        return piirtoalusta.getWidth();
    }

    public int peliAlueenKorkeus() {
        return piirtoalusta.getHeight();
    }

    public void lisaaNappainKuuntelija(KeyListener komponentti) throws ClassCastException, NullPointerException {
        if (komponentti == null) {
            throw new NullPointerException("Komponentin arvo null metodissa KayttoLiittyma.lisaaNappainKuuntelija()");
        }

        KeyListener kuuntelija = (KeyListener) komponentti;
        peliFrame.addKeyListener(kuuntelija);
    }

    public void lisaaPiirrettava(Entiteetti entiteetti) throws ClassCastException, NullPointerException {
        if (entiteetti == null) {
            throw new NullPointerException("Entiteetin arvo null metodissa KayttoLiittyma.lisaaPiirrettava()");
        }

        piirtoalusta.lisaaPiirrettava(entiteetti);

    }
}
