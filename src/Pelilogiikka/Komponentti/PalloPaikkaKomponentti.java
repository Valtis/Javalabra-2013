package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Reuna;
import Pelilogiikka.Komponentti.Viestit.TormaysReunaanViesti;
import Pelilogiikka.PisteKuuntelija;

public class PalloPaikkaKomponentti extends PaikkaKomponentti {

    private int aloitusX;
    private int aloitusY;
    private PisteKuuntelija kuuntelija;

    public PalloPaikkaKomponentti() {
        super();
    }
    
    public void asetaPisteKuuntelija(PisteKuuntelija kuuntelija) {
        this.kuuntelija = kuuntelija;
    }

    public PalloPaikkaKomponentti(int x, int y) {
        super(x, y);
        aloitusX = x;
        aloitusY = y;
    }

 

    @Override
    public void vieraile(TormaysReunaanViesti viesti) {
        if (viesti.getReuna() == Reuna.YLA || viesti.getReuna() == Reuna.ALA) {
            asetaPaikka(aloitusX, aloitusY);
            kuuntelija.pisteyta(viesti.getReuna());
        }
    }

}
