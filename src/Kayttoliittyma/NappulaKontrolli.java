package Kayttoliittyma;

import Pelilogiikka.Enumit.NappulaTyyppi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class NappulaKontrolli implements ActionListener {

    private JButton vaihdaPelaajan1InputNappula;
    private JButton vaihdaPelaajan2InputNappula;
    private JButton luoKimpoilevaEste;
    private JButton luoStaattinenEste;
    NappulaKuuntelija kuuntelija;

    public NappulaKontrolli(NappulaKuuntelija kuuntelija) {
        this.kuuntelija = kuuntelija;
    }

    public void asetaPelaaja1Vaihto(JButton nappula) {
        nappula.addActionListener(this);
        vaihdaPelaajan1InputNappula = nappula;
    }

    public void asetaPelaaja2Vaihto(JButton nappula) {
        nappula.addActionListener(this);
        vaihdaPelaajan2InputNappula = nappula;
    }

    public void asetaStaattinenEste(JButton nappula) {
        nappula.addActionListener(this);
        luoStaattinenEste = nappula;

    }

    public void asetaKimpoilevaEste(JButton nappula) {
        nappula.addActionListener(this);
        luoKimpoilevaEste = nappula;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vaihdaPelaajan1InputNappula) {
            kuuntelija.nappulaViesti(NappulaTyyppi.PELAAJA_1_INPUT_VAIHTO);
        } else if (e.getSource() == vaihdaPelaajan2InputNappula) {
            kuuntelija.nappulaViesti(NappulaTyyppi.PELAAJA_2_INPUT_VAIHTO);
        } else if (e.getSource() == luoKimpoilevaEste) {
            kuuntelija.nappulaViesti(NappulaTyyppi.KIMPOILEVA_ESTE);
        } else if (e.getSource() == luoStaattinenEste) {
            kuuntelija.nappulaViesti(NappulaTyyppi.STAATTINEN_ESTE);
        }
    }
}
