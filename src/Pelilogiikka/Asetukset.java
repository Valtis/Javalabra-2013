package Pelilogiikka;

import Kayttoliittyma.NappulaKuuntelija;
import Pelilogiikka.Entiteetti.Entiteetti;
import Pelilogiikka.Entiteetti.EntiteettiTehdas;
import Pelilogiikka.Enumit.EntiteettiTyyppi;
import Pelilogiikka.Enumit.KomponenttiTyyppi;
import Pelilogiikka.Enumit.NappulaTyyppi;
import Pelilogiikka.Enumit.Suunta;
import Pelilogiikka.Komponentti.InputKomponentti;
import Pelilogiikka.Komponentti.Komponentti;
import Pelilogiikka.Komponentti.PaikkaKomponentti;
import Pelilogiikka.Komponentti.PalloPaikkaKomponentti;
import Pelilogiikka.Komponentti.TekoalyInputKomponentti;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Asetukset implements NappulaKuuntelija {

    Peli peli;
    Entiteetti pelaaja1;
    Entiteetti pelaaja2;
    Entiteetti pallo;
    Komponentti pelaajan1PelaajaKontrolli;
    Komponentti pelaajan2PelaajaKontrolli;
    boolean pelaaja1OnAI;
    boolean pelaaja2OnAI;
    

    void haeAsetukset(Peli peli) {
        this.peli = peli;
        alustaEntiteetit();

    }

    private void alustaEntiteetit() {
        pallo = luoPallo(800 / 2 - 10, 600 / 2 - 10);
        ((PalloPaikkaKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PAIKKA)).asetaPisteKuuntelija(peli);

        peli.lisaaEntiteetti(pallo, false);

        //  peli.lisaaEntiteetti(luoAI(250, 10, pallo), false);


        pelaaja1 = luoPelaaja(Suunta.VASEN, KeyEvent.VK_LEFT, Suunta.OIKEA, KeyEvent.VK_RIGHT, 250, 530);
        pelaaja2 = luoPelaaja(Suunta.VASEN, KeyEvent.VK_A, Suunta.OIKEA, KeyEvent.VK_D, 250, 10);

        peli.lisaaEntiteetti(pelaaja1, true);
        peli.lisaaEntiteetti(pelaaja2, true);


        pelaajan1PelaajaKontrolli = pelaaja1.getKomponentti(KomponenttiTyyppi.INPUT);
        pelaajan2PelaajaKontrolli = pelaaja2.getKomponentti(KomponenttiTyyppi.INPUT);
        pelaaja1OnAI = false;
        pelaaja2OnAI = false;
    }

    private Entiteetti luoPelaaja(Suunta ensimmainenSuunta, int ensimmaisenSuunnanNappain, Suunta toinenSuunta, int toisenSuunnanNappain, int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti pelaaja = tehdas.luoEntiteetti(EntiteettiTyyppi.PELAAJA_MAILA, x, y);

        InputKomponentti input = (InputKomponentti) pelaaja.getKomponentti(KomponenttiTyyppi.INPUT);
        input.asetaNappain(ensimmaisenSuunnanNappain, ensimmainenSuunta);
        input.asetaNappain(toisenSuunnanNappain, toinenSuunta);

        return pelaaja;
    }

    private Entiteetti luoAI(int x, int y, Entiteetti pallo) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti ai = tehdas.luoEntiteetti(EntiteettiTyyppi.TEKOALY_MAILA, x, y);

        TekoalyInputKomponentti tekoalyInput = (TekoalyInputKomponentti) ai.getKomponentti(KomponenttiTyyppi.INPUT);
        tekoalyInput.asetaPallonPaikka((PaikkaKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PAIKKA));

        return ai;
    }

    private Entiteetti luoPallo(int x, int y) {
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti e = tehdas.luoEntiteetti(EntiteettiTyyppi.PALLO, x, y);

        return e;
    }

    private Entiteetti luoKimpoilevaEste() {
        Random random = new Random();
        int x = 100 + random.nextInt() % 400;
        int y = 100 + random.nextInt() % 200;
        
        
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti e = tehdas.luoEntiteetti(EntiteettiTyyppi.KIMPOILEVA_ESTE, x, y);

        return e;
    }
    
     private Entiteetti luoStaattineEste() {
        Random random = new Random();
        int x = 100 + Math.abs(random.nextInt()) % 400;
        int y = 100 + Math.abs(random.nextInt()) % 200;
        EntiteettiTehdas tehdas = new EntiteettiTehdas();
        Entiteetti e = tehdas.luoEntiteetti(EntiteettiTyyppi.STAATTINEN_ESTE, x, y);

        return e;
    }
    
    @Override
    public void nappulaViesti(NappulaTyyppi nappula) {
        switch (nappula) {
            case STAATTINEN_ESTE:
                peli.lisaaEntiteetti(luoStaattineEste(), false);
                break;
            case KIMPOILEVA_ESTE:
                peli.lisaaEntiteetti(luoKimpoilevaEste(), false);
                break;
            case PELAAJA_1_INPUT_VAIHTO:
                if (pelaaja1OnAI) {
                    pelaaja1.lisaaKomponentti(KomponenttiTyyppi.INPUT, pelaajan1PelaajaKontrolli);
                } else {
                    vaihdaAI(pelaaja1);
                }
                pelaaja1OnAI = !pelaaja1OnAI;
                break;

            case PELAAJA_2_INPUT_VAIHTO:
                if (pelaaja2OnAI) {
                    pelaaja2.lisaaKomponentti(KomponenttiTyyppi.INPUT, pelaajan2PelaajaKontrolli);
                } else {
                    vaihdaAI(pelaaja2);
                }

                pelaaja2OnAI = !pelaaja2OnAI;
                break;
        }
    }

    private void vaihdaAI(Entiteetti pelaaja) {
        TekoalyInputKomponentti k = new TekoalyInputKomponentti();
        k.asetaOmaPaikka((PaikkaKomponentti) pelaaja.getKomponentti(KomponenttiTyyppi.PAIKKA));
        k.asetaPallonPaikka((PaikkaKomponentti) pallo.getKomponentti(KomponenttiTyyppi.PAIKKA));
        pelaaja.lisaaKomponentti(KomponenttiTyyppi.INPUT, k);
    }
}
