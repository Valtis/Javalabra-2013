package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.Viestit.PalloNopeusViesti;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;

public class PalloPaikkaKomponentti extends PaikkaKomponentti {

    private int aloitusX;
    private int aloitusY;

    public PalloPaikkaKomponentti() {
        super();
    }

    public PalloPaikkaKomponentti(int x, int y) {
        super(x, y);
        aloitusX = x;
        aloitusY = y;
    }

    @Override
    public void vieraile(PalloNopeusViesti viesti) {
        asetaX(getX() + viesti.getXNopeus());
        asetaY(getY() + viesti.getYNopeus());
    }

    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        if (viesti.getReuna() == Reuna.YLA || viesti.getReuna() == Reuna.ALA) {
            asetaPaikka(aloitusX, aloitusY);
        }
    }
}
