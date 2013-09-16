package reittiopas;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Reittiopas2 {

    /**
     * Toteuta reittiopas A*-algoritmin avulla.
     *
     * Viime viikon leveyssuuntaiseen hakuun lisÃ¤tÃ¤Ã¤n nyt raitiovaunulinjat,
     * joiden avulla kuljetaan pysÃ¤kiltÃ¤ toiselle. PysÃ¤kki-oliot tietÃ¤vÃ¤t
     * viereiset pysÃ¤kin ja kaikki niille matkustavat linjat. Linja-oliot
     * tietÃ¤vÃ¤t kunkin pysÃ¤kin vÃ¤lisen ajan (voi vaihdella
     * linjakohtaisesti). Mielikuvitusmaailmassamme kaikki raitiovaunulinjat
     * lÃ¤htevÃ¤t 10 minuutin vÃ¤lein linjan ensimmÃ¤iseltÃ¤ pysÃ¤kiltÃ¤ ja
     * matkustaminen lÃ¤htÃƒÂ¶pysÃ¤kiltÃ¤ aloitetaan 0-9 minuutin kohdalla.
     *
     * Leveyssuuntaisesta hausta poiketen tarvitset nyt pitÃ¤Ã¤ muistissa omassa
     * hakutila-oliossasi ainakin: - hakutila, josta tÃ¤hÃ¤n tilaan on tultu -
     * aika, joka on jo kulunut lÃ¤hdÃ¤stÃ¤ - kellon aika joka tÃ¤llÃ¤ hetkellÃ¤
     * on (kulunut aika + lÃ¤htÃ¤aika) - heuristinen arvio kauan tÃ¤stÃ¤
     * hakutilasta kuluu aikaa maaliin (voit olettaa maksiminopeuden olevan 526
     * koordinaattipistettÃ¤ minuutissa)
     *
     * LisÃ¤ksi reitin tulostusta varten on hyÃ¶dyllistÃ¤ muistaa: - millÃ¤
     * linjalla tÃ¤hÃ¤n tilaan tultiin - kauan odotettiin viime pysÃ¤killÃ¤ -
     * kauan matkustettiin viime pysÃ¤kiltÃ¤
     *
     * JÃ¤rjestÃ¤ kÃ¤ytÃ¤vÃ¤t tilat kuluneen ajan + heuristisen arvion
     * perusteella nopein ensimmÃ¤iseksi.
     *
     * Maaliin pÃ¤Ã¤styÃ¤si tulosta reitin jokaisen pysÃ¤kin koodi, nimi ja
     * aika, jolloin sillÃ¤ pysÃ¤killÃ¤ ollaan, sekÃ¤ millÃ¤ linjalla pysÃ¤kkien
     * vÃ¤li matkustetaan (odotusaika ei ole vÃ¤lttÃ¤mÃ¤tÃ¤n). Leveyssuuntaisen
     * haun tapaan voit halutessasi visualisoida kuljettua reittiÃ¤
     * rLine-metodin avulla.
     *
     * Vinkki: kÃ¤ytÃ¤ hakutila-listauksessa PriorityQueue:ta ja toteuta omalle
     * hakutila-oliollesi Comparable<hakutila>-rajapinta, eli kirjoita sille
     * metodi compareTo, joka palauttaa arvot vastaavasti kuin esim.
     * String-luokan compareTo. Jos kÃ¤ytÃ¤t valmiina annettua luokkaa Tila2, on
     * se valmiiksi toteutettuna.
     *
     * http://docs.oracle.com/javase/6/docs/api/java/util/PriorityQueue.html
     * http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html
     *
     * @param verkko pysÃ¤kkiverkko
     * @param lahto lÃ¤htÃ¶pysÃ¤kin koodi
     * @param maali maalipysÃ¤kin koodi
     * @param aika LÃ¤htÃ¶aika (voit olettaa sen olevan 0-9 minuuttia)
     */
    public static void haku(Pysakkiverkko2 verkko, String lahto, String maali, int aika) {

        PriorityQueue<Tila2> pq = new PriorityQueue<>();
        ArrayList<String> loydetyt = new ArrayList<>();
        Tila2 maaliTila = null;
        boolean loydetty = false;

        Tila2 t = new Tila2(lahto, null, lahto, Math.abs(0 - aika), 0, aika, heuristinenArvio(lahto, maali, verkko));

        pq.add(t);

        while (!pq.isEmpty() && !loydetty) {
            t = pq.poll();
            loydetyt.add(t.getLinja());


            for (String uusi : verkko.getPysakki(t.getLinja()).getNaapurit()) {
                if (uusi.equals(maali)) {
//                    System.out.println("jep");
                    loydetty = true;
                    maaliTila = new Tila2(uusi, t, maali, Math.abs(0 - aika), 0, aika, heuristinenArvio(uusi, maali, verkko));
                    break;
                }
                if (!sisaltaako(uusi, pq) && !loydetyt.contains(uusi)) {
//                    System.out.println("lisätty");
                    pq.add(new Tila2(uusi, t, uusi, 0, 0, t.getNykyinenAika(), heuristinenArvio(uusi, maali, verkko)));
                }
            }
        }

//        for (String string : loydetyt) {
//            System.out.println(string);
//        }

        while (maaliTila != null) {
            System.out.println("Pysäkki nro: " + maaliTila.getLinja() + " " + verkko.getPysakki(maaliTila.getLinja()).getNimi() + " nykyinen aika: " + maaliTila.getNykyinenAika());
            maaliTila = maaliTila.getEdellinen();
        }


//        System.out.println(maaliTila.getLinja());

    }

    public static boolean sisaltaako(String osoite, PriorityQueue<Tila2> q) {
        for (Tila2 t : q) {
            if (t.getPysakki().equals(osoite)) {
                return true;
            }
        }
        return false;
    }

    public static double heuristinenArvio(String a, String maali, Pysakkiverkko2 v) {
        int aX = v.getPysakki(a).getXKoordinaatti();
        int aY = v.getPysakki(a).getYKoordinaatti();
        int mX = v.getPysakki(maali).getXKoordinaatti();
        int mY = v.getPysakki(maali).getYKoordinaatti();
        return Math.abs(mX - aX) + Math.abs(mY - aY);
    }

    /**
     * Laskee nopeimman linjan ja siltÃ¤ kuluvan ajan pysÃ¤kiltÃ¤
     * naapuripysÃ¤kille.
     *
     * @param verkko tutkittava pysÃ¤kkiverkko
     * @param p pysÃ¤kki jolla ollaan nyt
     * @param n naapuripysÃ¤kin koodi
     * @param aika aika juuri nyt
     * @return Object[3], jossa [0] String = Linja.koodi, [1] int = odotusaika,
     * [2] int = matka-aika
     */
    public static Object[] nopein(Pysakkiverkko2 verkko, Pysakki p, String n, int aika) {
        Object[] ret = {"", 100, 100}; // linja.koodi, odotusaika, matka-aika

        for (String s : p.getNaapuriinKulkevatLinjat(n)) {
            Linja l = verkko.getLinja(s);
            for (int i = 0; i < l.getPysakkikoodit().length; i++) {
                if (l.getPysakkikoodit()[i].equals(p.getKoodi())) {
                    // Lasketaan odotusaika modulo 10
                    int odotus = (l.getPysahtymisajat()[i] % 10) - (aika % 10);
                    odotus = odotus < 0 ? odotus + 10 : odotus;
                    int matka = l.getPysahtymisajat()[i + 1] - l.getPysahtymisajat()[i];
                    // Jos odotus + matka-aika on pienempi kuin aikaisempi,
                    // vaihdetaan nopeinta matkustuslinjaa
                    if ((Integer) ret[1] + (Integer) ret[2] > (odotus + matka)) {
                        ret[0] = l.getKoodi();
                        ret[1] = odotus;
                        ret[2] = matka;
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Pysakkiverkko2 verkko = new Pysakkiverkko2("./verkko2.json", "./linjat.json");
        System.out.println("");
        haku(verkko, "1150435", "1130446", 2);
    }

    /**
     * Tulostaa annetun reitin R:n piirtokomennot. Kutsu tÃ¤tÃ¤ metodia, ja
     * copypastea tulostuvat kolmeriviÃ¤ R:n komentoriville sen jÃ¤lkeen, kun
     * olet kirjoittanut source("/PATH/TO/rplot.txt") - komennon ja R on
     * piirtÃ¤nyt raitiovaunuverkon uuteen ikkunaan. Valitsemasi reitin tulisi
     * nÃ¤kyÃ¤ verkon pÃ¤Ã¤llÃ¤ oranssina.
     *
     * Vapaavalintainen visualisointi tyÃ¶kalu jos haluaa kÃ¤yttÃ¤Ã¤.
     *
     * @param x reitin (pysÃ¤kkien) x-koordinaatit
     * @param y reitin (pysÃ¤kkien) y-koordinaatit
     */
    public static void rLine(int[] x, int[] y) {
        String rx = "x <- c(";
        String ry = "y <- c(";

        for (int i = 0; i < x.length; i++) {
            rx = rx + x[i] + ", ";
            ry = ry + y[i] + ", ";
        }
        rx = rx.substring(0, rx.length() - 2) + ")";
        ry = ry.substring(0, ry.length() - 2) + ")";
        System.out.println(rx);
        System.out.println(ry);
        System.out.println("lines(x,y, lwd = 2, col = \"orange\")");
    }
}