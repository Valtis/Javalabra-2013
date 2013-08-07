package Pelilogiikka;

import Kayttoliittyma.KayttoLiittyma;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.InputKomponentti;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Pääpeliluokka sisältää pääluupin, pitää viittauksia yllä entiteetteihin ja
 * käyttöliittymään
 *
 */
public class Peli implements  PeliInterface {

    private final Object LUKKO;
    private KayttoLiittyma liittyma;
    private TormaysManageri tormaysManageri;
    private List<Entiteetti> entiteetit;
    private Asetukset asetukset;
    private final long TICK = 16 * 1000000; // 16 millisekuntia
    private long nykyinenAika;
    private int pelaajan1Pisteet;
    private int pelaajan2Pisteet;

    /**
     * Alustaa pelin arvot
     */
    public Peli() {
        pelaajan1Pisteet = 0;
        pelaajan2Pisteet = 0;
        LUKKO = new Object();

        entiteetit = new ArrayList<Entiteetti>();
        tormaysManageri = new TormaysManageri();
        liittyma = new KayttoLiittyma();
        asetukset = new Asetukset();
   
    }
    
    /**
     * Palauttaa pelaajan 1 pisteet
     * @return pelaajan 1 pisteet
     */
    public int getPelaajan1Pisteet() {
        return pelaajan1Pisteet;
    }
    
    /**
     * Palauttaa pelaajan 2 pisteet
     * @return pelaajan 2 pisteet
     */
    public int getPelaajan2Pisteet() {
        return pelaajan2Pisteet;
    }

    /**
     * Asettaa halutun entiteetin InputKomponentin kuuntelemaan
     * näppäimistösyötettä. Jos input-komponenttia ei ole, ei tee mitään <p>
     * Heittää ClassCastExceptionin jos komponentti ei ole InputKomponentti
     *
     * @param e Entiteetti joka haluaa kuunnella syötettä
     * @throws ClassCastException
     */
    private void asetaNappaimistoKuuntelija(Entiteetti e) throws ClassCastException {

        InputKomponentti input = (InputKomponentti) e.getKomponentti(KomponenttiTyyppi.INPUT);
        
        if (input == null) {
            return;
        }

        liittyma.lisaaNappainKuuntelija(input);
    }

    /**
     * Lisää entiteetin peliin.
     *
     * @param e Lisättävä entiteetti
     * @param tarvitseeNappaimistoSyotteen Tarvitseeko entiteetti
     * näppäimistösyötettä
     * @throws NullPointerException jos entiteetti on null
     * @throws ClassCastException jos jonkin komponentin tyyppi ei ole oikea
     */
    @Override
    public void lisaaEntiteetti(Entiteetti e, boolean tarvitseeNappaimistoSyotteen) throws NullPointerException, ClassCastException {
        
        liittyma.lisaaPiirrettava(e);
        tormaysManageri.lisaaTormaaja(e);

        if (tarvitseeNappaimistoSyotteen) {
            asetaNappaimistoKuuntelija(e);
        }

        synchronized (LUKKO) {
            entiteetit.add(e);
        }
    }

    /**
     * Aloittaa pelin pelaamisen
     */
    public void pelaa() {

        alusta();

        while (true) {

            paivitaPeliLogiikka();
            liittyma.piirra(pelaajan1Pisteet, pelaajan2Pisteet);
        }
    }

    /**
     * Päivittää pelilogiikan tilan. Päivittää entiteetit, päivittää törmäysten
     * tilan
     */
    private void paivitaPeliLogiikka() {
        long uusiAika = System.nanoTime();
        if (nykyinenAika + TICK < uusiAika) {

            long delta = uusiAika - nykyinenAika;
            nykyinenAika = uusiAika;

            paivitaEntiteetit((double) delta / (double) TICK);
            tormaysManageri.tarkistaTormaykset();
        }
    }

    /**
     * Päivittää entiteetit
     *
     * @param ticks Montako peliaskelta on kulunut viime päivityksestä
     */
    private void paivitaEntiteetit(double ticks) {
        synchronized (LUKKO) {
            for (Entiteetti e : entiteetit) {
                e.paivita(ticks);
            }
        }
    }
    /**
     * Antaa pelaajille pisteitä riippuen mihin reunaan osuttiin
     * @param reuna Reuna johonka pallo osui
     */
    @Override
    public void pisteyta(Reuna reuna) {
        if (reuna == Reuna.YLA) {
            ++pelaajan1Pisteet;
        } else if (reuna == Reuna.ALA) {
            ++pelaajan2Pisteet;
        }
    }
    /**
     * Alustaa tarvittavat arvot
     */
    private void alusta() {
        nykyinenAika = System.nanoTime();
        liittyma.alusta(asetukset);
        
        asetukset.haeAsetukset(this);
        SwingUtilities.invokeLater(liittyma);

        tormaysManageri.asetaAlueenKoko(liittyma.peliAlueenLeveys(), liittyma.peliAlueenKorkeus());
    }
}
