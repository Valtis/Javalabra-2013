package Pelilogiikka;

import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Entiteetti.EntiteettiTehdas;
import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import java.awt.event.KeyEvent;

public class Asetukset  {
    
    void haeAsetukset(Peli peli) {
       
        alustaEntiteetit(peli);
    
    }
    
    private void alustaEntiteetit(Peli peli) {
       Entiteetti pallo = luoPallo(800/2 - 10, 600/2 - 10);
       peli.lisaaEntiteetti(pallo, false);
       
       peli.lisaaEntiteetti(luoAI(250, 10, pallo), false);
       peli.lisaaEntiteetti(luoPelaaja(Suunta.VASEN, KeyEvent.VK_LEFT, Suunta.OIKEA, KeyEvent.VK_RIGHT, 250, 530), true);

    }
    
    private Entiteetti luoPelaaja(Suunta ensimmainenSuunta, int ensimmaisenSuunnanNappain, Suunta toinenSuunta, int toisenSuunnanNappain, int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti pelaaja = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, x, y);
        
        InputKomponentti input = (InputKomponentti)pelaaja.getKomponentti(KomponenttiTyyppi.INPUT);
        input.asetaNappain(ensimmaisenSuunnanNappain, ensimmainenSuunta);
        input.asetaNappain(toisenSuunnanNappain, toinenSuunta);

        return pelaaja;
    }
    
    private Entiteetti luoAI(int x, int y, Entiteetti pallo) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti ai = tehdas.luoEntiteetti(EntiteettiTyyppi.TEKOALY_MAILA, x, y);
           
        TekoalyInputKomponentti tekoalyInput = (TekoalyInputKomponentti)ai.getKomponentti(KomponenttiTyyppi.INPUT);
        tekoalyInput.asetaPallonPaikka((PaikkaKomponentti)pallo.getKomponentti(KomponenttiTyyppi.PAIKKA));
         
        return ai;
    }
    
    private Entiteetti luoPallo(int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti pallo = tehdas.luoEntiteetti(EntiteettiTyyppi.PALLO, x, y);
    
        return pallo;
    }
}
