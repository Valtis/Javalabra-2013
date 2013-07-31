package Pelilogiikka;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.TormaysKomponentti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class TormaysManageri {

    private int pelialueenLeveys;
    private int pelialueenKorkeus;
    private List<Entiteetti> tormaajat;

    public TormaysManageri() {
        tormaajat = new ArrayList<Entiteetti>();
    }

    public void asetaAlueenKoko(int leveys, int korkeus) {
        pelialueenLeveys = leveys;
        pelialueenKorkeus = korkeus;
    }

    public void lisaaTormaaja(Entiteetti tormaaja) {
        tormaajat.add(tormaaja);
    }

    public void tarkistaTormaykset() {
        for (Entiteetti e : tormaajat) {
            // tarkistetaan ettei entiteetti kulje reunan yli

            tarkistaReunat(e);
            tarkistaEntiteettienValisetTormaykset(e);
        }
    }

    private void tarkistaReunat(Entiteetti e) {
        TormaysKomponentti tormays = (TormaysKomponentti) e.getKomponentti(KomponenttiTyyppi.TORMAYS);
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

    private void tarkistaEntiteettienValisetTormaykset(Entiteetti tormaaja) {

        for (Entiteetti tormattava : tormaajat) {

            if (tormaaja == tormattava) {
                continue;
            }
            
            boolean tormaavat = tarkistaTormays(tormaaja, tormattava);

            if (tormaavat) {
                tormaaja.kasitteleValittomastiViesti(new TormaysEntiteettiinViesti(tormattava));
            }

        }
    }

    private boolean tarkistaTormays(Entiteetti tormaaja, Entiteetti tormattava) {

        TormaysKomponentti tormaajanTormaysKomponentti = (TormaysKomponentti) tormaaja.getKomponentti(KomponenttiTyyppi.TORMAYS);
        PaikkaKomponentti tormaajanPaikka = (PaikkaKomponentti) tormaaja.getKomponentti(KomponenttiTyyppi.PAIKKA);

        TormaysKomponentti tormattavanTormaysKomponentti = (TormaysKomponentti) tormattava.getKomponentti(KomponenttiTyyppi.TORMAYS);
        PaikkaKomponentti tormattavanPaikka = (PaikkaKomponentti) tormattava.getKomponentti(KomponenttiTyyppi.PAIKKA);

        // luodaan nelikulmiot...
        Rectangle tormaajanSuorakulmio = new Rectangle(tormaajanPaikka.getX(), tormaajanPaikka.getY(), tormaajanTormaysKomponentti.getLeveys(), tormaajanTormaysKomponentti.getKorkeus());
        Rectangle tormattavanSuorakulmio = new Rectangle(tormattavanPaikka.getX(), tormattavanPaikka.getY(), tormattavanTormaysKomponentti.getLeveys(), tormattavanTormaysKomponentti.getKorkeus());

        // ja käytetään valmista metodia tarkistamaan leikkaavatko nämä!
        return tormaajanSuorakulmio.intersects(tormattavanSuorakulmio);
    }
}
