package Kayttoliittyma;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.PiirtoKomponentti;
import Pelilogiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {
    
    private int RUUDUN_LEVEYS = 800;
    private int RUUDUN_KORKEUS = 600;
    private JFrame frame;
    private Piirtoalusta piirtoalusta;

    public boolean onNakyvilla() {
        return frame.isVisible();
    }

    @Override
    public void run() {

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta();
        container.add(piirtoalusta);
    }
    
    public void piirra() {
        frame.repaint();
    }

    public void alusta() {
        frame = new JFrame("Pong");
        frame.setPreferredSize(new Dimension(RUUDUN_LEVEYS, RUUDUN_KORKEUS));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
    }
    
    public int peliAlueenLeveys() {
        return piirtoalusta.getWidth();
    }
    
    
    public int peliAlueenKorkeus() {
        return piirtoalusta.getHeight();
    }
    
    public void lisaaNappainKuuntelija(Komponentti komponentti) throws ClassCastException, NullPointerException {
        if (komponentti == null) {
            throw new NullPointerException("Komponentin arvo null metodissa KayttoLiittyma.lisaaNappainKuuntelija()");
        }
        
        KeyListener kuuntelija = (KeyListener) komponentti;
        frame.addKeyListener(kuuntelija);
    }

    public void lisaaPiirrettava(Entiteetti entiteetti) throws ClassCastException, NullPointerException {
        if (entiteetti == null) {
            throw new NullPointerException("Entiteetin arvo null metodissa KayttoLiittyma.lisaaPiirrettava()");
        }
        
        piirtoalusta.lisaaPiirrettava(entiteetti);
        
    }
}
