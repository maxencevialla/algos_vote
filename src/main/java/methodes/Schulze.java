package methodes;

import exceptions.EgaliteException;
import exceptions.WrongCandidateNumberException;
import io.Resultat;
import io.Urne;

/**
 * Created by maxence on 30/05/17.
 */
public class Schulze extends Methode {
    private static Schulze ourInstance = new Schulze();

    public static Schulze getInstance() {
        return ourInstance;
    }

    private Schulze() {
    }

    /**
     * 3 étapes :
     *      - remplissage du tableau de préférences d
     *      - calcul des plus courts chemins via algorithme de Floyd-Marshall
     *      - recherche du candidat ayant gagné tous ses duels
     *
     * @param r
     * @return
     * @throws WrongCandidateNumberException
     * @throws EgaliteException
     */
    @Override
    public Resultat getResult(Urne r) throws WrongCandidateNumberException, EgaliteException {

        // d[i][j] représente le nombre de votants qui préfère le candidat i au candidat j
        int[][] d = new int[r.getNbCandidats()][r.getNbCandidats()];

        // p[i][j] représente la force du plus fort chemin entre les candidats i et j
        int[][] p = new int[r.getNbCandidats()][r.getNbCandidats()];

        //remplissage du tableau d
        for(int v = 0 ; v < r.getNbVotants() ; v++) {
            for(int i = 0 ; i < r.getNbCandidats() ; i++) {
                //les relations entre le i courant et j<i ont été traités lors du passage i=j
                for(int j = i ; j < r.getNbCandidats() ; j++) {
                    if(r.getVotes().get(v).get(i) < r.getVotes().get(v).get(j)) {
                        //Si i a un classement inférieur à j (i préféré à j), on incrémente d[i][j]
                        d[i][j]++;
                    } else if(r.getVotes().get(v).get(i) > r.getVotes().get(v).get(j)){
                        //Sinon, j a été préféré à i : on incrémente d[j][i]
                        d[j][i]++;
                    }
                }
            }
        }

        //Variante de algo Floyd-Marshall, permet de trouver le chemin le plus court entre 2 points dans un graphe pondéré
        // https://en.wikipedia.org/wiki/Schulze_method#Implementation

        //1er remplissage de p avec le contenu des arêtes du graphes, selon leur orientation
        for(int i = 0; i < r.getNbCandidats() ; i++) {
            for(int j = 0; j < r.getNbCandidats() ; j++) {
                if(i != j) {
                    if(d[i][j] > d[j][i]) {
                        p[i][j] = d[i][j];
                    } else {
                        p[i][j] = 0;
                    }
                }
            }
        }

        //A chaque passage dans la boucle i, on prends en compte les chemins passant par i
        for(int i = 0; i < r.getNbCandidats() ; i++) {
            for(int j = 0; j < r.getNbCandidats() ; j++) {
                if(i != j) {
                    for(int k = 0; k < r.getNbCandidats() ; k++) {
                        //On recherche un chemin entre j et k passant par i
                        if(i != k && j != k) {
                            //On met à jour la valeur du chemin ssi le passage par i est avantageux sur l'ancienne valeur
                            p[j][k] = Math.max(p[j][k], Math.min(p[j][i], p[i][k]));
                        }
                    }
                }
            }
        }

        //Fin algo FM

        //Détermination du nombre de duels gagnés par chaque candidat
        Double[] nbDuelsGagnes = new Double[r.getNbCandidats()];

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            nbDuelsGagnes[i] = 0.0;
        }

        for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            for(int j = 0 ; j < r.getNbCandidats() ; j++) {
                //Si i gagne son duel sur j, on augmente le nombre de duels gagnés par i
                //Sinon, l'incrémentation se fera quand les valeurs des variables de boucle seront inversées
                if(p[i][j] > p[j][i]) {
                    nbDuelsGagnes[i] += 1.0;
                }
            }
        }

        /*for(int i = 0 ; i < r.getNbCandidats() ; i++) {
            System.out.print(nbDuelsGagnes[i] + " ");
        }
        System.out.println();*/

        return new Resultat(classeParScore(nbDuelsGagnes), Schulze.class.getSimpleName());
    }
}
