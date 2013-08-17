package Kayttoliittyma;
/**
 * Rajapinta jonka toteuttaja kuuntelee näppäimistösyötettä. Tarpeellinen yksikkötestaukseen
 */
public interface NappainKuuntelija {

    void nappainPainettu(int koodi);

    void nappainVapautettu(int koodi);
}
