package reittiopas;

public class Linja {

    private String koodi;
    private String lyhytKoodi;
    private String nimi;
    private int[] x;
    private int[] y;
    private String[] psKoodit;
    private int[] psAjat;

    /**
     * Konstruktori, joka luo tyhjÃ¤n olion.
     */
    public Linja() {
        this.koodi = "";
        this.lyhytKoodi = "";
        this.nimi = "";
        this.x = null;
        this.y = null;
        this.psKoodit = null;
        this.psAjat = null;
    }

    /**
     * Palauttaa lyhyen linjakoodin.
     *
     * @return lyhyt linjakoodi, esim. 4, 3T. Sama molempiin suuntiin.
     */
    public String getLyhytKoodi() {

        return this.lyhytKoodi;
    }

    /**
     * Palauttaa linjan pitkÃ¤n koodin.
     *
     * @return linjan yksiselitteinen koodi, joka kertoo myÃ¶s suunnan. KÃ¤ytÃ¤
     * tÃ¤tÃ¤ tunnistaaksesi linja ja sen suunta yksikÃ¤sitteisesti.
     */
    public String getKoodi() {

        return this.koodi;
    }

    /**
     * Palauttaa linjan nimen.
     *
     * @return Linjan nimi, esim. Kaivopuisto - Kallio - ElÃ¤intarha.
     */
    public String getNimi() {

        return this.nimi;
    }

    /**
     * Palauttaa linjan pysÃ¤kkien koodit.
     *
     * @return linjan pysÃ¤kkien yksikÃ¤sitteiset koodit.
     */
    public String[] getPysakkikoodit() {

        return this.psKoodit;
    }

    /**
     * Palauttaa linjan pysÃ¤kkien x-koordinaatit.
     *
     * @return linjan kaikkien pysÃ¤kkien x-koordinaatit. JÃ¤rjestys on sama
     * kuin metodin getpsKoodit arvoilla.
     */
    public int[] getXKoordinaatit() {

        return this.x;
    }

    /**
     * Palauttaa linjan pysÃ¤kkien y-koordinaatit.
     *
     * @return linjan kaikkien pysÃ¤kkien y-koordinaatit. JÃ¤rjestys on sama
     * kuin metodin getpsKoodit arvoilla.
     */
    public int[] getYKoordinaatit() {

        return this.y;
    }

    /**
     * Palauttaa linjan pysÃ¤htymisajat.
     *
     * @return ajat lÃ¤htÃ¶hetkestÃ¤ laskien, jolloin linja on kullakin
     * pysÃ¤killÃ¤. JÃ¤rjestys on sama kuin metodin getpsKoodit arvoilla.
     */
    public int[] getPysahtymisajat() {

        return this.psAjat;
    }
}