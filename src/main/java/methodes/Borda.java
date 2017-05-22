package methodes;

import exceptions.WrongCandidateNumberException;
import io.Urne;
import io.Resultat;

import java.util.*;

/**
 * Created by maxence on 16/05/17.
 */
public class Borda extends Methode {
    private static Borda ourInstance = new Borda();

    public static Borda getInstance() {
        return ourInstance;
    }

    private Borda() {
    }

    public Resultat getResult(Urne r) throws WrongCandidateNumberException {

        if(!checkNumberOfCandidats(r.getNbCandidats())) {
            return null;
        }

        Map<Integer, Byte> mapClassement = getNbPoints(r);

        List<Byte> resultList = new ArrayList<Byte>();

        for(Integer i : mapClassement.keySet()) {
            resultList.add(mapClassement.get(i));
        }

        Resultat res = new Resultat(resultList, Borda.class.getSimpleName());

        return res;
    }

    private Map<Integer, Byte> getNbPoints(Urne r) {
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
        Map<Integer, Byte> mapClassement = new TreeMap<Integer, Byte>(Collections.<Integer>reverseOrder());
        for(int i = 0 ; i < nbVoix.length ; i++) {
            mapClassement.put(nbVoix[i], (byte)i);
        }

        return mapClassement;
    }
}
