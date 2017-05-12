package tests.arrays;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by maxence on 11/05/17.
 */
public class ResultTestArray {
    int nbCandidats;
    int nbVotants;

    ArrayList<ArrayList<Integer>> resultats;

    public ResultTestArray(int nbVotants, int nbCandidats) {
        this.nbVotants = nbVotants;
        this.nbCandidats = nbCandidats;
        this.resultats = new ArrayList<ArrayList<Integer>>();

        this.resultats = this.genererResultats();
    }

    /*
        Remplit la matrice resultats de taille nbCandidats*nbVotants un classement aléatoire
     */
    public ArrayList<ArrayList<Integer>> genererResultats() {
        Random rand = new Random();
        int myRand;
        ArrayList<Integer> line = null;

        for(int i = 0 ; i < this.nbVotants ; i++) {
            line = new ArrayList<Integer>();
            for(int j = 0 ; j < this.nbCandidats ; j++) {
                myRand = rand.nextInt(this.nbCandidats) + 1;
                while(line.contains(myRand)) {
                    myRand = rand.nextInt(this.nbCandidats) + 1;
                }
                //System.out.println("Add " + myRand +" to this array : " + line.toString());
                line.add(myRand);
            }
            resultats.add(line);
        }

        return resultats;
    }

    public void afficheResultats() {
        for(int j = 0 ; j < this.nbCandidats ; j++) {
            for(int i = 0 ; i < this.nbVotants ; i++) {
                System.out.print(resultats.get(i).get(j) + " ");
            }
            System.out.println("\n");
        }
    }
}
