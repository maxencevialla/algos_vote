package methodes;

import exceptions.WrongCandidateNumberException;
import io.Urne;
import io.Resultat;

import java.util.*;

/**
 * Created by maxence on 16/05/17.
 */
public class UninomUnTour extends Methode {
    private static UninomUnTour ourInstance = new UninomUnTour();

    public static UninomUnTour getInstance() {
        return ourInstance;
    }

    private UninomUnTour() {
    }

    public Resultat getResult(Urne r) throws WrongCandidateNumberException {

        if(!checkNumberOfCandidats(r.getNbCandidats())) {
            return null;
        }

        Integer[] premieresPlaces = new Integer[r.getNbCandidats()];

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            premieresPlaces[i] = 0;
        }

        //On parcourt les candidats et met à jour les premières places
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
            premieresPlaces[r.getVotes().get(i).indexOf((byte)1)]++;
        }

        //On place les couples <candidat, nombreDePremieresPlaces> dans une treemap classée par nb de voix
        Map<Integer, Byte> mapClassement = new TreeMap<Integer, Byte>(Collections.<Integer>reverseOrder());
        for(byte i = 0 ; i < premieresPlaces.length ; i++) {
            mapClassement.put(premieresPlaces[i], i);
        }

        List<Byte> resultList = new ArrayList<Byte>();

        for(Integer i : mapClassement.keySet()) {
            //System.out.println("Candidat n°" + mapClassement.get(i) + " : " + i + " voix.");
            resultList.add(mapClassement.get(i));
        }

        Resultat res = new Resultat(resultList, UninomUnTour.class.getSimpleName());

        return res;
    }
}
