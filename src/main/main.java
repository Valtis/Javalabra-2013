package main;

import Pelilogiikka.Peli;

public class main {

    static public void main(String[] args) {
        try {
            Peli peli = new Peli();
            peli.pelaa();
        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen: " + ex.getMessage());
        }

    }
}
