package methodes;

import exceptions.EgaliteException;
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

    public Resultat getResult(Urne r) throws WrongCandidateNumberException, EgaliteException {

        if(!checkNumberOfCandidats(r.getNbCandidats())) {
            return null;
        }

        Double[] nbVoix = new Double[r.getNbCandidats()];

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            nbVoix[i] = 0.0;
        }

        //On parcourt les candidats
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
            for(int j = 0 ; j < r.getNbCandidats() ; j++) {
                nbVoix[j] += (r.getNbCandidats() + 1) - r.getVotes().get(i).get(j);
            }
        }

        return new Resultat(classeParScore(nbVoix), Borda.class.getSimpleName());
    }

}
