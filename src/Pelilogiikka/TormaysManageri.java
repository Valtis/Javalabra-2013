package Pelilogiikka;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.TormaysKomponentti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import java.util.ArrayList;
import java.util.List;

public class TormaysManageri {

    private final int PELIALUEEN_LEVEYS;
    private final int PELIALUEEN_KORKEUS;
    private List<Entiteetti> tormaajat;

    public TormaysManageri(int leveys, int korkeus) {
        tormaajat = new ArrayList<Entiteetti>();
        PELIALUEEN_LEVEYS = leveys;
        PELIALUEEN_KORKEUS = korkeus;
    }

    public void lisaaTormaaja(Entiteetti tormaaja) {
        tormaajat.add(tormaaja);
    }

    public void tarkistaTormaykset() {
        for (Entiteetti e : tormaajat) {
            // tarkistetaan ettei entiteetti kulje reunan yli

            tarkistaReunat(e);
        }
    }

    private void tarkistaReunat(Entiteetti e) {
        TormaysKomponentti tormays = (TormaysKomponentti) e.getKomponentti(KomponenttiTyyppi.TORMAYS);
        PaikkaKomponentti paikka = (PaikkaKomponentti) e.getKomponentti(KomponenttiTyyppi.PAIKKA);

        if (paikka.getX() < 0) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.VASEN));
        } else if (paikka.getX() + tormays.getLeveys() > PELIALUEEN_LEVEYS) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.OIKEA));
        } else if (paikka.getY() < 0) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.YLA));
        } else if (paikka.getY() + tormays.getKorkeus() > PELIALUEEN_KORKEUS) {
            e.kasitteleValittomastiViesti(new TormaysReunaanViesti(Reuna.YLA));
        }
    }
}
