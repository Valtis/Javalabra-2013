package Pelilogiikka;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.TormaysAlueKomponentti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka joka havaitsee törmäykset ja viestittää niistä eteenpäin
 */
public class TormaysManageri {

    private int pelialueenLeveys;
    private int pelialueenKorkeus;
    private List<Entiteetti> tormaajat;
    private final Object LUKKO;

    /**
     * Konstruktori <p> Alustaa sisäiset tietorakenteet
     */
    public TormaysManageri() {
        tormaajat = new ArrayList<Entiteetti>();
        LUKKO = new Object();
    }

    /**
     * Asettaa pelialueen rajat reunoihin törmäämistä varten
     *
     * @param leveys Pelialueen leveys
     * @param korkeus Pelialueen korkeus
     */
    public void asetaAlueenKoko(int leveys, int korkeus) {
        pelialueenLeveys = leveys;
        pelialueenKorkeus = korkeus;
    }

    /**
     * Lisää entiteetin jonka törmäyksiä valvotaan. Jos Entiteetillä ei ole
     * TORMAYS_ALUE-tyypin komponenttia, komponenttia ei lisätä.
     *
     * @param tormaaja Lisättävä entiteetti
     * @return Lisättiinkö törmääjä
     */
    public boolean lisaaTormaaja(Entiteetti tormaaja) {
        if (tormaaja.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE) == null) {
            return false;
        }

        synchronized (LUKKO) {
            tormaajat.add(tormaaja);
        }
        return true;
    }

    /**
     * Tarkistaa onko mikään entiteetti törmäämässä reunaan tai toiseen
     * entiteettiin ja viestittää näistä eteenpäin jos on törmäyksia
     */
    public void tarkistaTormaykset() {
        synchronized (LUKKO) {
            for (Entiteetti e : tormaajat) {
                // tarkistetaan ettei entiteetti kulje reunan yli

                tarkistaReunat(e);
                tarkistaEntiteettienValisetTormaykset(e);
            }
        }
    }

    /**
     * Tarkistaa onko entiteetti törmännyt seinään. <p> Jos törmäys havaitaan,
     * pyytää entiteettiä käsittelemään välittömästi TormaysReunaanViestin
     *
     * @param e Entiteetti jonka törmäystä tarkistetaan
     */
    private void tarkistaReunat(Entiteetti e) {
        TormaysAlueKomponentti tormays = (TormaysAlueKomponentti) e.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE);
        PaikkaKomponentti paikka = (PaikkaKomponentti) e.getKomponentti(KomponenttiTyyppi.PAIKKA);

        if (paikka.getX() < 0) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.VASEN));
        } else if (paikka.getX() + tormays.getLeveys() > pelialueenLeveys) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.OIKEA));
        } else if (paikka.getY() < 0) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.YLA));
        } else if (paikka.getY() + tormays.getKorkeus() > pelialueenKorkeus) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.ALA));
        }
    }

    /**
     * Tarkistaa onko entiteetti törmännyt toiseen entiteettiin <p> Jos törmäys
     * havaitaan, pyytää entiteettiä käsittelemään välittömästi
     * TormaysEntiteettiinViestin
     *
     * @param e Entiteetti jonka törmäystä tarkistetaan
     */
    private void tarkistaEntiteettienValisetTormaykset(Entiteetti tormaaja) {
        for (Entiteetti tormattava : tormaajat) {

            if (tormaaja == tormattava) {
                continue;
            }

            PaluuParametrit tormays = tarkistaTormays(tormaaja, tormattava);

            if (tormays.tormasi) {
                tormaaja.kasitteleValittomastiViesti(new TormaysEntiteettiinViesti(tormattava, tormays.osumaReuna));
            }
        }
    }
    /**
     * Apumetodi tarkistaEntiteettienValisetTormaykset-metodille
     * Tarkistaa törmäyksen ja palauttaa onko törmätty ja mihin reunaan jos on törmätty.
     * @param tormaaja Törmäävä entiteetti
     * @param tormattava Entiteetti johonka tarkistetaan törmäystä
     * @return 
     */
    private PaluuParametrit tarkistaTormays(Entiteetti tormaaja, Entiteetti tormattava) {

        TormaysAlueKomponentti tormaajanTormaysKomponentti = (TormaysAlueKomponentti) tormaaja.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE);
        PaikkaKomponentti tormaajanPaikka = (PaikkaKomponentti) tormaaja.getKomponentti(KomponenttiTyyppi.PAIKKA);

        TormaysAlueKomponentti tormattavanTormaysKomponentti = (TormaysAlueKomponentti) tormattava.getKomponentti(KomponenttiTyyppi.TORMAYS_ALUE);
        PaikkaKomponentti tormattavanPaikka = (PaikkaKomponentti) tormattava.getKomponentti(KomponenttiTyyppi.PAIKKA);

        // luodaan nelikulmiot...
        Rectangle tormaajanSuorakulmio = new Rectangle(tormaajanPaikka.getX(), tormaajanPaikka.getY(), tormaajanTormaysKomponentti.getLeveys(), tormaajanTormaysKomponentti.getKorkeus());
        Rectangle tormattavanSuorakulmio = new Rectangle(tormattavanPaikka.getX(), tormattavanPaikka.getY(), tormattavanTormaysKomponentti.getLeveys(), tormattavanTormaysKomponentti.getKorkeus());

        // ja käytetään valmista metodia tarkistamaan leikkaavatko nämä!
        PaluuParametrit parametrit = new PaluuParametrit();
        if ((parametrit.tormasi = tormaajanSuorakulmio.intersects(tormattavanSuorakulmio)) == true) {
            parametrit.osumaReuna = maaritaTormaysReuna(tormaajanSuorakulmio, tormattavanSuorakulmio);
        }

        return parametrit;
    }
    /**
     * Määrittää mihin reunaan entiteetti törmäsi
     * @param tormaajanSuorakulmio Törmäävän entiteetin paikkatiedot
     * @param tormattavanSuorakulmio Törmättävän entiteetin paikkatiedot
     * @return Reuna johonka törmättiin
     */
    private Reuna maaritaTormaysReuna(Rectangle tormaajanSuorakulmio, Rectangle tormattavanSuorakulmio) {
        int toleranssi = 6;
        
        int tormaajanKeskiPisteX = tormaajanSuorakulmio.x + tormaajanSuorakulmio.width / 2;
        int tormaajanKeskiPisteY = tormaajanSuorakulmio.y + tormaajanSuorakulmio.height / 2;

        int tormattavanKeskiPisteX = tormattavanSuorakulmio.x + tormattavanSuorakulmio.width / 2;
        int tormattavanKeskiPisteY = tormattavanSuorakulmio.y + tormattavanSuorakulmio.height / 2;

        int xEro = tormaajanKeskiPisteX - tormattavanKeskiPisteX;
        int yEro = tormaajanKeskiPisteY - tormattavanKeskiPisteY;


        
        if (yEro < 0 && Math.abs(xEro) + toleranssi < tormaajanSuorakulmio.width / 2 + tormattavanSuorakulmio.width / 2) {
            return Reuna.YLA;
        }

        if (yEro > 0 && Math.abs(xEro) + toleranssi < tormaajanSuorakulmio.width / 2 + tormattavanSuorakulmio.width / 2) {
            return Reuna.ALA;
        }

        if (xEro < 0 && Math.abs(yEro) + toleranssi < tormaajanSuorakulmio.height / 2 + tormattavanSuorakulmio.height / 2) {
            return Reuna.VASEN;
        }

        return Reuna.OIKEA;
    }
    /**
     * Sisäinen apuluokka jotta tarkistaTormays-metodi voi palauttaa kaksi arvoa
     */
    private class PaluuParametrit {

        public boolean tormasi;
        public Reuna osumaReuna;
    }
}
