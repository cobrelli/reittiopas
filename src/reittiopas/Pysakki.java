package reittiopas;

import java.util.HashMap;

/**
 * Luokka pysÃ¤kkiverkon solmun tietojen tallennukseen.
 */
public class Pysakki {

    private String koodi;
    private String osoite;
    private String nimi;
    private int x;
    private int y;
    private HashMap<String, String[]> naapurit;

    /**
     * Konstruktori, joka luo tyhjÃ¤n olion.
     */
    public Pysakki() {

        this.koodi = "";
        this.osoite = "";
        this.nimi = "";
        this.x = 0;
        this.y = 0;
        this.naapurit = new HashMap<String, String[]>();
    }

    /**
     * Palauttaa pysÃ¤kin koodin.
     *
     * @return PysÃ¤kin yksiselitteinen koodi. ID, jota voit kÃ¤yttÃ¤Ã¤ pysÃ¤kin
     * tunnistamiseen.
     */
    public String getKoodi() {

        return this.koodi;
    }

    /**
     * Palauttaa pysÃ¤kin osoitteen.
     *
     * @return PysÃ¤kin katuosoite. Jotkin osoitteet sisÃ¤ltÃ¤vÃ¤t kadun ja
     * numeron, toiset vain kadun nimen.
     */
    public String getOsoite() {
        return this.osoite;
    }

    /**
     * Palauttaa pysÃ¤kin nimen.
     *
     * @return PysÃ¤kin nimi. TÃ¤mÃ¤ ei vÃ¤lttÃ¤mÃ¤ttÃ¤ ole yksiselitteinen.
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     * Palauttaa pysÃ¤kin sijainnin x-koordinaatin.
     *
     * @return PysÃ¤kin maantieteellisen sijainnin x-koordinaatti.
     */
    public int getXKoordinaatti() {
        return this.x;
    }

    /**
     * Palauttaa pysÃ¤kin sijainnin y-koordinaatin
     *
     * @return PysÃ¤kin maantieteellisen sijainnin y-koordinaatti.
     */
    public int getYKoordinaatti() {
        return this.y;
    }

    /**
     * Palauttaa naapuripysÃ¤kkien koodit.
     *
     * @return naapuripysÃ¤kit tunnuskoodit
     */
    public String[] getNaapurit() {

        return (this.naapurit.keySet()).toArray(new String[0]);
    }

    /**
     * Palauttaa yhdelle naapuripysÃ¤kille kulkevien linjojen koodit.
     *
     * @param naapuri jonkin naapuripysÃ¤kin tunnuskoodi
     * @return linjojen tunnukset
     */
    public String[] getNaapuriinKulkevatLinjat(String naapuri) {
        return naapurit.get(naapuri);
    }
}
