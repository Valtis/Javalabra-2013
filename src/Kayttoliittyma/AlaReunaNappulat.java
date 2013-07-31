package Kayttoliittyma;

import Pelilogiikka.Asetukset;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AlaReunaNappulat extends JPanel {
    private JButton herp;
   
    public AlaReunaNappulat() {
       // super(new GridLayout(1, 2));
       
        herp = new JButton("herp derp");
        //vaihtelePelaaja2AI.addActionListener(asetukset);
        add(herp);
    }
}
