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

    private String nomMethode;

    private ClassementParametrable() {
        super();
    }

    public ClassementParametrable(Function<ArrayList<Byte>, Double> myParam, String nomMethode) {
        this();
        this.param = myParam;
        this.nomMethode = nomMethode;
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

        Resultat res = genereResultatParScore(classementMoyen);

        //On travaille sur des valeurs agrégées de classement, on veut donc les classer du plus petit au plus grand
        Collections.reverse(res.getClassement());

        res.setNomMethode(ClassementParametrable.class.getSimpleName() + "_" + this.nomMethode);

        return res;
    }
}
