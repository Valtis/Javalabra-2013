

package Kayttoliittyma;


import Pelilogiikka.Asetukset;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class AsetusRuutu extends JFrame {

    private int RUUDUN_LEVEYS = 400;
    private int RUUDUN_KORKEUS = 200;
 
    private NappulaKontrolli kuuntelija;
    
    public AsetusRuutu(String title, Asetukset asetukset) {
        super(title);
        kuuntelija = new NappulaKontrolli(asetukset);
      
        setPreferredSize(new Dimension(RUUDUN_LEVEYS, RUUDUN_KORKEUS));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(getContentPane(), asetukset);
        pack();
        setVisible(true);  
    }

    private void luoKomponentit(Container container, Asetukset asetukset) {
        
        container.setLayout(new GridLayout(4, 1));
      
        JButton button = new JButton("Vaihda pelaajan 1 kontrolli");
        kuuntelija.asetaPelaaja1Vaihto(button);
        container.add(button);    
        
        button = new JButton("Vaihda pelaajan 2 kontrolli");
        kuuntelija.asetaPelaaja2Vaihto(button);
        container.add(button);
        
        button = new JButton("Luo kimpoileva este");
        kuuntelija.asetaKimpoilevaEste(button);
        container.add(button);
        
        button = new JButton("Luo staattinen este");
        kuuntelija.asetaStaattinenEste(button);
        container.add(button);

    }
    
   
  
 
    
}
