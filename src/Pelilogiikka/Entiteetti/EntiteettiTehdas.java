package Pelilogiikka.Entiteetti;

import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Komponentti.EntiteettiTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.KimpoaSeinastaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.MailaNopeusKomponentti;
import Pelilogiikka.Komponentti.MailaPaikkaKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.LiikkuvaObjektiNopeusKomponentti;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPiirtoKomponentti;
import Pelilogiikka.Komponentti.PalloSeinaTormaysKasittelijaKomponentti;
import Pelilogiikka.Komponentti.SuoraKaidePiirtoKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import Pelilogiikka.Komponentti.TormaysAlueKomponentti;

public class EntiteettiTehdas {

    public Entiteetti luoEntiteetti(EntiteettiTyyppi tyyppi, int x, int y) {
        switch (tyyppi) {
            case PALLO:
                return luoPallo(x, y);
            case PELAAJA_MAILA:
                return luoPelaajanMaila(x, y);
            case TEKOALY_MAILA:
                return luoTekoalyMaila(x, y);
            case STAATTINEN_ESTE:
                return luoStaattinenEste(x, y);
            case KIMPOILEVA_ESTE:
                return luoKimpoilevaEste(x, y);
        }
        return null;
    }

    private Entiteetti luoPallo(int x, int y) {
        Entiteetti e = new Entiteetti();
        // kovakoodaus nopeudelle
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new LiikkuvaObjektiNopeusKomponentti(6));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PalloPaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new PalloPiirtoKomponentti(20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(17, 17));
        e.lisaaKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA, new PalloSeinaTormaysKasittelijaKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA, new EntiteettiTormaysKasittelijaKomponentti());
       
        return e;
    }

    private Entiteetti luoStaattinenEste(int x, int y) {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(20, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(20, 20));
        return e;
    }
    
    private Entiteetti luoKimpoilevaEste(int x, int y) {
        Entiteetti e = new Entiteetti();
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new PaikkaKomponentti(x, y));
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(20, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(20, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new LiikkuvaObjektiNopeusKomponentti(6));
        e.lisaaKomponentti(KomponenttiTyyppi.SEINA_TORMAYS_KASITTELIJA, new KimpoaSeinastaTormaysKasittelijaKomponentti());
        e.lisaaKomponentti(KomponenttiTyyppi.ENTITEETTI_TORMAYS_KASITTELIJA, new EntiteettiTormaysKasittelijaKomponentti());
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
        k.asetaOmaPaikka((PaikkaKomponentti) e.getKomponentti(KomponenttiTyyppi.PAIKKA));
        e.lisaaKomponentti(KomponenttiTyyppi.INPUT, k);
        return e;

    }

    private void luoYhteisetMailaKomponentit(Entiteetti e, int x, int y) {
        e.lisaaKomponentti(KomponenttiTyyppi.NOPEUS, new MailaNopeusKomponentti(4));
        e.lisaaKomponentti(KomponenttiTyyppi.PAIKKA, new MailaPaikkaKomponentti(x, y));
        // kovakoodatut luvut, oikeasti varmaan parempi että luettaisiin tiedostosta\tulisi parametreina
        e.lisaaKomponentti(KomponenttiTyyppi.PIIRTO, new SuoraKaidePiirtoKomponentti(150, 20));
        e.lisaaKomponentti(KomponenttiTyyppi.TORMAYS_ALUE, new TormaysAlueKomponentti(150, 20));
    }
}
