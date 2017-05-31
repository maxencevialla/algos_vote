package methodes;

import exceptions.EgaliteException;
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

    public Resultat getResult(Urne r) throws WrongCandidateNumberException, EgaliteException {

        if(!checkNumberOfCandidats(r.getNbCandidats())) {
            return null;
        }

        Double[] premieresPlaces = new Double[r.getNbCandidats()];

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            premieresPlaces[i] = 0.0;
        }

        //On parcourt les candidats et met à jour les premières places
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
            premieresPlaces[r.getVotes().get(i).indexOf((byte)1)]++;
        }

        Resultat res = genereResultatParScore(r.getNumCandidatPref(), premieresPlaces);
        res.setNomMethode(UninomUnTour.class.getSimpleName());

        return res;

        //return new Resultat(classeParScore(premieresPlaces), UninomUnTour.class.getSimpleName());
    }
}
