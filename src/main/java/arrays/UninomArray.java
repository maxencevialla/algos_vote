package arrays;

import java.util.ArrayList;

/**
 * Created by maxence on 11/05/17.
 */
public class UninomArray {


    public static void main(String[] args) {
        ResultArray result = new ResultArray(5000000, 12);

        Integer[] premieresPlaces = new Integer[result.nbCandidats];

        for(int i = 0 ; i < result.nbCandidats ; i++) {
            premieresPlaces[i] = 0;
        }

        int maxPremieresPlaces = 0, numCandidatMaxPremieresPlaces = -1;

        long start = System.currentTimeMillis();

        //On parcourt les candidats
        for(int i = 0 ; i < result.nbVotants ; i++) {
            premieresPlaces[result.resultats.get(i).indexOf(1)]++;
        }

        for(int i = 0 ; i < result.nbCandidats ; i++) {
            if(premieresPlaces[i] > maxPremieresPlaces) {
                maxPremieresPlaces = premieresPlaces[i];
                numCandidatMaxPremieresPlaces = i;
            }
        }

        System.out.println("Vainqueur trouvé en " + (System.currentTimeMillis() - start) + "ms");

        for(int i = 0 ; i < result.nbCandidats ; i++) {
            System.out.println("Candidat n°" + i + " avec " + premieresPlaces[i] + " premières places");
        }

        System.out.println("\n");
        System.out.println("Vainqueur : candidat n°" + numCandidatMaxPremieresPlaces + " avec " + maxPremieresPlaces + " voix.");

    }
}
