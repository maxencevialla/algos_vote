package methodes;

import exceptions.EgaliteException;
import exceptions.WrongCandidateNumberException;
import io.Urne;
import io.Resultat;

import java.util.List;

/**
 * Created by maxence on 16/05/17.
 */
public class UninomDeuxTours extends Methode {
    private static UninomDeuxTours ourInstance = new UninomDeuxTours();

    public static UninomDeuxTours getInstance() {
        return ourInstance;
    }

    private UninomDeuxTours() {
    }

    public Resultat getResult(Urne r) throws WrongCandidateNumberException, EgaliteException {

        if(!checkNumberOfCandidats(r.getNbCandidats())) {
            return null;
        }

        Resultat res = UninomUnTour.getInstance().getResult(r);

        //Récupération du classement du premier tour
        List<Byte> classementPremierTour = res.getClassement();

        byte premier = classementPremierTour.get(0);
        byte second = classementPremierTour.get(1);

        int nbVotesPourPremier = 0, nbVotesPourSecond = 0;

        //Comptage des votes du second tour
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
            if(r.getVotes().get(i).get(premier) > r.getVotes().get(i).get(second)) {
                nbVotesPourPremier++;
            } else {
                nbVotesPourSecond++;
            }
        }

        List<Byte> classementSecondTour = classementPremierTour;
        //System.out.println("Premier : " + nbVotesPourPremier + " voix.");
        //System.out.println("Second : " + nbVotesPourSecond + " voix.");

        //On inverse le premier et le second du classement du premier tour si besoin
        if(nbVotesPourPremier > nbVotesPourSecond) {
            classementSecondTour.set(0, second);
            classementSecondTour.set(1, premier);
        }

        Resultat resultat = new Resultat(classementSecondTour, UninomDeuxTours.class.getSimpleName());

        return resultat;
    }
}
