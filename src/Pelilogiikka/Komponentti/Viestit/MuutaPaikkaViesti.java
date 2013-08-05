package Pelilogiikka.Komponentti.Viestit;

/**
 * Viesti joka kertoo paikkakomponentille kuinka paljon paikka on muuttunut. <br>
 * Nopeuskomponentti lähettää tämän viestin jos nopeus on ei-nolla.
 */
public class MuutaPaikkaViesti implements Viesti {

    private final int X_MUUTOS;
    private final int Y_MUUTOS;

    /**
     * Konstruktori. Ottaa x- ja y-koordinaatin muutokset vastaan
     * @param x Kuinka paljon x-koordinaatti on muuttunut
     * @param y Kuinka paljon y-koordinaatti on muuttunut
     */
    public MuutaPaikkaViesti(int x, int y) {
        this.X_MUUTOS = x;
        this.Y_MUUTOS = y;
    }
    /**
     * Getteri. Kertoo x-koordinaatin muutoksen
     * @return x-koordinaatin muutos
     */
    public int getXMuutos() {
        return X_MUUTOS;
    }
    /**
     * Getteri. Kertoo y-koordinaatin muutoksen
     * @return y-koordinaatin muutos
     */
    public int getYMuutos() {
        return Y_MUUTOS;
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
