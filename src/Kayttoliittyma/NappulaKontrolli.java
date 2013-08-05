package Kayttoliittyma;

import Pelilogiikka.Enumit.NappulaTyyppi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

/**
 * Luokka joka kuuntelee annettuja nappuloita ja välittää NappulaKuuntelija-rajapinnan toteuttavalle luokalle tiedon eteenpäin kun nappulaa on painettu
 */
public class NappulaKontrolli implements ActionListener {
    
    private Map<Object, NappulaTyyppi> nappulaViestit;
    private NappulaKuuntelija kuuntelija;
    /**
     * Lisää kuuntelijan tälle kontrollille
     * @param kuuntelija 
     */
    public NappulaKontrolli(NappulaKuuntelija kuuntelija) {
        this.kuuntelija = kuuntelija;
        nappulaViestit = new HashMap<Object, NappulaTyyppi>();
    }
    /**
     * Lisää nappulan ja viestin joka tulee välittää eteenpäin kun sitä on painettu
     * @param nappula Nappula jota kuunnellaan
     * @param tyyppi  Nappulaan liittyvä viesti
     */
    public void lisaaNappula(JButton nappula, NappulaTyyppi tyyppi) {
        nappula.addActionListener(this);
        nappulaViestit.put(nappula, tyyppi);
    }
    /**
     * Toteuttaa ActionListener-rajapinnan actionPerformed-metodin
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (nappulaViestit.containsKey(e.getSource())) {
            kuuntelija.nappulaViesti(nappulaViestit.get(e.getSource()));
        }
    }
}
