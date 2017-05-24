package methodes;

import exceptions.EgaliteException;
import exceptions.WrongCandidateNumberException;
import io.Resultat;
import io.Urne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by maxence on 24/05/17.
 */
public class VoteAlternatif extends Methode {
    private static VoteAlternatif ourInstance = new VoteAlternatif();

    public static VoteAlternatif getInstance() {
        return ourInstance;
    }

    private VoteAlternatif() {
    }

    @Override
    public Resultat getResult(Urne r) throws WrongCandidateNumberException, EgaliteException {

        double maxVotes = 0.0;
        Double nbVotes[] = new Double[r.getNbCandidats()];
        List<Byte> classement, classementFinal = new ArrayList<>();
        Byte numCandidatElimine = -1;


        //On fait un tour supplémentaire tant qu'on a pas atteint le quota de votes pour 1 candidat
        while(maxVotes <= r.getNbVotants()/2) {
            System.out.println("Classement final de longueur " + classementFinal.size());

            //Réinitialisation du nombre de votes de chaque candidat
            for(int i = 0 ; i < r.getNbCandidats() ; i++) {
                nbVotes[i] = 0.0;
            }

            //Comptage des voix pour chaque candidat
            for(int i = 0 ; i < r.getNbVotants() ; i++) {
                //Si on n'est pas au premier tour, on supprime des votes le candidat éliminé au tour précédent
                if(numCandidatElimine != -1) {
                    r.getVotes().get(i).remove(numCandidatElimine);
                }
                //System.out.println(r.getVotes().get(i).indexOf((byte)1));
                int min = Collections.min(r.getVotes().get(i));

                nbVotes[r.getVotes().get(i).indexOf((byte)min)]++;
            }


            //mise à jour du classementet détermination du candidat à éliminer
            classement = classeParScore(nbVotes);

            for(Byte b : classement) {
                System.out.print(b + " ");
            }

            //TODO pb actuel : on élimine le candidat qui a le moins de vote, soit généralement quelqu'un de déjà éliminé....
            
            numCandidatElimine = classement.get(classement.size()-classementFinal.size()-1);
            classementFinal.add(0, numCandidatElimine);

            System.out.println("Elimine candidat " + numCandidatElimine);

            //maj du nombre de vote maximal
            maxVotes = nbVotes[Collections.max(classement)];
            try {
                Thread.sleep(500);
            } catch(Exception e) {

            }

        }

        return null;
    }
}
