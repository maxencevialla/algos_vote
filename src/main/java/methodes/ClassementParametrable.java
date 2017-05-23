package methodes;

import exceptions.EgaliteException;
import exceptions.WrongCandidateNumberException;
import io.Resultat;
import io.Urne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Created by maxence on 22/05/17.
 */
public class ClassementParametrable extends Methode {

    private Function<ArrayList<Byte>, Double> param;

    private ClassementParametrable() {
        super();
    }

    public ClassementParametrable(Function<ArrayList<Byte>, Double> myParam) {
        this();
        this.param = myParam;
    }

    @Override
    public Resultat getResult(Urne r) throws WrongCandidateNumberException, EgaliteException {

        Double[] classementMoyen = new Double[r.getNbCandidats()];
        ArrayList<Byte> classementCandidatCourant;

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            classementCandidatCourant = new ArrayList<>(); //Initialisation à chaque candidat
            for(int j = 0 ; j < r.getNbVotants() ; j++) {
                //On agrège tous les classements du candidat i
                //System.out.println("Candidat " + i + ", votant " + j + " : " + r.getVotes().get(j).get(i));
                classementCandidatCourant.add(r.getVotes().get(j).get(i));
            }

            classementMoyen[i] = param.apply(classementCandidatCourant);
        }

        List<Byte> classement = classeParScore(classementMoyen);

        //On travaille sur des valeurs agrégées de classement, on veut donc les classer du plus petit au plus grand
        Collections.reverse(classement);

        return new Resultat(classement, ClassementParametrable.class.getSimpleName());
    }
}
