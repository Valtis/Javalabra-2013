package Pelilogiikka.Entiteetti;

import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.MailaNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.SuoraKaidePiirtoKomponentti;
import Pelilogiikka.Komponentti.TormaysKomponentti;


public class EntiteettiTehdas {

    public Entiteetti luoEntiteetti(EntiteettiTyyppi tyyppi, int x, int y) {
        switch (tyyppi) {
            case PALLO:
                return luoPallo();
            case PELAAJA_MAILA:
                return luoMaila(x, y);
            case TEKOALY_MAILA:
                throw new UnsupportedOperationException("Ei vielä implementoitu!");
        }
        return null;
    }

    private Entiteetti luoPallo() {
        Entiteetti e = new Entiteetti();
        return e;
    }
    
    private Entiteetti luoMaila(int x, int y) {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.INPUT, new InputKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new MailaNopeusKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new MailaPaikkaKomponentti(x, y));
        // kovakoodatut luvut, oikeasti varmaan parempi että luettaisiin tiedostosta\tulisi parametreina
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(150, 20)); 
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS, new TormaysKomponentti(150, 20));
        return e;
    }
    
}
