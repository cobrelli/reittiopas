package reittiopas;

public class Tila2 implements Comparable<Tila2> {

    private Tila2 edellinen;         // Tila, josta tÃ¤hÃ¤n tilaan tultiin
    private String pysakki;         // Pysakki.koodi
    private String linja;         // Linja.koodi
    private int odotusaika;                 // odotusaika viime pysÃ¤killÃ¤
    private int viimePysakkivalinMatkaaika;                 // viime kertaisen pysÃ¤kinvÃ¤lin aika
    private int nykyinenAika;
    private double heuristinenArvo;    // euklidinen etÃ¤isyys / 526

    public Tila2() {
        this.pysakki = "";
        this.edellinen = null;
        this.linja = "";
        this.odotusaika = 0;
        this.viimePysakkivalinMatkaaika = 0;
        this.nykyinenAika = 0;
        this.heuristinenArvo = 0;
    }

    public Tila2(String koodi, Tila2 edellinen, String linja, int odotusaika,
            int viimePysakkivalinMatkaaika, int aika, double heuristinenArvo) {
        this.pysakki = koodi;
        this.edellinen = edellinen;
        this.linja = linja;
        this.odotusaika = odotusaika;
        this.viimePysakkivalinMatkaaika = viimePysakkivalinMatkaaika;
        this.nykyinenAika = aika;
        this.heuristinenArvo = heuristinenArvo;
    }

    /**
     * Palauttaa edellisen tilan.
     *
     * @return tila, josta tÃ¤hÃ¤n tilaan tultiin.
     */
    public Tila2 getEdellinen() {

        return this.edellinen;
    }

    /**
     * Palauttaa nykyisen pysÃ¤kin.
     *
     * @return pysÃ¤kin koodi.
     */
    public String getPysakki() {

        return this.pysakki;
    }

    /**
     * Palauttaa nykyisen linjan koodin.
     *
     * @return linjan koodi.
     */
    public String getLinja() {

        return this.linja;
    }

    /**
     * Palauttaa odotusajan edellisellÃ¤ pysÃ¤killÃ¤.
     *
     * @return odotusaika minuutteina.
     */
    public int getOdotusaika() {

        return this.odotusaika;
    }

    /**
     * Palauttaa edellisen pysÃ¤kkivÃ¤lin keston.
     *
     * @return edellisellÃ¤ pysÃ¤kkivÃ¤lillÃ¤ kulunut aika minuutteina.
     */
    public int getViimePysakkivalinMatkaaika() {

        return this.viimePysakkivalinMatkaaika;
    }

    /**
     * Palauttaa ajan tÃ¤lle pysÃ¤kille tullessa.
     *
     * @return nykyinen aika minuutteina. Kokonaismatka-aika selviÃ¤Ã¤
     * vÃ¤hentÃ¤mÃ¤llÃ¤ tÃ¤stÃ¤ lÃ¤htÃ¶aika.
     */
    public int getNykyinenAika() {

        return this.nykyinenAika;
    }

    /**
     * Palauttaa arvion loppumatkan kestosta.
     *
     * @return heuristinen arvio loppumatkan kestosta minuutteina. Laskettu
     * tÃ¤mÃ¤n pysÃ¤kin ja maalipysÃ¤kin vÃ¤lisen euklidisen etÃ¤isyyden
     * perusteella.
     */
    public double getHeuristinenArvo() {

        return this.heuristinenArvo;
    }

    @Override
    public int compareTo(Tila2 t) {
        if (this.nykyinenAika + this.heuristinenArvo > t.nykyinenAika + t.heuristinenArvo) {
            return 1;
        }
        if (this.nykyinenAika + this.heuristinenArvo < t.nykyinenAika + t.heuristinenArvo) {
            return -1;
        }
        return 0;
    }
}