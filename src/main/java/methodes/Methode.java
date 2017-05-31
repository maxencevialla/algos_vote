package methodes;

import exceptions.EgaliteException;
import exceptions.WrongCandidateNumberException;
import io.Urne;
import io.Resultat;

import java.util.*;

/**
 * Created by maxence on 15/05/17.
 */
public abstract class Methode {

    public abstract Resultat getResult(Urne r) throws WrongCandidateNumberException, EgaliteException;

    /** Le nombre de candidats doit rentrer dans un Byte
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
    public void printAndTimeResult(Urne r) throws WrongCandidateNumberException, EgaliteException {
        long start = System.currentTimeMillis();
        Resultat res = getResult(r);

        if(res == null) {
            return;
        }

        System.out.println(
                res.getClassement().get(0) + " gagne via " +
                res.getNomMethode() + " (calculé en " + (System.currentTimeMillis() - start) + "ms)");

       /* for(int i = 0 ; i < res.getClassement().size() ; i++) {
            System.out.print(res.getClassement().get(i) + " ");
        }

        System.out.println();*/
    }

    /**
     *
     * @param scores : score POSITIF OU NUL de chaque candidat, la place dans le tableau représentant le numéro de chaque candidat
     * @return classement des candidats
     */
    //TODO gérer l'influence sur l'égalité si un des candidats à égalité doit être privilégié
   public List<Byte> classeParScore(Double[] scores) {
        List<Double> s = Arrays.asList(scores);
        List<Byte> r = new ArrayList<>();
        int i;

        while(Collections.max(s) > -1) {
            i = s.indexOf(Collections.max(s));
            r.add((byte)i);
            s.set(i, -1.0);
        }
        return r;
    }


    public Resultat genereResultatParScore(Double[] scores) {
        List<Byte> classement = new ArrayList<>();
        Set<Set<Byte>> egalites = new HashSet<>();

        System.out.print("Scores : ");
        for(int a = 0 ; a < scores.length ; a++) {
            System.out.print(scores[a] + " ");
        }
        System.out.println();

        double scoreCourant;
        Set<Byte> setCourant;

        //On recherche les égalités et on remplit la liste de set
        for(int i = 0 ; i < scores.length ; i++) {
            scoreCourant = scores[i];
            setCourant = new HashSet<>();

            //Recherche des candidats ayant le même score que i
            for(int j = 0 ; j < scores.length ; j++) {
                //Le set ne permet pas la présence de doublons, donc pas de risque d'ajouter plusieurs fois le même candidat
                if(scores[j] == scoreCourant && j != i) {
                    setCourant.add((byte)i); //On traite les candidats à égalité avec i
                    setCourant.add((byte)j);
                }
            }
            //Le set ne permet pas la présence de doublons, donc pas de risque d'avoir 2 sets identiques dans egalites
            if(!setCourant.isEmpty() && setCourant != null) {
                egalites.add(setCourant);
            }
        }

        int k;
        List<Double> s = Arrays.asList(scores);

        while(Collections.max(s) > -1) {
            k = s.indexOf(Collections.max(s));
            classement.add((byte)k);
            s.set(k, -1.0);
        }


        System.out.print("Egalités : ");
        if(!egalites.isEmpty() && egalites != null) {
            for (Set<Byte> mySet : egalites) {
                System.out.print("(");
                for (Byte b : mySet) {
                    System.out.print(b + " ");
                }
                System.out.print(") ");
            }
        }
        System.out.println();

        return new Resultat(classement, egalites);
    }

    /**
     *
     * @param scores : score de chaque candidat, la place dans le tableau représentant le numéro de chaque candidat
     * @return classement des candidats
     */
    @Deprecated
    public List<Byte> oldclasseParScore(Double[] scores) throws EgaliteException {
        Map<Double, Byte> mapClassement = new TreeMap<>(Collections.reverseOrder());


        for(byte i = 0 ; i < scores.length ; i++) {
            if(!mapClassement.containsKey(scores[i])) {
                mapClassement.put(scores[i], i);
            } else {
                /**  GERER LES EGALITES PLUS PROPREMENT **/
                mapClassement.put(scores[i]*1.001*i, i);
                //On modifie arbitrairement le score du candidat i pour pouvoir le placer dans la map
                //throw new EgaliteException("Egalité entre candidats " + i + " et " + mapClassement.get(scores[i]));
            }

        }

        List<Byte> resultList = new ArrayList<>();

        for(Double d : mapClassement.keySet()) {
            resultList.add(mapClassement.get(d));
        }

        return resultList;
    }

}
