package reittiopas;

import java.util.HashMap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.gson.*;

public class Pysakkiverkko2 {

    /** Kaikki pysÃƒÂ¤kit taulukossa. 
     *  
     *  Verkko on osittain suunnattu, eli jossain kohdissa pysÃƒÂ¤kkien vÃƒÂ¤lin
     *  pÃƒÂ¤ÃƒÂ¤see kulkemaan vain toiseen suuntaan.
     *  */
    private Pysakki[] pysakit;
    /** PysÃƒÂ¤kit key:koodi value:olio hakupareina. Oliot ovat samat kuin 
     *  pysakit-taulukossa. Pysakki p = this.psMap.get(Pysakki.koodi)
     * */
    private HashMap<String, Pysakki> psMap;
    /**	Kaikki linjat taulukossa.
     * 
     * 	Linjan molemmat suunnat ovat erikseen taulukossa ja ne ovat erotetta-
     *  vissa Linja.koodi:lla. 
     */
    private Linja[] linjat;
    /** Linjat key:koodi value:olio hakupareina. Oliot ovat samat kuin 
     *  linjat-taulukossa. 
     */
    private HashMap<String, Linja> lnMap;

    /** Konstruktori joka alustaa 'pysakit', 'psMap', 'linjat' sekÃƒÂ¤ 'lnMap' 
     *  tietorakenteet. Lukee 'verkko.json' sekÃƒÂ¤ 'linjat.json' tiedostot, 
     *  erittelee sekÃƒÂ¤ muuntaa niistÃƒÂ¤ kaikki Pysakki- ja Linja-oliot.
     * 
     * @param verkkoPolku /PATH/TO/verkko.json 
     * @param linjaPolku /PATH/TO/linjat.json
     */
    public Pysakkiverkko2(String verkkoPolku, String linjaPolku) {
        Gson gson = new Gson();

        // Luetaan pysÃƒÂ¤kit verkko.json tiedostosta.
        JsonArray psArr = readJSON(verkkoPolku);
        this.pysakit = new Pysakki[psArr.size()];
        for (int i = 0; i < psArr.size(); i++) {
            this.pysakit[i] = gson.fromJson(psArr.get(i), Pysakki.class);
        }
        this.psMap = new HashMap<String, Pysakki>();
        for (Pysakki p : pysakit) {
            this.psMap.put(p.getKoodi(), p);
        }

        /*
        for (Pysakki p: this.pysakit) {
        for (String s: p.naapurit.keySet()) {
        String ls = "";
        for (String c: p.naapurit.get(s)) ls = ls + c + " ";
        System.out.println(p.koodi +" "+ p.nimi +" -> " + s + " " + this.psMap.get(s).nimi + " " + ls); 	
        }
        }
         */

        // Luetaan raitiovaunulinjat linjat.json tiedostosta.
        JsonArray lnArr = readJSON(linjaPolku);
        this.linjat = new Linja[lnArr.size()];

        for (int i = 0; i < lnArr.size(); i++) {
            this.linjat[i] = gson.fromJson(lnArr.get(i), Linja.class);
        }
        this.lnMap = new HashMap<String, Linja>();
        for (Linja l : this.linjat) {
            this.lnMap.put(l.getKoodi(), l);
        }
    }

    /** Apumetodi, kÃƒÂ¤ytÃƒÂ¤ konstruktoria. Lukee annetun tiedoston palautettavaan
     *  Stringiin.
     * 
     * @param filePath path to file.
     * @return file as string
     * @throws java.io.IOException
     */
    private static String readFileAsString(String filePath) throws java.io.IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;

        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException ignored) {
                }
            }
        }
        return new String(buffer);
    }

    /** Apumetodi, kÃƒÂ¤ytÃƒÂ¤ konstruktoria. Parsii annetusta tiedostosta JSON
     *  taulukon.
     *
     * @param filePath tiedostopolku luettavaan tiedostoon
     * @return JsonArray, joka edustaa tiedostosta luettuja JSON-olioita.
     */
    private static JsonArray readJSON(String filePath) {

        JsonParser parser = new JsonParser();
        String json = "";
        try {
            json = readFileAsString(filePath);
        } catch (Exception e) {
        }

        JsonArray arr = parser.parse(json).getAsJsonArray();
        return arr;
    }

    /**
     * Palauttaa pysÃ¤kkikoodia vastaavan PysÃ¤kki-olion viitteen
     * 
     * @param koodi pysÃ¤kin tunniste
     * @return tunnistetta vastaava pysÃ¤kki
     */
    public Pysakki getPysakki(String koodi) {

        return psMap.get(koodi);
    }

    /**
     * Palauttaa verkon pysÃ¤kit.
     * 
     * @return PysÃ¤kki-oliot
     */
    public Pysakki[] getPysakkilista() {

        return pysakit;
    }

    public Linja[] getLinjat() {

        return this.linjat;
    }

    public Linja getLinja(String koodi) {

        return lnMap.get(koodi);
    }
}