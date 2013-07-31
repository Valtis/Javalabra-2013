

package Pelilogiikka.Komponentti;

import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.Viestit.LiikeViesti;
import Pelilogiikka.Komponentti.Viestit.PelaajaNopeusViesti;
import java.util.EnumMap;
import java.util.Map;


public class TekoalyInputKomponentti extends Komponentti {
    private PaikkaKomponentti pallonPaikka;
    private PaikkaKomponentti omaPaikka;
     

    public void asetaPallonPaikka(PaikkaKomponentti pallonPaikkaKomponentti) {
        this.pallonPaikka = pallonPaikkaKomponentti;
    }
    
    public void asetaOmaPaikka(PaikkaKomponentti omaPaikkaKomponentti) {
        omaPaikka = omaPaikkaKomponentti;
    }
   
    @Override
    public void paivita(double ticks) {
       if (pallonPaikka == null || omaPaikka == null) {
           throw new NullPointerException("Alustamattomia muuttujia TekoAlyInputKomponentissa");
       }
       if (omaPaikka.getX() < pallonPaikka.getX() ) {
           viestit.add(new LiikeViesti(Suunta.OIKEA, true));
       }
       else {
           viestit.add(new LiikeViesti(Suunta.VASEN, true));
       }
    }

}
