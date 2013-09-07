package reittiopas;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Reittioppaan pÃ¤Ã¤luokka.
 */
public class Reittiopas1 {

    /**
     * Toteuta leveyssuuntainen haku. Pida muistissa omassa hakutilaoliossasi
     * mikÃ¤ oli edeltava tila, josta ko. tilaan pÃ¤Ã¤stiin.
     *
     * Maaliin pÃ¤Ã¤styÃ¤si tulosta lÃ¤pikÃ¤ytyjen pysÃ¤kkien koodit (ja nimet)
     * Voit halutessasi myos visualisoida kuljettua reittiÃ¤ rLine-metodin
     * avulla.
     *
     * @param bfs pysÃ¤kkiverkko
     * @param lahto Lahtopysakin koodi
     * @param maali Maalipysakin koodi
     */
    public static void haku(Pysakkiverkko bfs, String lahto, String maali) {

        PriorityQueue<String> lista = new PriorityQueue<>();
        PriorityQueue<String> kaydyt = new PriorityQueue<>();

        lista.add(lahto);
        kaydyt.add(lahto);

        HashMap<String, Tila> edel = new HashMap<>();

        edel.put(lahto, new Tila(lahto, null));

        while (!lista.isEmpty()) {

            String t = lista.poll();

            if (t.equals(maali)) {
                System.out.println("Löydetty " + t + " on sama kuin " + maali);
                break;
            }

            for (String s : bfs.getPysakki(t).getNaapurit()) {
                if (!kaydyt.contains(s)) {
                    kaydyt.add(s);
                    lista.add(s);
                    if (!edel.containsKey(s)) {
                        edel.put(s, new Tila(t, edel.get(t)));
                    }
                }
            }
        }
        tulosta(maali, lahto, bfs, edel);
    }

    private static void tulosta(String maali, String lahto, Pysakkiverkko bfs, HashMap<String, Tila> edel) {
        String p = maali;

        Stack<String> reitti = new Stack();

        while (!p.equals(lahto)) {
            reitti.add(bfs.getPysakki(p).getOsoite());
            p = edel.get(p).getKoodi();
        }

        while (!reitti.empty()) {
            System.out.println(reitti.pop());
        }
    }

    /**
     * Tulostaa annetun reitin R plot komennot. Kutsu tata metodia, ja
     * copypastea tulostuvat kolmerivia R:n komentoriville sen jalkeen kun olet
     * kirjoittanut source("/PATH/TO/rplot.txt") - komennon ja R on piirtany
     * raitiovaunuverkon uuteen ikkunaan. Valitsemasi reitin tulisi nakya verkon
     * paalla oranssina.
     *
     * Vapaavalintainen visualisointityokalu jos haluaa kayttaa.
     *
     * @param x reitin (pysakkien) x-koordinaatit
     * @param y reitin (pysakkien) y-koordinaatit
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

    /**
     * Tulostaa kahden pysÃ¤kin vÃ¤lisen reitin, jolla on vÃ¤hiten
     * vÃ¤lipysÃ¤kkejÃ¤
     *
     * @param args komentoriviparametrit
     */
    public static void main(String[] args) {

        Pysakkiverkko bfs = new Pysakkiverkko("./verkko.json");
        System.out.println("");
        haku(bfs, "1250429", "1121480");
//        System.out.println(bfs.getPysakki("1121480").getOsoite()); // Testi

    }
}
