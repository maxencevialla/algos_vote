package methodes;

import io.ResultArray;
import io.Resultat;

import java.util.*;

/**
 * Created by maxence on 15/05/17.
 */
public class Borda extends Methode {

    public static Resultat getResult(ResultArray r) {

        Integer[] nbVoix = new Integer[r.getNbCandidats()];

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            nbVoix[i] = 0;
        }

        //On parcourt les candidats
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
            for(int j = 0 ; j < r.getNbCandidats() ; j++) {
                nbVoix[j] += (r.getNbCandidats() + 1) - r.getVotes().get(i).get(j);
            }
        }

        //On place les couples <candidat, nombreDePremieresPlaces> dans une treemap classée par nb de voix
        Map<Integer, Integer> mapClassement = new TreeMap<Integer, Integer>(Collections.<Integer>reverseOrder());
        for(int i = 0 ; i < nbVoix.length ; i++) {
            mapClassement.put(nbVoix[i], i);
        }

        List<Integer> resultList = new ArrayList<Integer>();

        for(Integer i : mapClassement.keySet()) {
            resultList.add(mapClassement.get(i));
        }

        Resultat res = new Resultat(resultList, UninomUnTour.class.getName());

        return res;
    }
}