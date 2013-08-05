package Pelilogiikka.Komponentti.Viestit;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Enumit.Reuna;
import java.security.InvalidParameterException;

/**
 * Viesti joka viestittää törmäyskäsittelijälle että on törmätty entiteettiin
 *
 */
public class TormaysEntiteettiinViesti implements Viesti {

    private final Entiteetti TORMATTY;
    private final Reuna TORMAYS_REUNA;
    /**
     * Konstruktori. Ottaa parametrina entiteetin johon on törmätty ja mihin reunaan on törmätty<br>
     * @param tormatty Entiteetti johonka törmättiin
     * @param tormaysReuna Mihin reunaan törmättiin
     */
    public TormaysEntiteettiinViesti(Entiteetti tormatty, Reuna tormaysReuna) {
        TORMATTY = tormatty;
        TORMAYS_REUNA = tormaysReuna;
    }
    /**
     * Getteri. Palauttaa törmääjän
     * @return Entiteetti johon törmättiin
     */
    public Entiteetti getTormaaja() {
        return TORMATTY;
    }
    /**
     * Getteri. Palauttaa törmäyspisteen
     * @return Skaalattu arvo väliltä 0 - 1 joka kertoo mihin kohtaa törmäävän entiteetin x-akselilla entiteetin törmäys tapahtui.
     */
    public Reuna getTormaysReuna() {
        return TORMAYS_REUNA;
    }

    /**
     * Ottaa vastaan vierailijan ja vierailee siellä.
     *
     * @param k ViestiVierailija-rajapinnan toteuttava luokka
     */
    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }
}
