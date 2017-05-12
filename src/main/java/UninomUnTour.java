import java.util.*;

/**
 * Created by maxence on 12/05/17.
 */
public class UninomUnTour {
    public static Resultat getResult(ResultArray r) {

        Integer[] premieresPlaces = new Integer[r.getNbCandidats()];

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            premieresPlaces[i] = 0;
        }

        int maxPremieresPlaces = 0, numCandidatMaxPremieresPlaces = -1;

        //On parcourt les candidats
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
            premieresPlaces[r.getVotes().get(i).indexOf(1)]++;
        }

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            if(premieresPlaces[i] > maxPremieresPlaces) {
                maxPremieresPlaces = premieresPlaces[i];
                numCandidatMaxPremieresPlaces = i;
            }
        }

        Resultat res = new Resultat(numCandidatMaxPremieresPlaces, convertResToMap(premieresPlaces));



        return res;
    }

    public static Map convertResToMap(Integer[] t) {
        Map<Integer, Integer> m = new TreeMap<Integer, Integer>();

        for(int i = 0 ; i < t.length ; i++) {
            m.put(t[i], i);
        }

        return m;
    }
}
