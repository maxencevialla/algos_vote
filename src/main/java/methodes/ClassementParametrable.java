package methodes;

import exceptions.WrongCandidateNumberException;
import io.Resultat;
import io.Urne;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by maxence on 22/05/17.
 */
public class ClassementParametrable extends Methode {
    private static ClassementParametrable ourInstance = new ClassementParametrable();

    public static ClassementParametrable getInstance() {
        return ourInstance;
    }

    private ClassementParametrable() {
    }

    private static Function<ArrayList<Byte>, Integer> param;

    public static void setParam(Function<ArrayList<Byte>, Integer> myParam) {
        param = myParam;
    }

    @Override
    public Resultat getResult(Urne r) throws WrongCandidateNumberException {

        Integer[] classementMoyen = new Integer[r.getNbCandidats()];
        ArrayList<Byte> classementCandidatCourant;

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            classementCandidatCourant = null; //Initialisation à chaque candidat
            for(int j = 0 ; j < r.getNbVotants() ; j++) {
                //On agrège tous les classements du candidat i
                classementCandidatCourant.add(r.getVotes().get(j).get(i));
            }

            classementMoyen[i] = param.apply(classementCandidatCourant);
        }

        return new Resultat(classeParScore(classementMoyen), ClassementParametrable.class.getSimpleName());
    }
}
