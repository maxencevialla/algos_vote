package methodes;

import io.Urne;
import io.Resultat;

/**
 * Created by maxence on 15/05/17.
 */
public abstract class Methode {

    public abstract Resultat getResult(Urne r);

    public static boolean checkNumberOfCandidats(int nbCandidats) {
        if(nbCandidats < 2) {
            return false;
        }
        return true;
    }

    public void printAndTimeResult(Urne r) {
        long start = System.currentTimeMillis();
        Resultat res = getResult(r);

        if(res == null) {
            return;
        }

        System.out.println(res.getNomMethode() + " calculé en " + (System.currentTimeMillis() - start) + "ms.");

        for(int i = 0 ; i < res.getClassement().size() ; i++) {
            System.out.print(res.getClassement().get(i) + " ");
        }

        System.out.println();
    }
}
