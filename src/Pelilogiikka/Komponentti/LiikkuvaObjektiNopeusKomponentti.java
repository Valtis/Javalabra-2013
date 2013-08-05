package Pelilogiikka.Komponentti;

import Pelilogiikka.Komponentti.Viestit.AlustaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.MuutaPaikkaViesti;
import java.util.Random;

public class LiikkuvaObjektiNopeusKomponentti extends Komponentti {

    private int xNopeus;
    private int yNopeus;
    private final int MAX_NOPEUS;

    public LiikkuvaObjektiNopeusKomponentti(int maxNopeus) {
        MAX_NOPEUS = maxNopeus;
        asetaSatunnainenNopeus();
    }

    @Override
    public void paivita(double ticks) {
        viestit.add(new MuutaPaikkaViesti(xNopeus, yNopeus));
    }

    @Override
    public void vieraile(MuutaNopeusViesti viesti) {
        yNopeus = (int) (yNopeus * viesti.getYNopeudenMuutos());
        xNopeus = (int) (xNopeus * viesti.getXNopeudenMuutos());
    }

    @Override
    public void vieraile(AlustaNopeusViesti viesti) {
        asetaSatunnainenNopeus();
    }

    // public void vieraile()
    private void asetaSatunnainenNopeus() {

        Random random = new Random();

        int xEtumerkki = (int) Math.pow(-1, random.nextInt() % 2);
        int yEtumerkki = (int) Math.pow(-1, random.nextInt() % 2);

        xNopeus = xEtumerkki * MAX_NOPEUS / 3 + Math.abs(random.nextInt()) % MAX_NOPEUS / 3;
        yNopeus = yEtumerkki * (int) Math.sqrt(MAX_NOPEUS * MAX_NOPEUS - xNopeus * xNopeus);
    }
}
