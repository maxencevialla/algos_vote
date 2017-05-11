package tables;

import java.util.Random;

/**
 * Created by maxence on 10/05/17.
 *
 *
 * Créé une matrice aléatoire représentant une élection demandant à nbVotants de classer nbCandidats
 * La valeur (i,j) de la matrice représente le classement du candidat i par le votant j
 */
public class ResultMatrix {
    int nbCandidats;
    int nbVotants;

    Integer[][] resultats;

    public ResultMatrix(int nbVotants, int nbCandidats) {
        this.nbVotants = nbVotants;
        this.nbCandidats = nbCandidats;
        this.resultats = new Integer[this.nbVotants][this.nbCandidats];

        this.resultats = this.genererResultats();
    }

    /*
        Remplit la matrice resultats de taille nbCandidats*nbVotants un classement aléatoire
     */
    public Integer[][] genererResultats() {
        Random rand = new Random();
        int myRand;

        for(int i = 0 ; i < this.nbVotants ; i++) {
            for(int j = 0 ; j < this.nbCandidats ; j++) {
                myRand = rand.nextInt(this.nbCandidats) + 1;
                while(inColumn(i, myRand)) {
                    myRand = rand.nextInt(this.nbCandidats) + 1;
                }
                this.resultats[i][j] = myRand;
            }
        }

        return resultats;
    }

    public void afficheResultats() {
        for(int j = 0 ; j < this.nbCandidats ; j++) {
            for(int i = 0 ; i < this.nbVotants ; i++) {
                System.out.print(this.resultats[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    /*
    Renvoie true si la colonne column contient la valeur n, et faux sinon
     */
    public boolean inColumn(int column, int n) {
        for(int j = 0 ; j < this.nbCandidats ; j++) {
            if(this.resultats[column][j] != null) {
                if (n == this.resultats[column][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
