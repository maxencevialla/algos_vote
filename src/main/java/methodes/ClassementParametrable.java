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

        //Les fonctions de la classe Méthode travaillent en plaçant le score le plus haut en premier
        //Ici, on travaille sur un classement, dont on cherche à minimiser la valeur
        //On choisit donc de remplacer chaque classement par son inverse, ce qui permet d'inverser le classement en conservant l'ordre
        //On cherche alors à maximiser ses inverses
        for(int i = 0 ; i < classementMoyen.length ; i++) {
            classementMoyen[i] = 1/classementMoyen[i];
        }

        Resultat res = genereResultatParScore(r.getNumCandidatPref(), classementMoyen);

        res.setNomMethode(ClassementParametrable.class.getSimpleName() + "_" + this.nomMethode);

        return res;
    }
}
