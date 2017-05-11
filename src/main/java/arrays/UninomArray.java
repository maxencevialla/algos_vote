package arrays;

import java.util.ArrayList;

/**
 * Created by maxence on 11/05/17.
 */
public class UninomArray {

    public static void printResult(ResultArray a) {

        Integer[] premieresPlaces = new Integer[a.nbCandidats];

        for(int i = 0 ; i < a.nbCandidats ; i++) {
            premieresPlaces[i] = 0;
        }

        int maxPremieresPlaces = 0, numCandidatMaxPremieresPlaces = -1;

        long start = System.currentTimeMillis();

        //On parcourt les candidats
        for(int i = 0 ; i < a.nbVotants ; i++) {
            premieresPlaces[a.resultats.get(i).indexOf(1)]++;
        }

        for(int i = 0 ; i < a.nbCandidats ; i++) {
            if(premieresPlaces[i] > maxPremieresPlaces) {
                maxPremieresPlaces = premieresPlaces[i];
                numCandidatMaxPremieresPlaces = i;
            }
        }

        System.out.println("Vainqueur trouvé via arrays en " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("Vainqueur : candidat n°" + numCandidatMaxPremieresPlaces + " avec " + maxPremieresPlaces + " voix.");

    }

}
