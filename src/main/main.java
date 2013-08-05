package main;

import Pelilogiikka.Peli;
/**
 * Main-luokka. Luo ja käynnistää pelin.
 * 
 */
public class main {
    /**
     * Main-metodi
     * @param args Komentoriviargumentit. Jätetään huomioimatta. 
     */
    static public void   main(String[] args) {
        try {
            Peli peli = new Peli();
            peli.pelaa();
        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen: " + ex.getMessage());
        }
    }
}
