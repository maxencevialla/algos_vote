package methodes;

import exceptions.WrongCandidateNumberException;
import io.Urne;
import io.Resultat;

import java.util.*;

/**
 * Created by maxence on 15/05/17.
 */
public abstract class Methode {

    public abstract Resultat getResult(Urne r) throws WrongCandidateNumberException;

    /** Le nombre de candidats doit rentrer dans unByte
    *   doit être supérieur à 2 pour garantir l'efficacité des méthodes de vote
     */
    public static boolean checkNumberOfCandidats(int nbCandidats) throws WrongCandidateNumberException {
        if(nbCandidats < 2 || nbCandidats > 127) {
            throw new WrongCandidateNumberException("Le nombre de candidats doit être compris entre 2 et 127 (actuel : "
                    + nbCandidats + ")");
        }
        return true;
    }

    /**
     *  Calcule les résultats sur l'urne r, affiche le classement pour chacune des méthodes, et le temps nécessaire pour chaque méthode
     *  Principalement utile en débuggage
     *
     * @param r
     * @throws Exception
     */
    public void printAndTimeResult(Urne r) throws WrongCandidateNumberException {
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

    /**
     *
     * @param scores : score de chaque candidat, la place dans le tableau représentant le numéro de chaque candidat
     * @return classement des candidats
     */
    public List<Byte> classeParScore(Double[] scores) {
        Map<Double, Byte> mapClassement = new TreeMap<Double, Byte>(Collections.<Double>reverseOrder());

        for(byte i = 0 ; i < scores.length ; i++) {
            mapClassement.put(scores[i], i);
        }

        List<Byte> resultList = new ArrayList<Byte>();

        for(Double d : mapClassement.keySet()) {
            resultList.add(mapClassement.get(d));
        }

        return resultList;
    }
}
