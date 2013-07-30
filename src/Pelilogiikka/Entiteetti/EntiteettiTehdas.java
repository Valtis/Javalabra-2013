package Pelilogiikka.Entiteetti;

import Kayttoliittyma.Piirtoalusta;
import Pelilogiikka.Enumit.EntiteettiTyyppi;
import javax.swing.JFrame;

public class EntiteettiTehdas {

    public Entiteetti luoEntiteetti(EntiteettiTyyppi tyyppi, JFrame frame, Piirtoalusta alusta) {
        switch (tyyppi) {
            case PALLO:
                return luoPallo(frame, alusta);
            case MAILA:
                return luoMaila(frame, alusta);
        }
        return null;
    }

    private Entiteetti luoPallo(JFrame frame, Piirtoalusta alusta) {
        Entiteetti e = new Entiteetti();
        return e;
    }
    
    private Entiteetti luoMaila(JFrame frame, Piirtoalusta alusta) {
        Entiteetti e = new Entiteetti();
        return e;
    }
    
}
