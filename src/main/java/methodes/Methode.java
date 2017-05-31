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

        byte vainqueur = res.getClassement().get(0);

        String egalite = " ";

        if(res.premierEgalite) {
            egalite = " (Egalité) ";
        }

        System.out.println(
                vainqueur + " gagne" + egalite + "via " +
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


    public Resultat genereResultatParScore(byte numCandidatPref, Double[] scores) {
        List<Byte> classement = new ArrayList<>();
        Set<Set<Byte>> egalites = new HashSet<>();

        /*System.out.print("Scores : ");
        for(int a = 0 ; a < scores.length ; a++) {
            System.out.print(scores[a] + " ");
        }
        System.out.println();*/

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


        /*System.out.print("Egalités : ");
        if(!egalites.isEmpty() && egalites != null) {
            for (Set<Byte> mySet : egalites) {
                System.out.print("(");
                for (Byte b : mySet) {
                    System.out.print(b + " ");
                }
                System.out.print(") ");
            }
        }
        System.out.println();*/

        Resultat resultat = new Resultat(classement, egalites);

        majPremierEgalite(numCandidatPref, resultat);

        return resultat;
    }

    /**
     * Met à jour la variable du résultat indiquant si le premier candidat est sujet à égalité
     * @param res
     */
    public void majPremierEgalite(byte numCandidatPref, Resultat res) {

        byte vainqueur = res.getClassement().get(0);

        //On regarde si le premier candidat apparaît dans un set d'égalités
        if(res.getEgalites() != null) {
            for(Set<Byte> s : res.getEgalites()) {
                if(s.contains(vainqueur)) {
                    res.premierEgalite = true;
                }
            }
        }

        deplacePremier(numCandidatPref, res);

    }

    /**
     * Permet de placer le candidat souhaité en première position si ce dernier fait partie d'une égalité à la première place
     * @param numCandidatPref
     * @param res
     */
    public void deplacePremier(byte numCandidatPref, Resultat res) {
        //Impossible de travailler si on ne connait pas le candidat préféré ou s'il n'est pas dans l'inégalité de tête
        if(numCandidatPref == -1 || !res.premierEgalite) { return; }

        Set<Byte> mySet = null;

        //Recherche du set d'égalité contenant le vainqueur
        if(res.getEgalites() != null) {
            for (Set<Byte> s : res.getEgalites()) {
                //Si le set d'égalité contient le vainqueur et le candidat souhaité vainqueur
                if (s.contains(numCandidatPref) && s.contains(res.getClassement().get(0))) {
                    mySet = s;
                }
            }
        }

        //Rien à faire si le candidat souhaité vainqueur n'est pas dans la liste d'égalités
        if(mySet == null) { return; }

        List<Byte> newClassement = res.getClassement();

        System.out.print("Classement avant modif : ");
        for(byte b : newClassement) {
            System.out.print(b + " ");
        }
        System.out.println();

        System.out.print("Set d'égalité de tête : ");
        for(Byte b :mySet) {
            System.out.print(b + " ");
        }
        System.out.println();

        //Si le candidat souhaité n'est pas en première place,
        // on l'échange avec le candidat du set situé en dernière place dans le classement
        if(numCandidatPref != res.getClassement().get(0)) {
            newClassement.set(res.getClassement().indexOf(numCandidatPref), res.getClassement().get(0));
            newClassement.set(0, numCandidatPref);
        }

        System.out.print("Classement après modif : ");
        for(byte b : newClassement) {
            System.out.print(b + " ");
        }
        System.out.println();

        res.setClassement(newClassement);

    }

}
