package reittiopas;

import java.util.HashMap;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.google.gson.*;

/**
 * Luokka pysÃ¤kkiverkon kuvaamiseen ja sen tietojen lukemiseen JSON-tiedostosta
 */
public class Pysakkiverkko {

    /** 
     * Kaikki pysakit taulukossa. 
     * 
     * 	Pari huomioita pysakkien muodostamasta verkosta: 
     *  1.) verkko on osittain suunnattu, eli jossain kohdissa pysakkien valin
     *  paasee kulkemaan vain toiseen suuntaan.
     *  
      */
    private Pysakki[] pysakit;
    
    /** Pysakit key: koodi value: olio hakupareina. Oliot ovat samat kuin 
     *  pysakit-taulukossa. 
     * */
    private HashMap<String, Pysakki> psMap;

    /** 
     * Konstruktori. Lukee verkkoa kuvaavan JSON-tiedoston ja luo sen pohjalta 
     * Pysakki-olioiden verkon.
     * 
     * @param filePath polku tiedostoon (sis. tiedoston nimen)
     */
    public Pysakkiverkko(String filePath) {
        
        JsonArray psArr = readJSON(filePath);
        Gson gson = new Gson();
        this.pysakit = new Pysakki[psArr.size()];
        for (int i = 0; i < psArr.size(); i++) {
            this.pysakit[i] = gson.fromJson(psArr.get(i), Pysakki.class);
        }
        this.psMap = new HashMap<String, Pysakki>();
        for (Pysakki p : pysakit) {
            this.psMap.put(p.getKoodi(), p);
        }
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
        
        return pysakit.clone();
    }

    /**
     * Lukee annetun tiedoston palautettavaan Stringiin
     * @param filePath tiedoston sijainti
     * @return tiedoston sisÃ¤ltÃ¶ merkkijonona
     */
    private static String readFileAsString(String filePath) throws IOException {
        
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

    /** 
     * Parsii annetusta tiedostosta JSON-taulukon.
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
}
