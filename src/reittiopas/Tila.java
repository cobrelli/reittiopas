package reittiopas;

/**
 * Tila-listan alkio. Tila-listaa voi kÃ¤yttÃ¤Ã¤ merkkijonojen, kuten
 * pysÃ¤kkikoodien tallettamiseen linkitettynÃ¤ listana.
 */
public class Tila {

    private Tila edellinen;
    private String koodi;

    /**
     * Konstruktori, joka luo tyhjÃ¤n alkion.
     */
    public Tila() {
        this.koodi = "";
        this.edellinen = null;
    }

    /**
     * Konstruktori, joka luo valmiin alkion.
     *
     * @param koodi merkkijono, esimerkiksi pysÃ¤kkikoodi.
     * @param edellinen viite listan edelliseen alkioon.
     */
    public Tila(String koodi, Tila edellinen) {
        this.koodi = koodi;
        this.edellinen = edellinen;
    }

    /**
     * Palauttaa listan edellisen alkion.
     *
     * @return viite listan edelliseen alkioon.
     */
    public Tila getEdellinen() {

        return this.edellinen;
    }

    /**
     * Palauttaa alkion tallettaman merkkijonon.
     *
     * @return listan alkion arvo, esimerkiksi pysÃ¤kkikoodi.
     */
    public String getKoodi() {

        return this.koodi;
    }
}