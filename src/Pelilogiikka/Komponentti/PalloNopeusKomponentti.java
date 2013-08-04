package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.PalloNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysEntiteettiinViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import java.util.Random;


public class PalloNopeusKomponentti extends Komponentti {

    private int xNopeus;
    private int yNopeus;
    private final int MAX_NOPEUS;
    private final int TORMAYS_HUOMIOIMATTAJATTAMIS_AIKA = 3;
    private int entiteettiTormaysLaskuri;
    private int seinaTormaysLaskuri;


    public PalloNopeusKomponentti(int maxNopeus) {
        MAX_NOPEUS = maxNopeus;
        asetaSatunnainenNopeus();
        entiteettiTormaysLaskuri = 0;
        seinaTormaysLaskuri = 0;
    }

    @Override
    public void paivita(double ticks) {
        viestit.add(new PalloNopeusViesti(xNopeus, yNopeus));
        if (entiteettiTormaysLaskuri > 0) {
            --entiteettiTormaysLaskuri;
        }

        if (seinaTormaysLaskuri > 0) {
            --seinaTormaysLaskuri;
        }
    }

    @Override
    public void vieraile(TormaysReunaanViesti viesti) {

        switch (viesti.getReuna()) {
            case YLA:
            case ALA:
                asetaSatunnainenNopeus();
                break;
            case VASEN:
            case OIKEA:
                if (seinaTormaysLaskuri > 0) {
                    break;
                }
                xNopeus = -xNopeus;
                seinaTormaysLaskuri = TORMAYS_HUOMIOIMATTAJATTAMIS_AIKA;
        }
    }

    @Override
    public void vieraile(TormaysEntiteettiinViesti viesti) {
        // annetaan törmäyksen jälkeen objektille hieman aikaa irtaantua
        if (entiteettiTormaysLaskuri > 0) {
            return;
        }

        double tormaysPiste = viesti.getTormaysPiste();
        // 0.0: yNopeus = -MAX_NOPEUS*0.3; 1.0: yNopeus = -MAX_NOPEUS*0.7
        double skaalaus = 0.4 + tormaysPiste * 0.4;

        int yEtumerkki = -yNopeus / Math.abs(yNopeus);
        int xEtumerkki = xNopeus /Math.abs(xNopeus); //(int) -((0.5 - tormaysPiste) / Math.abs(0.5 - tormaysPiste));



        yNopeus = yEtumerkki * (int) (skaalaus * MAX_NOPEUS);


        System.out.println(yNopeus);
        xNopeus = xEtumerkki * (int) Math.sqrt(MAX_NOPEUS * MAX_NOPEUS - yNopeus * yNopeus);

        System.out.println("Törmäyspiste: " + viesti.getTormaysPiste());
        entiteettiTormaysLaskuri = TORMAYS_HUOMIOIMATTAJATTAMIS_AIKA;
    }

    private void asetaSatunnainenNopeus() {

        Random random = new Random();

        int xEtumerkki = (int) Math.pow(-1, random.nextInt() % 2);
        int yEtumerkki = (int) Math.pow(-1, random.nextInt() % 2);

        xNopeus = xEtumerkki * MAX_NOPEUS / 3 + Math.abs(random.nextInt()) % MAX_NOPEUS / 3;
        yNopeus = yEtumerkki * (int) Math.sqrt(MAX_NOPEUS * MAX_NOPEUS - xNopeus * xNopeus);
    }
}
