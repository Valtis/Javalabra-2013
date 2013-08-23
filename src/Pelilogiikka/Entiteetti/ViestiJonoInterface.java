package Pelilogiikka.Entiteetti;

import Pelilogiikka.Komponentti.Viestit.Viesti;

/**
 * Rajapinta viestijonolle joka annetaan komponenteille. Komponentti-luokka
 * toteuttaa tämän
 *
 */
public interface ViestiJonoInterface {

    /**
     * Lisää annetun viestin viestijonoon. Viesti käsitellään seuraavalla
     * framella.
     *
     * @param v Lisättävä viesti
     */
    public void lisaaViesti(Viesti v);

    /**
     * Käsittelee annetun viestin välittömästi eikä odota seuraavaa framea.
     *
     * @param v Käsiteltävä viesti
     */
    public void kasitteleValittomastiViesti(Viesti v);
}
