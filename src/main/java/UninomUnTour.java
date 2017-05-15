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

        //On parcourt les candidats
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
            premieresPlaces[r.getVotes().get(i).indexOf(1)]++;
        }

        //On place les couples <candidat, nombreDePremieresPlaces> dans une treemap classée par nb de voix
        Map<Integer, Integer> mapClassement = new TreeMap<Integer, Integer>(Collections.<Integer>reverseOrder());
        for(int i = 0 ; i < premieresPlaces.length ; i++) {
            mapClassement.put(premieresPlaces[i], i);
        }

        List<Integer> resultList = new ArrayList<Integer>();

        for(Integer i : mapClassement.keySet()) {
            System.out.println("Candidat n°" + mapClassement.get(i) + " : " + i + " voix.");
            resultList.add(mapClassement.get(i));
        }

        Resultat res = new Resultat(resultList, UninomUnTour.class.getName());

        return res;
    }
}
