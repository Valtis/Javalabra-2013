package Pelilogiikka.Entiteetti;

import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.MailaNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloNopeusKomponentti;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPiirtoKomponentti;
import Pelilogiikka.Komponentti.SuoraKaidePiirtoKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import Pelilogiikka.Komponentti.TormaysKomponentti;


public class EntiteettiTehdas {

    public Entiteetti luoEntiteetti(EntiteettiTyyppi tyyppi, int x, int y) {
        switch (tyyppi) {
            case PALLO:
                return luoPallo(x, y);
            case PELAAJA_MAILA:
                return luoPelaajanMaila(x, y);
            case TEKOALY_MAILA:
                return luoTekoalyMaila(x, y);
        }
        return null;
    }

    private Entiteetti luoPallo(int x, int y) {
        Entiteetti e = new Entiteetti();
        // kovakoodaus nopeudelle testaukseksi nyt, pitäisi varmaan siirtää parametriksi tjsp
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new PalloNopeusKomponentti(12));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PalloPaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(30));
        // törmäyskomponentti hieman palloa pienempi jotta ei näyttäisi törmäävän tyhjään kun ottaa kulmista kiinni
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS, new TormaysKomponentti(25, 25));
       
        return e;
    }
    
    private Entiteetti luoPelaajanMaila(int x, int y) {
        
        
        Entiteetti e = new Entiteetti();
        luoYhteisetMailaKomponentit(e, x, y);
        e.lisaaKomponentti(KomponenttiTyyppi.INPUT, new InputKomponentti());
        return e;
    }

    private Entiteetti luoTekoalyMaila(int x, int y) {
        Entiteetti e = new Entiteetti();
        luoYhteisetMailaKomponentit(e, x, y);
        
        TekoalyInputKomponentti k = new TekoalyInputKomponentti();
        k.asetaOmaPaikka((PaikkaKomponentti)e.getKomponentti(KomponenttiTyyppi.PAIKKA));
        e.lisaaKomponentti(KomponenttiTyyppi.INPUT, k);
        return e;
        
    }

    private void luoYhteisetMailaKomponentit(Entiteetti e, int x, int y) {
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new MailaNopeusKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new MailaPaikkaKomponentti(x, y));
        // kovakoodatut luvut, oikeasti varmaan parempi että luettaisiin tiedostosta\tulisi parametreina
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(150, 20)); 
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS, new TormaysKomponentti(150, 20));
    }
    
}
