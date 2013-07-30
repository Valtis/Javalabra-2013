package Kayttoliittyma;

import Pelilogiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Piirtoalusta piirtoalusta;
    private Peli peli;

    @Override
    public void run() {
        frame = new JFrame("Pong");
        frame.setPreferredSize(new Dimension(800, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
        AlustaPeli();
        frame.pack();
        frame.setVisible(true);



    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta();
        container.add(piirtoalusta);

    }

    private void AlustaPeli() {
        peli = new Peli();
        
    }
}
