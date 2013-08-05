

package Kayttoliittyma;


import Pelilogiikka.Asetukset;
import Pelilogiikka.Enumit.NappulaTyyppi;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Asetusikkuna
 * 
 */
public class AsetusRuutu extends JFrame {

    private int RUUDUN_LEVEYS = 400;
    private int RUUDUN_KORKEUS = 200;
 
    private NappulaKontrolli kuuntelija;
    /**
     * Luo 400x200-kokoisen ikkunan jossa on neljä nappulaa asetuksia varten
     * @param title Ikkunan nimi
     * @param asetukset Asetukset-objekti joka kuuntelee nappuloiden tapahtumia
     */
    public AsetusRuutu(String title, Asetukset asetukset) {
        super(title);
        kuuntelija = new NappulaKontrolli(asetukset);
      
        setPreferredSize(new Dimension(RUUDUN_LEVEYS, RUUDUN_KORKEUS));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoNappulat(getContentPane());
        pack();
        setVisible(true);  
    }
    /**
     * Luo nappulat ikkunalle
     * @param container Container johonka nappulat tallennetaan
     */
    private void luoNappulat(Container container) {
        
        container.setLayout(new GridLayout(4, 1));
      
        JButton button = new JButton("Vaihda pelaajan 1 kontrolli");
        kuuntelija.lisaaNappula(button, NappulaTyyppi.PELAAJA_1_INPUT_VAIHTO);
        container.add(button);    
        
        button = new JButton("Vaihda pelaajan 2 kontrolli");
        kuuntelija.lisaaNappula(button, NappulaTyyppi.PELAAJA_2_INPUT_VAIHTO);
        container.add(button);
        
        button = new JButton("Luo kimpoileva este");
        kuuntelija.lisaaNappula(button, NappulaTyyppi.KIMPOILEVA_ESTE);
        container.add(button);
        
        button = new JButton("Luo staattinen este");
        kuuntelija.lisaaNappula(button, NappulaTyyppi.STAATTINEN_ESTE);
        container.add(button);

    }
}
