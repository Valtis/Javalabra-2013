package Pelilogiikka;

import Kayttoliittyma.PeliRuutu;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.InputKomponentti;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Peli implements PisteKuuntelija {

    private final Object LUKKO;
    private PeliRuutu liittyma;
    private TormaysManageri tormaysManageri;
    private List<Entiteetti> entiteetit;
    private final long TICK = 16 * 1000000; // 16 millisekuntia
    private long nykyinenAika;
    private int pelaajan1Pisteet;
    private int pelaajan2Pisteet;

    public Peli() {
        pelaajan1Pisteet = 0;
        pelaajan2Pisteet = 0;
        LUKKO = new Object();

        entiteetit = new ArrayList<Entiteetti>();
        tormaysManageri = new TormaysManageri();
        nykyinenAika = System.nanoTime();
    }

    private void alustaUI(Asetukset asetukset) {
        liittyma = new PeliRuutu();
        liittyma.alusta(asetukset);
    }

    private void asetaKuuntelija(Entiteetti e) throws NullPointerException, ClassCastException {
        InputKomponentti input = (InputKomponentti) e.getKomponentti(KomponenttiTyyppi.INPUT);
        liittyma.lisaaNappainKuuntelija(input);
    }

    public void lisaaEntiteetti(Entiteetti e, boolean tarvitseeNappaimistoSyotteen) {

        liittyma.lisaaPiirrettava(e);
        tormaysManageri.lisaaTormaaja(e);

        if (tarvitseeNappaimistoSyotteen) {
            asetaKuuntelija(e);
        }

        synchronized (LUKKO) {
            entiteetit.add(e);
        }
    }

    public void pelaa() {
        Asetukset asetukset = new Asetukset();
        alustaUI(asetukset);
        asetukset.haeAsetukset(this);


        SwingUtilities.invokeLater(liittyma);

        tormaysManageri.asetaAlueenKoko(liittyma.peliAlueenLeveys(), liittyma.peliAlueenKorkeus());

        while (liittyma.onNakyvilla()) {

            paivitaPeliLogiikka();
            liittyma.piirra(pelaajan1Pisteet, pelaajan2Pisteet);
        }
    }

    private void paivitaPeliLogiikka() {
        long uusiAika = System.nanoTime();
        if (nykyinenAika + TICK < uusiAika) {
            long delta = uusiAika - nykyinenAika;
            nykyinenAika = uusiAika;
            paivitaEntiteetit((double) delta / (double) TICK);
            tormaysManageri.tarkistaTormaykset();
        }
    }

    private void paivitaEntiteetit(double ticks) {
        synchronized (LUKKO) {
            for (Entiteetti e : entiteetit) {
                e.paivita(ticks);
            }
        }
    }

    @Override
    public void pisteyta(Reuna reuna) {
        if (reuna == Reuna.YLA) {
            ++pelaajan1Pisteet;
        } else if (reuna == Reuna.ALA) {
            ++pelaajan2Pisteet;
        }
    }
}
