

package Pelilogiikka.Komponentti.Viestit;


public class AlustaNopeusViesti implements Viesti {

    @Override
    public void otaVastaanVierailija(ViestiVierailija k) {
        k.vieraile(this);
    }

}
