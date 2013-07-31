package Pelilogiikka.Entiteetti;

import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.MailaNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloNopeusKomponentti;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPiirtoKomponentti;
import Pelilogiikka.Komponentti.SuoraKaidePiirtoKomponentti;
import Pelilogiikka.Komponentti.TormaysKomponentti;


public class EntiteettiTehdas {

    public Entiteetti luoEntiteetti(EntiteettiTyyppi tyyppi, int x, int y) {
        switch (tyyppi) {
            case PALLO:
                return luoPallo(x, y);
            case PELAAJA_MAILA:
                return luoMaila(x, y);
            case TEKOALY_MAILA:
                throw new UnsupportedOperationException("Ei vielä implementoitu!");
        }
        return null;
    }

    private Entiteetti luoPallo(int x, int y) {
        Entiteetti e = new Entiteetti();
        // kovakoodaus nopeudelle testaukseksi nyt, pitäisi varmaan siirtää parametriksi tjsp
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new PalloNopeusKomponentti(10));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PalloPaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(30));
        // törmäyskomponentti hieman palloa pienempi jotta ei näyttäisi törmäävän tyhjään kun ottaa kulmista kiinni
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS, new TormaysKomponentti(25, 25));
       
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
