package tables;

import arrays.ResultArray;
import tables.ResultMatrix;

/**
 * Created by maxence on 10/05/17.
 */
public class UninomUnTour {

    public static void printResult(ResultMatrix m) {
        Integer[][] resultMatrix = m.resultats;

        Integer[] premieresPlaces = new Integer[m.nbCandidats];
        int maxPremieresPlaces = 0, numCandidatMaxPremieresPlaces = -1;

        long start = System.currentTimeMillis();

        //On parcourt les candidats
        for(int i = 0 ; i < m.nbCandidats ; i++) {
            int tmp = 0; //Nombre de premières places du candidat courant

            //On compte les premières places du candidat courant
            for(int j = 0 ; j < m.nbVotants ; j++) {
                if(resultMatrix[j][i] == 1) {
                    tmp++;
                }
            }
            //Maj du candidat premier si on a plus de premières places pour i que pour le candidat stocké
            if(tmp > maxPremieresPlaces) {
                numCandidatMaxPremieresPlaces = i;
                maxPremieresPlaces = tmp;
            }
            premieresPlaces[i] = tmp;
        }

        System.out.println("Vainqueur trouvé via tableaux en " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("Vainqueur : candidat n°" + numCandidatMaxPremieresPlaces + " avec " + maxPremieresPlaces + " voix.");

    }
}
