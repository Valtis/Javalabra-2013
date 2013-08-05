

package Pelilogiikka.Komponentti.Viestit;

/**
 * Viesti joka ilmoittaa nopeuden muutoksesta törmäyksen johdosta.
 */
public class MuutaNopeusViesti implements Viesti {
    private final double X_MUUTOS;
    private final double Y_MUUTOS;

     /**
     * Konstruktori. Ottaa vastaan x- ja y-suuntaisen nopeuden muutoksen.<br>
     * Arvon ollessa 1 nopeus ei muutu, arvon ollessa -1 nopeus vaihtaa suuntaansa
     * 
     * @param xMuutos X-akselin suuntaisen nopeuden muutos
     * @param yMuutos Y-akselin suuntaisen nopeuden muutos
     */
    public MuutaNopeusViesti(double xMuutos, double yMuutos) {
        this.X_MUUTOS = xMuutos;
        this.Y_MUUTOS = yMuutos;
    }
    /**
     * Getteri. Palauttaa X-akselin suuntaisen nopeuden muutoksen
     * @return X-akselin suuntaisen nopeuden muutos
     */
    public double getXNopeudenMuutos() {
        return X_MUUTOS;
    }
    /**
     * Getteri. Palauttaa Y-akselin suuntaisen nopeuden muutoksen
     * @return Y-akselin suuntaisen nopeuden muutos
     */
    public double getYNopeudenMuutos() {
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
